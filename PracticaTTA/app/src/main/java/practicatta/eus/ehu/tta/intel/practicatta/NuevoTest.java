package practicatta.eus.ehu.tta.intel.practicatta;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NuevoTest extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_test);
        //opciones();

        RadioGroup grupo=(RadioGroup)findViewById(R.id.test_choices);
        int i;
        for (i=0;i<4;i++){
            RadioButton radio=new RadioButton(this);
            radio.setText("Opcion".concat(Integer.toString(i+1)));
            radio.setOnClickListener(this);
            radio.setId(i);
            grupo.addView(radio);
        }
    }

    public void opciones(){
        //Tema 3 pag 19
    }

    @Override
    public void onClick(View view) {

        findViewById(R.id.boton_enviar).setVisibility(View.VISIBLE);
    }

    public void enviar(View view){

        int correcto=3; //Opcion 4
        RadioGroup grupo=(RadioGroup)findViewById(R.id.test_choices);
        int choices=grupo.getChildCount();
        LinearLayout layout=(LinearLayout)findViewById(R.id.activity_nuevo_test);

        for(int i=0;i<choices;i++){
            grupo.getChildAt(i).setEnabled(false);
        }

        layout.removeView(findViewById(R.id.boton_enviar));

        grupo.getChildAt(correcto).setBackgroundColor(Color.GREEN);
        int eleccion=grupo.getCheckedRadioButtonId();

        if(eleccion!=correcto) {
            grupo.getChildAt(eleccion).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(), R.string.incorrecto, Toast.LENGTH_SHORT).show();

            //if(advise!=null&&!advise.isEmpty())
            findViewById(R.id.boton_ayuda).setVisibility(View.VISIBLE);
        }
        else{
            Toast.makeText(getApplicationContext(),R.string.correcto,Toast.LENGTH_SHORT).show();
        }

    }

    public void verAyuda(View view){

        LinearLayout layout=(LinearLayout)findViewById(R.id.activity_nuevo_test);
        String ayuda="<html><body>Esta es la ayuda de prueba</body></html>";
        WebView w= new WebView(this);

        w.loadData(ayuda,"text/html",null);
        w.setBackgroundColor(Color.TRANSPARENT);
        w.setLayerType(WebView.LAYER_TYPE_SOFTWARE,null);
        layout.addView(w);
    }
}
