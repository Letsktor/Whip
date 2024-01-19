package hu.undieb.whip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private Button btnPlay;
    private ImageView imgWhip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay=findViewById(R.id.btnPlay);
        imgWhip=findViewById(R.id.imgWhip);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,CurrentWhip.class);
                intent.putExtra("whip_url","https://pngimg.com/d/whip_PNG8.png");
                startActivity(intent);
            }
        });

        Glide.with(this).load("https://pngimg.com/d/whip_PNG8.png").into(imgWhip);
    }
}