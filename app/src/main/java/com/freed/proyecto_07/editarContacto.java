package com.freed.proyecto_07;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.freed.proyecto_07.interfaces.Status;
import com.freed.proyecto_07.models.Telefono;
import com.freed.proyecto_07.saveDataHelper.SharePreferencesHelper;
import com.freed.proyecto_07.utilidades.FireStoreHelper;
import com.freed.proyecto_07.utilidades.StringsHelpers;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link editarContacto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class editarContacto extends Fragment implements Status {

    private EditText editText_nombre, editText_lasName, editText_email, editText_phone;
    private Button button_add;
    private FireStoreHelper fireStoreHelper = new FireStoreHelper();
    private Telefono telefono;

    // TODO: Rename parameter arguments, choose names that match

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public editarContacto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment editarContacto.
     */
    // TODO: Rename and change types and number of parameters
    public static editarContacto newInstance(String param1, String param2) {
        editarContacto fragment = new editarContacto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Status status = this;
        fireStoreHelper.setStatus(status);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_contacto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText_nombre = view.findViewById(R.id.editText_editarContactoNombre);
        editText_lasName = view.findViewById(R.id.editText_editarContactoApellido);
        editText_email = view.findViewById(R.id.editTextText_EditatContactoEmailAddress);
        editText_phone = view.findViewById(R.id.editTextPhone_EditarContactoTelefono);
        button_add = view.findViewById(R.id.button_EditatContactoEdit);

        SharePreferencesHelper sharePreferencesHelper = new SharePreferencesHelper(getContext(),"contacto");
        final String nombre = sharePreferencesHelper.read("nombre");
        final String apellido = sharePreferencesHelper.read("apellido");
        final String email = sharePreferencesHelper.read("email");
        final String telefono1 = sharePreferencesHelper.read("telefono");
        final String id = sharePreferencesHelper.read("id");

        editText_nombre.setText(nombre);
        editText_lasName.setText(apellido);
        editText_email.setText(email);
        editText_phone.setText(telefono1);

//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle(id);
//        builder.show();


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = ProgressDialog.show(getView().getContext(), "",
                        "Cargando. Porfa espera...", true);
                dialog.show();

                if (!nombre.isEmpty()){
                    if (!apellido.isEmpty()){
                        if (editText_phone.getText().toString().length() == 10){
                            if (new StringsHelpers().isEmail(editText_email.getText().toString())){

                                fireStoreHelper.editTelefono(id,
                                        editText_nombre.getText().toString(),
                                        editText_lasName.getText().toString(),
                                        editText_phone.getText().toString(),
                                        editText_email.getText().toString(),
                                        getContext());
                                dialog.dismiss();
                                getActivity().onBackPressed();

                            }else{
                                editText_email.setError("Email erroneo");
                                editText_email.getText().clear();
                            }

                        }else{
                            editText_phone.setError("Telefono erroneo");
                            editText_phone.getText().clear();
                        }
                    }else{
                        editText_lasName.setError("Apellido erroneo");
                        editText_lasName.getText().clear();
                    }
                }else{
                    editText_nombre.setError("nombre erroneo");
                    editText_nombre.getText().clear();
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public void status(String mensaje) {

        Toast.makeText(getContext(),mensaje,Toast.LENGTH_SHORT);
    }
}