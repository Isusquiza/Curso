package com.jesusmariagarcia.formulariocontacto;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if( intent != null) {

            Bundle parametros = getIntent().getExtras();

            if(parametros != null) {

                TextInputEditText ietNombre = (TextInputEditText) findViewById(R.id.ietNombre);
                DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
                TextInputEditText ietTelefono = (TextInputEditText) findViewById(R.id.ietTelefono);
                TextInputEditText ietEmail = (TextInputEditText) findViewById(R.id.ietEmail);
                TextInputEditText ietDescripcion = (TextInputEditText) findViewById(R.id.ietDescripcion);

                int dia = parametros.getInt(getResources().getString(R.string.ConfFechaDia));
                int mes = parametros.getInt(getResources().getString(R.string.ConfFechaMes));
                int año = parametros.getInt(getResources().getString(R.string.ConfFechaAño));

                ietNombre.setText(parametros.getString(getResources().getString(R.string.ConfNombre)));
                datePicker.init(año, mes, dia, null);

                ietTelefono.setText(parametros.getString(getResources().getString(R.string.ConfTelefono)));
                ietEmail.setText(parametros.getString(getResources().getString(R.string.ConfEmail)));
                ietDescripcion.setText(parametros.getString(getResources().getString(R.string.ConfDescripcion)));
            }
        }
    }

    public void onClickBtnSiguiente(View view) {

        TextInputEditText ietNombre = (TextInputEditText) findViewById(R.id.ietNombre);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        TextInputEditText ietTelefono = (TextInputEditText) findViewById(R.id.ietTelefono);
        TextInputEditText ietEmail = (TextInputEditText) findViewById(R.id.ietEmail);
        TextInputEditText ietDescripcion = (TextInputEditText) findViewById(R.id.ietDescripcion);

        Intent intent = new Intent(MainActivity.this, ConfirmarDatos.class);

        intent.putExtra(getResources().getString(R.string.Nombre), ietNombre.getText().toString());
        intent.putExtra(getResources().getString(R.string.FechaDia), datePicker.getDayOfMonth());
        intent.putExtra(getResources().getString(R.string.FechaMes), datePicker.getMonth());
        intent.putExtra(getResources().getString(R.string.FechaAño), datePicker.getYear());
        intent.putExtra(getResources().getString(R.string.Telefono), ietTelefono.getText().toString());
        intent.putExtra(getResources().getString(R.string.Email), ietEmail.getText().toString());
        intent.putExtra(getResources().getString(R.string.Descripcion), ietDescripcion.getText().toString());

        startActivity(intent);
        finish();
    }
}
