package com.example.pinheiro.serfeliz;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class ResizableImageView extends android.support.v7.widget.AppCompatImageView {

    public ResizableImageView(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public ResizableImageView(Context context){
        super(context);
    }

    @Override
    protected  void onMeasure(int widthMeasureSpec ,int heightMeasureSpec){

        Drawable d =  getDrawable();
        if(d == null){

            super.setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);

            return;
        }

        int imageHeight = 220;
        int imageWidth  = d.getIntrinsicWidth();

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        float imageRatio = 0.0F;
        if (imageHeight >0){
            imageRatio = imageWidth / imageHeight;

        }
        float sizeRatio = 0.0F;
        if (imageHeight >0){
            imageRatio = imageWidth / imageHeight;
        }

        int width;
        int height;
        if (imageRatio >= sizeRatio){

            width = widthSize;
            height = width * imageHeight  / imageWidth;
        }else {

            height = heightSize;
            width = height * imageWidth / imageHeight;
        }

        setMeasuredDimension(width,height);
    }
}
