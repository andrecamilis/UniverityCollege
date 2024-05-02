package com.andrezin.univeritycollege.dao;

import static com.andrezin.univeritycollege.util.ConnectionFactory.COLUMN_ID;
import static com.andrezin.univeritycollege.util.ConnectionFactory.COLUMN_NAME;
import static com.andrezin.univeritycollege.util.ConnectionFactory.COLUMN_PASSWORD;
import static com.andrezin.univeritycollege.util.ConnectionFactory.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.andrezin.univeritycollege.model.User;
import com.andrezin.univeritycollege.util.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    private ConnectionFactory conexao;
    private SQLiteDatabase db;

    public UserDAO(Context context){

        conexao = new ConnectionFactory(context);
        db = conexao.getWritableDatabase();
    }

    public long insert(User user){
        ContentValues values = new ContentValues();
        values.put("nome", user.getNome());
        values.put("senha", user.getSenha());

        return(db.insert("aluno", null, values));
    }

    public User read(Integer id){
        String args[] = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_PASSWORD},"id = ?", args, null, null,null);
        cursor.moveToFirst();
        User user = new User();
        if(cursor.getCount()>0){
            user.setId(cursor.getInt(0));
            user.setNome(cursor.getString(1));
            user.setSenha(cursor.getString(2));
        }
        return user;
    }

    public List<User>userList(){
        List<User> userList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_PASSWORD}, null, null,null,null,null);
        while (cursor.moveToFirst()){
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setNome(cursor.getString(1));
            user.setSenha(cursor.getString(2));
            userList.add(user);
        }
        return userList;
    }

}
