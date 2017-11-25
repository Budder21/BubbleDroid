package kns.bubbledroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton playButton, helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (ImageButton)findViewById(R.id.playButton);
        playButton.setOnClickListener(e->{
                startActivity(new Intent(this, GameActivity.class));
        });

        helpButton = (ImageButton)findViewById(R.id.helpButton);
        helpButton.setOnClickListener(e->{
            startActivity(new Intent(this, HelpActivity.class));
        });
    }

}
