package com.mark.colocpatternsample;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/*
 * グラデーション用アニメーション
 */
public class TestAnimation extends Animation {

    private EffectView effectView;

    public TestAnimation(EffectView effectView) {
            /*currentPosition = heartView.getPosition();
            endPosition = pos;*/
        this.effectView = effectView;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        //interpolatedTime: 0.0f -> 1.0f
        //int pp = (int)((endPosition-currentPosition)*interpolatedTime);

        //グラデーションを設定
        effectView.setGradation( interpolatedTime );
        //heartView.requestLayout();
        effectView.invalidate();
    }

}
