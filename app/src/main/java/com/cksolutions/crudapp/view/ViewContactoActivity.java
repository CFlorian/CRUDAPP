package com.cksolutions.crudapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cksolutions.crudapp.R;
import com.cksolutions.crudapp.controller.ListAdapter;
import com.cksolutions.crudapp.model.ContactosModel;
import com.cksolutions.crudapp.model.dbData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewContactoActivity extends AppCompatActivity {

    private TextView tvText;
    private dbData conn;
    private String text = "";
    private ListAdapter adapter;
    private ListView listView;
    private ArrayList<ContactosModel> arrayContactos = new ArrayList<ContactosModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacto);
        listView = findViewById(R.id.list_view);

        conn = new dbData(this);
        arrayContactos = new ArrayList<>();
        arrayContactos.clear();

        final List<Map> mapListContacto = conn.getContactosList();
        for (Map data : mapListContacto){
            arrayContactos.add(new ContactosModel(data.get("nombre").toString(),data.get("telefono").toString()));
        }

        adapter = new ListAdapter(getApplicationContext(),arrayContactos);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewContactoActivity.this);
                builder.setTitle("Accion");
                builder.setMessage("Desea eliminar contacto");
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        arrayContactos.remove(i);
                        conn.eliminar_contacto(arrayContactos.get(i).getTelefono());
                        adapter.notifyDataSetChanged();
                    }});

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }});
                AlertDialog alert = builder.create();
                alert.show();
                Log.e("TAG", "data "+ arrayContactos.get(i).getName());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
