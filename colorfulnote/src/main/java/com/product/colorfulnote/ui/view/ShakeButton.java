package com.product.colorfulnote.ui.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.animation.CycleInterpolator;
import android.widget.Button;

import com.product.colorfulnote.R;
import com.product.colorfulnote.ui.helper.ThemeHelper;
import com.product.common.utils.AnimatorUtils;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
public class ShakeButton extends Button {

    public ShakeButton(Context context) {
        super(context);
        setBackgroundResource(ThemeHelper.getInstance().getBtnColor());
    }

    public ShakeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundResource(ThemeHelper.getInstance().getBtnColor());
    }

    public ShakeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundResource(ThemeHelper.getInstance().getBtnColor());
    }

    public void shake() {
        getAnimatorSet().start();
    }

    @NonNull
    private AnimatorSet getAnimatorSet() {
        AnimatorSet set = AnimatorUtils.createAnimatorSet();
        ObjectAnimator translationX = AnimatorUtils.ofFloat(this, "translationX", 0.0f, 20.0f, 0.0f);
        ObjectAnimator translationY = AnimatorUtils.ofFloat(this, "translationY", 0, 10);
        set.setDuration(getResources().getInteger(R.integer.anim_shake_duration));
        set.setInterpolator(new CycleInterpolator(getResources().getInteger(R.integer.anim_shake_cycle_num)));
        set.play(translationX);
        // set.play(translationX).with(translationY);
        return set;
    }
}
