package kns.bubbledroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Paint paint = new Paint();
        paint.setColor(0);
        Bitmap bitmap = Bitmap.createBitmap(480,800,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(50,50,200,200,paint);
        LinearLayout layout = (LinearLayout) findViewById(R.id.all);
        layout.setBackgroundDrawable(new BitmapDrawable(bitmap));
    }
}
