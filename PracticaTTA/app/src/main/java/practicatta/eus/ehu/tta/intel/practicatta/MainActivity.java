package practicatta.eus.ehu.tta.intel.practicatta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        if(authenticate(username,passwd)){
            intent.putExtra(EvaluacionTTA.EXTRA_LOGIN,username);
            startActivity(intent);
        }

    }
    public boolean authenticate(String username,String password){

        return true;
    }
}
