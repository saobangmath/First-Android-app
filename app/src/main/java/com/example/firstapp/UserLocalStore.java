
package com.example.firstapp;
import android.content.Context;
import android.content.SharedPreferences;

    public class UserLocalStore {
        public static final String SP_NAME = "User_details";
        SharedPreferences userLocalDatabase;
        public UserLocalStore(Context context){
            userLocalDatabase = context.getSharedPreferences(SP_NAME,0);

        }

        public void storeUserData(User user){
            SharedPreferences.Editor spEditor = userLocalDatabase.edit();
            spEditor.putString("username",user.name);
            spEditor.putString("email",user.email);
            spEditor.putString("password",user.password);
            spEditor.putString("telephone",user.telephone);
            spEditor.commit();
        }
        public User getLoginUser(){
            String name = userLocalDatabase.getString("name","");
            String email = userLocalDatabase.getString("email","");
            String phone = userLocalDatabase.getString("telephone","");
            String pass = userLocalDatabase.getString("password","");
            User storedUser = new User(name,email,pass,phone);
            return storedUser;
        }
        public void setUserLogin(boolean login){
            SharedPreferences.Editor editor = userLocalDatabase.edit();
            editor.putBoolean("Login",login);
            editor.commit();
        }
        public void cleanUserData(){
            SharedPreferences.Editor editor = userLocalDatabase.edit();
            editor.clear();
            editor.commit();
        }
    }


