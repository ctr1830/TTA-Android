package Data;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by cristian on 13/01/17.
 */

public class Test implements Serializable {
    private  ArrayList<Choice> choices =new ArrayList<>();
    private String wording;

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public void setChoices(Choice choice) {
        choices.add(choice);
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }


}

