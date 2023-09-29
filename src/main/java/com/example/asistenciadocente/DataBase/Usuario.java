package com.example.asistenciadocente.DataBase;

public class Usuario {
    public String Nombre;
    public String Titulo;
    public String Correo;
    public String Dias;
    public String Entrada;
    public String Salida;
    public boolean Estado;
    public String Rol;
    public String PP;

    public Usuario(String nombre, String titulo, String correo, String dias, String entrada, String salida, boolean estado, String rol, String PP) {
        Nombre = nombre;
        Titulo = titulo;
        Correo = correo;
        Dias = dias;
        Entrada = entrada;
        Salida = salida;
        Estado = estado;
        Rol = rol;
        this.PP = PP;
    }

}

