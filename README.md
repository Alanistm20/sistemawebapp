# sistemawebapp
**TECNOLOGIAS APLICADAS**
<p align="center"> <img src="imagenes/java.jpg" alt="Java 17" width="100"/>  <img src="imagenes/spring.png" alt="Spring Boot 3" width="100"/>   <img src="imagenes/springdata.png" alt="Spring Data JPA" width="100"/><img src="imagenes/thymeleaf.png" alt="Thymeleaf" width="100"/>  <img src="imagenes/hibernate.png" alt="Hibernate Validator" width="100"/>  <img src="imagenes/supabase.png" alt="PostgreSQL / Supabase" width="100"/><img src="imagenes/bootstrap.png" alt="Bootstrap 5" width="100"/><img src="imagenes/maven.png" alt="Maven" width="100"/> </p>


**FUNCIONALIDADES**


⚪ CRUD de Películas 


⚪  Registro de Clientes


🟢 Gestión de Alquileres y Devoluciones


✅  Formularios con validación (Hibernate Validator)


🔵 Vistas dinámicas con Thymeleaf y Bootstrap


🟣 Integración con PostgreSQL/Supabase


**AUTOR**
🔮NICOLE TRASLAVIÑA @Alanistm20

**paso 1- cumplir con los requerimientos del examen-**


**paso 2- crear bd**
tabla clientes: (id_cliente, nombre, email). Define tipos y longitudes. Inserta 3 registros de prueba. 
CREATE TABLE clientes ( 
    id_cliente SERIAL PRIMARY KEY, 
    nombre VARCHAR(100) NOT NULL, 
    email VARCHAR(100) NOT NULL 
); 

**tabla peliculas: (id_pelicula, titulo, genero, stock). Inserta 3 registros de prueba** 
CREATE TABLE peliculas ( 
    id_pelicula SERIAL PRIMARY KEY, 
    titulo VARCHAR(150) NOT NULL, 
    genero VARCHAR(50), 
    stock INT NOT NULL 

); 


**tabla alquileres: (id_alquiler autogenerado, fecha, id_cliente, total).**
CREATE TABLE alquileres ( id_alquiler SERIAL PRIMARY KEY, fecha DATE NOT NULL DEFAULT CURRENT_DATE, id_cliente INT REFERENCES clientes(id_cliente), total NUMERIC(10,2) NOT NULL ); 


**tabla detalle_alquiler: clave compuesta (id_alquiler, id_pelicula), cantidad.**

CREATE TABLE detalle_alquiler ( id_alquiler INTEGER REFERENCES alquileres(id_alquiler) ON DELETE CASCADE, id_pelicula INTEGER REFERENCES peliculas(id_pelicula), cantidad INTEGER NOT NULL, PRIMARY KEY (id_alquiler, id_pelicula) ); 

