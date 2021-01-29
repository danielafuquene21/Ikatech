package com.ikatech.dataObject;

import java.io.Serializable;

public class Vehicle implements Serializable {

    private String marca;
    private String modelo;
    private String estado;
    private String imagen;
    private boolean favorito;
    private Location ubicacion;
    private String nombreColeccion;
    private String tipoCombustion;
    private String tipo;

    public boolean isFavorito() {
        return favorito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public Location getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Location ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombreColeccion() {
        return nombreColeccion;
    }

    public void setNombreColeccion(String nombreColeccion) {
        this.nombreColeccion = nombreColeccion;
    }

    public String getTipoCombustion() {
        return tipoCombustion;
    }

    public void setTipoCombustion(String tipoCombustion) {
        this.tipoCombustion = tipoCombustion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
