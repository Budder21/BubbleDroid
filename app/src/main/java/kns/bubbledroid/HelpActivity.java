package kns.bubbledroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Button helpButton = (Button)findViewById(R.id.backButton);
        helpButton.setOnClickListener(e-> {
            startActivity(new Intent(this, MainActivity.class));
        });

    }
}
