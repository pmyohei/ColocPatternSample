package com.mark.colocpatternsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private float mHSV[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View gradation1 = findViewById(R.id.gradation1);
        GradientDrawable bgDraw
                = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
                    new int[]{0xFFa18cd1,
                              0xFFfbc2eb});
        gradation1.setBackground(bgDraw);
        gradation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //エフェクトハート画面へ
                Intent intent = new Intent(MainActivity.this, EffectActivity.class);
                startActivity(intent);
            }
        });

        View gradation2 = findViewById(R.id.gradation2);
        bgDraw = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
                new int[]{0xFFa8edea,
                          0xFFfed6e3});
        gradation2.setBackground(bgDraw);
        gradation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //エフェクトドット画面へ
                Intent intent = new Intent(MainActivity.this, EffectDotActivity.class);
                startActivity(intent);
            }
        });

        View toEffect = findViewById(R.id.toEffect);
        toEffect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //エフェクト画面へ
                Intent intent = new Intent(MainActivity.this, HomeEffectActivity.class);
                startActivity(intent);
            }
        });

        View anim = findViewById(R.id.toAnim);
        anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //サンプルアニメーション画面へ
                Intent intent = new Intent(MainActivity.this, AnimSampleActivity.class);
                startActivity(intent);
            }
        });



        View basic = findViewById(R.id.basic);
        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //ランダムなHSVを取得
                mHSV = createRandomHSV();
                //背景色として設定
                view.setBackgroundColor(Color.HSVToColor(mHSV));

                //ランダム
                int pairColor = getPairColor(mHSV);
                View view2 = findViewById(R.id.random);
                view2.setBackgroundColor(pairColor);

                //補色
                pairColor = getPairComplementaryColor(mHSV);
                View complementary = findViewById(R.id.complementary);
                complementary.setBackgroundColor(pairColor);

                //4分割
                pairColor = getPairIntermediateColor(mHSV);
                View intermediate = findViewById(R.id.intermediate);
                intermediate.setBackgroundColor(pairColor);

                //3分割
                pairColor = getPairOpornentColor(mHSV);
                View opornent = findViewById(R.id.opornent);
                opornent.setBackgroundColor(pairColor);

                //15度
                pairColor = getPairAnalogyColor(mHSV);
                View analogy = findViewById(R.id.analogy);
                analogy.setBackgroundColor(pairColor);
            }
        });

        //ランダムのリスナー
        View random = findViewById(R.id.random);
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mHSV == null) {
                    return;
                }

                //配色ペアを取得
                int pairColor = getPairColor(mHSV);
                view.setBackgroundColor(pairColor);
            }
        });

        //補色のリスナー
        findViewById(R.id.complementary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mHSV == null) {
                    return;
                }

                //配色ペアを取得
                int pairColor = getPairComplementaryColor(mHSV);
                view.setBackgroundColor(pairColor);
            }
        });

        //3分割のリスナー
        findViewById(R.id.opornent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mHSV == null) {
                    return;
                }

                //配色ペアを取得
                int pairColor = getPairOpornentColor(mHSV);
                view.setBackgroundColor(pairColor);
            }
        });

        //4分割のリスナー
        findViewById(R.id.intermediate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mHSV == null) {
                    return;
                }

                //配色ペアを取得
                int pairColor = getPairIntermediateColor(mHSV);
                view.setBackgroundColor(pairColor);
            }
        });

        //15度のリスナー
        findViewById(R.id.analogy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mHSV == null) {
                    return;
                }

                //配色ペアを取得
                int pairColor = getPairAnalogyColor(mHSV);
                view.setBackgroundColor(pairColor);
            }
        });
    }


    /*
     * HSV値をランダムに生成
     */
    private float[] createRandomHSV() {

        float[] hsv = new float[3];

        Random randomH = new Random();
        Random randomS = new Random();
        Random randomV = new Random();
        hsv[0] = randomH.nextInt(3601) / 10f;
        //hsv[1] = randomS.nextInt(101) / 100f;
        //hsv[2] = randomV.nextInt(101) / 100f;
        //hsv[1] = 0.5f + (randomS.nextInt(51) / 100f);
        hsv[1] = (randomS.nextInt(51) / 100f);
        hsv[2] = 0.8f + (randomV.nextInt(21) / 100f);

        Log.i("createRandomHSV", "色相=" + hsv[0] + " 彩度=" + hsv[1] + " 明度=" + hsv[2]);

        return hsv;
        //return Color.HSVToColor( hsv );
    }

    /*
     * 指定されたHSV値に対する補色を取得
     */
    private int getPairColor(float[] hsv) {

        Random random = new Random();
        int kind = random.nextInt(4);

        switch ( kind ){
            case 0:
                return getPairComplementaryColor( hsv );
            case 1:
                return getPairOpornentColor( hsv );
            case 2:
                return getPairIntermediateColor( hsv );
            case 3:
                return getPairAnalogyColor( hsv );
            default:
                return getPairIntermediateColor( hsv );
        }
    }

    /*
     * 指定されたHSV値に対する補色を取得
     */
    private int getPairComplementaryColor(float[] hsv) {

        float[] pairHsv = new float[3];

        //補色の色相を取得
        if (hsv[0] + 180f > 360f) {
            pairHsv[0] = hsv[0] - 180f;
        } else {
            pairHsv[0] = hsv[0] + 180f;
        }

        //彩度／明度はランダム
        Random randomS = new Random();
        Random randomV = new Random();
        //pairHsv[1] = randomS.nextInt(101) / 100f;
        //pairHsv[2] = randomV.nextInt(101) / 100f;
        pairHsv[1] = (randomS.nextInt(51) / 100f);
        pairHsv[2] = 0.8f + (randomV.nextInt(21) / 100f);

        //ベースの色の明度が1.0未満で、ペアの明度も1.5未満なら
/*        if( (hsv[2] < 0.1) && (pairHsv[2] < 0.15) ){

            float tmp = pairHsv[2];

            //ランダムな値を明度に加算
            Random randomAdd = new Random();
            pairHsv[2] += (randomAdd.nextInt(86) / 100f) + 0.15f;

            Log.i("補色", "明度調整=" + tmp + " → " + pairHsv[2]);
        }*/

        Log.i("補色", "色相=" + pairHsv[0] + " 彩度=" + pairHsv[1] + " 明度=" + pairHsv[2]);

        return Color.HSVToColor(pairHsv);
    }

    /*
     * 指定されたHSV値に対する15度隣の色を取得
     */
    private int getPairAnalogyColor(float[] hsv ){

        float[] pairHsv = new float[3];

        //どっち側の色相取るか
        Random randomDirection = new Random();
        int dir = randomDirection.nextInt(2);

        final float RADIUS = 15f;
        if( dir == 0 ){
            pairHsv[0] = ( hsv[0] + RADIUS > 360f ? hsv[0] - RADIUS : hsv[0] + RADIUS );
        } else {
            pairHsv[0] = ( hsv[0] - RADIUS < 0f ? hsv[0] + RADIUS : hsv[0] - RADIUS );
        }

        //彩度／明度はランダム
        Random randomS = new Random();
        Random randomV = new Random();
        //pairHsv[1] = randomS.nextInt(101) / 100f;
        //pairHsv[2] = randomV.nextInt(101) / 100f;
        pairHsv[1] = (randomS.nextInt(51) / 100f);
        pairHsv[2] = 0.8f + (randomV.nextInt(21) / 100f);

        //ベースの色の明度が1.0未満で、ペアの明度も1.5未満なら
/*        if( (hsv[2] < 0.1) && (pairHsv[2] < 0.15) ){

            float tmp = pairHsv[2];

            //ランダムな値を明度に加算
            Random randomAdd = new Random();
            pairHsv[2] += (randomAdd.nextInt(86) / 100f) + 0.15f;

            Log.i("15度の隣", "明度調整=" + tmp + " → " + pairHsv[2]);
        }*/

        Log.i("15度の隣", "色相=" + pairHsv[0] + " 彩度=" + pairHsv[1] + " 明度=" + pairHsv[2]);

        return Color.HSVToColor(pairHsv);
    }

    /*
     * 指定されたHSV値に対する4等分した時の隣の色を取得
     */
    private int getPairIntermediateColor(float[] hsv ){

        float[] pairHsv = new float[3];

        //どっち側の色相取るか
        Random randomDirection = new Random();
        int dir = randomDirection.nextInt(2);

        final float RADIUS = 45f;
        if( dir == 0 ){
            pairHsv[0] = ( hsv[0] + RADIUS > 360f ? hsv[0] - RADIUS : hsv[0] + RADIUS );
        } else {
            pairHsv[0] = ( hsv[0] - RADIUS < 0f ? hsv[0] + RADIUS : hsv[0] - RADIUS );
        }

        //彩度／明度はランダム
        Random randomS = new Random();
        Random randomV = new Random();
        //pairHsv[1] = randomS.nextInt(101) / 100f;
        //pairHsv[2] = randomV.nextInt(101) / 100f;
        pairHsv[1] = (randomS.nextInt(51) / 100f);
        pairHsv[2] = 0.8f + (randomV.nextInt(21) / 100f);

        //ベースの色の明度が1.0未満で、ペアの明度も1.5未満なら
/*        if( (hsv[2] < 0.1) && (pairHsv[2] < 0.15) ){

            float tmp = pairHsv[2];

            //ランダムな値を明度に加算
            Random randomAdd = new Random();
            pairHsv[2] += (randomAdd.nextInt(86) / 100f) + 0.15f;

            Log.i("4分割の隣", "明度調整=" + tmp + " → " + pairHsv[2]);
        }*/

        Log.i("4分割の隣", "色相=" + pairHsv[0] + " 彩度=" + pairHsv[1] + " 明度=" + pairHsv[2]);

        return Color.HSVToColor(pairHsv);
    }

    /*
     * 指定されたHSV値に対する3等分した時の隣の色を取得
     */
    private int getPairOpornentColor(float[] hsv ){

        float[] pairHsv = new float[3];

        //どっち側の色相取るか
        Random randomDirection = new Random();
        int dir = randomDirection.nextInt(2);

        final float RADIUS = 120f;
        if( dir == 0 ){
            pairHsv[0] = ( hsv[0] + RADIUS > 360f ? hsv[0] - RADIUS : hsv[0] + RADIUS );
        } else {
            pairHsv[0] = ( hsv[0] - RADIUS < 0f ? hsv[0] + RADIUS : hsv[0] - RADIUS );
        }

        //彩度／明度はランダム
        Random randomS = new Random();
        Random randomV = new Random();
        //pairHsv[1] = randomS.nextInt(101) / 100f;
        //pairHsv[2] = randomV.nextInt(101) / 100f;
        pairHsv[1] = (randomS.nextInt(51) / 100f);
        pairHsv[2] = 0.8f + (randomV.nextInt(21) / 100f);

        //ベースの色の明度が1.0未満で、ペアの明度も1.5未満なら
/*        if( (hsv[2] < 0.1) && (pairHsv[2] < 0.15) ){

            float tmp = pairHsv[2];

            //ランダムな値を明度に加算
            Random randomAdd = new Random();
            pairHsv[2] += (randomAdd.nextInt(86) / 100f) + 0.15f;

            Log.i("3分割の隣", "明度調整=" + tmp + " → " + pairHsv[2]);
        }*/

        Log.i("3分割の隣", "色相=" + pairHsv[0] + " 彩度=" + pairHsv[1] + " 明度=" + pairHsv[2]);

        return Color.HSVToColor(pairHsv);
    }
    

}