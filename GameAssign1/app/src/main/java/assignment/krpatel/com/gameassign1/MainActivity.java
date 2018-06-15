package assignment.krpatel.com.gameassign1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        class RenderView extends View {
            Paint paint;
            public RenderView(Context context) {
                super(context);
                paint = new Paint();
            }
            protected void onDraw(Canvas canvas) {
                canvas.drawRGB(255, 255, 255);
                paint.setColor(Color.RED);

                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(3);
                //paint.setColor(0xff00ff00);
                canvas.drawCircle((canvas.getWidth()-70) / 2, 200, 140, paint);
                canvas.drawRect(300, 340, 700, 800, paint);
                canvas.drawLine(300, 340, 210, 650, paint);
                canvas.drawLine(700, 340, 790, 650, paint);
                canvas.drawLine(300, 800, 300, 1400, paint);
                canvas.drawLine(700, 800, 700, 1400, paint);

                paint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(((canvas.getWidth()) / 2) + 15, 150, 15, paint);
                canvas.drawCircle((canvas.getWidth())  / 2 - 75, 150, 15, paint);

                canvas.drawLine(canvas.getWidth() / 2 - 30, 200, canvas.getWidth() / 2 - 30, 250, paint);

                canvas.drawLine(canvas.getWidth() / 2 - 85, 270, canvas.getWidth() / 2 + 40, 270, paint);

                paint.setColor(0x770000ff);


                invalidate();
            }
        }
        setContentView(new RenderView(this));
    }

}
