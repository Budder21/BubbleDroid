package kns.bubbledroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import kns.graphics.Recources;

public class GameActivity extends AppCompatActivity {

    private PlayState play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Recources.currentActivity = this;
        setContentView(R.layout.activity_game);
        Recources.init(this);
        play = new PlayState();
        setContentView(play);
    }

    @Override
    protected void onPause() {
        super.onPause();
        play.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        play.resume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        play.onTouch(e);
        return true;
    }
}
