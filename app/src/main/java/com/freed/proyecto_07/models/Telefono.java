package com.freed.proyecto_07.models;

public class Telefono {

    private String uid , nombre , telefono , email , apellido;

    public Telefono(String uid,String nombre, String apellido,String telefono,String email){

        this.uid = uid;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.apellido = apellido;


    }


    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }
}
