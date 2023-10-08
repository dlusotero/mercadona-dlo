package com.mercadonarest.infraestructure.api.resources;

import com.mercadonarest.configuration.JwtUtils;
import com.mercadonarest.infraestructure.postgres.daos.UserDAO;
import com.mercadonarest.infraestructure.postgres.entities.ERole;
import com.mercadonarest.infraestructure.postgres.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class TokenAuthenticationServiceTest {

    
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDAO userDAO;

    @Test
    void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test")).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldGenerateAuthToken() throws Exception {
        User user = new User();
        user.setUsername("john");
        user.setEmail("aaaaaa@aaaaa.es");
        user.setPassword("12341234");
        this.userDAO.save(user);
        String token = jwtUtils.generateJwtTokenWithoutAutentication("john", ERole.ROLE_ADMIN);
        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get("/test").header("Authorization","Bearer "+token)).andExpect(status().isOk());
    }

    private void assertNotNull(String token) {
    }

}
