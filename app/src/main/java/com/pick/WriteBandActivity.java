package com.pick;

import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by 10 on 2016-08-01.
 */
public class WriteBandActivity extends AppCompatActivity {


    //에디트 텍스트
    private EditText editText;

    //스피너에 뿌려질 Array 형식의 Data를 담은 Adapter 생성
    private Spinner mSpinner = null;
    // 아답터에 담겨질 스피너 아답터 생성
    private ArrayAdapter<String> mSpinnerAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.band_wrtie_form);

        // toolBar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.concert_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_dialog_alert);
        //뒤로가기 이미지 보내주면 바꿀것.
        //아직 뒤로가기 menifest 에도 설정 하지 않았음,
        //왜 안바뀌지;;;@_@:;;;



        //  에디트 텍스트 포커스 맞추기.
        editText = (EditText) findViewById(R.id.written);
        editText.requestFocus();


        //스피너 부분
        //장르 스피너
        mSpinner = (Spinner) findViewById(R.id.spinner_genre);
        mSpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                (String[]) getResources().getStringArray(R.array.sp_genre));
        //dropdown 모양 설정 부분.
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //스피너에 아답터를 연결
        mSpinner.setAdapter(mSpinnerAdapter);

        //지역 스피너
        mSpinner = (Spinner) findViewById(R.id.spinner_si);
        mSpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                (String[])getResources().getStringArray(R.array.city));
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mSpinnerAdapter);

        //구 스피너
        // 지역의 스피너 값에 따라 구의 내용이 바뀌어야 함,,
        mSpinner = (Spinner) findViewById(R.id.spinner_gu);
        mSpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                (String[])getResources().getStringArray(R.array.gu));
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mSpinnerAdapter);

        //지원파트
        mSpinner = (Spinner) findViewById(R.id.spinner_part);
        mSpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                (String[])getResources().getStringArray(R.array.candidate));
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mSpinnerAdapter);

        //에디트 텍스트 처음 시작되는 키보드 숨김
        InputMethodManager immhide = (InputMethodManager) getSystemService(MainActivity.INPUT_METHOD_SERVICE);
        immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);


    }

}
