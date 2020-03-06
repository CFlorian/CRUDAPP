package com.cksolutions.crudapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.cksolutions.crudapp.R;
import com.cksolutions.crudapp.model.dbData;

public class MainActivity extends AppCompatActivity {

    private dbData conn;
    private EditText etNombre, etFechaNac, etTelefono;
    private RadioButton rbM, rbF;
    private Button btnGuardar, btnVer;
    private String sNombre, sFechaNac;
    private int iTelefono, iSexo; //1 Masculino, 2 Femenino

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conn = new dbData(this);
        initUI();
        listener();
    }

    private void initUI() {
        etNombre = findViewById(R.id.etNombre);
        etFechaNac = findViewById(R.id.etFechaNac);
        etTelefono = findViewById(R.id.etTelefono);
        rbM = findViewById(R.id.rbM);
        rbF = findViewById(R.id.rbF);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnVer = findViewById(R.id.btnVer);
    }

    private void listener() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etNombre.getText().toString().isEmpty() &&
                        !etFechaNac.getText().toString().isEmpty() &&
                        !etTelefono.getText().toString().isEmpty()){

                    sNombre = etNombre.getText().toString();
                    sFechaNac = etFechaNac.getText().toString();
                    iTelefono = Integer.parseInt(etTelefono.getText().toString());

                    if (rbM.isChecked()){
                        iSexo = 1;
                    }else if (rbF.isChecked()){
                        iSexo = 2;
                    }else {
                        Toast.makeText(getApplicationContext(), "Selecione Sexo", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String respuesta = conn.inserta_contacto(sNombre,sFechaNac,iTelefono, iSexo);
                    Toast.makeText(getApplicationContext(), respuesta, Toast.LENGTH_SHORT).show();
                    sNombre = ""; sFechaNac = ""; iTelefono = 0; iSexo = 0;
                    etNombre.setText(""); etFechaNac.setText(""); etTelefono.setText("");
                }else {
                    Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewContactoActivity.class);
                startActivity(intent);
            }
        });
    }
}
