package com.freed.proyecto_07;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.freed.proyecto_07.Adapter.TelefonoAdapter;
import com.freed.proyecto_07.interfaces.Status;
import com.freed.proyecto_07.interfaces.Telefonos;
import com.freed.proyecto_07.models.Telefono;
import com.freed.proyecto_07.utilidades.FireStoreHelper;

import java.util.List;
import java.util.Objects;
import static androidx.navigation.Navigation.findNavController;

public class ListaFragment extends Fragment implements Status, Telefonos {

    private ListView listView_telefonos;
    private ImageButton imageButton_add;
    private ProgressBar progressBar;
    private FireStoreHelper firestoreHelper = new FireStoreHelper();



    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firestoreHelper.setStatus(this);
        firestoreHelper.setOnTelefonosListener(this);
        firestoreHelper.listenAllUserPhones();

    }

    @Override
    public void onStart() {

        firestoreHelper.getAllTelefonos();
        super.onStart();

    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_lista, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        listView_telefonos = view.findViewById(R.id.listView_lista);
        imageButton_add = view.findViewById(R.id.imageButton_add);
        progressBar = view.findViewById(R.id.progressBar);
        imageButton_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(requireView()).navigate(R.id.action_listaFragment_to_fragment_1);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void status(String mensaje) {
        Toast.makeText(getView().getContext(),mensaje,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getAll(List<Telefono> telefonoList) {
        progressBar.setVisibility(View.INVISIBLE);
        listView_telefonos.setVisibility(View.VISIBLE);
        TelefonoAdapter adapterTelefono;
        adapterTelefono = new TelefonoAdapter(ListaFragment.this.getContext(),R.layout.list_item_telefono,telefonoList);
        adapterTelefono.setRequireView(getView());
        listView_telefonos.setAdapter(adapterTelefono);

    }
}
