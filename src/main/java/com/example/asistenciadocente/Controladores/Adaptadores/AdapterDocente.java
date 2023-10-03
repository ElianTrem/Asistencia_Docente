package com.example.asistenciadocente.Controladores.Adaptadores;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asistenciadocente.DataBase.Usuario;
import com.example.asistenciadocente.R;

import java.util.ArrayList;

public class AdapterDocente extends BaseAdapter {
    public ArrayList<Usuario> datoDocente;
    public Context context;

    public AdapterDocente(ArrayList<Usuario> listadeDocentes, Context context) {
        datoDocente = listadeDocentes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datoDocente.size();
    }

    @Override
    public Object getItem(int i) {
        return datoDocente.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Usuario docente = (Usuario) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.boxdocente, null);
        TextView nombre = view.findViewById(R.id.Display_Name);
        TextView titulo = view.findViewById(R.id.Display_titulo);
        TextView codigo = view.findViewById(R.id.Display_codigo);
        ImageView PP = view.findViewById(R.id.Display_pp);
        nombre.setText(docente.Nombre);
        titulo.setText(docente.Titulo);
        codigo.setText(docente.Codigo);
        String personPhotoUrl = docente.PP;
        Glide.with(context).load(personPhotoUrl).into(PP);

        return view;
    }
}
