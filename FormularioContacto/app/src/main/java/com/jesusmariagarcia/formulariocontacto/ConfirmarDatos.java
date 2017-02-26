package com.jesusmariagarcia.formulariocontacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class ConfirmarDatos extends AppCompatActivity {

    private TextView etConfNombre;
    private TextView etConfFecha;
    private TextView etConfTelefono;
    private TextView etConfEmail;
    private TextView etConfDescripcion;

    private int dia, mes, año;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        Bundle parametros = getIntent().getExtras();

        etConfNombre = (TextView) findViewById(R.id.etConfNombre);
        etConfFecha = (TextView) findViewById(R.id.etConfFecha);
        etConfTelefono = (TextView) findViewById(R.id.etConfTelefono);
        etConfEmail = (TextView) findViewById(R.id.etConfEmail);
        etConfDescripcion = (TextView) findViewById(R.id.etConfDescripcion);


        dia = parametros.getInt(getResources().getString(R.string.FechaDia));
        mes = parametros.getInt(getResources().getString(R.string.FechaMes));
        año = parametros.getInt(getResources().getString(R.string.FechaAño));

        etConfNombre.setText(parametros.getString(getResources().getString(R.string.Nombre)));
        etConfFecha.setText(dia + "/" + (mes + 1) + "/" + año );
        etConfTelefono.setText(parametros.getString(getResources().getString(R.string.Telefono)));
        etConfEmail.setText(parametros.getString(getResources().getString(R.string.Email)));
        etConfDescripcion.setText(parametros.getString(getResources().getString(R.string.Descripcion)));

    }

    public void onClickBtnEditar(View view) {
        Intent intent = new Intent(ConfirmarDatos.this, MainActivity.class);

        intent.putExtra(getResources().getString(R.string.ConfNombre), etConfNombre.getText().toString());
        intent.putExtra(getResources().getString(R.string.ConfFechaDia), dia);
        intent.putExtra(getResources().getString(R.string.ConfFechaMes), mes);
        intent.putExtra(getResources().getString(R.string.ConfFechaAño), año);
        intent.putExtra(getResources().getString(R.string.ConfTelefono), etConfTelefono.getText().toString());
        intent.putExtra(getResources().getString(R.string.ConfEmail), etConfEmail.getText().toString());
        intent.putExtra(getResources().getString(R.string.ConfDescripcion), etConfDescripcion.getText().toString());

        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ConfirmarDatos.this, MainActivity.class);

            startActivity(intent);
            finish();
        }

        return super.onKeyDown(keyCode, event);
    }
}
