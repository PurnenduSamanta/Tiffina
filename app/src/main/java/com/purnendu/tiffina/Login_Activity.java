package com.purnendu.tiffina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Login_Activity extends AppCompatActivity {
    LinearLayout mobile_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        mobile_login=findViewById(R.id.mobile_login);
        mobile_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Activity.this,LoginWithMobileActivity.class);
                startActivity(intent);
            }
        });
    }
}