package practicatta.eus.ehu.tta.intel.practicatta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EvaluacionTTA extends AppCompatActivity {
    public final static String EXTRA_LOGIN= "es.tta.practicaTTA.login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_tt);

        Intent intent =getIntent();
        String usuario=intent.getStringExtra(EXTRA_LOGIN);
        TextView textLogin=(TextView)findViewById(R.id.tituloyusername);
        textLogin.setText("Bienvenido ".concat(usuario));

        TextView subtitulo=(TextView)findViewById(R.id.subtitulo);
        subtitulo.setText("Leccion 1: Introducción a Android");
    }

    public void nuevoTest (View view) {
        Intent nuevotest= new Intent(this,NuevoTest.class);
        startActivity(nuevotest);
    }

    public void nuevoEjercicio (View view) {
        Intent nuevoejercicio= new Intent(this,NuevoEjercicio.class);
        startActivity(nuevoejercicio);
    }

    public void seguimiento (View view) {
        //Intent seguimiento= new Intent(this,EvaluacionTTA.class);
        //startActivity(seguimiento);
    }
}
