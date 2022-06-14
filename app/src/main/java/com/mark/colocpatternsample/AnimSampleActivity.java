package com.mark.colocpatternsample;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;

public class AnimSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_sample);

        //色変更アニメーションの起動
        findViewById(R.id.bt_switch).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        View anim = findViewById(R.id.animColor);
                        View parent = findViewById(R.id.animparent);

                        int pettern1 = getResources().getColor( R.color.pettern1 );
                        int pettern2 = getResources().getColor( R.color.pettern2 );
                        ValueAnimator toBlue = ObjectAnimator.ofArgb(anim, "color", pettern1, pettern2);
                        ValueAnimator toRed  = ObjectAnimator.ofArgb(parent, "color", pettern2, pettern1);
                        toBlue.setDuration(500);
                        toRed.setDuration(500);

                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.play(toBlue).with(toRed);
                        animatorSet.start();

/*                        //単純な色の変更サンプル
                        int fromColor = getResources().getColor( R.color.pettern1 );
                        int toColor = getResources().getColor( R.color.pettern2 );

                        ValueAnimator animator1 = ValueAnimator.ofObject(new ArgbEvaluator(), fromColor, toColor);
                        ValueAnimator animator2 = ValueAnimator.ofObject(new ArgbEvaluator(), fromColor, toColor);
                        animator1.setDuration(1000);
                        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                anim.setBackgroundColor((int)valueAnimator.getAnimatedValue());
                                parent.setBackgroundColor((int)valueAnimator.getAnimatedValue());
                            }
                        });
                        animator1.start();*/
                    }
                }
        );


    }
}