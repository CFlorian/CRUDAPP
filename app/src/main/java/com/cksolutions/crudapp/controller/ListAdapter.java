package com.cksolutions.crudapp.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cksolutions.crudapp.R;
import com.cksolutions.crudapp.model.ContactosModel;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ContactosModel> {

    private ArrayList<ContactosModel> dataSet;
    Context mContext;


    private static class ViewHolder {
        TextView tvNombre;
        TextView tvNumero;
    }


    public ListAdapter (Context context, ArrayList<ContactosModel> contactos){
        super(context, R.layout.list_item, contactos);
        this.dataSet = contactos;
        this.mContext=context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ContactosModel contactosModel = getItem(position);
        ListAdapter.ViewHolder viewHolder;

        final View result;
        if (convertView == null){
            viewHolder = new ListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            viewHolder.tvNombre = convertView.findViewById(R.id.tvNombre);
            viewHolder.tvNumero = convertView.findViewById(R.id.tvTelefono);

            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ListAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }


        viewHolder.tvNombre.setText(contactosModel.getName());
        viewHolder.tvNumero.setText(contactosModel.getTelefono());
        return convertView;
    }


}
