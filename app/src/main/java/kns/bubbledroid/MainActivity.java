package kns.bubbledroid;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

//TODO: actually support the options button
/**
 * The main activity of the game: when the app is launched, it goes to this activity. It shows
 * the name of the game and three buttons: play, help, and options
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The music player for the game
     */
    private static MediaPlayer backgroundMusic;

    /**
     * Sets the layout and adds listeners for the three buttons
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(backgroundMusic == null){
            backgroundMusic = MediaPlayer.create(this,R.raw.background);
            backgroundMusic.setLooping(true);
            backgroundMusic.start();
        }

        ImageButton playButton = (ImageButton)findViewById(R.id.playButton);
        playButton.setOnClickListener(e->{
                startActivity(new Intent(this, GameActivity.class));
        });

        ImageButton helpButton = (ImageButton)findViewById(R.id.helpButton);
        helpButton.setOnClickListener(e->{
            startActivity(new Intent(this, HelpActivity.class));
        });
    }

}
