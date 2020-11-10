/*
 Esta es la clase inmigrante
es la que se usa como apoyo para indertar, consultar y/o editar inmigrantes de los registros
 */
package Logica;

/**
 *
 * @author Mateo
 */
public class inmigrante {
    String id;
    String Nombre;
    String Apellidos;
    String Procedencia;
    String Nacionalidad;

    public inmigrante(String id, String Nombre, String Apellidos, String Procedencia, String Nacionalidad) {
        this.id = id;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Procedencia = Procedencia;
        this.Nacionalidad = Nacionalidad;
    }

    public inmigrante() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getProcedencia() {
        return Procedencia;
    }

    public void setProcedencia(String Procedencia) {
        this.Procedencia = Procedencia;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String Nacionalidad) {
        this.Nacionalidad = Nacionalidad;
    }
    
    
    
}
