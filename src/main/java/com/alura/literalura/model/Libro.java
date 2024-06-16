package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String titulo;

    @ManyToOne
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Idioma lenguaje;
    private Integer numeroDescargas;

    public Libro() {
    }

    public Libro(DatosLibros datosLibro) {
        this.titulo = datosLibro.titulo();
        this.lenguaje = Idioma.fromString(datosLibro.idiomas().toString().split(",")[0].trim());
        this.numeroDescargas = datosLibro.numeroDescargas();
    }

    @Override
    public String toString() {
        String nombreAutor = Optional.ofNullable(autor).map(Autor::getNombre).orElse("Autor desconocido");
        return String.format("""
                ---------- Libro ----------
                Titulo: %s
                Autor: %s
                Idioma: %s
                Numero de Descargas: %d
                ---------------------------
                """, titulo, nombreAutor, lenguaje, Optional.ofNullable(numeroDescargas).orElse(0));
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(Idioma lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
