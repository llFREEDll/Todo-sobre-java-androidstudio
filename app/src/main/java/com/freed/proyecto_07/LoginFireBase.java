package com.freed.proyecto_07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.freed.proyecto_07.interfaces.Status;
import com.freed.proyecto_07.models.User;
import com.freed.proyecto_07.utilidades.FireBaseAuthHelper;
import com.freed.proyecto_07.utilidades.StringsHelpers;

public class LoginFireBase extends AppCompatActivity  {

    private CardView cardView_login,cardView_registrarse;
    private EditText editText_user,editText_pass;
    private FireBaseAuthHelper fireBaseAuthHelper = new FireBaseAuthHelper();

    @Override
    protected void onStart() {
        super.onStart();
        fireBaseAuthHelper.setContext(this);
        fireBaseAuthHelper.setStatusListener(new Status() {
            @Override
            public void status(String message)
            {
                Toast.makeText(LoginFireBase.this,message,Toast.LENGTH_SHORT).show();
            }
        });

        User user = FireBaseAuthHelper.getUser();
        if(user!=null)
        {
            Log.e("users",user.getUser());

            Intent intent = new Intent(LoginFireBase.this, login002.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fire_base);
        getSupportActionBar().setElevation(0);
        setTitle("Login");

        cardView_login = findViewById(R.id.cardView_login);
        cardView_registrarse = findViewById(R.id.cardView_registrarse);
        editText_pass = findViewById(R.id.editTextTextPassword_fb);
        editText_user = findViewById(R.id.editTextText_userFB);

        cardView_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginFireBase.this,RegistreActivity.class);
                startActivity(intent);
            }
        });
        cardView_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = ProgressDialog.show(LoginFireBase.this, "",
                        "Cargando. Porfa espera...", true);
                dialog.show();
                fireBaseAuthHelper.signInWithEmailAndPassword(editText_user.getText().toString(),editText_pass.getText().toString(),dialog);
            }
        });
    }


}