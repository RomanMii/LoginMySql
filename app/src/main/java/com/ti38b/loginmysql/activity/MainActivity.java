package com.ti38b.loginmysql.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ti38b.loginmysql.R;
import com.ti38b.loginmysql.apputil.AppConfig;
import com.ti38b.loginmysql.models.ApiResponse;
import com.ti38b.loginmysql.retrofitutil.ApiClient;
import com.ti38b.loginmysql.retrofitutil.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button newUser;
    Button login;
    EditText usernameTxt;
    EditText passwordTxt;
    CheckBox checkBox;
    private boolean isRememberUserLogin = false;
    private AppConfig appConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newUser = findViewById(R.id.loginNewUserButton);
        login = findViewById(R.id.loginButton);
        usernameTxt = findViewById(R.id.loginUsernameEditText);
        passwordTxt = findViewById(R.id.loginPasswordEditText);
        checkBox = findViewById(R.id.loginCheckBox);
        appConfig = new AppConfig(this);
        if(appConfig.isUserLogin()){
            String username = appConfig.getNameOfUser();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
            finish();
        }

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){
        String username = usernameTxt.getText().toString();
        String password = passwordTxt.getText().toString();

        Call<ApiResponse> call = ApiClient.getApiClient()
                .create(ApiInterface.class)
                .performUserLogin(username,password);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.code()==200){
                    if(response.body().getStatus().equals("ok")){
                        if(response.body().getResultCode()==1){
                            String username = response.body().getUsername();
                            if(isRememberUserLogin){
                                appConfig.updateUserLoginStatus(true);
                                appConfig.saveUserUsername(username);
                            }
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            intent.putExtra("username",username);
                            startActivity(intent);
                            finish();
                        }else{
                            displayUserInformation("login failed");
                        }
                    }else{
                        displayUserInformation("something went wrong");
                    }
                }else{
                    displayUserInformation("something went wrong");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void displayUserInformation(String message){
        Toast toast = Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void checkBoxClicked(View view){
        isRememberUserLogin = ((CheckBox)view).isChecked();
    }
}