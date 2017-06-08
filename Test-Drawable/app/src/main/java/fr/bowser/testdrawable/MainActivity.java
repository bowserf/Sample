package fr.bowser.testdrawable;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    /**
     * UI
     */
    private ImageView imgBase;
    private ImageView imgSecond;
    private Button btnApplyNewDrawable;

    /**
     * A {@link Drawable} generate from the call of newDrawable() on the drawable displayed by
     * {@link #imgBase}.
     */
    private Drawable newDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBase = (ImageView) findViewById(R.id.img_base);
        imgSecond = (ImageView) findViewById(R.id.img_second);

        findViewById(R.id.btn_change_color).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_change_color_after_mutate).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_get_new_drawable).setOnClickListener(mOnClickListener);

        btnApplyNewDrawable = (Button)findViewById(R.id.btn_apply_new_modified_drawable);
        btnApplyNewDrawable.setOnClickListener(mOnClickListener);
    }

    /**
     * We change the Drawable color of {@link #imgBase}. By doing "setColorFilter" directly on the
     * drawable, all other place where this drawable is used will be affected at the next draw of
     * the view.
     *
     * To save memory, all drawables pointing on the same resource share the same Constant state.
     * This Constant state contains, by example, the bitmap to display. Because, this state is
     * shared, it means that if we change the color of the bitmap for one view, all others views
     * will have their bitmap affected and at the next draw, we will see the modification.
     *
     * This method call updateImgSecond() to update the view and see if it has been affected by the
     * modification applied on {@link #imgBase}.
     */
    private void changeColorOfImgBase() {
        imgBase.getDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

        updateImgSecond();
    }

    /**
     * This method get the drawable used by {@link #imgBase} but we call "mutate()" on it to get a
     * drawable with a copy of the "Constant state" which contains the bitmap displayed by the view.
     * By this way, we can modify the drawable without impacting other same Drawables.
     *
     * This method call updateImgSecond() to update the view and see if it has been affected by the
     * modification applied on {@link #imgBase}.
     */
    private void changeColorOfImgBaseAfterMutate() {
        imgBase.getDrawable().mutate().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);

        updateImgSecond();
    }

    /**
     * Get a copy of the drawable used by {@link #imgBase} and change its color. By doing this, we
     * get a new drawable but it shares the constant state of the base drawable. If we modify the
     * new drawable, it won't affect {@link #imgBase} until we send the new drawable to the view.
     *
     * This method call updateImgSecond() to update the view and see that because the constant state
     * has been modified, the change impact also this view.
     */
    private void generateAndModifyNewDrawable() {
        newDrawable = imgBase.getDrawable().getConstantState().newDrawable();
        newDrawable.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        updateImgSecond();

        btnApplyNewDrawable.setEnabled(true);
    }

    /**
     * This method is only used to invalidate {@link #imgSecond}. After having applied a change on
     * {@link #imgBase} drawable, {@link #imgSecond}'s drawable has been modified by the view has
     * been redraw, so we invalidate it to see if there is a modification.
     */
    private void updateImgSecond() {
        imgSecond.invalidate();
    }

    /**
     * Apply {@link #newDrawable} to {@link #imgBase}.
     */
    private void applyNewDrawable(){
        if(newDrawable == null){
            throw new IllegalStateException("Call updateImgSecond() before calling this method");
        }
        imgBase.setImageDrawable(newDrawable);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_change_color:
                    changeColorOfImgBase();
                    break;
                case R.id.btn_change_color_after_mutate:
                    changeColorOfImgBaseAfterMutate();
                    break;
                case R.id.btn_get_new_drawable:
                    generateAndModifyNewDrawable();
                    break;
                case R.id.btn_apply_new_modified_drawable:
                    applyNewDrawable();
                    break;
            }
        }
    };

}
