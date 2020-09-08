package com.freed.proyecto_07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.freed.proyecto_07.saveDataHelper.SharePreferencesHelper;

public class Login extends AppCompatActivity {
    private Button button_logout;
    private TextView textView_saludo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_logout = findViewById(R.id.button_LogOut);
        textView_saludo = findViewById(R.id.textView_saludo);

        SharePreferencesHelper sharePreferencesHelper = new SharePreferencesHelper(Login.this,"credencialesLogin");
        String user = sharePreferencesHelper.read("user");
        String sangre = sharePreferencesHelper.read("sangre");
        textView_saludo.setText("Hola " + user + " tu tipo de sangre es: " + sangre);

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,MainActivity.class);
                SharePreferencesHelper sharePreferencesHelper = new SharePreferencesHelper(Login.this, "credencialesLogin");
                sharePreferencesHelper.Clear();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);
            }
        });
    }
}