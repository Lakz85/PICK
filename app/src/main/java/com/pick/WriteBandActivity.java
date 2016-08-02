package com.pick;

import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by 10 on 2016-08-01.
 */
public class WriteBandActivity extends AppCompatActivity {

    DiscreteSeekBar ageSlider;
    Display display;
    Point displaysize;
    int width;
    Paint paint;
    int point = 0;
    //Create a new bitmap that is the width of the screen
    /*Bitmap bitmap = Bitmap.createBitmap(width, 100, Bitmap.Config.ARGB_8888);
    //A new canvas to draw on.
    Canvas canvas = new Canvas(bitmap);*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_band);
        ageSlider = (DiscreteSeekBar) findViewById(R.id.ageSlider);
        ageSlider.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                return value * 10;
            }
        });
    }

}
