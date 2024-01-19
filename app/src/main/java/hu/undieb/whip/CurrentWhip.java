package hu.undieb.whip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.window.OnBackInvokedDispatcher;

import com.bumptech.glide.Glide;

public class CurrentWhip extends AppCompatActivity implements SensorEventListener{
    private ImageView WhipImage;
    private MediaPlayer mediaPlayer;
    private Sensor acceleromater;
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_whip);
        WhipImage=findViewById(R.id.imgWhiper);
        Intent intent=getIntent();
        mediaPlayer=MediaPlayer.create(this,R.raw.whip_sound_effect);
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        acceleromater=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener((SensorEventListener) this,acceleromater,SensorManager.SENSOR_DELAY_NORMAL);

        if(null!=intent) {
            String img_url = intent.getStringExtra("whip_url");
            Glide.with(this).asBitmap().load(img_url).into(WhipImage);
        }


    }
    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            float x=event.values[0];
            float y=event.values[1];
            float z=event.values[2];
            boolean playing=false;
            if (x>10 && z>10 && playing==false){
                mediaPlayer.start();
                playing=true;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sensorManager.unregisterListener(this);
    }
}

