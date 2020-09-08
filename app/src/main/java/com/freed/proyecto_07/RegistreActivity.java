package com.freed.proyecto_07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.freed.proyecto_07.interfaces.Status;
import com.freed.proyecto_07.models.User;
import com.freed.proyecto_07.utilidades.FireBaseAuthHelper;
import com.freed.proyecto_07.utilidades.StringsHelpers;
import com.google.firebase.auth.FirebaseAuth;

public class RegistreActivity extends AppCompatActivity {

    private CardView cardView_registrarse;
    private EditText editText_user,editText_pass;
    private StringsHelpers stringsHelpers = new StringsHelpers();
    private FireBaseAuthHelper fireBaseAuthHelper = new FireBaseAuthHelper();

    @Override
    protected void onStart() {
        super.onStart();
        fireBaseAuthHelper.setContext(this);
        fireBaseAuthHelper.setStatusListener(new Status() {
            @Override
            public void status(String message)
            {
                Toast.makeText(RegistreActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);
        getSupportActionBar().setElevation(0);


        cardView_registrarse = findViewById(R.id.cardView_registrarse2);
        editText_pass = findViewById(R.id.editTextTextPassword_fb2);
        editText_user = findViewById(R.id.editTextText_userFB2);

        cardView_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog dialog = ProgressDialog.show(RegistreActivity.this, "",
                        "Cargando. Porfa espera...", true);
                dialog.show();
                fireBaseAuthHelper.CreateMailAndPass(editText_user.getText().toString(),editText_pass.getText().toString(),dialog);

            }
        });

    }

}