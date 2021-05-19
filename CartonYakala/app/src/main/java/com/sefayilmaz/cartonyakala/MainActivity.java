package com.sefayilmaz.cartonyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageHelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.telephony.ims.ImsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView skoreText;
    int puan;

    ImageView imageView0;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;

    ImageView[] imageArray;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.timeText);
        skoreText = findViewById(R.id.skoreText);

        puan =0;

        imageView0 = findViewById(R.id.imageView0);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);

        imageArray = new ImageView[]{imageView0,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};

        hideImages();

        new CountDownTimer(8000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: "+ millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {

                timeText.setText("Süre Bitti :)");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                 alert.setTitle("Restart ??");
                 alert.setMessage("Emin misin ?");
                 alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {

                         Intent intent = getIntent();
                         finish();
                         startActivity(intent);

                     }
                 });
                 alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         Toast.makeText(MainActivity.this, "Oyun Bitti", Toast.LENGTH_SHORT).show();

                     }
                 });
                 alert.show();


            }
        }.start();

    }
    public void skoreArtiş(View view){

        puan++;
        skoreText.setText("puan: " + puan);

    }
    public void hideImages(){

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
}