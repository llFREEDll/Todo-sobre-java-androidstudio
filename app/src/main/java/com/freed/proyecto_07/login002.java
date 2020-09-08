package com.freed.proyecto_07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.freed.proyecto_07.interfaces.Status;
import com.freed.proyecto_07.models.User;
import com.freed.proyecto_07.saveDataHelper.FileHelper;
import com.freed.proyecto_07.utilidades.FireBaseAuthHelper;

public class login002 extends AppCompatActivity {

    //private Button button_CS;
    private CameraManager cameraManager;
    private String cameraId;
    public boolean isActive = false ;
    private FireBaseAuthHelper fireBaseAuthHelper = new FireBaseAuthHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login002);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {

            cameraId = cameraManager.getCameraIdList()[0];

        }catch (CameraAccessException ae){

            Toast.makeText(login002.this,"Error al abrir camara",Toast.LENGTH_SHORT).show();

        }
        //button_CS = findViewById(R.id.button_cerrarSesion);
//        button_CS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                ProgressDialog progressDialog = ProgressDialog.show(login002.this,"","Saliendo",true);
//                progressDialog.show();
//                fireBaseAuthHelper.singOut(progressDialog);
//                User user = FireBaseAuthHelper.getUser();
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fireBaseAuthHelper.setContext(this);
        //fireBaseAuthHelper.setStatusListener((Status) this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.item_cerrar_sesion :
                ProgressDialog progressDialog = ProgressDialog.show(login002.this,"","Saliendo",true);
                progressDialog.show();
                fireBaseAuthHelper.singOut(progressDialog);

                break;
            case R.id.light :

                isActive = !isActive;

                flashOnOff(cameraId,isActive);




                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void flashOnOff(String camaraid, boolean enable){
        try {

            cameraManager.setTorchMode(camaraid,enable);

        }catch (CameraAccessException AE){

            Toast.makeText(login002.this,"Error al cargar el flash",Toast.LENGTH_SHORT).show();
        }
    }
}