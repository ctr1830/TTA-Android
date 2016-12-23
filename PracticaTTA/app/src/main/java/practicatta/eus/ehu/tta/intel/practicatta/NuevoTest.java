package practicatta.eus.ehu.tta.intel.practicatta;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

    public void enviar(){

    }
}
