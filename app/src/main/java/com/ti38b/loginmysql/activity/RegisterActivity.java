package com.ti38b.loginmysql.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ti38b.loginmysql.R;
import com.ti38b.loginmysql.models.ApiResponse;
import com.ti38b.loginmysql.retrofitutil.ApiClient;
import com.ti38b.loginmysql.retrofitutil.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton;
    private EditText usernameTxt;
    private EditText emailTxt;
    private EditText passwordTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = findViewById(R.id.registerButton);
        usernameTxt = findViewById(R.id.registerUsernameEditText);
        emailTxt = findViewById(R.id.registerEmailEditText);
        passwordTxt = findViewById(R.id.registerPasswordEditText);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp(){
        String username = usernameTxt.getText().toString();
        String email = emailTxt.getText().toString();
        String password = passwordTxt.getText().toString();

        Call<ApiResponse> call = ApiClient.getApiClient()
                .create(ApiInterface.class)
                .performUserSignIn(username,email,password);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.code() == 200){
                    if(response.body().getResultCode()==1){
                        displayInfo("Registration success");
                        onBackPressed();
                        finish();
                    }else{
                        displayInfo("User already exist");
                    }
                }else{
                    displayInfo("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void displayInfo(String message){
        Toast toast = Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT);
        toast.show();
    }
}