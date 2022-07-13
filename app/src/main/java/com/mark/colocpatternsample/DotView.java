package com.mark.colocpatternsample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class DotView extends View {
    private Paint mPaint;
    private int mSize;

    float mRadius =  0.0f;

    private int shadowColor = getResources().getColor( R.color.yellow );

    /*
     *　レイアウトから生成時用
     */
    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /*
     *　コードから生成時用
     */
    public DotView(Context context) {
        super(context);
        init();
    }

    public void init(){
        //初期設定
        setRandomSize();
        setRandomAlpha();

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
        mPaint.setColor( getResources().getColor( R.color.right_yellow ) );
        mPaint.setAntiAlias(true);
    }

    /*
     * サイズのランダム設定
     */
    public void setRandomAlpha(){
        Random random = new Random();
        float alpha = random.nextInt(11) / 10f;
        //Log.i("透明度ランダム", "alpha=" + alpha);
        setAlpha( alpha );
    }

    /*
     * サイズのランダム設定
     */
    public void setRandomSize(){
        Random random = new Random();
        int offset = random.nextInt(21);
        mSize = 40 + offset;
        //mSize = 10;
    }

    /*
     * サイズ設定
     */
    public void setSize(int size){
        mSize = size;
    }

    public void setColorID(int colorID){
        mPaint.setColor(getResources().getColor(colorID));
        invalidate();
    }

    public int getColorHex(){
        return mPaint.getColor();
    }
    public void setColorHex(int colorHex){
        mPaint.setColor(colorHex);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();

        //paint.setShadowLayer( (width / 4f), width / 4, getHeight() / 4, Color.RED );
        mPaint.setShadowLayer( mSize, 0, 0, shadowColor );

        //paint.setColor(getResources().getColor(R.color.mark_5));
        //canvas.drawCircle(width / 2f, getHeight() / 2f, mSize, mPaint);
        canvas.drawCircle(width / 2f, getHeight() / 2f, mSize/2, mPaint);
    }

    public void setShadow(){
        shadowColor = Color.BLUE;
        invalidate();
    }

    public void setMovex( float value ){
        setTranslationX( value );
    }

    /*
     * onMeasure
     *   本ビューへのサイズ反映を目的にオーバーライド
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //設定サイズを保持していれば、反映する
        if (mSize > 0) {
            setMeasuredDimension(mSize*10, mSize*10);
        }
    }





}
