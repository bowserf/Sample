package fr.bowser.testdrawable;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imgBase;
    private ImageView imgSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBase = (ImageView)findViewById(R.id.img_base);
        imgSecond = (ImageView)findViewById(R.id.img_second);

        findViewById(R.id.btn_change_color).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_change_color_after_mutate).setOnClickListener(mOnClickListener);
    }

    /**
     *
     */
    private void changeColorOfImgBase(){
        imgBase.getDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

        updateImgSecond();
    }

    /**
     *
     */
    private void changeColorOfImgBaseAfterMutate(){
        imgBase.getDrawable().mutate().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);

        updateImgSecond();
    }

    private void updateImgSecond(){
        imgSecond.invalidate();
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_change_color:
                    changeColorOfImgBase();
                    break;
                case R.id.btn_change_color_after_mutate:
                    changeColorOfImgBaseAfterMutate();
                    break;
            }
        }
    };
}
