package com.example.asistenciadocente.Controladores.Adaptadores;

import static java.security.AccessController.getContext;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.example.asistenciadocente.DataBase.Usuario;
import com.example.asistenciadocente.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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
        //Mostramos cuando se selecciona un docente
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            
            }
        });

        return view;

    }
    private void showbottomdialog(){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.adddocente);
        EditText nombre = dialog.findViewById(R.id.edit_txt_nom);
        EditText titulo = dialog.findViewById(R.id.txt_titulo);
    }
    private void mostrarTimePickerDialog(final TextView textView) {
        Calendar cal = Calendar.getInstance();
        int hora = cal.get(Calendar.HOUR_OF_DAY);
        int minuto = cal.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Formatear la hora seleccionada como string
                String amPm;
                if (hourOfDay >= 12) {
                    amPm = "PM";
                    if (hourOfDay > 12) {
                        hourOfDay -= 12;
                    }
                } else {
                    amPm = "AM";
                }

                String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d %s", hourOfDay, minute, amPm);

                // Mostrar la hora seleccionada en el TextView
                textView.setText(horaSeleccionada);
            }
        }, hora, minuto, false); // <- Cambia el último argumento a 'false'

        timePickerDialog.show();
    }

    private void mostrarAlertDialogDiasTrabajados(final TextView textView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Selecciona los días trabajados");

        final boolean[] seleccionados = new boolean[7]; // Array para almacenar las selecciones

        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};


        builder.setMultiChoiceItems(diasSemana, seleccionados, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                seleccionados[which] = isChecked;
            }
        });

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ArrayList<String> diasSeleccionados = new ArrayList<>();
                for (int i = 0; i < seleccionados.length; i++) {
                    if (seleccionados[i]) {
                        diasSeleccionados.add(diasSemana[i]);
                    }
                }
                // Mostrar los días seleccionados en el TextView
                textView.setText(diasSeleccionados.toString());
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
