package com.freed.proyecto_07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.freed.proyecto_07.saveDataHelper.SharePreferencesHelper;

public class PersistenciaDeDatos extends AppCompatActivity {

    private TextView textView_nombre;
    private Button button_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistencia_de_datos);

        textView_nombre = findViewById(R.id.textView_nombreRecibido);
        button_logout = findViewById(R.id.button_logout);

        Bundle bundle = getIntent().getExtras();
        BundleHelper bundleHelper = new BundleHelper();

        if (bundle != null){
            String nombre = bundleHelper.readBundleString("nombre",bundle);
            textView_nombre.setText("Hola " + nombre);
        }else{
            SharePreferencesHelper sharePreferencesHelper = new SharePreferencesHelper(PersistenciaDeDatos.this,"credenciales");
            String nombre = sharePreferencesHelper.read("nombre");
            textView_nombre.setText("Hola " + nombre);
        }
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersistenciaDeDatos.this,MainActivity.class);
                SharePreferencesHelper sharePreferencesHelper = new SharePreferencesHelper(PersistenciaDeDatos.this, "credenciales");
                sharePreferencesHelper.Clear();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);
            }
        });
    }

}