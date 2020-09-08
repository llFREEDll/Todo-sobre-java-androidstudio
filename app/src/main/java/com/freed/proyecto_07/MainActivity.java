package com.freed.proyecto_07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.freed.proyecto_07.saveDataHelper.FileHelper;
import com.freed.proyecto_07.saveDataHelper.SharePreferencesHelper;

public class MainActivity extends AppCompatActivity {
    private Button button_calculadoraCB;
    private Button button_calcularDivisas;
    private Button button_toggleSwitch;
    private Button button_PDP;
    private TextView textView_pasoDeParametros;
    private Button button_login;
    private EditText editText_user;
    private EditText editText_password;
    private Button button_save;
    private Button button_read;
    private EditText editText_Multiline;
    private String usuario = "Alfredo" , contrasena = "123";
    private FileHelper fileHelper;
    private String data = "";
    private Spinner spinner , spinner_tipoDeSangre;
    private Button button_listView;
    private Button button_cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_calculadoraCB = findViewById(R.id.button_calculadoraCB);
        button_calcularDivisas = findViewById(R.id.button_divisas);
        button_toggleSwitch = findViewById(R.id.button_toggleSwitch);
        button_PDP = findViewById(R.id.button_PDP);
        textView_pasoDeParametros = findViewById(R.id.editTextText_PasoDeParametros);
        button_login = findViewById(R.id.button_login);
        editText_password = findViewById(R.id.editTextTextPassword_password);
        editText_user = findViewById(R.id.editTextTextPersonName_Usuario);
        button_save = findViewById(R.id.button_login_save);
        button_read = findViewById(R.id.button_read);
        editText_Multiline = findViewById(R.id.editTextTextMultiLine_1);
        spinner = findViewById(R.id.spinner);
        spinner_tipoDeSangre = findViewById(R.id.spinner_tipoDeSangre);
        button_listView = findViewById(R.id.button_listView);
        button_cardView = findViewById(R.id.button_CardView);

        showInfoSpinnerString(diasSemanaArray());
        showInfoSpinnerTipoSangre(TipoDeSangre());
//        showInfoSpinnerCharSecuence(diasSemanaArray2());

        String seleccion  = spinner.getSelectedItem().toString();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0)
                    Toast.makeText(MainActivity.this,"Dia de la semana: " + parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"selecciona un dia valido" ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_tipoDeSangre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0)
                    Toast.makeText(MainActivity.this, parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"selecciona un tipo de sangre valido" ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button_listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,listView.class);
                startActivity(intent);
            }
        });

        fileHelper = new FileHelper(MainActivity.this);

        button_calculadoraCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CalculadoraCB.class);
                startActivity(intent);
            }
        });
        button_calcularDivisas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ConvertirDivisas.class);
                startActivity(intent);
            }
        });
        button_toggleSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ToggleSwitch.class);
                startActivity(intent);
        }
        });
        button_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginFireBase.class);
                startActivity(intent);
            }
        });
        button_PDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = textView_pasoDeParametros.getText().toString();

                if (!name.isEmpty()){
                    BundleHelper bundleHelper = new BundleHelper();
                    bundleHelper.writeBundle("nombre",name,bundleHelper.getBundle());

                    SharePreferencesHelper sharePreferencesHelper = new SharePreferencesHelper(MainActivity.this,"credenciales");
                    sharePreferencesHelper.write("nombre", name);

                    Intent intent = new Intent(MainActivity.this,PersistenciaDeDatos.class);
                    intent.putExtras(bundleHelper.getBundle());
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    finish();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"Ingresa un nombre",Toast.LENGTH_SHORT).show();
                    textView_pasoDeParametros.setError("Requerido");
                }



            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editText_user.getText().toString();
                String password = editText_password.getText().toString();

                if (!user.isEmpty() && !password.isEmpty()){

                    if (user.equals(usuario) && password.equals(contrasena) && !spinner_tipoDeSangre.getSelectedItem().equals(" ")){

                        SharePreferencesHelper sharePreferencesHelper = new SharePreferencesHelper(MainActivity.this,"credencialesLogin");
                        sharePreferencesHelper.write("user", user);
                        sharePreferencesHelper.write("sangre", spinner_tipoDeSangre.getSelectedItem().toString());
                        sharePreferencesHelper.write("password", password);

                        Intent intent = new Intent(MainActivity.this,Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        finish();
                        startActivity(intent);
                    } else {

                        Toast.makeText(MainActivity.this,"usuario/contraseña incorrectos",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(MainActivity.this,"Ingresa un usuario/contraseña ",Toast.LENGTH_SHORT).show();

                    if(editText_password.getText().toString().isEmpty() && editText_user.getText().toString().isEmpty()){
                        editText_password.setError("Requerido");
                        editText_user.setError("Requerido");
                    }
                    else if(editText_user.getText().toString().isEmpty())
                        editText_user.setError("Requerido");
                    else if (editText_password.getText().toString().isEmpty())
                        editText_password.setError("Requerido");
                    else if (spinner_tipoDeSangre.getSelectedItem().equals("")){
                        Toast.makeText(MainActivity.this,"Ingresa un tipo de sangre",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });



    }

    private void showInfoSpinnerString(ArrayAdapter<String> arrayAdapter) {
        spinner.setAdapter(arrayAdapter);
    }
    private void showInfoSpinnerTipoSangre(ArrayAdapter<String> arrayAdapter) {
        spinner_tipoDeSangre.setAdapter(arrayAdapter);
    }
    private void showInfoSpinnerCharSecuence(ArrayAdapter<CharSequence> arrayAdapter) {

    }
    private ArrayAdapter<String> TipoDeSangre(){
        ArrayAdapter<String> tipoSangre = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_item,StaticHelper.arrayTipoDeSangre);
        return tipoSangre;
    }
    private ArrayAdapter<String> diasSemanaList(){
        ArrayAdapter<String> diasSemanas = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_item,StaticHelper.list);
        return diasSemanas;
    }
    private ArrayAdapter<String> diasSemanaArray(){
        ArrayAdapter<String> diasSemanas = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_item,StaticHelper.array);
        return diasSemanas;
    }
//    private ArrayAdapter<CharSequence> diasSemanaArray2(){
//        ArrayAdapter<CharSequence> diasSemanas ;//new ArrayAdapter<>(MainActivity.this,android.R.array.,android.R.layout.simple_spinner_item);
//        return diasSemanas;
//    }
    public void click(View view){

        int idView = view.getId();

        if (idView == R.id.button_read){

            //editText_Multiline.getText().clear();
            data = fileHelper.readData();
            Log.e("datos2" , data);
            editText_Multiline.setText(data);

        }else if(idView == R.id.button_login_save){
            data = editText_Multiline.getText().toString();
            if (!data.isEmpty()){
                if (fileHelper.saveData(data)){

                    Toast.makeText(MainActivity.this,"Guardado!!",Toast.LENGTH_SHORT).show();
                    editText_Multiline.getText().clear();

                }else Toast.makeText(MainActivity.this," no Guardado :c",Toast.LENGTH_SHORT).show();

            }else editText_Multiline.setError("rellena este campo");


        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharePreferencesHelper sharePreferencesHelper = new SharePreferencesHelper(MainActivity.this,"credenciales");
        if(sharePreferencesHelper.read("user") != null ){

            Intent intent = new Intent(MainActivity.this,PersistenciaDeDatos.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(intent);

        }
        SharePreferencesHelper sharePreferencesHelperLogin = new SharePreferencesHelper(MainActivity.this,"credencialesLogin");

        if(sharePreferencesHelperLogin.read("user") != null && sharePreferencesHelperLogin.read("password") != null){

            Intent intent = new Intent(MainActivity.this,Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(intent);
        }
    }
}