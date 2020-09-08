package com.freed.proyecto_07;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.freed.proyecto_07.interfaces.Status;
import com.freed.proyecto_07.models.Telefono;
import com.freed.proyecto_07.utilidades.FireStoreHelper;
import com.freed.proyecto_07.utilidades.StringsHelpers;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_1 extends Fragment implements Status {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String name, lastName, email, phone;
    private EditText editText_nombre, editText_lasName, editText_email, editText_phone;
    private Button button_add;
    private FireStoreHelper fireStoreHelper = new FireStoreHelper();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_1 newInstance(String param1, String param2) {
        Fragment_1 fragment = new Fragment_1();
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
        return inflater.inflate(R.layout.fragment_1, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText_nombre = view.findViewById(R.id.editTextTextPersonName3);
        editText_lasName = view.findViewById(R.id.editTextTextPersonName4);
        editText_email = view.findViewById(R.id.editTextTextEmailAddress);
        editText_phone = view.findViewById(R.id.editTextPhone);
        button_add = view.findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = ProgressDialog.show(getView().getContext(), "",
                        "Cargando. Porfa espera...", true);
                dialog.show();
                String nombre = editText_nombre.getText().toString();
                String apellido = editText_lasName.getText().toString();
                email = editText_email.getText().toString();
                String telefono = editText_phone.getText().toString();
                if (!nombre.isEmpty()){
                    if (!apellido.isEmpty()){
                        if (editText_phone.getText().toString().length() == 10){
                            if (new StringsHelpers().isEmail(editText_email.getText().toString())){
                                fireStoreHelper.addPhone(new Telefono(UUID.randomUUID().toString(),nombre,apellido,telefono,email),dialog);
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
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    private void onCLick(View view){


    }

    @Override
    public void status(String mensaje) {
        Toast.makeText(getView().getContext(),mensaje,Toast.LENGTH_SHORT).show();
    }
}