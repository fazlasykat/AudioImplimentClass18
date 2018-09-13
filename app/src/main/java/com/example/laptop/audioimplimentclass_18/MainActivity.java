package com.example.laptop.audioimplimentclass_18;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView startTextView, titelTextView, endTetView;
    private Button forWordButton, puseButton, backWordButton,backButton;
    private SeekBar seekBar;

    private MediaPlayer mediaPlayer;

    private double startTime = 0;
    private double endTime = 0;
    private Handler myHandler;
    private int forwardTime = 5000;
    private int rewindTime = 5000;
    private int oneTimeOnly = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        initFuncton();




    }

    public void initView(){



        startTextView = findViewById(R.id.startTextViewId);
        titelTextView = findViewById(R.id.titelTextViewId);
        endTetView = findViewById(R.id.endingTextViewId);

        forWordButton = findViewById(R.id.forwordButtonId);
        puseButton = findViewById(R.id.puseButtonId);
        backWordButton = findViewById(R.id.backWordButtonId);
        backButton = findViewById(R.id.backButtonId);

        seekBar = findViewById(R.id.seekBarId);

        myHandler = new Handler();

        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.myaudio);

        puseButton.setEnabled(false);
        seekBar.setClickable(false);
        titelTextView.setText("My Sudio.Mp3");





    }


    public void initFuncton(){

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Your Musdic Playing",Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                startTime = mediaPlayer.getCurrentPosition();
                endTime = mediaPlayer.getDuration();


                if (oneTimeOnly == 0){

                    seekBar.setMax((int)endTime);
                    oneTimeOnly = 1;


                }

                startTextView.setText(String.format("%d min, %d sec",
                        TimeUnit.MICROSECONDS.toMinutes((long)startTime),
                        TimeUnit.MICROSECONDS.toMinutes((long)startTime)-
                                TimeUnit.MINUTES.toSeconds(
                                        TimeUnit.MICROSECONDS.toMinutes((long)startTime)
                                )
                ));


                startTextView.setText(String.format("%d min, %d sec",
                        TimeUnit.MICROSECONDS.toMinutes((long)endTime),
                        TimeUnit.MICROSECONDS.toMinutes((long)endTime)-
                                TimeUnit.MINUTES.toSeconds(
                                        TimeUnit.MICROSECONDS.toMinutes((long)endTime)
                                )
                ));

                seekBar.setProgress((int)startTime);
                myHandler.postDelayed(updateSongTime, 100);
                puseButton.setEnabled(true);
            }
        });




    }


    private Runnable updateSongTime = new Runnable() {
        @Override
        public void run() {

            startTime = mediaPlayer.getCurrentPosition();
            startTextView.setText(String.format("%d min, %d sec",
                    TimeUnit.MICROSECONDS.toMinutes((long)startTime),
                    TimeUnit.MICROSECONDS.toMinutes((long)startTime)-
                            TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MICROSECONDS.toMinutes((long)startTime)
                            )
            ));

            seekBar.setProgress((int)startTime);
            myHandler.postDelayed(this,100);
        }
    };


}
