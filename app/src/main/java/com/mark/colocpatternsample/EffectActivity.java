package com.mark.colocpatternsample;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;

import java.util.Random;

public class EffectActivity extends AppCompatActivity {

    static public final int HEART1 = 0;
    static public final int HEART2 = 1;
    static public final int HEART3 = 2;
    static public final int TRIANGLE = 3;
    static public final int DIA = 4;
    static public final int STAR = 5;
    static public final int SPARCLE_SHORT = 6;
    static public final int SPARCLE_SHIN = 7;
    static public final int SPARCLE_LONG = 8;
    static public final int SPARCLE_RANDOM = 9;
    static public final int FLOWER = 10;
    static public final int SAKURA = 11;
    static public final int O2 = 12;

    static String KEY_KIND = "kind";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effect_heart);

        //遷移元から種別を取得
        Intent intent = getIntent();
        int kind = intent.getIntExtra(KEY_KIND, 0);

        //親レイアウト
        FrameLayout fl = findViewById(R.id.fl_root);

        fl.post(()-> {
            Log.i("サイズ問題", "width(fl)=" + fl.getWidth());

            int centerX = fl.getWidth() / 2;
            int centerY = fl.getHeight() / 2;

            //-- ランダムにいくつも生成 --//
            for( int i = 0; i < 32; i++ ){
                EffectView effectView = new EffectView(this, kind);
                fl.addView(effectView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT );

                //この時点のサイズは０
                //Log.i("サイズ問題", "width=" + heartView.getWidth());

                effectView.post(()-> {
                    Log.i("サイズ問題", "post() width=" + effectView.getWidth());
                });

                //座標をランダム生成
                Random random = new Random();
                int offsetX = random.nextInt(centerX + 1);
                int offsetY = random.nextInt(centerY + 1);
                //偶数ならマイナス設定
                if( (offsetX % 2) == 0 ){
                    offsetX *= -1;
                }
                //偶数ならマイナス設定
                if( (offsetY % 2) == 0 ){
                    offsetY *= -1;
                }

                //位置設定
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) effectView.getLayoutParams();
                mlp.setMargins(centerX + offsetX, centerY + offsetY, mlp.rightMargin, mlp.bottomMargin);
                //mlp.setMargins(100 + 300, 200 + 300, mlp.rightMargin, mlp.bottomMargin);

                Log.i("サイズ問題", "ｘ=" + (centerX + offsetX));


                //アニメーション（xmlを利用）
                if( i < 0 ){
                    random = new Random();
                    int duration = random.nextInt( 20001 ) + 10000;
                    int delay    = random.nextInt( 21 );

                    //適用アニメーション
                    AnimatorSet tmpSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.repeat_rotate);
                    tmpSet.setTarget(effectView);
                    //tmpSet.setDuration( duration );
                    //tmpSet.setStartDelay( delay );
                    tmpSet.start();

                    //animファイルの利用
/*                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
                    heartView.startAnimation(animation);*/
                }

                //アニメーション（ビュー内クラスを利用）
                if( i < 0 ) {
                    TestAnimation animation = new TestAnimation(effectView);
                    animation.setDuration(5000);
                    animation.setRepeatCount(Animation.INFINITE);
                    animation.setRepeatMode(Animation.RESTART);
                    effectView.startAnimation(animation);
                }

                //アニメーション（ビュー内クラスを利用、曲線モーションの動作確認
                if( i < 0 ) {

                    random = new Random();
                    int duration = random.nextInt( 2001 ) + 2000;
                    int delay    = random.nextInt( 601 );

                    Path path = new Path();
                    //円弧を描くような動き
                    //path.arcTo(centerX + offsetX, centerY + offsetY, centerX + offsetX + 40f, centerY + offsetY + 800f, 90f, 180f, true);

                    //カーブ
                    //path.moveTo ( centerX + offsetX, centerY + offsetY);
                    //path.rQuadTo ( (centerX + offsetX) + 100, (centerY + offsetY) + 200, (centerX + offsetX) + 0 , (centerY + offsetY) + 400);
                    path.quadTo ( (centerX + offsetX) + 100, (centerY + offsetY) + 200, (centerX + offsetX) + 0 , (centerY + offsetY) + 400);

                    ObjectAnimator animator = ObjectAnimator.ofFloat(effectView, View.X, View.Y, path);
                    animator.setDuration(duration);
                    //animator.setStartDelay(delay);
                    animator.setInterpolator( new DecelerateInterpolator());
                    animator.start();

                }
            }
        });



    }
}