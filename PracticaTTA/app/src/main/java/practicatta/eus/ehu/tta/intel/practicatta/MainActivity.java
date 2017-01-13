package practicatta.eus.ehu.tta.intel.practicatta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import Business.ObtencionDatos;
import Data.User;
import Business.Comunicacion;
import Business.RestClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login (View view) {
        Intent intent= new Intent(this,EvaluacionTTA.class);
        String username =((EditText)findViewById(R.id.usuario)).getText().toString();
        String passwd =((EditText)findViewById(R.id.contrasena)).getText().toString();

        //authenticate(username,passwd);
        authenticate("12345678A","tta");

    }
    public void authenticate(final String dni, final String pass){

        new Comunicacion<User>(this){
            @Override
            protected User work() throws Exception{
                ObtencionDatos data = new ObtencionDatos(dni,pass);
                User user= data.getUser();
                return user;
            }

            @Override
            protected void onFinish(User result) {
                Intent  intent =new Intent(MainActivity.this,EvaluacionTTA.class);
                intent.putExtra(EvaluacionTTA.EXTRA_LOGIN,result.getUsername());
                intent.putExtra(EvaluacionTTA.EXTRA_DNI,dni);
                intent.putExtra(EvaluacionTTA.EXTRA_PASSWD,pass);
                startActivity(intent);
            }
        }.execute();
    }
}
