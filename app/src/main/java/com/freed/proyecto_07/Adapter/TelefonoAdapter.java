package com.freed.proyecto_07.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.freed.proyecto_07.R;
import com.freed.proyecto_07.models.Telefono;
import com.freed.proyecto_07.saveDataHelper.SharePreferencesHelper;
import com.freed.proyecto_07.utilidades.FireStoreHelper;

import java.util.List;

import static androidx.navigation.Navigation.findNavController;

public class TelefonoAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private int list_item_layout;
    private Context context;
    private List<Telefono> telefonos;
    private View requireView;

    public TelefonoAdapter(Context context,int list_item_layout,List<Telefono> telefonos)
    {
        this.context=context;
        this.list_item_layout = list_item_layout;
        this.telefonos=telefonos;
        this.inflater = (LayoutInflater) ((Activity)context).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return telefonos.size();
    }

    @Override
    public Object getItem(int position) {
        return telefonos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return telefonos.indexOf(telefonos.get(position));
    }

    @Override
    public View getView(final int position, View contentView, ViewGroup viewGroup) {
        final Telefono telefono = telefonos.get(position);
        TelefonoHolder telefonoHolder=null;
        if(contentView == null)
        {
            contentView = inflater.inflate(list_item_layout,null);
            telefonoHolder = new TelefonoHolder();

            telefonoHolder.textView_nombre = contentView.findViewById(R.id.textView_nombreFS);
            telefonoHolder.textView_telefono = contentView.findViewById(R.id.textView_TelefonoFS);
            telefonoHolder.textView_email = contentView.findViewById(R.id.textView_emailFS);
            telefonoHolder.imageButton_delete = contentView.findViewById(R.id.imageButton_deleteFS);
            telefonoHolder.imageButton_edit = contentView.findViewById(R.id.imageButton_editFS);
            contentView.setTag(telefonoHolder);

            telefonoHolder.textView_nombre.setText(telefono.getNombre().concat(" ").concat(telefono.getApellido()));
            telefonoHolder.textView_telefono.setText(telefono.getTelefono());
            telefonoHolder.textView_email.setText(telefono.getEmail());

            telefonoHolder.imageButton_edit.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    SharePreferencesHelper sharePreferencesHelper = new SharePreferencesHelper(context,"contacto");
                    sharePreferencesHelper.write("id", telefono.getUid());
                    sharePreferencesHelper.write("nombre", telefono.getNombre());
                    sharePreferencesHelper.write("apellido", telefono.getApellido());
                    sharePreferencesHelper.write("telefono", telefono.getTelefono());
                    sharePreferencesHelper.write("email", telefono.getEmail());

                    findNavController(requireView).navigate(R.id.action_listaFragment_to_editarContacto);

                }
            });
            telefonoHolder.imageButton_delete.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    final AlertDialog.Builder  alertDialogBuilder = new AlertDialog.Builder(TelefonoAdapter.this.context);
//                    final AlertDialog alertDialog = alertDialogBuilder.create();
//                    alertDialog.setCancelable(false);
                    alertDialogBuilder.setTitle("Eliminación");
                    alertDialogBuilder.setMessage("¿Quieres eliminar el teléfono de: "+telefono.getNombre()+"?");
                    alertDialogBuilder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
//                            eliminacion de firebase
                            new FireStoreHelper().DeleteTelefono(telefono.getUid());
                            // eliminar de la lista telefonos que tenemos con el metodo ???
                            telefonos.remove(position);
                            // notificacion del cambio en la lista
                            notifyDataSetChanged();
//
                            dialogInterface.cancel();
                        }
                    });
                    alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    alertDialogBuilder.show();

                }
            });

        }
        else
        {
            contentView.getTag();
        }

        return contentView;
    }
    public void setRequireView(View requireView){

        this.requireView = requireView;

    }
    static class TelefonoHolder
    {
        public TextView textView_nombre,textView_telefono,textView_email;
        public ImageButton imageButton_delete, imageButton_edit;
    }
}
