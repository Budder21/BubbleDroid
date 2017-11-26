package kns.bubbledroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * @author Calvin
 * This is the game over screen the user sees upon completing the came. It shows a 'Try Again'
 * button and a 'Home' button
 */
public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Button tryAgainButton = (Button)findViewById(R.id.Game_Over_Replay_Button);
        tryAgainButton.setOnClickListener(e->{
            System.out.println("New Game");
            startActivity(new Intent(this, GameActivity.class));
        });
        Button homeButton = (Button)findViewById(R.id.Game_Over_Home_Button);
        tryAgainButton.setOnClickListener(e->{
            System.out.println("Home Menu");
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}
