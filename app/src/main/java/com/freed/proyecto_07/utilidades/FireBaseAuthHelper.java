package com.freed.proyecto_07.utilidades;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.freed.proyecto_07.Login;
import com.freed.proyecto_07.RegistreActivity;
import com.freed.proyecto_07.interfaces.Status;
//import com.freed.proyecto_07.models.User;
import com.freed.proyecto_07.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class FireBaseAuthHelper {

    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();;
// ...
// Initialize Firebase Auth

    private Status status;
    private Context context;
    private final StringsHelpers stringsHelpers = new StringsHelpers();


    public void setContext(Context context){
        this.context = context;

    }

    public static User CreateUser(FirebaseUser fireBaseUser){

        return (fireBaseUser!=null)?new User(/*uid*/fireBaseUser.getUid()):null;
    }
    public static User getUser(){
        return CreateUser(mAuth.getCurrentUser());
    }
    public void CreateMailAndPass(String email, String password, final ProgressDialog progressDialog){
        if (stringsHelpers.isUserPass(email,password)){

            status.status("Credenciales correctas en sintaxis");
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                //FirebaseUser user = mAuth.getCurrentUser();
                                status.status("ingresando al sistema");
                                //CreateUser(user);
                                progressDialog.dismiss();
                                Intent intent = new Intent(context, com.freed.proyecto_07.login002.class);
                                context.startActivity(intent);
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //updateUI(null);
                                String error = Objects.requireNonNull(task.getException().getMessage());
                                if (error.equals("The email address is already in use by another account."))

                                    status.status("Email ya usado");

                                else if (error.equals("The given password is invalid. [ Password should be at least 6 characters ]"))

                                    status.status("COntraseña demasiado corta");

                                else

                                    status.status("Error al registrarse");

                                progressDialog.dismiss();
                                Log.e("ERROR: ", Objects.requireNonNull(error) );
                                //status.status("ERROR");
                                CreateUser(null);

                            }

                            // ...
                        }
                    });

        }else{
            status.status("Error: credenciales incorrectas en sintaxis");
            progressDialog.dismiss();

        }


    }


    public void  singOut(ProgressDialog progressDialog){
        mAuth.signOut();
        progressDialog.dismiss();
        Intent intent = new Intent(context, com.freed.proyecto_07.LoginFireBase.class);
        context.startActivity(intent);
        ((Activity)context).finish();


    }
    public void setStatusListener(Status status){

        this.status = status;
    }
    public void signInWithEmailAndPassword(final String email, String password, final ProgressDialog progressDialog) {

        if (stringsHelpers.isUserPass(email, password)) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                status.status("Ingresando al sistema...");
                                progressDialog.dismiss();
                                Intent intent = new Intent(context, com.freed.proyecto_07.login002.class);
                                context.startActivity(intent);
                                ((Activity)context).finish();

                            } else {
                                String error = Objects.requireNonNull(task.getException()).getMessage();
                                switch (error) {
                                    case "There is no user record corresponding to this identifier. The user may have been deleted.":
                                        status.status("Esas credenciales no existen en la base de datos o el email es invalido");
                                        break;
                                    case "The password is invalid or the user does not have a password.":
                                        status.status("La contraseña es incorrecta");
                                        break;
                                    case "The user account has been disabled by an administrator.":
                                        status.status("Cuenta inhabilidada, contacta al administrador");
                                        break;
                                    default:
                                        status.status("Verifica tu conexión a Internet");
                                        break;

                                }
                                Log.e("error", error);
                                progressDialog.dismiss();
                            }
                        }
                    });
        } else {
            status.status("Error en el email o en el password");
            progressDialog.dismiss();
        }
    }
    public void setOnStatusListener(Status status)
    {
        this.status=status;
    }
}

