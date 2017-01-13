package Data;

/**
 * Created by cristian on 13/01/17.
 */

public class User {

    private Integer id;
    private String username;
    private Integer lesson_number;
    private String lesson_title;
    private Integer next_test;
    private Integer next_exercise;

    public User(Integer id,String username, Integer lesson_number,String lesson_title, Integer next_test, Integer next_exercise){
        this.id=id;
        this.username=username;
        this.lesson_number=lesson_number;
        this.lesson_title=lesson_title;
        this.next_test=next_test;
        this.next_exercise=next_exercise;
    }

    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public Integer getLesson_number(){
        return this.lesson_number;
    }

    public void setLesson_number(Integer lesson_number){
        this.lesson_number=lesson_number;
    }
    public String getLesson_title(){
        return this.lesson_title;
    }
    public void setLesson_title(String lesson_title){
        this.lesson_title=lesson_title;
    }
    public Integer getNext_test(){
        return this.next_test;
    }
    public void setNext_test(Integer next_test){
        this.next_test=next_test;
    }
    public Integer getNext_exercise(){
        return this.next_exercise;
    }
    public void setNext_exercise(Integer next_exercise){
        this.next_exercise=next_exercise;
    }
}

