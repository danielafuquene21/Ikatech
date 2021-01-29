package com.ikatech.businessObject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ikatech.dataObject.User;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Const.query_login);
        db.execSQL(Const.query_created_user);
    }

    public DataBase(Context context){
        super(context,Const.DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    /*public void editarUsuario(User u, User user){
        SQLiteDatabase db = getWritableDatabase();
        try {
            if(db!=null) {
                db.execSQL("update Nits set N_IDE = '" + u.getId() + "', C_EMP = '" + u.getCodEmpresa() + "', " +
                        "NOM = '" + u.getNombrePersonaJuridica() + "', IDE = '" + u.getTipoId() + "', DIR = '" + u.getDireccion() + "', " +
                        "TEL = '" + u.getTelefono() + "', AA = '" + u.getApartadoAereo() + "'," +
                        " EST = '" + u.getEstado() + "' , NOM1 = '" + u.getPrimerNombre() + "', NOM2 = '" + u.getSegundoNombre() + "'," +
                        " APE1 = '" + u.getPriemrApellido() + "', APE2 = '" + u.getSegundoApellido() + "', " +
                        "DIR_ELECT = '" + u.getCorreo() + "', DIG = '" + u.getDigitoVerificacion() + "'," +
                        "  TIPO_NIT = '" + u.getTipoNit() + "', TIP_CONTRIB = '" + u.getTipoContribuyente() + "' , " +
                        "TIP_TERC = '" + u.getTipoTercero() + "', FECHA = '" + u.getFecha() + "' where N_IDE = '" + user.getId() + "'");
            }
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }

    }*/
    public void login (User u){
        SQLiteDatabase db = getWritableDatabase();
        try{
            if(db!=null){
                String query ="INSERT INTO "+Const.USER_SESSION_TABLE+" VALUES ('"+u.getUsername()+"' , " +
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
                String query ="INSERT INTO "+Const.USER_TABLE+" VALUES ('"+u.getUsername()+"' , " +
                        "' "+u.getName()+"' , '"+u.getPassword()+"' , '"+u.getAddress()+"' )";

                db.execSQL(query);
                db.close();
            }
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
    }

    public User loadUser(int username) {
        SQLiteDatabase db = getReadableDatabase();
        User user = new User();
        try{
            String query = "SELECT  "+Const.CEDULA+" , "+
                    Const.NOMBRE+" , "+Const.USER_CONTRASENA+" , "+
                    Const.DIRECCION+"  FROM  "+Const.USER_TABLE+"  WHERE  "+Const.CEDULA+" = '"+username+"'  ";
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

    /*public List<User> mostrarUsuarios(){
        SQLiteDatabase db = getReadableDatabase();
        List<User> usuarios = new ArrayList<>();
        try{
            Cursor cursor =db.rawQuery("SELECT N_IDE, NOM, IDE , DIG FROM Nits ", null);

            User u = new User();
            if(cursor.moveToFirst()){
                do{
                    u = new User();
                    u.setId(cursor.getInt(cursor.getColumnIndex("N_IDE")));
                    u.setNombrePersonaJuridica(cursor.getString(cursor.getColumnIndex("NOM")));
                    u.setTipoNit(cursor.getString(cursor.getColumnIndex("IDE")));
                    u.setDigitoVerificacion(cursor.getString(cursor.getColumnIndex("DIG")));
                    usuarios.add(u);
                }while (cursor.moveToNext());
            }
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
        return usuarios;
    }
    public User cargarUsuario(String codigo){
        SQLiteDatabase db = getReadableDatabase();
        List<User> usuarios = new ArrayList<>();
        User u = new User();
        try{
            Cursor cursor =db.rawQuery("SELECT * FROM Nits where N_IDE ='"+codigo+"'", null);

            if(cursor.moveToFirst()){
                do{
                    u = new User();
                    u.setId(cursor.getInt(cursor.getColumnIndex("N_IDE")));
                    u.setNombrePersonaJuridica(cursor.getString(cursor.getColumnIndex("NOM")));
                    u.setTipoNit(cursor.getString(cursor.getColumnIndex("IDE")));
                    u.setDigitoVerificacion(cursor.getString(cursor.getColumnIndex("DIG")));
                    u.setDireccion(cursor.getString(cursor.getColumnIndex("DIR")));
                    u.setTelefono(cursor.getString(cursor.getColumnIndex("TEL")));
                    u.setApartadoAereo(cursor.getString(cursor.getColumnIndex("AA")));
                    u.setEstado(cursor.getString(cursor.getColumnIndex("EST")));
                    u.setPrimerNombre(cursor.getString(cursor.getColumnIndex("NOM1")));
                    u.setSegundoNombre(cursor.getString(cursor.getColumnIndex("NOM2")));
                    u.setPriemrApellido(cursor.getString(cursor.getColumnIndex("APE1")));
                    u.setSegundoApellido(cursor.getString(cursor.getColumnIndex("APE2")));
                    u.setTipoId(cursor.getString(cursor.getColumnIndex("TIPO_NIT")));
                    u.setTipoContribuyente(cursor.getString(cursor.getColumnIndex("TIP_CONTRIB")));
                    u.setTipoTercero(cursor.getString(cursor.getColumnIndex("TIP_TERC")));
                    u.setFecha(cursor.getString(cursor.getColumnIndex("FECHA")));

                }while (cursor.moveToNext());

            }
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
        return u;
    }

    public void eliminarUsuario(String codigo){

        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL("DELETE FROM "+TABLA_NOMBRE+ " WHERE N_IDE = '"+codigo+"' ");
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
    }*/

}