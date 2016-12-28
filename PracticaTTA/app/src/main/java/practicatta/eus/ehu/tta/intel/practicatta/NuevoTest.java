package practicatta.eus.ehu.tta.intel.practicatta;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

public class NuevoTest extends AppCompatActivity implements View.OnClickListener {
    private String[] ayuda = {"<html><body>Esta es la ayuda de prueba</body></html>", "http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4", "audio"};
    private String[] mime = {"text/html", "video/mp4", "audio/mpeg"};
//    private View v=new View(this);
//    private MediaPlayer player;
//    private MediaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_test);
        //opciones();

        RadioGroup grupo = (RadioGroup) findViewById(R.id.test_choices);
        int i;
        for (i = 0; i < 4; i++) {
            RadioButton radio = new RadioButton(this);
            radio.setText("Opcion".concat(Integer.toString(i + 1)));
            radio.setOnClickListener(this);
            radio.setId(i);
            grupo.addView(radio);
        }
    }

    public void opciones() {
        //Tema 3 pag 19
    }

    @Override
    public void onClick(View view) {

        findViewById(R.id.boton_enviar).setVisibility(View.VISIBLE);
    }

    public void enviar(View view) {

        int correcto = 3; //Opcion 4
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
            if (ayuda[eleccion] != null) {
                findViewById(R.id.boton_ayuda).setVisibility(View.VISIBLE);
            }
        } else {
            Toast.makeText(getApplicationContext(), R.string.correcto, Toast.LENGTH_SHORT).show();
        }

    }

    public void verAyuda(View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_nuevo_test);
        RadioGroup grupo = (RadioGroup) findViewById(R.id.test_choices);
        int eleccion = grupo.getCheckedRadioButtonId();
            switch (eleccion) {
                case 0:
                    showHtml(ayuda[0]);
                    break;
                case 1:
                    showVideo(ayuda[1]);
                    break;
                case 2:
                    showAudio(ayuda[2]);
                    break;
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

            w.loadData(ayuda[opcion], mime[opcion], null);
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

        //video.setVideoPath("storage/sdcard0/Movies/video.mp4");
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
/*
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_nuevo_test);
        player.setOnPreparedListener(this);
        controller=new MediaController(this){
            @Override
            public boolean dispatchKeyEvent(KeyEvent event){
                if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
                    release();
                    //onExit.run();
                }
                return super.dispatchKeyEvent(event);
            }
        };

        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            player.setDataSource(this,Uri.parse(ayuda));
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        layout.addView(v);
        player.start();
    }

    public void release(){

        if(player!=null){
            player.stop();
            player.release();
            player=null;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

        controller.setMediaPlayer(this);
        controller.setAnchorView(v);
        controller.show(0);
    }

    @Override
    public void start() {

    }

    @Override
    public void pause() {

    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

    @Override
    public void seekTo(int i) {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return false;
    }

    @Override
    public boolean canSeekBackward() {
        return false;
    }

    @Override
    public boolean canSeekForward() {
        return false;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }*/
    }
}
