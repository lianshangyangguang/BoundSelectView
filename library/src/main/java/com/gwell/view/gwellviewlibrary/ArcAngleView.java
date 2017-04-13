package com.gwell.view.gwellviewlibrary;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xiyingzhu on 2017/4/5.
 */
public class ArcAngleView extends View {
    private Context mContext;
    private Paint paint;
    private Path path;
    private float currentAngle;

    public ArcAngleView(Context context) {
        this(context, null);
    }

    public ArcAngleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        paint = new Paint();
        path = new Path();
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int mWidth = 0;
        int mHeight = 0;
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = Utils.dip2px(mContext, 90);
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = Utils.dip2px(mContext, 50);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();

        drawArc(canvas);
        drawPoint(canvas);

        canvas.restore();
        super.onDraw(canvas);
    }

    private void drawPoint(Canvas canvas) {
        paint.reset();
        path.reset();
        paint.setColor(Color.rgb(0x49,0x90,0xff));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.rotate(currentAngle, Utils.dip2px(mContext, 41.5f), Utils.dip2px(mContext, 37.75f));
        path.moveTo(Utils.dip2px(mContext, 41.5f), Utils.dip2px(mContext, 26));
        path.lineTo(Utils.dip2px(mContext, 38f), Utils.dip2px(mContext, 35));
        path.lineTo(Utils.dip2px(mContext, 45f), Utils.dip2px(mContext, 35));
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(Utils.dip2px(mContext, 41.5f), Utils.dip2px(mContext, 37.75f), Utils.dip2px(mContext, 4.25f), paint);
    }

    private void drawArc(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(Utils.dip2px(mContext, 1));
        paint.setAntiAlias(true);
        paint.setTextSize(Utils.dip2px(mContext, 9));
        canvas.drawArc(Utils.dip2px(mContext, 14), Utils.dip2px(mContext, 10), Utils.dip2px(mContext, 69), Utils.dip2px(mContext, 57), -35, -110, false, paint);
        canvas.drawText("0°", Utils.dip2px(mContext, 5), Utils.dip2px(mContext, 24), paint);
        canvas.drawText("110°", Utils.dip2px(mContext, 70), Utils.dip2px(mContext, 24), paint);
        canvas.drawLine(Utils.dip2px(mContext, 16), Utils.dip2px(mContext, 17), Utils.dip2px(mContext, 22), Utils.dip2px(mContext, 23), paint);
        canvas.drawLine(Utils.dip2px(mContext, 61), Utils.dip2px(mContext, 23), Utils.dip2px(mContext, 67), Utils.dip2px(mContext, 17), paint);
    }

    public void setAngle(float percent) {
        float angle = percent * 110;
        angle -= 55;
        float from = 0;
        if (currentAngle != 0) {
            from = currentAngle;
        }
        angle = angle > 360 ? angle % 360 : angle;
        angle = angle < -360 ? angle % 360 : angle;
        if (angle > 55) {
            angle = 55;
        } else if (angle < -55) {
            angle = -55;
        }
        ValueAnimator animator = ValueAnimator.ofFloat(from, angle);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentAngle = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(500);
        animator.start();
    }
}