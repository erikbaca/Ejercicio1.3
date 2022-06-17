package com.cdp.ejercicio13.Procesos;

public class personas
{
    // Nombre de la BDD
    public static final String NameDatabase = "DBPM01";

    // Creacion de la tabla de la BDD
    public static final String tablaPersonas = "personasT";

    //Campos de la tabla
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    // Sentencias SQL para la creacion de tabla
    public static final String CreateTablePersonas = "CREATE TABLE personasT ( id INTEGER PRIMARY KEY AUTOINCREMENT,nombres TEXT," +
                                                     "apellidos TEXT, edad INTEGER, correo TEXT, " +
                                                     "direccion TEXT)";

    public static final String DroptablePersonas = "DROP TABLE IF EXISTS personasT";

}
