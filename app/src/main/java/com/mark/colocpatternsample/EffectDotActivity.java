package com.mark.colocpatternsample;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.Random;

public class EffectDotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effect);

        //親レイアウト
        FrameLayout fl = findViewById(R.id.fl_root);

        //-- ランダムにいくつも生成 --//
        for( int i = 0; i < 25; i++ ){
            DotView dotView = new DotView(this);
            fl.addView( dotView );

            //座標をランダム生成
            Random random = new Random();
            int offsetX = random.nextInt(401);
            int offsetY = random.nextInt(401);
            //偶数ならマイナス設定
            if( (offsetX % 2) == 0 ){
                offsetX *= -1;
            }
            //偶数ならマイナス設定
            if( (offsetY % 2) == 0 ){
                offsetY *= -1;
            }

            //位置設定
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) dotView.getLayoutParams();
            mlp.setMargins(100 + offsetX, 200 + offsetY, mlp.rightMargin, mlp.bottomMargin);

            if( i < 10 ){
                random = new Random();
                int duration = random.nextInt( 2501 ) + 1000;
                int delay    = random.nextInt( 1001 );

                //アニメーション
                AnimatorSet tmpSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.repeat_alpha_middle);
                tmpSet.setTarget(dotView);
                tmpSet.setDuration( duration );
                tmpSet.setStartDelay( delay );
                tmpSet.start();

            } else {
                random = new Random();
                int duration = random.nextInt( 2501 ) + 1000;
                int delay    = random.nextInt( 1001 );
                int toValue  = random.nextInt( 201 );

                //アニメーション:xml
/*                AnimatorSet tmpSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.repeat_alpha_move_middle);
                tmpSet.setTarget(dotView);
                //tmpSet.setDuration( duration );
                tmpSet.setStartDelay( delay );
                tmpSet.setupEndValues();*/

                //アニメーション:インスタンス
                ValueAnimator alphaAnim = ObjectAnimator.ofFloat(dotView, "alpha", 1.0f, 0.6f);
                ValueAnimator moveAnim  = ObjectAnimator.ofFloat(dotView, "movex", toValue, 0f);
                alphaAnim.setDuration(1000);
                alphaAnim.setRepeatCount(-1);
                alphaAnim.setRepeatMode(ValueAnimator.REVERSE);
                moveAnim.setDuration(15000);
                moveAnim.setStartDelay(delay);
                moveAnim.setRepeatCount(-1);
                moveAnim.setRepeatMode(ValueAnimator.RESTART);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(alphaAnim).with(moveAnim);
                animatorSet.start();
            }
        }


/*
        View test = new View( this );
        test.setBackgroundColor(Color.WHITE);

        FrameLayout fl = findViewById(R.id.fl_root);
        fl.addView( test );

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.apprear);
        test.startAnimation(animation);
*/

        /*-- 透過してアニメーション設定 --*/
/*        View v = findViewById(R.id.v_effe);
        //v.setAlpha(0.0f);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //エフェクトアニメーション開始
                //v.setAlpha(1.0f);
                Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.apprear);
                findViewById(R.id.v_effe).startAnimation(animation);
            }
        });*/

    }
}