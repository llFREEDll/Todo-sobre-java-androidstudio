package com.freed.proyecto_07;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.freed.proyecto_07.interfaces.Status;
import com.freed.proyecto_07.models.Telefono;
import com.freed.proyecto_07.utilidades.FireStoreHelper;
import com.freed.proyecto_07.utilidades.StringsHelpers;
import java.util.UUID;

public class AddElementFragment extends Fragment implements Status{


    private EditText editText_nombre,editText_apellidos,editText_telefono,editText_email;
    private Button button;
    private FireStoreHelper firestoreHelper = new FireStoreHelper();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Status status = this;
        firestoreHelper.setStatus(status);
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText_nombre = view.findViewById(R.id.editTextTextPersonName3);
        editText_apellidos = view.findViewById(R.id.editTextTextPersonName4);
        editText_telefono = view.findViewById(R.id.editTextPhone);
        editText_email = view.findViewById(R.id.editTextTextEmailAddress);
        button = view.findViewById(R.id.button_add);
        onClick();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void onClick() {
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ProgressDialog dialog = ProgressDialog.show(getView().getContext(), "",
                        "Cargando. Porfa espera...", true);
                dialog.show();
                String nombre=editText_nombre.getText().toString();
                String apellidos = editText_apellidos.getText().toString();
                String telefono = editText_telefono.getText().toString();
                String email = editText_email.getText().toString();
                if(!nombre.isEmpty()){
                    if(!apellidos.isEmpty()){
                        if (telefono.length()==10)
                        {
                            if(new StringsHelpers().isEmail(email))
                            {
                                firestoreHelper.addPhone(new Telefono(UUID.randomUUID().toString(),nombre,apellidos,telefono,email),dialog);
                            }
                            else
                            {
                                editText_email.setError("Email erróneo");
                                editText_email.getText().clear();
                                dialog.dismiss();
                            }
                        }
                        else
                        {
                            editText_telefono.setError("Teléfono incompleto");
                            editText_telefono.getText().clear();
                            dialog.dismiss();
                        }
                    }
                    else
                    {
                        editText_apellidos.setError("Campo requerido");
                        editText_apellidos.getText().clear();
                        dialog.dismiss();
                    }
                }
                else
                {
                    editText_nombre.setError("Campo requerido");
                    editText_nombre.getText().clear();
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public void status(String mensaje) {
        if(mensaje.equals("Télefono agregado"))
        {
            editText_nombre.getText().clear();
            editText_apellidos.getText().clear();
            editText_telefono.getText().clear();
            editText_email.getText().clear();
        }
        Toast.makeText(getView().getContext(),mensaje,Toast.LENGTH_SHORT).show();
    }
}
