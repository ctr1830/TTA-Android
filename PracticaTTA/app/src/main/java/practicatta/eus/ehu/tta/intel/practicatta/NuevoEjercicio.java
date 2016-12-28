package practicatta.eus.ehu.tta.intel.practicatta;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class NuevoEjercicio extends AppCompatActivity {
    public final static int PICTURE_REQUEST_CODE=1;
    public final static int VIDEO_REQUEST_CODE=2;
    public final static int AUDIO_REQUEST_CODE=3;
    public final static int READ_REQUEST_CODE=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_ejercicio);
    }

    public void sacarFoto(View view){

        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(this,R.string.no_camera,Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null){
                File dir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                try {
                    File file = File.createTempFile("tta", ".jpg", dir);
                    Uri pictureUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
                    startActivityForResult(intent,PICTURE_REQUEST_CODE);

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(this,R.string.no_app,Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void grabarVideo(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(this,R.string.no_camera,Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent =new Intent (MediaStore.ACTION_VIDEO_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!= null)
                startActivityForResult(intent, VIDEO_REQUEST_CODE);
            else{
                Toast.makeText(this,R.string.no_app,Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void grabarAudio(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(this,R.string.no_camera,Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent (MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if(intent.resolveActivity(getPackageManager())!=null)
                startActivityForResult(intent,AUDIO_REQUEST_CODE);
            else{
                Toast.makeText(this,R.string.no_app,Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void subirFichero(View view){

        int miversion = android.os.Build.VERSION.SDK_INT;
        if (miversion >= Build.VERSION_CODES.KITKAT) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            startActivityForResult(intent, READ_REQUEST_CODE);
        }
        else{
            Toast.makeText(this,R.string.no_api,Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if( resultCode != Activity.RESULT_OK)
            return;
        switch(requestCode){
            case PICTURE_REQUEST_CODE:
                Toast.makeText(this,"foto adquirida",Toast.LENGTH_SHORT).show();
                //subirFichero(data.getData());
                break;
            case VIDEO_REQUEST_CODE:
                Toast.makeText(this,"video adquirido",Toast.LENGTH_SHORT).show();
                break;
            case AUDIO_REQUEST_CODE:
                Toast.makeText(this,"audio adquirido",Toast.LENGTH_SHORT).show();
                break;
            case READ_REQUEST_CODE:
                Toast.makeText(this,"audio adquirido",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
