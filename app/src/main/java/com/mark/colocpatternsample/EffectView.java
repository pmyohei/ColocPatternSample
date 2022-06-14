package com.mark.colocpatternsample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class EffectView extends View {
    private Paint mPaint;
    private int mSize = 0;
    private float mHalfSize = 0;
    private int mColor = 0;
    private int mShadowColor;// = getResources().getColor(R.color.yellow);
    private int mEffectKind;

    /*
     *　レイアウトから生成時用
     */
    public EffectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /*
     *　コードから生成時用
     */
    public EffectView(Context context, int effectKind) {
        super(context);
        mEffectKind = effectKind;
        init();
    }

    public void init() {

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
            //※API30以下は、影の描画に必要な処理
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        //初期設定
        setRandomColor();
        setRandomSize();
        setRandomAlpha();
        setRandomRotate();

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        //Paintへの色設定
        setPaintColor();


    }

    /*
     * Paint色設定
     */
    public void setPaintColor() {

        int colorType = 2;

        switch (colorType) {
            case 0:
                //単色
                mPaint.setColor(mColor);
                break;

            case 1:
                //グラデーション：LinearGradient：直線
                Shader gradient = new LinearGradient(
                        //グラデーション開始座標
                        0, 0,
                        //グラデーション終了座標
                        0, mSize,
                        mColor, Color.WHITE, Shader.TileMode.MIRROR);
                mPaint.setShader(gradient);

                break;

            case 2:
                //グラデーション：RadialGradient：放射状
                float halfSize = mSize / 2f;

                gradient = new RadialGradient(
                        //中心座標
                        halfSize, halfSize,
                        //半径
                        halfSize / 1,
                        //Color.WHITE, getResources().getColor(R.color.pinkTest),
                        Color.WHITE, getResources().getColor(R.color.white11),
                        //Color.WHITE, Color.WHITE,
                        //Color.WHITE, mColor,
                        Shader.TileMode.CLAMP);
                mPaint.setShader(gradient);

                break;
        }

    }

    /*
     * 色のランダム設定
     */
    public void setRandomColor() {

        int color;
        int shadowColor;

        Random random = new Random();
        int i = random.nextInt(6);
        switch (i) {
            case 0:
                color = getResources().getColor(R.color.right_yellow);
                shadowColor = getResources().getColor(R.color.yellow);
                break;
            case 1:
                color = getResources().getColor(R.color.right_red);
                shadowColor = getResources().getColor(R.color.red);
                break;
            case 2:
                color = getResources().getColor(R.color.right_blue);
                shadowColor = getResources().getColor(R.color.blue);
                break;
            case 3:
                color = getResources().getColor(R.color.right_green);
                shadowColor = getResources().getColor(R.color.green);
                break;
            case 4:
                color = getResources().getColor(R.color.right_pink);
                shadowColor = getResources().getColor(R.color.pink);
                break;
            case 5:
                color = getResources().getColor(R.color.right_purple);
                shadowColor = getResources().getColor(R.color.purple);
                break;
            default:
                color = getResources().getColor(R.color.right_yellow);
                shadowColor = getResources().getColor(R.color.yellow);
                break;
        }

        mColor = color;
        mShadowColor = shadowColor;
    }

    /*
     * サイズのランダム設定
     */
    public void setRandomAlpha() {
        Random random = new Random();
        //float alpha = random.nextInt(11) / 10f;
        //float alpha = random.nextInt(4) / 10f + 0.7f;
        //float alpha = random.nextInt(4) / 10f + 0.7f;
        //Log.i("透明度ランダム", "alpha=" + alpha);
        //setAlpha(alpha);
        setAlpha(1.0f);
    }

    /*
     * サイズのランダム設定
     */
    public void setRandomSize() {
        Random random = new Random();
        int offset = random.nextInt(201);
        mSize = 10 + offset;
        //mSize = 10;
        //mSize = 200;
        mHalfSize = mSize / 2f;

        Log.i("サイズ問題", "setRandomSize width=" + mSize);
    }

    /*
     * 角度のランダム設定
     */
    public void setRandomRotate() {
        Random random = new Random();
        int rotate = random.nextInt(361);

        setRotate( rotate );

    }

    /*
     * サイズ設定
     */
    public void setSize(int size) {
        mSize = size;
    }

    public void setColorID(int colorID) {
        mPaint.setColor(getResources().getColor(colorID));
        invalidate();
    }

    public int getColorHex() {
        return mPaint.getColor();
    }

    public void setColorHex(int colorHex) {
        mPaint.setColor(colorHex);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (mSize > 0) {
            setMeasuredDimension(mSize, mSize);
        }

        //requestLayout()で再度コールされるため、ここで位置設定はしない
/*        //大きさをランダムサイズにする
        if( mSize > 0 ){
            setMeasuredDimension(mSize, mSize);

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

            Log.i("Test", "コールチェク");

            //位置設定
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) getLayoutParams();
            mlp.setMargins(400 + offsetX, 400 + offsetY, mlp.rightMargin, mlp.bottomMargin);
        }*/
    }

    /*
     * 三角形
     */
    private Path createTriangle() {

        Path path = new Path();

        path.moveTo(mSize / 2, 0);
        path.lineTo(0, mSize);
        path.lineTo(mSize, mSize);
        path.lineTo(mSize / 2, 0);
        path.close();

        return path;
    }

    /*
     * ダイア
     */
    private Path createDia() {

        Path path = new Path();

        path.moveTo(mHalfSize, 0);
        path.lineTo(0, mHalfSize);
        path.lineTo(mHalfSize, mSize);
        path.lineTo(mSize, mHalfSize);
        path.lineTo(mHalfSize, 0);
        path.close();

        return path;
    }

    /*
     * ハート1
     */
    private Path createHeart1() {

        Path path = new Path();

        path.moveTo(mHalfSize, mSize);
        path.lineTo(0, mHalfSize);
        path.cubicTo(0, 0, mHalfSize, 0, mHalfSize, mHalfSize);
        path.cubicTo(mHalfSize, 0, mSize, 0, mSize, mHalfSize);
        path.lineTo(mHalfSize, mSize);
        path.close();

        return path;
    }

    /*
     * ハート2
     */
    private Path createHeart2() {

        Path path = new Path();

        path.moveTo(mHalfSize, mSize);
        path.cubicTo(0, mHalfSize, mHalfSize / 2, 0, mHalfSize, mHalfSize);
        path.cubicTo(mHalfSize + (mHalfSize / 2), 0, mSize, mHalfSize, mHalfSize, mSize);
        path.close();

        return path;
    }

    /*
     * ハート3
     */
    private Path createHeart3() {

        Path path = new Path();

        path.moveTo(mHalfSize, mSize);
        path.cubicTo(mHalfSize * 0.66f, mHalfSize, mHalfSize * 0.33f, mSize, 0, mHalfSize);
        path.cubicTo(0, 0, mHalfSize, 0, mHalfSize, mHalfSize);
        path.cubicTo(mHalfSize, 0, mSize, 0, mSize, mHalfSize);
        path.cubicTo(mHalfSize + (mHalfSize * 0.66f), mSize, mHalfSize + (mHalfSize * 0.33f), mHalfSize, mHalfSize, mSize);
        path.close();

        return path;
    }

    /*
     * 星を作成
     */
    private Path createStar() {

        int startAngle = -90;               //90度の位置から頂点を描画
        float viewRadius = mHalfSize / 2f;  //基本半径
        float outerRadius = mHalfSize / 2f; //基本半径に加算
        float innerRadius = 0;              //基本半径から減算
        int points = 5;                     //外側の頂点数（普通の星の場合は5つ）

        //頂点の描画
        Path path = setVertex(
                points,
                startAngle,
                (viewRadius + outerRadius),
                (viewRadius - innerRadius)
        );

        return path;
    }

    /*
     * スパークル：短め
     */
    private Path createSparcleShort() {

        int startAngle = -90;                               //90度の位置から頂点を描画
        float viewRadius = mHalfSize / 4f;                  //基本半径
        float outerRadius = mHalfSize - (mHalfSize / 4f);   //基本半径に加算
        float innerRadius = 0;                              //基本半径から減算
        int points = 4;                                     //外側の頂点数（普通の星の場合は5つ）

        //頂点の描画
        Path path = setVertex(
                points,
                startAngle,
                (viewRadius + outerRadius),
                (viewRadius - innerRadius)
        );

        return path;
    }

    /*
     * スパークル：細め
     */
    private Path createSparcleShin() {

        int startAngle = -90;               //90度の位置から頂点を描画
        float viewRadius = mHalfSize / 8f;               //基本半径
        float outerRadius = mHalfSize - (mHalfSize / 8f); //基本半径に加算
        float innerRadius = 0;                          //基本半径から減算
        int points = 4;                                 //外側の頂点数（普通の星の場合は5つ）

        //頂点の描画
        Path path = setVertexShin(
                points,
                startAngle,
                (viewRadius + outerRadius),
                (viewRadius - innerRadius)
        );

        return path;
    }

    /*
     * スパークル：不規則
     */
    private Path createSparcleRandom() {

        int startAngle = -90;                               //90度の位置から頂点を描画
        float viewRadius = mSize / 8f;                      //基本半径
        float innerRadius = viewRadius * 0.6f;              //基本半径から減算
        int points = 9;                                     //外側の頂点数（普通の星の場合は5つ）

        //頂点の描画
        Path path = setVertexRandomLen(
                points,
                startAngle,
                viewRadius,
                (viewRadius - innerRadius)
        );
        return path;
    }

    /*
     * スパークル：長め
     */
    private Path createSparcleLong() {

        Path path = new Path();

        final int CENTER_CIRCLE_RADIUS = 4;
        final int POS_DIFF = CENTER_CIRCLE_RADIUS / 2;

        //中央円
        path.addCircle(mHalfSize, mHalfSize, CENTER_CIRCLE_RADIUS, Path.Direction.CW);
        //上
        path.moveTo(mHalfSize - POS_DIFF, mHalfSize);
        path.lineTo(mHalfSize, 0);
        path.lineTo(mHalfSize + POS_DIFF, mHalfSize);
        //下
        path.lineTo(mHalfSize, mSize);
        path.lineTo(mHalfSize - POS_DIFF, mHalfSize);
        path.close();
        //左
        path.moveTo(mHalfSize, mHalfSize + POS_DIFF);
        path.lineTo(0, mHalfSize);
        path.lineTo(mHalfSize, mHalfSize - POS_DIFF);
        //右
        path.lineTo(mSize, mHalfSize);
        path.lineTo(mHalfSize, mHalfSize + POS_DIFF);
        path.close();

        return path;
    }

    /*
     * スパークル：中央に円あり
     */
    private Path create2Sparcle( Canvas canvas ) {

/*
        int startAngle = -90;                               //90度の位置から頂点を描画
        float viewRadius = mHalfSize / 4f;                  //基本半径
        //float outerRadius = mHalfSize - (mHalfSize / 2f);   //基本半径に加算
        float outerRadius = mHalfSize;   //基本半径に加算
        float innerRadius = 0;                              //基本半径から減算
        int points = 8;                                     //外側の頂点数（普通の星の場合は5つ）
*/



        /*
          中心円の生成
         */
        Path circlePath = new Path();
        circlePath.addCircle(mHalfSize, mHalfSize, mHalfSize / 3, Path.Direction.CW);

        //放射状グラデーション
        Shader gradient = new RadialGradient(
                //中心座標
                mHalfSize, mHalfSize,
                //半径
                mHalfSize / 3,
                getResources().getColor(R.color.white88), getResources().getColor(R.color.white00),
                Shader.TileMode.CLAMP);

        //ペイントにグラデーションを設定
        Paint paint = new Paint();
        paint.setShader(gradient);
        paint.setAlpha( 0x99 );

        paint.setShadowLayer( mSize / 8f, 0, 0, Color.WHITE);

        //描画してsave
        canvas.drawPath(circlePath, paint);
        canvas.save();

        //頂点の描画：十字
        Path sparclePath = setVertexShin(
                4,
                -90,
                //(viewRadius + outerRadius),
                mHalfSize,
                mHalfSize * 0.06f
        );

        //ペイントにグラデーションを設定
        Paint paint2 = new Paint();
        //グラデーション：RadialGradient：放射状
        Shader gradient1 = new RadialGradient(
                //中心座標
                mHalfSize, mHalfSize,
                //半径
                mHalfSize / 1,
                Color.WHITE, getResources().getColor(R.color.white00),
                Shader.TileMode.CLAMP);
        paint2.setShader(gradient1);
        paint2.setShadowLayer( mSize / 8f, 0, 0, Color.WHITE);

        //描画後、スパークル側を復元
        canvas.drawPath(sparclePath, paint2);
        canvas.save();

        //頂点の描画：十字斜め
        Path sparclePath2 = setVertexShin(
                4,
                -45,
                //(viewRadius + outerRadius),
                mHalfSize / 2,
                mHalfSize * 0.06f
        );

        //ペイントにグラデーションを設定
        paint2 = new Paint();
        //放射状のグラデーション
        gradient1 = new RadialGradient(
                //中心座標
                mHalfSize, mHalfSize,
                //半径
                mHalfSize / 1,
                Color.WHITE, getResources().getColor(R.color.white11),
                Shader.TileMode.CLAMP);
        paint2.setShader(gradient1);

        //描画後、スパークル側を復元
        canvas.drawPath(sparclePath2, paint2);
        canvas.restore();



/*        final int CENTER_CIRCLE_RADIUS = 4;
        final int POS_DIFF = CENTER_CIRCLE_RADIUS / 2;

        //中央円
        Path path = new Path();
        path.addCircle(mHalfSize, mHalfSize, mHalfSize/2, Path.Direction.CW);

        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL);
        paint1.setColor( Color.WHITE );
        //mPaint.setShadowLayer( mSize/8f, 0, 0, Color.WHITE);

        //描画してsave
        canvas.drawPath(path, paint1);
        canvas.save();

        //中央円
        Path path2 = new Path();
        path2.addCircle(mHalfSize + 30, mHalfSize + 30, mHalfSize/2, Path.Direction.CW);
        //描画の色を変更
        paint1.setColor( Color.RED );
        //描画
        canvas.drawPath(path2, paint1);
        //saveしていた描画内容を復元
        canvas.restore();*/

        return null;
    }



    /*
     * 花びら
     */
    private Path createFlower() {

        Path path = new Path();
        float x_25 = mSize * 0.25f;
        float x_75 = mSize * 0.75f;
        float y_25 = mSize * 0.25f;
        float y_75 = mSize * 0.75f;

        path.moveTo(mHalfSize, mHalfSize);
        //上の花びら
        path.quadTo(x_25, y_25, mHalfSize, 0);       //左
        path.quadTo(x_75, y_25, mHalfSize, mHalfSize);  //右
        //左の花びら
        path.quadTo(x_25, y_25, 0, mHalfSize);       //上
        path.quadTo(x_25, y_75, mHalfSize, mHalfSize);  //下
        //下の花びら
        path.quadTo(x_25, y_75, mHalfSize, mSize);      //左
        path.quadTo(x_75, y_75, mHalfSize, mHalfSize);  //右
        //右の花びら
        path.quadTo(x_75, y_75, mSize, mHalfSize);      //下
        path.quadTo(x_75, y_25, mHalfSize, mHalfSize);  //上

        path.close();

        return path;
    }

    /*
     * 桜の花びら
     */
    private Path createSakura() {

        Path path = new Path();

        //描画の頂点
        int startAngle = -90;     //90度の位置から頂点を描画
        int points = 5;           //外側の頂点数（普通の星の場合は5つ）

        //角度差分：基準の角度に対して、各頂点の角度差分
        final int LEFT_CTRL_ANGLE = -40;
        final int RIGHT_CTRL_ANGLE = 40;
        final int CENTER_ANGLE = 0;
        final int LEFT_TIP_ANGLE = -10;
        final int RIGHT_TIP_ANGLE = 10;

        //花びらの幅
        float petalWidth = mSize / 3f;
        //花びらの幅の半分
        float halfPetalWidth = petalWidth / 2f;

        //各点の高さ情報
        float petalHeight = mHalfSize;
        float innerHeight = mHalfSize - halfPetalWidth;
        float ctrlHeight = mHalfSize / 2f;

        //描画中心座標
        float cx = mHalfSize;
        float cy = mHalfSize;

        //ビューの中心へ移動
        path.moveTo(cx, cy);
        //円の半径
        float halfPI = (float) Math.PI / 180f;

        //各頂点間の角度
        float oneAngle = 360f / points;
        for (int i = 0; i < points; i++) {
            //基準の角度
            float angle = startAngle + (oneAngle * i);
            float radians = (float) (angle * halfPI);
            //各頂点のラジアン
            float leftCtrlRadians = (float) ((angle + LEFT_CTRL_ANGLE) * halfPI);
            float rightCtrlRadians = (float) ((angle + RIGHT_CTRL_ANGLE) * halfPI);
            float centerRadians = (float) ((angle + CENTER_ANGLE) * halfPI);
            float leftTipRadians = (float) ((angle + LEFT_TIP_ANGLE) * halfPI);
            float rightTipRadians = (float) ((angle + RIGHT_TIP_ANGLE) * halfPI);

            //各高さに対して角度を反映した値を算出
            float tranceLeftCtrlX = (float) (ctrlHeight * Math.cos(leftCtrlRadians));
            float tranceLeftCtrlY = (float) (ctrlHeight * Math.sin(leftCtrlRadians));
            float tranceRightCtrlX = (float) (ctrlHeight * Math.cos(rightCtrlRadians));
            float tranceRightCtrlY = (float) (ctrlHeight * Math.sin(rightCtrlRadians));
            float tranceInnerX = (float) (innerHeight * Math.cos(centerRadians));
            float tranceInnerY = (float) (innerHeight * Math.sin(centerRadians));
            float tranceLeftTipX = (float) (petalHeight * Math.cos(leftTipRadians));
            float tranceLeftTipY = (float) (petalHeight * Math.sin(leftTipRadians));
            float tranceRightTipX = (float) (petalHeight * Math.cos(rightTipRadians));
            float tranceRightTipY = (float) (petalHeight * Math.sin(rightTipRadians));

            //基準位置を元に座標を算出
            float leftCtrlPosX = cx + tranceLeftCtrlX;
            float leftCtrlPosY = cy + tranceLeftCtrlY;
            float rightCtrlPosX = cx + tranceRightCtrlX;
            float rightCtrlPosY = cy + tranceRightCtrlY;
            float innerPosX = cx + tranceInnerX;
            float innerPosY = cy + tranceInnerY;
            float leftTipPosX = cx + tranceLeftTipX;
            float leftTipPosY = cy + tranceLeftTipY;
            float rightTipPosX = cx + tranceRightTipX;
            float rightTipPosY = cy + tranceRightTipY;

            //花びらの左側
            path.quadTo(leftCtrlPosX, leftCtrlPosY, leftTipPosX, leftTipPosY);
            //内側の切れ込み（右）
            path.lineTo(innerPosX, innerPosY);
            //内側の切れ込み（左）
            path.lineTo(rightTipPosX, rightTipPosY);
            //花びらの右側
            path.quadTo(rightCtrlPosX, rightCtrlPosY, cx, cy);

            Log.i("変換座標", "中心 " + cx + ", " + cy);
            Log.i("変換座標", "左頂点 " + leftTipPosX + ", " + leftTipPosY);
            Log.i("変換座標", "右頂点 " + rightTipPosX + ", " + rightTipPosY);
            Log.i("変換座標", "切れ込み頂点 " + innerPosX + ", " + innerPosY);
            Log.i("変換座標", "左制御点 " + leftCtrlPosX + ", " + leftCtrlPosY);
            Log.i("変換座標", "右制御点 " + rightCtrlPosX + ", " + rightCtrlPosY);

            Log.i("角度変換の確認", "基準値=1\t角度(angle)=" + angle + "\ttestRadians=" + radians);
            Log.i("角度変換の確認", "変換結果(cos)=\t" + (1 * Math.cos(radians)));
            Log.i("角度変換の確認", "変換結果(sin)=\t" + (1 * Math.sin(radians)));
        }
        path.close();

        return path;
    }

    /*
     * 頂点に描画
     */
    private Path setVertex(
            int points,
            float startAngle,
            float outerDist,
            float innerDist) {

        //パス
        Path path = new Path();

        //中心座標
        float centerX = mHalfSize;
        float centerY = mHalfSize;

        //頂点の角度間隔
        float oneAngle = 360f / points;

        //頂点の数だけ処理
        for (int i = 0; i < points; i++) {
            float angle = startAngle + (oneAngle * i);
            float radians = (float) (angle * (Math.PI / 180f));
            float halfRadians = (float) ((angle + (oneAngle / 2f)) * (Math.PI / 180f));

            //外側の頂点
            float base = (float) (outerDist * Math.cos(radians));
            float height = (float) (outerDist * Math.sin(radians));
            float x = centerX + base;
            float y = centerY + height;

            if (i == 0) {
                //初めは移動
                path.moveTo(x, y);
            } else {
                //ライン描画
                path.lineTo(x, y);
            }

            Log.i("角度変換の確認", "角度(angle)=" + angle);
            Log.i("角度変換の確認", "変換結果=" + x + "\ty=" + y);

            //内側の情報
            base = (float) (innerDist * Math.cos(halfRadians));
            height = (float) (innerDist * Math.sin(halfRadians));
            x = centerX + base;
            y = centerY + height;

            //ライン
            path.lineTo(x, y);

            Log.i("角度変換の確認", "変換結果=" + x + "\ty=" + y);
        }

        return path;
    }

    /*
     * スパークル線設定
     *
     */
    private Path setVertexShin(
            int points,
            float startAngle,
            float outerDist,
            float innerDist) {

        //パス
        Path path = new Path();

        //中心座標
        float centerX = mHalfSize;
        float centerY = mHalfSize;

        //頂点の角度間隔
        points = 4;
        float oneAngle = 360f / points;

        //頂点の数だけ処理
        for (int i = 0; i < points; i++) {
            float angle = startAngle + (oneAngle * i);
            float radians = (float) (angle * (Math.PI / 180f));

            //頂点スキップチェック
            /*
            float drawAngle = Math.abs( angle );
            if( (drawAngle != 0) && (drawAngle != 90) && (drawAngle != 180) && (drawAngle != 270) ){
                continue;
            }*/

            //外側の頂点
            float base = (float) (outerDist * Math.cos(radians));
            float height = (float) (outerDist * Math.sin(radians));
            float x = centerX + base;
            float y = centerY + height;

            if (i == 0) {
                //初めは移動
                path.moveTo(x, y);
            } else {
                //ライン描画
                path.lineTo(x, y);
            }

            //内側の情報
            float angleIn = angle + 45 ;
            float radiansIn = (float) (angleIn * (Math.PI / 180f));
            base = (float) (innerDist * Math.cos(radiansIn));
            height = (float) (innerDist * Math.sin(radiansIn));
            x = centerX + base;
            y = centerY + height;

            //ライン
            path.lineTo(x, y);
        }

        return path;
    }

    /*
     * 頂点に描画：長さはランダム
     */
    private Path setVertexRandomLen(
            int points,
            float startAngle,
            float viewRadius,
            float innerDist ) {

        //パス
        Path path = new Path();
        //中心座標
        float centerX = mHalfSize;
        float centerY = mHalfSize;
        //頂点の角度間隔
        float oneAngle = 360f / points;

        //光の長さの範囲
        int range = (int)mHalfSize * 100;
        Random random = new Random();

        //指定頂点の数だけ繰り返し
        for (int i = 0; i < points; i++) {
            float angle = startAngle + (oneAngle * i);
            float radians = (float) (angle * (Math.PI / 180f));
            float halfRadians = (float) ( (angle + (oneAngle / 2f)) * (Math.PI / 180f));

            //光の長さをランダムに生成
            float outerRadius = random.nextInt(range + 1) / 100f;

            //外側の情報
            float base   = (float) ((viewRadius + outerRadius) * Math.cos(radians));
            float height = (float) ((viewRadius + outerRadius) * Math.sin(radians));
            float x = centerX + base;
            float y = centerY + height;

            if (i == 0) {
                //初めは移動
                path.moveTo(x, y);
            } else {
                //ライン描画
                path.lineTo(x, y);
            }

            Log.i("スパークル", "----  角度=" + angle);
            Log.i("スパークル", "頂点 x=\t" + x + "\t" + y);

            base   = (float) (innerDist * Math.cos(halfRadians));
            height = (float) (innerDist * Math.sin(halfRadians));
            x = centerX + base;
            y = centerY + height;

            //ライン
            path.lineTo(x, y);

            //内側の情報
/*            float tmpAngle = startAngle + (testAngle * i);
            float tmpRadians = (float) (tmpAngle * (Math.PI / 180f));
            base   = (float) ((viewRadius - innerRadius) * Math.cos(tmpRadians));
            height = (float) ((viewRadius - innerRadius) * Math.sin(tmpRadians));
            x = cx + base;
            y = cy + height;

            Log.i("スパークル", "内側1 x=\t" + x + "\t" + y);

            //ライン
            path.lineTo(x, y);

            //次の内側から外側への開始位置まで移動
            base   = (float) ((viewRadius - innerRadius) * Math.cos(radians));
            height = (float) ((viewRadius - innerRadius) * Math.sin(radians));
            x = cx + base;
            y = cy + height;

            Log.i("スパークル", "内側2 x=\t" + x + "\t" + y);

            //ライン
            path.lineTo(x, y);*/
        }
        //path.addCircle( halfSize, halfSize, (viewRadius - innerRadius + 5), Path.Direction.CW);
        path.close();

        return path;
    }

    /*
     * 頂点に描画
     */
    private Path setVertexBetweenShort(
            int points,
            float startAngle,
            float outerDist,
            float innerDist) {

        //パス
        Path path = new Path();

        //中心座標
        float centerX = mHalfSize;
        float centerY = mHalfSize;

        //頂点の角度間隔
        float oneAngle = 360f / points;

        //頂点の数だけ処理
        for (int i = 0; i < points; i++) {
            float angle = startAngle + (oneAngle * i);
            float radians = (float) (angle * (Math.PI / 180f));
            float halfRadians = (float) ((angle + (oneAngle / 2f)) * (Math.PI / 180f));

            //間の長さは短くする
            float outerValue;
            if( (i % 2) == 1 ){
                outerValue = outerDist / 2;
            } else {
                outerValue = outerDist;
            }

            //外側の頂点
            float base = (float) (outerValue * Math.cos(radians));
            float height = (float) (outerValue * Math.sin(radians));
            float x = centerX + base;
            float y = centerY + height;

            if (i == 0) {
                //初めは移動
                path.moveTo(x, y);
            } else {
                //ライン描画
                path.lineTo(x, y);
            }

            //内側の情報
            base = (float) (innerDist * Math.cos(halfRadians));
            height = (float) (innerDist * Math.sin(halfRadians));
            x = centerX + base;
            y = centerY + height;

            //ライン
            path.lineTo(x, y);
        }

        return path;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        Log.i("サイズ確認", "getMeasuredHeight()=" + getMeasuredHeight());
        Log.i("サイズ確認", "mSize=" + mSize);

        //mSize = 200;
        float halfSize = mSize * 0.5f;

        //Path path = new Path();
        Path path;

        switch( mEffectKind ){
            case EffectActivity.HEART1:
                path = createHeart1();
                break;

            case EffectActivity.HEART2:
                path = createHeart2();
                break;

            case EffectActivity.HEART3:
                path = createHeart3();
                break;

            case EffectActivity.TRIANGLE:
                path = createTriangle();
                break;

            case EffectActivity.DIA:
                path = createDia();
                break;

            case EffectActivity.STAR:
                path = createStar();
                break;

            case EffectActivity.SPARCLE_SHORT:
                path = createSparcleShort();
                break;

            case EffectActivity.SPARCLE_SHIN:
                path = createSparcleShin();
                break;

            case EffectActivity.SPARCLE_LONG:
                path = createSparcleLong();
                break;

            case EffectActivity.SPARCLE_RANDOM:
                path = createSparcleRandom();
                break;

            case EffectActivity.FLOWER:
                path = createFlower();
                break;

            case EffectActivity.SAKURA:
                path = createSakura();
                break;

            case EffectActivity.O2:
                path = create2Sparcle( canvas );
                break;

            default:
                path = createSakura();
                break;
        }

        //--------------
        // 共通設定
        //--------------
        if( mEffectKind == EffectActivity.O2 ){
            //canvasの処理はpath生成時に実行

        } else {

            mPaint.setStyle(Paint.Style.FILL);
            //mPaint.setStyle(Paint.Style.STROKE);
            //mPaint.setStrokeWidth( 4 );
            mPaint.setShadowLayer( mSize/8f, 0, 0, mShadowColor);
            //mPaint.setShadowLayer( 20, 0, 0, mShadowColor);

            canvas.drawPath(path, mPaint);
        }


    }

    /*
     * 回転
     */
    public void setRotate( float rotate ){
        Log.i("回転", "値=" + rotate);
        setRotation( rotate );
    }

    public void setShadow(){
        mShadowColor = Color.BLUE;
        invalidate();
    }

    public void setGradation( float process ){

        //Log.i("アニメーション処理", "時間=" + process);

        final float INTERVAL = 0.25f;
        final float SWITCH_TIME_1 = 0.25f;
        final float SWITCH_TIME_2 = 0.5f;
        final float SWITCH_TIME_3 = 0.75f;
        final float SWITCH_TIME_4 = 1.0f;

        int width = mSize;
        int height = mSize;

        float startX, startY;
        float endX, endY;
        if( process <= SWITCH_TIME_1 ){
            //-- 開始座標 --//
            //移動方向：右
            startX = width * ( process / INTERVAL );
            startY = 0;
            //-- 終了座標 --//
            //移動方向：左
            endX = width * (1 - ( process / INTERVAL ));
            endY = height;

        } else if( process <= SWITCH_TIME_2 ){
            //-- 開始座標 --//
            //移動方向：下
            startX = width;
            startY = height * ( (process - SWITCH_TIME_1) / INTERVAL);

            //-- 終了座標 --//
            //移動方向：上
            endX = 0;
            endY = height * (1 - ((process - SWITCH_TIME_1) / INTERVAL));

        } else if( process <= SWITCH_TIME_3 ){
            //-- 開始座標 --//
            //移動方向：左
            startX = width * (1 - (process - SWITCH_TIME_2) / INTERVAL);
            startY = height;
            //-- 終了座標 --//
            //移動方向：右
            endX = width * ((process - SWITCH_TIME_2) / INTERVAL);
            endY = 0;

        } else {
            //-- 開始座標 --//
            //移動方向：上
            startX = 0;
            startY = height * (1 - ( (process - SWITCH_TIME_3) / INTERVAL ));
            //-- 終了座標 --//
            //移動方向：下
            endX = width;
            endY = height * ( (process - SWITCH_TIME_3) / INTERVAL );
        }

/*        if( process > 0.5f ){
            startX = mSize;
            startY = mSize;
            endX = 0;
            endY = 0;
        } else {
            return;
        }*/

        //Log.i("アニメーション処理", "グラデーション設定=" + process + " start=" + startX + ", "+ startY);
        //Log.i("アニメーション処理", "グラデーション設定=" + process + " end=" + endX + ", "+ endY);

        //グラデーションの設定
        Shader gradient = new LinearGradient(
                startX, startY,             //グラデーション開始座標
                endX, endY,                 //グラデーション終了座標
                Color.RED,
                Color.WHITE,
                Shader.TileMode.MIRROR);
        mPaint.setShader( gradient );
    }

    /*
     * ObjectAnimator からのコール用
     *   放物線
     */
/*    public void setParabolaPos( float value ){
        setTranslationX( value );
    }*/

}
