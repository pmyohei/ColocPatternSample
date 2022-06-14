package com.mark.colocpatternsample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class AnimView extends View {

    /*
     *　レイアウトから生成時用
     */
    public AnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /*
     *　コードから生成時用
     */
    public AnimView(Context context) {
        super(context);
    }

    public void setColor( int color ) {

        Log.i("色設定", "setColor=" + color);

        setBackgroundColor( color );
    }

    public int getColor( int color ) {
        ColorDrawable colorDrawable = (ColorDrawable) getBackground();
        int colorInt = colorDrawable.getColor();

        Log.i("色設定", "getColor=" + colorInt);

        return colorInt;
    }
}
