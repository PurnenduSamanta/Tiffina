package com.purnendu.tiffina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class LoginWithMobileActivity extends AppCompatActivity {

    EditText password,number;
    Button proceed;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_mobile);
        password=findViewById(R.id.password);
        number=findViewById(R.id.number);
        proceed=findViewById(R.id.proceed);
       progressDialog=new ProgressDialog(LoginWithMobileActivity.this);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mpass,mph;
                RequestQueue queue = Volley.newRequestQueue(LoginWithMobileActivity.this);
                mpass=password.getText().toString().trim();
                mph=number.getText().toString().trim();
                if(TextUtils.isEmpty(mpass))
                {
                   password.setError("Required Field");
                    return;
                }
                if(TextUtils.isEmpty(mph))
                {
                    number.setError("Required Field");
                    return;

                }
                progressDialog.setMessage("Processing...");
                progressDialog.show();

                String url=" https://mekvahan.com/api/android_intern_task";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                if(response.equals("{\"status\":true}")) {
                                    Intent intent = new Intent(LoginWithMobileActivity.this, WelcomeActivity.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(LoginWithMobileActivity.this, "You are not a valid user", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(LoginWithMobileActivity.this, "Problem Occurred,Check Internet Connection", Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("mobile",mph);
                        params.put("password",mpass);
                        return params;
                    }
                };
                queue.add(postRequest);



            }
        });
    }
}