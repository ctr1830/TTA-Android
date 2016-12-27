package practicatta.eus.ehu.tta.intel.practicatta;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
    private String[] ayuda = {"<html><body>Esta es la ayuda de prueba</body></html>", "video", "audio"};
    private String[] mime = {"text/html", "video/mp4", "audio/mpeg"};

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
                switch (eleccion) {
                    case 0:
                        layout.removeView(findViewById(R.id.boton_video));
                        layout.removeView(findViewById(R.id.boton_audio));
                        findViewById(R.id.boton_html).setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        layout.removeView(findViewById(R.id.boton_html));
                        layout.removeView(findViewById(R.id.boton_audio));
                        findViewById(R.id.boton_video).setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        layout.removeView(findViewById(R.id.boton_video));
                        layout.removeView(findViewById(R.id.boton_html));
                        findViewById(R.id.boton_audio).setVisibility(View.VISIBLE);
                        break;
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), R.string.correcto, Toast.LENGTH_SHORT).show();
        }

    }

    public void showHtml(View view) {

        RadioGroup grupo = (RadioGroup) findViewById(R.id.test_choices);
        int opcion = grupo.getCheckedRadioButtonId();

        if (ayuda[opcion].substring(0, 10).contains("://")) {
            Uri uri = Uri.parse(ayuda[opcion]);
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

    public void showVideo(View view) {

        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_nuevo_test);
        VideoView video = new VideoView(this);
        ViewGroup.LayoutParams parameters = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        video.setLayoutParams(parameters);

        video.setVideoPath("storage/sdcard0/Movies/video.mp4");

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

    public void showAudio(View view) {

        MediaPlayer media = new MediaPlayer();
        AudioPlayer audio = new AudioPlayer(view);

        try {
            audio.setAudioUri(Uri.parse("storage/sdcard0/Music/audio.mp3"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        audio.onPrepared(media);
        audio.start();


        /*
        try {
            media.setDataSource("storage/sdcard0/Music/audio.mp3");
        }catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            media.prepare();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        media.start();
    }*/
    }
}
