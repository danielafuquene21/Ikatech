package com.ikatech.businessObject;

public class Const {

    public static final String DATABASE_NAME = "dataBase.db";


    public static final String USER_TABLE = "Usuario";
    public static final String VEHICLE_TABLE = "Vehiculo";
    public static final String USER_SESSION_TABLE = "UsuarioSesion";

    /**
     * Atributos para la tabla Usuario
     */
    public static final String NOMBRE = "Nombre";
    public static final String CEDULA = "Cedula";
    public static final String USER_CONTRASENA = "Contrasena";
    public static final String DIRECCION = "Direccion";
    private static final String IMAGEN = "Imagen";


    /**
     * Atributos para la tabla Vehiculos
     */
    private static final String USER_CEDULA = "UserCc";
    private static final String MARCA = "Marca";
    private static final String MODELO = "Modelo";
    private static final String ESTATDO = "Estado";
    private static final String IMAGEN_VEHI = "ImagenVehi";
    private static final String FAVORITO = "Favorito";
    private static final String TIPO_COMBUSTION = "TipoCombustion";
    private static final String UBICACION = "Ubicacion";

    /**
     * Atributos para la tabla Usuario que se encuentra con la sesion iniciada
     */
    public static final String USERNAME = "UserName";
    public static final String LOGIN_CONSTRASENA = "Contrasena";


    /**
     * Creacion de query para iniciar sesion
     */
    public static final String query_login = "CREATE TABLE IF NOT EXISTS "
            + USER_SESSION_TABLE + " (" + USERNAME + " INT PRIMARY KEY, "
            +LOGIN_CONSTRASENA + " TEXT )";

    /**
     * Creacion de query para crear la tabla de Usuarios
     */
    public static final String query_created_user = "CREATE TABLE IF NOT EXISTS "
            + USER_TABLE + " (" + CEDULA + " INT PRIMARY KEY, "
            +NOMBRE + " TEXT , "
            + USER_CONTRASENA +" TEXT ,"
            +DIRECCION + " TEXT )";

    /**
     * Atributos para obtener el objeto vehiculo desde el json
     */
    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String DELET_REQUEST = "delet_request";
    public static final String STATE = "state";
    public static final String FAVORITE = "favorite";
    public static final String IMAGE = "image";
    public static final String COLLECTION_NAME = "collection_name";
    public static final String COMBUSTION_TYPE = "combustion_type";

    /**
     * intents referencias
     */
    public static final String VEHI = "vehicle";
}

