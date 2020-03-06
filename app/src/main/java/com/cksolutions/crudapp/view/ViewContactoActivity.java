package com.cksolutions.crudapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.cksolutions.crudapp.R;
import com.cksolutions.crudapp.model.dbData;

import java.util.ArrayList;
import java.util.List;

public class ViewContactoActivity extends AppCompatActivity {

    private TextView tvText;
    private dbData conn;
    private String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacto);
        conn = new dbData(this);
        tvText = findViewById(R.id.tvText);

        ArrayList<String> my_array = new ArrayList<String>();
        my_array = conn.getContactos();
        for (int i= 0;  i < my_array.size(); i++){
            text = text + my_array.get(i) + "\n";
        }

        tvText.setText(text);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
