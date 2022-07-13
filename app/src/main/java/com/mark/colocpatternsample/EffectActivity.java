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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
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
            int bottom = fl.getHeight();
            bottom -= 100;

            //-- ランダムにいくつも生成 --//
            for( int i = 0; i < 16; i++ ){
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
                int viewX = centerX + offsetX;
                int viewY = centerY + offsetY;
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) effectView.getLayoutParams();
                mlp.setMargins(viewX, viewY, mlp.rightMargin, mlp.bottomMargin);
                //mlp.setMargins(centerX, bottom, mlp.rightMargin, mlp.bottomMargin);
                //mlp.setMargins(100 + 300, 200 + 300, mlp.rightMargin, mlp.bottomMargin);
                //Log.i("サイズ問題", "ｘ=" + (centerX + offsetX));

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
                    //int duration  = random.nextInt( 2001 ) + 1000;
                    int duration = 3000;
                    int delay     = random.nextInt( 601 );
                    int endHeight = random.nextInt( 401 ) + 300;
                    int endCtrl = (int)(endHeight * 0.75f);
                    int halfHeight = (int)(endHeight * 0.5f);
                    int halfCtrl = (int)(halfHeight * 0.5f);

                    int xRange = random.nextInt( 81 ) + 10;

                    Path path = new Path();
                    //円弧を描くような動き
                    //path.arcTo(viewX, viewY, viewX + 40f, viewY + 800f, 90f, 180f, true);

                    //カーブ
                    path.moveTo ( centerX, bottom);
                    //path.rQuadTo ( (viewX) + 100, (viewY) + 200, (viewX) + 0 , (viewY) + 400);
                    path.quadTo ( centerX + xRange, bottom - halfCtrl, centerX, bottom - halfHeight);
                    path.quadTo ( centerX - xRange, bottom - endCtrl, centerX, bottom - endHeight);

                    //上昇
                    ObjectAnimator floatAnimator = ObjectAnimator.ofFloat(effectView, View.X, View.Y, path);
                    floatAnimator.setDuration(duration);
                    floatAnimator.setStartDelay(delay);
                    floatAnimator.setInterpolator( new AccelerateDecelerateInterpolator());
                    //floatAnimator.setInterpolator( new DecelerateInterpolator());
                    //floatAnimator.setInterpolator( new LinearInterpolator());
                    floatAnimator.start();

                    //徐々に透明化
                    ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(effectView, "alpha", 1f, 0f);
                    alphaAnimator.setDuration(duration);
                    alphaAnimator.setStartDelay(delay);
                    alphaAnimator.setInterpolator( new LinearInterpolator());
                    alphaAnimator.start();

                    //アニメータをセットして開始
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.play(floatAnimator).with(alphaAnimator);
                    animatorSet.start();
                }

                //アニメーション（ビュー内クラスを利用、曲線モーションの動作確認
                if( i < 0 ) {
                    random = new Random();
                    int duration  = random.nextInt( 2001 ) + 1000;
                    //int duration = 3000;
                    int delay     = random.nextInt( 601 );

                    //拡大縮小の繰り返し
                    ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(effectView, "scale", 1.0f,  0.4f);
                    scaleAnimator.setDuration(duration);
                    scaleAnimator.setStartDelay(delay);
                    scaleAnimator.setInterpolator( new LinearInterpolator());
                    scaleAnimator.setRepeatCount( -1 );
                    scaleAnimator.setRepeatMode( ValueAnimator.REVERSE );
                    scaleAnimator.start();

                    //アニメータをセットして開始
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.play(scaleAnimator);
                    animatorSet.start();
                }

            }
        });

    }
}