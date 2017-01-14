package practicatta.eus.ehu.tta.intel.practicatta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EvaluacionTTA extends AppCompatActivity {
    public static String username= "es.tta.practicaTTA.login";
    public static String dni="";
    public static String passwd="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_tt);

        Bundle extras=getIntent().getExtras();

        username = extras.getString("username");
        dni = extras.getString("dni");
        passwd = extras.getString("passwd");

        TextView textLogin=(TextView)findViewById(R.id.tituloyusername);
        textLogin.setText("Bienvenido ".concat(username));

        TextView subtitulo=(TextView)findViewById(R.id.subtitulo);
        subtitulo.setText("Leccion 1: Introducción a Android");
    }

    public void nuevoTest (View view) {
        Intent nuevo_test= new Intent(this,NuevoTest.class);
        Bundle extras=new Bundle();
        extras.putString("dni",dni);
        extras.putString("passwd",passwd);
        nuevo_test.putExtras(extras);
        startActivity(nuevo_test);
    }

    public void nuevoEjercicio (View view) {
        Intent nuevo_ejercicio= new Intent(this,NuevoEjercicio.class);
        Bundle extras=new Bundle();
        extras.putString("dni",dni);
        extras.putString("passwd",passwd);
        nuevo_ejercicio.putExtras(extras);
        startActivity(nuevo_ejercicio);
    }

    public void seguimiento (View view) {
        //Intent seguimiento= new Intent(this,EvaluacionTTA.class);
        //startActivity(seguimiento);
    }
}
