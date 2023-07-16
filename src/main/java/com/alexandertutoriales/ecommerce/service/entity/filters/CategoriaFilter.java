package com.alexandertutoriales.ecommerce.service.entity.filters;

public class CategoriaFilter {
    private String nombre;
    private String verInactivos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVerInactivos() {
        return verInactivos;
    }

    public void setVerInactivos(String verInactivos) {
        this.verInactivos = verInactivos;
    }
}
