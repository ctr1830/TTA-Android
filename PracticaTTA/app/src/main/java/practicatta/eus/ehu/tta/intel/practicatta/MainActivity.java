package practicatta.eus.ehu.tta.intel.practicatta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

import Data.User;
import Model.Comunicacion;
import Model.RestClient;

public class MainActivity extends AppCompatActivity {

    private User data;
    private RestClient cliente;
    private String url="http://u017633.ehu.eus:28080/ServidorTta/rest/tta";
    private String path="getStatus?dni=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login (View view) {
        Intent intent= new Intent(this,EvaluacionTTA.class);
        String username =((EditText)findViewById(R.id.usuario)).getText().toString();
        String passwd =((EditText)findViewById(R.id.contrasena)).getText().toString();

        if(authenticate(username,passwd)){
            intent.putExtra(EvaluacionTTA.EXTRA_LOGIN,username);
            startActivity(intent);
        }

    }
    public boolean authenticate(final String username, final String password){
        new Comunicacion<User>(this){
            @Override
            protected User work() throws Exception{
                cliente.setHttpBasicAuth(username,password);
                cliente.getJson()
                return
            }

            @Override
            protected void onFinish(User result) {

            }
        }.execute();
        return true;
    }
}
