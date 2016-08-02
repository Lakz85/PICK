package com.pick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by 10 on 2016-08-01.
 */
public class WriteIndividualActivity extends AppCompatActivity {


    DiscreteSeekBar ageSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_individual);
        ageSlider = (DiscreteSeekBar) findViewById(R.id.ageSlider);
        ageSlider.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                return value * 10;
            }
        });

    }

}
