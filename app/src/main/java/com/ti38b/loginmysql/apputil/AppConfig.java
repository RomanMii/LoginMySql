package com.ti38b.loginmysql.apputil;

import android.content.Context;
import android.content.SharedPreferences;
import com.ti38b.loginmysql.R;

public class AppConfig {

    private Context context;
    private SharedPreferences sharedPreferences;

    public AppConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file_key),Context.MODE_PRIVATE);
    }

    public boolean isUserLogin(){
        return sharedPreferences.getBoolean(context.getString(R.string.pref_is_user_login),false);
    }

    public void updateUserLoginStatus(boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_is_user_login),status);
        editor.apply();
    }

    public void saveUserUsername(String username){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_username),username);
        editor.apply();
    }

    public String getNameOfUser(){
        return sharedPreferences.getString(context.getString(R.string.pref_user_username),"Unknown");
    }
}
