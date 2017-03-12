package com.jesusmariagarcia.petagram2.menu;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.os.AsyncTask;
import android.widget.Toast;

import com.jesusmariagarcia.petagram2.R;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

public class DevContactActivity extends AppCompatActivity {

    TextInputEditText ietNombre;
    TextInputEditText ietEmail;
    TextInputEditText ietMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_contact);
    }



    public void onClickBtnEnviarComentario(View view) {

        ietNombre = (TextInputEditText) findViewById(R.id.ietNombre);
        ietEmail = (TextInputEditText) findViewById(R.id.ietEmail);
        ietMensaje = (TextInputEditText) findViewById(R.id.ietMensaje);

        String[] recipients = { "test@xxx" }; // Nuestra dirección de correo
        SendEmailAsyncTask email = new SendEmailAsyncTask(this);

        email.m = new Mail("user"/*user*/, "pass"/*pass*/);
        email.m.set_from(ietEmail.getText().toString());
        email.m.setBody(ietMensaje.getText().toString());
        email.m.set_to(recipients);
        email.m.set_subject("Comentario de " + ietNombre.getText().toString());
        email.execute();

        // Limpiar formulario después de enviar el correo
        ietNombre.setText("");
        ietEmail.setText("");
        ietMensaje.setText("");

    }

}

class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
    Mail m;
    private Context mContext;
    private String mAsyncTaskMsg;

    public SendEmailAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            if (m.send()) {
                mAsyncTaskMsg = "Mensaje enviado";
            } else {
                mAsyncTaskMsg = "Mensaje no enviado";
            }
            return true;

        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
            mAsyncTaskMsg = "Autenticación fallida";
            return false;
        } catch (MessagingException e) {
            e.printStackTrace();
            mAsyncTaskMsg = "Error al enviar el mensaje";
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            mAsyncTaskMsg = "Error inesperado";
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        Toast.makeText(mContext, mAsyncTaskMsg, Toast.LENGTH_SHORT).show();
    }
}