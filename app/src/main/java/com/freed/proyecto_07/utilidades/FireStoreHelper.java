package com.freed.proyecto_07.utilidades;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.freed.proyecto_07.interfaces.Status;
import com.freed.proyecto_07.interfaces.Telefonos;
import com.freed.proyecto_07.models.Telefono;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FireStoreHelper {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference directorio = db.collection("telefonos");
    private Status status ;
    private Telefonos telefonos;

    public void addPhone(Telefono telefono, final ProgressDialog dialog){
        Map<String,Object> tel = new HashMap<>();
        tel.put("nombre",telefono.getNombre());
        tel.put("apellido",telefono.getApellido());
        tel.put("telefono",telefono.getTelefono());
        tel.put("email",telefono.getEmail());

        directorio.document(telefono.getUid()).set(tel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete()){

                    status.status("Telefono Agregado");
                    System.out.println("agregado");

                }else {
                    status.status("Telefono no Agregado");
                    System.out.println("error" + task.getException().getMessage());
                }
                dialog.dismiss();
            }
        });
    }
    public void DeleteTelefono(String idTelefono){
        directorio.document(idTelefono).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete()){
                    System.out.println("Eliminado");

                }else {
                    System.out.println("error" + task.getException().getMessage());
                }
            }
        });
    }
    public void getAllTelefonos(){
        final List <Telefono> telefonosLista = new ArrayList<>();

        directorio.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isComplete()){
                    Map <String, Object> telefono ;
                    for (QueryDocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult()) ) {
                        System.out.println(documentSnapshot.getId());
                        telefono = documentSnapshot.getData();
                        telefonosLista.add(new Telefono(documentSnapshot.getId(),String.valueOf(telefono.get("nombre")),
                                String.valueOf(telefono.get("apellido")),
                                String.valueOf(telefono.get("telefono")),
                                String.valueOf(telefono.get("email"))
                                ));
                    }
                    telefonos.getAll(telefonosLista);
                }else {
                    System.out.println("error" + task.getException().getMessage());
                }

            }
        });
    }
    public void editTelefono(String telefonoid, String nombre, String apellido, String telefono, String email, final Context context){
        Map<String,Object> tel = new HashMap<>();
        tel.put("nombre",nombre);
        tel.put("apellido",apellido);
        tel.put("telefono",telefono);
        tel.put("email",email);

        DocumentReference washingtonRef = db.collection("telefonos").document(telefonoid);

// Set the "isCapital" field of the city 'DC'
        washingtonRef
                .update(tel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context,"Editado correctamente",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,"ERROR!",Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void listenAllUserPhones(){

                directorio.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {

                            return;
                        }

                        List<Telefono> telefonosLista = new ArrayList<>();
                        Map <String, Object> telefono ;
                        for (QueryDocumentSnapshot documentSnapshot: value ) {
                            telefono = documentSnapshot.getData();
                            telefonosLista.add(new Telefono(documentSnapshot.getId(),String.valueOf(telefono.get("nombre")),
                                    String.valueOf(telefono.get("apellido")),
                                    String.valueOf(telefono.get("telefono")),
                                    String.valueOf(telefono.get("email"))
                            ));
                        }
                        telefonos.getAll(telefonosLista);

                    }
                });
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public void setOnTelefonosListener(Telefonos telefonos)
    {
        this.telefonos = telefonos;
    }


}
