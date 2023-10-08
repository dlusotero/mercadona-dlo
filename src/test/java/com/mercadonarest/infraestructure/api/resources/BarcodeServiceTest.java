package com.mercadonarest.infraestructure.api.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadonarest.configuration.JwtUtils;
import com.mercadonarest.domain.model.Barcode;
import com.mercadonarest.infraestructure.postgres.daos.BarcodeDAO;
import com.mercadonarest.infraestructure.postgres.daos.CodigoProductoDAO;
import com.mercadonarest.infraestructure.postgres.daos.ProveedorDAO;
import com.mercadonarest.infraestructure.postgres.daos.UserDAO;
import com.mercadonarest.infraestructure.postgres.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.print.Book;
import java.util.Objects;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BarcodeServiceTest {

    
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BarcodeDAO barcodeDAO;

    @Autowired
    private ProveedorDAO proveedorDAO;

    @Autowired
    private CodigoProductoDAO codigoProductoDAO;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private JwtUtils jwtUtils;

    private String token;
    private final Long barcodeValid = 8437008459059L;
    private final long barcodeWithoutProveedorValid = 1111111459059L;
    private final long  barcodeWithoutCodigoProductoValid = 8437008111119L;




    @BeforeAll()
    public void createBarcode(){
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@test.es");
        user.setPassword("test");
        token = jwtUtils.generateJwtTokenWithoutAutentication("test", ERole.ROLE_ADMIN);
        CodigoProductoEntity codigoProducto = new CodigoProductoEntity();
        codigoProducto.setCode(45905L);
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCode(8437008L);
        proveedor.setName("Mercadona");
        BarcodeEntity barcodeValid = new BarcodeEntity();
        barcodeValid.setCode(this.barcodeValid);
        BarcodeEntity barcodeWithoutCodigoProductoValidEntity = new BarcodeEntity();
        barcodeWithoutCodigoProductoValidEntity.setCode(this.barcodeWithoutCodigoProductoValid);
        BarcodeEntity barcodeWithoutProveedorValidEntity = new BarcodeEntity();
        barcodeWithoutProveedorValidEntity.setCode(this.barcodeWithoutProveedorValid);
        userDAO.save(user);
        proveedorDAO.save(proveedor);
        codigoProductoDAO.save(codigoProducto);
        barcodeDAO.save(barcodeValid);
        barcodeDAO.save(barcodeWithoutCodigoProductoValidEntity);
        barcodeDAO.save(barcodeWithoutProveedorValidEntity);
    }


    @Test
    void testGivenNewBarcodeWhithBarcodeValidThenReturnOK() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/barcode/"+ barcodeValid.toString()).header("Authorization","Bearer "+token)).andExpect(status().isOk())
        .andExpect(result -> Assertions.assertEquals("application/json", Objects.requireNonNull(result.getResponse().getContentType())));
    }

    @Test
    void testVerifyCache() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/barcode/"+ barcodeValid.toString()).header("Authorization","Bearer "+token));
        Assertions.assertNotNull(getCachedBook(barcodeValid));


    }

    @Test
    void testGivenNewBarcodeWhithoutProveedorValidThenNotFoundException() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/barcode/"+ barcodeWithoutProveedorValid).header("Authorization","Bearer "+token)).andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertEquals("Not Found Exception. Proveedor no registrado en la BD", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    void testGivenNewBarcodeWhithoutCodigoProductoValidThenNotFoundException() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/barcode/"+ barcodeWithoutCodigoProductoValid).header("Authorization","Bearer "+token)).andExpect(status().isNotFound())
                .andExpect(result -> Assertions.assertEquals("Not Found Exception. Codigo de producto no registrado en la BD", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    void testGivenNewBarcodeWhithBarcodeAlreadyCreatedThenConflict() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/barcode").content(asJsonString(Barcode.builder().code(barcodeValid).build())).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header("Authorization","Bearer "+token)).andExpect(status().isConflict());
    }

    @Test
    void testGivenNewBarcodeWhithInvalidDestinationDigitValueWhenCreateThenConflict() throws Exception {
        final Long barcodeValid = 8437008459057L;
        mvc.perform(MockMvcRequestBuilders.post("/barcode").content(asJsonString(Barcode.builder().code(barcodeValid).build())).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header("Authorization","Bearer "+token)).andExpect(status().isConflict());
    }

    @Test
    void testGivenNewBarcodeValidWhenCreateThenOk() throws Exception {
        final Long barcodeValid = 8437008459058L;
        mvc.perform(MockMvcRequestBuilders.post("/barcode").content(asJsonString(Barcode.builder().code(barcodeValid).build())).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header("Authorization","Bearer "+token)).andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<Barcode> getCachedBook(Long code) {
        return Optional.ofNullable(cacheManager.getCache("barcode_cache")).map(c -> c.get(code, Barcode.class));

}}
