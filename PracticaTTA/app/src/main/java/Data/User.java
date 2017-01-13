package Data;

import java.io.Serializable;

/**
 * Created by cristian on 13/01/17.
 */

public class User implements Serializable{

    private int id;
    private String username;
    private int lesson_number;
    private String lesson_title;
    private int next_test;
    private int next_exercise;

    public User(int id,String username, int lesson_number,String lesson_title, int next_test, int next_exercise){
        this.id=id;
        this.username=username;
        this.lesson_number=lesson_number;
        this.lesson_title=lesson_title;
        this.next_test=next_test;
        this.next_exercise=next_exercise;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public int getLesson_number(){
        return this.lesson_number;
    }

    public void setLesson_number(int lesson_number){
        this.lesson_number=lesson_number;
    }
    public String getLesson_title(){
        return this.lesson_title;
    }
    public void setLesson_title(String lesson_title){
        this.lesson_title=lesson_title;
    }
    public int getNext_test(){
        return this.next_test;
    }
    public void setNext_test(int next_test){
        this.next_test=next_test;
    }
    public int getNext_exercise(){
        return this.next_exercise;
    }
    public void setNext_exercise(int next_exercise){
        this.next_exercise=next_exercise;
    }
}

