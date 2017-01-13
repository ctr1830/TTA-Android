package practicatta.eus.ehu.tta.intel.practicatta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import Data.User;
import Model.Comunicacion;
import Model.RestClient;

public class MainActivity extends AppCompatActivity {

    private User data;
    private RestClient cliente= new RestClient("http://u017633.ehu.eus:28080/ServidorTta/rest/tta");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login (View view) {
        Intent intent= new Intent(this,EvaluacionTTA.class);
        String username =((EditText)findViewById(R.id.usuario)).getText().toString();
        String passwd =((EditText)findViewById(R.id.contrasena)).getText().toString();

        authenticate(username,passwd);

    }
    public void authenticate(final String username, final String password){

        new Comunicacion<User>(this){
            @Override
            protected User work() throws Exception{
                JSONObject json;
                cliente.setHttpBasicAuth(username,password);
                json = cliente.getJson(String.format("getStatus?dni=%s",username));

                System.out.println("AKI LLEGO");

                int id=json.getInt("id");
                String username=json.getString("user");
                int lesson_number=json.getInt("lessonNumber");
                String lesson_title=json.getString("lessonTitle");
                int next_test=json.getInt("nextTest");
                int next_exercise=json.getInt("nextExercise");

                data.setId(id);
                data.setUsername(username);
                data.setLesson_number(lesson_number);
                data.setLesson_title(lesson_title);
                data.setNext_exercise(next_exercise);
                data.setNext_test(next_test);
                return data;
            }

            @Override
            protected void onFinish(User result) {
                Intent  intent =new Intent();
                intent.putExtra(EvaluacionTTA.EXTRA_LOGIN,data.getUsername());
                startActivity(intent);
            }
        }.execute();
    }
}
