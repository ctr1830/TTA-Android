package Business;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import Data.Test;
import Data.User;

/**
 * Created by cristian on 13/01/17.
 */

public class ObtencionDatos {

    private RestClient cliente= new RestClient("http://u017633.ehu.eus:28080/ServidorTta/rest/tta");
    private User data;
    private String dni;
    private String password;

    public ObtencionDatos(String dni,String password){
        this.dni=dni;
        this.password=password;
    }

    public User getUser() throws Exception{
        JSONObject json;

        cliente.setHttpBasicAuth(dni,password);
        json = cliente.getJson(String.format("getStatus?dni=%s",dni));

        int id=json.getInt("id");
        String username=json.getString("user");
        int lesson_number=json.getInt("lessonNumber");
        String lesson_title=json.getString("lessonTitle");
        int next_test=json.getInt("nextTest");
        int next_exercise=json.getInt("nextExercise");

        User user = new User (id,username,lesson_number,lesson_title,next_exercise,next_test);
        return user;
    }

    public Test getTest() throws Exception{
        JSONObject json;
        Test test = new Test();
        cliente.setHttpBasicAuth(dni,password);
        json = cliente.getJson("getTest?id=1");

        test.setWording(json.getString("wording"));
        JSONArray array =json.getJSONArray("choices");
        for(int i=0; i< array.length();i++){
            JSONObject item=array.getJSONObject(i);
            //pagina 16 diapositivas
            
        }


        return test;

    }
}
