URL SWAGGER UI: http://localhost:8080/swagger-ui/index.html#/
Por el enunciado se desarrollo una API con las siguientes condiciones:
Para insertar un nuevo EAN válido tiene que darse las siguientes condiciones:
    - Existir el proveedor asociado al EAN en la BD
    - Existir la referencia de código en la BD
    - No existir EAN duplicados.

Para probar esto en SWAGGER realizar lo siguiente:
1.Registrarse con un usuario (Se puede dejar todo como string y poner unicamente en el email string@string.es)
2.Hacer loggin con este usuario.
3.Obtener el token y añadirlo en la parte superior derecha de la aplicación.
4. Ya se tiene acceso al resto de peticiones

Para la parte de test se realizaron varios test de integración probando lo siguiente:
1. Validación del JWT
2. Validación de los metodos principales del EAN (Barcode)
3. Validación de que la cache funciona correctamente.

Por falta de tiempo no se realizaron mas test (unitarios y para el resto de entidades) pero se no habría problema en realizarlos o los podemos comentar en la revisión de la prueba.
Lo mismo con flyway, actualemnte trabajamos con el en la empresa faltaría añadir la dependencia al gradlew y crear el primer script donde se creen las tablas.

OTROS COMENTARIOS:
- Se emplea la arquitectura exagonal ya que bajo mi punto de vista permite un acoplamiento mínimo entre el modelo y cualquier elemento externo y cambiante.
- La carpeta com/mercadonarest/infraestructure/postgres tiene este nombre porque es la BD que empleariamos en PROD.
- Lo subo en este repositorio porque no me deja clonar el repositorio que me habeis mandado.
- Tome la decisión de realizar test de integración ya que me parecen los mas complicados de configurar. Incluso
se podría hacer un decorador que se ponga en todos los test de integración y se encargue de inyectar el JWT en todas las peticiones.
  
  

