package practicatta.eus.ehu.tta.intel.practicatta;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
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

    private NetworkReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    protected void onResume(){
        super.onResume();
        IntentFilter filter= new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkReceiver();
        this.registerReceiver(receiver,filter);
    }

    protected void onPause(){
        super.onPause();
        if(receiver != null){
            this.unregisterReceiver(receiver);
        }
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
                Bundle extras=new Bundle();
                extras.putString("username",result.getUsername());
                extras.putString("dni",dni);
                extras.putString("passwd",pass);
                intent.putExtras(extras);
                startActivity(intent);
            }
        }.execute();
    }
}
