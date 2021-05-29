package com.ti38b.loginmysql.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ti38b.loginmysql.R;
import com.ti38b.loginmysql.apputil.AppConfig;

public class HomeActivity extends AppCompatActivity {

    TextView usernameTxt;
    Button logout;

    private AppConfig appConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        usernameTxt = findViewById(R.id.resultTextView);
        logout = findViewById(R.id.logoutButton);
        appConfig = new AppConfig(this);
        String username = getIntent().getStringExtra("username");
        usernameTxt.setText(username);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appConfig.updateUserLoginStatus(false);
                startActivity(new Intent(HomeActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}