package Business;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import Data.Choice;
import Data.Exercise;
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
        User user = new User ();
        JSONObject json;

        cliente.setHttpBasicAuth(dni,password);
        json = cliente.getJson(String.format("getStatus?dni=%s",dni));

        user.setId(json.getInt("id"));
        user.setUsername(json.getString("user"));
        user.setLesson_number(json.getInt("lessonNumber"));
        user.setLesson_title(json.getString("lessonTitle"));
        user.setNext_test(json.getInt("nextTest"));
        user.setNext_exercise(json.getInt("nextExercise"));
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
            Choice choice =new Choice();
            choice.setId(item.getInt("id"));
            choice.setAdvise(item.getString("advise"));
            choice.setAnswer(item.getString("answer"));
            choice.setCorrect(item.getBoolean("correct"));
            if(item.optJSONObject("resourceType")!=null){
            choice.setMime(item.optJSONObject("resourceType").optString("mime",null));
            }
            test.setChoices(choice);
        }
        return test;
    }

    public Exercise getEjercicio() throws Exception{
        JSONObject json;
        Exercise ejercicio = new Exercise();
        cliente.setHttpBasicAuth(dni,password);
        json = cliente.getJson("getExercise?id=1");

        ejercicio.setId(json.getInt("id"));
        ejercicio.setWording(json.getString("wording"));
        ejercicio.setTitle(json.getJSONObject("lessonBean").getString("title"));

        return ejercicio;
    }

    public int postChoice(int userid,int choiceid) throws Exception{

        JSONObject json=new JSONObject();
        cliente.setHttpBasicAuth(dni,password);

        json.put("userId",userid);
        json.put("choiceId",choiceid);

        return cliente.postJson(json,"postChoice");
    }

    public int postFile(String path, InputStream is, String filename)throws Exception{
        cliente.setHttpBasicAuth(dni,password);

        return cliente.postFile(path,is,filename);

    }

}
