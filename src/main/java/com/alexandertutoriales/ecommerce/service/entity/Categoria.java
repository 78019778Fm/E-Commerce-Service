package com.alexandertutoriales.ecommerce.service.entity;

import javax.persistence.*;

@Entity
public class Categoria {
    public static final String C_NOMBRE = "nombre";
    public static final String C_VIGENCIA = "vigencia";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100)
    private String nombre;
    @Column
    private boolean vigencia;
    @OneToOne
    private DocumentoAlmacenado foto;
    @Transient
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

    public DocumentoAlmacenado getFoto() {
        return foto;
    }

    public void setFoto(DocumentoAlmacenado foto) {
        this.foto = foto;
    }

    public String getVigenciaString() {
        return vigenciaString;
    }

    public void setVigenciaString(String vigenciaString) {
        this.vigenciaString = vigenciaString;
    }
}
