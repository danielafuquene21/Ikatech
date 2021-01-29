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
     * Atributos para la tabla Usuario que se encuentra con la sesion iniciada
     */
    public static final String USERNAME = "UserName";
    public static final String LOGIN_CONSTRASENA = "Contrasena";


    /**
     * Creacion de query para iniciar sesion
     */
    public static final String query_login = "CREATE TABLE IF NOT EXISTS "
            + USER_SESSION_TABLE + " (" + USERNAME + " INT , "
            +LOGIN_CONSTRASENA + " TEXT  , indice  INTEGER PRIMARY KEY AUTOINCREMENT )";

    /**
     * Creacion de query para crear la tabla de Usuarios
     */
    public static final String query_created_user = "CREATE TABLE IF NOT EXISTS "
            + USER_TABLE + " (" + CEDULA + " INT , "
            +NOMBRE + " TEXT , "
            + USER_CONTRASENA +" TEXT ,"
            +DIRECCION + " TEXT  , indice  INTEGER PRIMARY KEY AUTOINCREMENT )";

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
    public static final String TYPE = "type";
    public static final String ADDRESS = "address";
    public static final String LAT = "lat";
    public static final String LON = "lon";

    public static final String LOCATION = "location";
    public static final String L_ADDRESS = "address";
    public static final String L_LATITUDE = "latitude";
    public static final String L_LONGITUDE = "longitude";

    /**
     * intents referencias
     */
    public static final String VEHI = "vehicle";
    public static final String BOUGHT_VEHI = "bou_vehicle";
    public static final String US = "user";

    /**
     * Creacion de query para crear la tabla de Vehiculos que se usara
     * para guardar los vehiculos que se compren o adquieran
     */
    public static final String query_created_vehicle = "CREATE TABLE IF NOT EXISTS "
            + VEHICLE_TABLE + " (" + CEDULA + " INT , "
            + USER_CONTRASENA + " TEXT , "
            + BRAND +" TEXT ,"
            + MODEL +" TEXT ,"
            + STATE +" TEXT ,"
            + FAVORITE +" TEXT ,"
            + IMAGE +" TEXT ,"
            + COLLECTION_NAME +" TEXT ,"
            + COMBUSTION_TYPE + " TEXT ,"
            + ADDRESS +" TEXT ,"
            + LAT +" TEXT ,"
            + LON +" TEXT ,"
        +TYPE +" TEXT, indice  INTEGER PRIMARY KEY AUTOINCREMENT )";


}

