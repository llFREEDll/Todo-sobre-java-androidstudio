package com.freed.proyecto_07;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class listView extends AppCompatActivity {

    private ArrayList<String> nombre = new ArrayList<>(Arrays.asList("alfredo", "edgar" , "vicente", "antonio", "marco","alfredo", "edgar" , "vicente", "antonio", "marco","alfredo", "edgar" , "vicente", "antonio", "marco","alfredo", "edgar" , "vicente", "antonio", "marco"));
    private ArrayAdapter<String> stringArrayAdapter;
    private ArrayAdapter<String> ArrayAdapterFilter;
    private ListView listView;
    private Button button_ingresar;
    private EditText editText_ingresar;
    private EditText editText_buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = findViewById(R.id.listView_nombre);
        editText_ingresar = findViewById(R.id.editTextTextPersonName_ingresar);
        button_ingresar = findViewById(R.id.button_ingresar);
        editText_buscar = findViewById(R.id.editTextTextPerson_buscar);

        stringArrayAdapter = new ArrayAdapter<>(listView.this,android.R.layout.simple_list_item_1,nombre);
        ArrayAdapterFilter = new ArrayAdapter<>(listView.this,android.R.layout.simple_list_item_1,nombre);

        editText_buscar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    //listView.setAdapter(null);
                    ArrayAdapterFilter = new ArrayAdapter<>(listView.this,android.R.layout.simple_list_item_1,nombre);
                    listView.setAdapter(ArrayAdapterFilter);
                }


            }
        });
        editText_buscar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    //listView.setAdapter(null);
                    stringArrayAdapter = new ArrayAdapter<>(listView.this,android.R.layout.simple_list_item_1,nombre);
                    listView.setAdapter(stringArrayAdapter);
                }


            }
        });

        editText_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0 ){

                    listView.setAdapter(ArrayAdapterFilter);
                    ArrayAdapterFilter.notifyDataSetChanged();
                    stringArrayAdapter.notifyDataSetChanged();
                    ArrayAdapterFilter.getFilter().filter(s);
                }else{
                    stringArrayAdapter.notifyDataSetChanged();
                    ArrayAdapterFilter.notifyDataSetChanged();
                    listView.setAdapter(stringArrayAdapter);
                }




            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre.add(editText_ingresar.getText().toString());

                stringArrayAdapter.notifyDataSetChanged();
                ArrayAdapterFilter.notifyDataSetChanged();
                Toast.makeText(listView.this,"Nuevo dato agregado " + editText_ingresar.getText().toString(),Toast.LENGTH_SHORT).show();
                editText_ingresar.getText().clear();
            }
        });


        listView.setAdapter(stringArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(com.freed.proyecto_07.listView.this);
                builder.setTitle("El nombre seleccionado es:");
                builder.setMessage(nombre.get(position));
                builder.show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(com.freed.proyecto_07.listView.this);
                builder.setTitle("Eliminar");
                builder.setMessage("Deseas eliminar a " + nombre.get(position) + "?");
                builder.setCancelable(false);
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        nombre.remove(position);

                        stringArrayAdapter.notifyDataSetChanged();
                        ArrayAdapterFilter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return true;
            }
        });
    }
}