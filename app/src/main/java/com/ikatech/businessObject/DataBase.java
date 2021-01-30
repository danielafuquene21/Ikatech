package com.ikatech.businessObject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ikatech.dataObject.Location;
import com.ikatech.dataObject.User;
import com.ikatech.dataObject.Vehicle;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Const.query_login);
        db.execSQL(Const.query_created_user);
        db.execSQL(Const.query_created_vehicle);
    }

    public DataBase(Context context){
        super(context,Const.DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void login (User u){
        SQLiteDatabase db = getWritableDatabase();
        try{
            if(db!=null){
                String query ="INSERT INTO "+Const.USER_SESSION_TABLE+" ("+Const.USERNAME+" , "+
                        Const.LOGIN_CONSTRASENA+") VALUES ('"+u.getUsername()+"' , " +
                        "' "+u.getPassword()+"' )";

                db.execSQL(query);
                db.close();
            }
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
    }

    public boolean existentUserLogin(User u){
        SQLiteDatabase db = getReadableDatabase();
        boolean exist = false;
        try{
            Cursor cursor =db.rawQuery("SELECT * FROM  "+Const.USER_TABLE+"  WHERE  "+Const.CEDULA+" = '"+u.getUsername()+"'  " +
                    "AND "+Const.USER_CONTRASENA +" = '"+u.getPassword()+"'", null);

            if(cursor.moveToFirst()){
                do{
                    exist = true;
                }while (cursor.moveToNext());
            }
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
        return exist;
    }
    public boolean existentUser(User u){
        SQLiteDatabase db = getReadableDatabase();
        boolean exist = false;
        try{
            Cursor cursor =db.rawQuery("SELECT * FROM  "+Const.USER_TABLE+"  WHERE  "+Const.CEDULA+" = '"+u.getUsername()+"'  " +
                    "AND "+Const.USER_CONTRASENA +" = '"+u.getPassword()+"'", null);

            if(cursor.moveToFirst()){
                do{
                    exist = true;
                }while (cursor.moveToNext());
            }
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
        return exist;
    }

    public void insertUser (User u){
        SQLiteDatabase db = getWritableDatabase();
        try{
            if(db!=null){
                String query ="INSERT INTO "+Const.USER_TABLE+" ( "+Const.CEDULA+" , " +
                         Const.NOMBRE+ " , "+
                        Const.LOGIN_CONSTRASENA+" , "+Const.DIRECCION+") VALUES ('"+u.getUsername()+"' , " +
                        "' "+u.getName()+"' , '"+u.getPassword()+"' , '"+u.getAddress()+"' )";

                db.execSQL(query);
                db.close();
            }
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
    }

    public User loadUser(int username, String pass) {
        SQLiteDatabase db = getReadableDatabase();
        User user = new User();
        try{
            String query = "SELECT  "+Const.CEDULA+" , "+
                    Const.NOMBRE+" , "+Const.USER_CONTRASENA+" , "+
                    Const.DIRECCION+"  FROM  "+Const.USER_TABLE+"  WHERE  "+Const.CEDULA+" = '"+username+"'  AND  "+Const.USER_CONTRASENA+" = '"+pass+"' ";
            Cursor cursor =db.rawQuery(query, null);

            if(cursor.moveToFirst()){
                do{
                    user.setUsername(cursor.getInt(cursor.getColumnIndex(Const.CEDULA)));
                    user.setName(cursor.getString(cursor.getColumnIndex(Const.NOMBRE)));
                    user.setPassword(cursor.getString(cursor.getColumnIndex(Const.USER_CONTRASENA)));
                    user.setAddress(cursor.getString(cursor.getColumnIndex(Const.DIRECCION)));
                }while (cursor.moveToNext());
            }
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
        return user;
    }

    public void buyVehicle(Vehicle vehicle, User user) {
       SQLiteDatabase db = getWritableDatabase();
        try{
            if(db!=null){
                String query ="INSERT INTO "+Const.VEHICLE_TABLE+" " +
                        " ("+Const.CEDULA+" , "+Const.USER_CONTRASENA+
                        " , "+Const.BRAND+" , " +
                        Const.MODEL+" , " +
                        Const.STATE+ " , " +
                        Const.FAVORITE+" , " +
                        Const.IMAGE+" , " +
                        Const.COLLECTION_NAME +" , " +
                        Const.COMBUSTION_TYPE+" , "+
                        Const.ADDRESS+" , "+
                        Const.LAT+" , "+
                        Const.LON+" , "+
                        Const.DELET_REQUEST+" , "+
                        Const.TYPE+" ) VALUES ('"+user.getUsername()+"' , '"+user.getPassword()+"', '" +
                        vehicle.getMarca()+"' , '" +
                        vehicle.getModelo()+"' , '" +
                        vehicle.getEstado()+"' , '" +
                        vehicle.getFavorito()+"' , '" +
                        vehicle.getImagen()+"' , '" +
                        vehicle.getNombreColeccion()+"' , '" +
                        vehicle.getTipoCombustion()+"' , '"+
                        vehicle.getUbicacion().getAddres()+"' , '"+
                        vehicle.getUbicacion().getLat()+"' , '"+
                        vehicle.getUbicacion().getLon()+"' , '"+
                        vehicle.getEliminar()+"' , '" +
                        vehicle.getTipo()+"')";

                db.execSQL(query);
                db.close();
            }
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
    }

    public User getCurrentUser() {
        SQLiteDatabase db = getReadableDatabase();
        User user = new User();
        try{
            String query = "SELECT  "+Const.LOGIN_CONSTRASENA+" , "+
                    Const.USERNAME+"   FROM  "+Const.USER_SESSION_TABLE;
            Cursor cursor =db.rawQuery(query, null);

            if(cursor.moveToFirst()){
                do{
                    user.setUsername(cursor.getInt(cursor.getColumnIndex(Const.USERNAME)));
                    user.setPassword(cursor.getString(cursor.getColumnIndex(Const.LOGIN_CONSTRASENA)));
                }while (cursor.moveToNext());
            }
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
        return user;
    }

    public ArrayList<Vehicle> loadMyVehicles(User user) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Vehicle> lisVeh = new ArrayList<>();
        try{
            String query = "SELECT  "+Const.CEDULA+" , "+Const.USER_CONTRASENA+
                    " , "+Const.USER_CONTRASENA+" , "+Const.BRAND+ ", "
                    +Const.MODEL+ ", "+
                    Const.STATE+ " , "+
                    Const.FAVORITE+ " , "+
                    Const.IMAGE+ " , "+
                    Const.ADDRESS+ " , "+
                    Const.LAT+ " , "+
                    Const.LON+ " , "+
                    Const.COLLECTION_NAME +" , " +
                    Const.COMBUSTION_TYPE+ "  FROM  "+Const.VEHICLE_TABLE+"  WHERE  "+Const.CEDULA+" = '"+user.getUsername()+"' AND "+Const.USER_CONTRASENA+" = '"+user.getPassword()+"'  ";
            Cursor cursor =db.rawQuery(query, null);

            if(cursor.moveToFirst()){
                do{
                    Vehicle vehicle = new Vehicle();
                    Location loc = new Location();
                    vehicle.setMarca(cursor.getString(cursor.getColumnIndex(Const.BRAND)));
                    vehicle.setModelo(cursor.getString(cursor.getColumnIndex(Const.MODEL)));
                    vehicle.setEstado(cursor.getString(cursor.getColumnIndex(Const.STATE)));
                    vehicle.setFavorito(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(Const.FAVORITE))));
                    vehicle.setImagen(cursor.getString(cursor.getColumnIndex(Const.IMAGE)));
                    vehicle.setNombreColeccion(cursor.getString(cursor.getColumnIndex(Const.COLLECTION_NAME)));
                    vehicle.setTipoCombustion(cursor.getString(cursor.getColumnIndex(Const.COMBUSTION_TYPE)));
                    loc.setAddres(cursor.getString(cursor.getColumnIndex(Const.ADDRESS)));
                    loc.setLat(cursor.getString(cursor.getColumnIndex(Const.LAT)));
                    loc.setLon(cursor.getString(cursor.getColumnIndex(Const.LON)));
                    vehicle.setUbicacion(loc);
                    lisVeh.add(vehicle);
                }while (cursor.moveToNext());
            }
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
        return lisVeh;
    }

    public void deleteCurrentUser() {

        SQLiteDatabase db = getWritableDatabase();
        try{
            if(db!=null){
                String query ="DELETE FROM "+Const.USER_SESSION_TABLE;
                db.execSQL(query);
                db.close();
            }
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
    }

    public int sizeManualVehicle(User user) {
        SQLiteDatabase db = getReadableDatabase();
        int cant = 0;
        try{
            String query = "SELECT count(*) AS total FROM "+Const.VEHICLE_TABLE+" WHERE "+Const.TYPE+" = 'manual' " +
                    " AND "+Const.CEDULA+" = '"+user.getUsername()+"' AND "+Const.USER_CONTRASENA+" LIKE '%"+String.valueOf(user.getPassword()).trim()+"%'";
            Cursor cursor =db.rawQuery(query, null);

            if(cursor.moveToFirst()){
                do{
                    cant =(cursor.getInt(cursor.getColumnIndex("total")));
                }while (cursor.moveToNext());
            }
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
        return cant;
    }


}