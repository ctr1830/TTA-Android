package Data;

import java.io.Serializable;
import java.io.StringReader;

/**
 * Created by cristian on 13/01/17.
 */

public class Test implements Serializable {
    private String wording;
    private int id;
    private String advise;
    private String answer;
    private boolean correct;
    private String mime;

  /*  public Test(String wording, int id, String advise, String answer, boolean correct, String mime){
        this.wording=wording;
        this.id=id;
        this.advise=advise;
        this.answer=answer;
        this.correct=correct;
        this.mime=mime;
    }*/

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }
}

