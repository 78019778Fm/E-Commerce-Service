package com.alexandertutoriales.ecommerce.service.entity.dto;

public class CategoriaDto {
    private Integer id;
    private String nombre;
    private boolean vigencia;
    private DocumentoAlmacenadoDto foto;
    private String vigenciaString;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public DocumentoAlmacenadoDto getFoto() {
        return foto;
    }

    public void setFoto(DocumentoAlmacenadoDto foto) {
        this.foto = foto;
    }

    public String getVigenciaString() {
        return vigenciaString;
    }

    public void setVigenciaString(String vigenciaString) {
        this.vigenciaString = vigenciaString;
    }
}
