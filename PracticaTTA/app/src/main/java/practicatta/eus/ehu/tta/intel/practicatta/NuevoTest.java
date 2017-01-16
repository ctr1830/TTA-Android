package practicatta.eus.ehu.tta.intel.practicatta;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import junit.runner.BaseTestRunner;

import java.io.IOException;
import java.util.ArrayList;

import Business.Comunicacion;
import Business.ObtencionDatos;
import Data.Test;

public class NuevoTest extends AppCompatActivity implements View.OnClickListener {
    public static String dni;
    public static String passwd;
    private int correcto;
    private int user_id;
    private ArrayList<String>ayuda=new ArrayList<>();
    private ArrayList<String>mime=new ArrayList<>();
    private MediaPlayer mediaPlayer=new MediaPlayer();
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_test);
        Bundle extras=getIntent().getExtras();
        dni = extras.getString("dni");
        passwd = extras.getString("passwd");
        user_id=extras.getInt("user_id");

        pedirTest();
    }

    public void enviarChoice(final int user_id, final int choice){
        new Comunicacion<Integer>(this){
            @Override
            protected Integer work() throws Exception{
                ObtencionDatos data = new ObtencionDatos(dni,passwd);
                Integer codigo=data.postChoice(user_id,choice);
                return codigo;
            }

            @Override
            protected void onFinish(Integer result) {
                System.out.println(result);
            }
        }.execute();
    }

    public void pedirTest(){
        new Comunicacion<Test>(this){
            @Override
            protected Test work() throws Exception{
                ObtencionDatos data = new ObtencionDatos(dni,passwd);
                Test test=data.getTest();
                return test;
            }

            @Override
            protected void onFinish(Test result) {
                TextView enunciado = (TextView)findViewById(R.id.test_enunciado);
                enunciado.setText(result.getWording());
                RadioGroup grupo = (RadioGroup) findViewById(R.id.test_choices);
                for (int i = 0; i < result.getChoices().size(); i++) {
                    RadioButton radio = new RadioButton(NuevoTest.this);
                    radio.setText(result.getChoices().get(i).getAnswer());
                    radio.setOnClickListener(NuevoTest.this);
                    radio.setId(i);
                    grupo.addView(radio);
                    if (result.getChoices().get(i).isCorrect()){
                        correcto=i;
                    }
                    ayuda.add(result.getChoices().get(i).getAdvise());
                    mime.add(result.getChoices().get(i).getMime());
                }
            }
        }.execute();
    }


    @Override
    public void onClick(View view) {

        findViewById(R.id.boton_enviar).setVisibility(View.VISIBLE);
    }

    public void enviar(View view) {

        RadioGroup grupo = (RadioGroup) findViewById(R.id.test_choices);
        int choices = grupo.getChildCount();
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_nuevo_test);

        for (int i = 0; i < choices; i++) {
            grupo.getChildAt(i).setEnabled(false);
        }

        layout.removeView(findViewById(R.id.boton_enviar));

        grupo.getChildAt(correcto).setBackgroundColor(Color.GREEN);
        int eleccion = grupo.getCheckedRadioButtonId();

        if (eleccion != correcto) {
            grupo.getChildAt(eleccion).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(), R.string.incorrecto, Toast.LENGTH_SHORT).show();
            enviarChoice(user_id,eleccion);
            if (ayuda.get(eleccion) != null) {
                findViewById(R.id.boton_ayuda).setVisibility(View.VISIBLE);
            }
        } else {
            Toast.makeText(getApplicationContext(), R.string.correcto, Toast.LENGTH_SHORT).show();
            enviarChoice(user_id,eleccion);
        }

    }


    public void verAyuda(View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_nuevo_test);
        RadioGroup grupo = (RadioGroup) findViewById(R.id.test_choices);
        int eleccion = grupo.getCheckedRadioButtonId();

        if(mime.get(eleccion).contains("html")){
            showHtml(ayuda.get(eleccion));
        }
        else if(mime.get(eleccion).contains("video")){
            showVideo(ayuda.get(eleccion));
        }
        else if(mime.get(eleccion).contains("audio")){
            showAudio(ayuda.get(eleccion));
        }

    }

    public void showHtml(String html) {

        RadioGroup grupo = (RadioGroup) findViewById(R.id.test_choices);
        int opcion = grupo.getCheckedRadioButtonId();

        if (html.substring(0, 10).contains("://")) {
            Uri uri = Uri.parse(html);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else {

            LinearLayout layout = (LinearLayout) findViewById(R.id.activity_nuevo_test);
            WebView w = new WebView(this);

            w.loadData(ayuda.get(opcion), mime.get(opcion), null);
            w.setBackgroundColor(Color.TRANSPARENT);
            w.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
            layout.addView(w);
        }
    }

    public void showVideo(String url) {

        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_nuevo_test);
        VideoView video = new VideoView(this);
        ViewGroup.LayoutParams parameters = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        video.setLayoutParams(parameters);

        video.setVideoURI(Uri.parse(url));

        MediaController controller = new MediaController(this) {
            @Override
            public void hide() {

            }

            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    finish();
                }
                return super.dispatchKeyEvent(event);
            }
        };

        controller.setAnchorView(video);
        video.setMediaController(controller);

        layout.addView(video);
        video.start();

    }

    public void showAudio(String ayuda) {

        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_nuevo_test);
        View v=new View(this);
        AudioPlayer audio=new AudioPlayer(v, new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });
        try {
            audio.setAudioUri(Uri.parse(ayuda));
            layout.addView(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
