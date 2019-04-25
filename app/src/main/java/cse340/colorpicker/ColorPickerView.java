package cse340.colorpicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * This is a subclass of ColorPicker, that is, this View implements a ColorPicker.
 *
 * There are several class fields, enums, callback classes, and helper functions which have
 * been implemented for you.
 *
 * PLEASE READ ColorPicker.java to learn about these.
 */
public class ColorPickerView extends ColorPicker {
    private int alpha;
    public ColorPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCurrentColor = DEFAULT_COLOR;
        mState = State.START;
        alpha = 255;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint1 = new Paint();

        paint1.setColor(mCurrentColor);
        canvas.drawCircle(mCenterX,mCenterY, mRadius-mRadius*RADIUS_TO_THUMB_RATIO*2, paint1);
        float centerX =  (float) (mCenterX + (mRadius - mRadius*RADIUS_TO_THUMB_RATIO)*Math.cos(getAngleFromColor(mCurrentColor)));
        float centerY =  (float) (mCenterY + (mRadius - mRadius*RADIUS_TO_THUMB_RATIO)*Math.sin(getAngleFromColor(mCurrentColor)));
        paint1.setColor(Color.WHITE);
        paint1.setAlpha(alpha);
        canvas.drawCircle(centerX, centerY, mRadius*RADIUS_TO_THUMB_RATIO, paint1);

        // TODO
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mCenterY = (float) getMeasuredHeight() / 2;
        mCenterX = (float) getMeasuredWidth() / 2;
        mRadius = mCenterY;

        // TODO;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        EssentialGeometry geometry = essentialGeometry(event);
        float angle1 = getTouchAngle(event.getX(), event.getY());
        int color = getColorFromAngle(angle1);
        Log.i("colorpicker", "angle " + angle1 + " name: " + colorToString(color));


        switch (mState) {
             case START:
                 // TODO
                 if((geometry == EssentialGeometry.WHEEL) && (event.getAction() == MotionEvent.ACTION_DOWN) ) {
                     mCurrentColor = color;
                     alpha = 255;
                     invalidate();
                     mState = State.INSIDE;

                 }
                 return true;
             case INSIDE:
                 if((event.getAction() == MotionEvent.ACTION_MOVE) && (geometry == EssentialGeometry.WHEEL)) {
                     alpha = 255/2;
                     mCurrentColor = color;
                     invalidate();
                 } else if((event.getAction() == MotionEvent.ACTION_UP)) {
                     if((geometry == EssentialGeometry.WHEEL)){
                         mCurrentColor = color;
                         alpha = 255;
                         invalidate();
                         mColorListener.onColorSelected(mCurrentColor);

                     } else {
                         alpha = 255;
                         mColorListener.onColorSelected(mCurrentColor);
                         invalidate();
                     }
                 }
                 return true;
                 // TODO
             default:
                 return false;
         }
    }
    public String colorToString(@ColorInt int color) {
        return String.format("RGB #%06X", (0xFFFFFF & color));
    }
    /**
     * Converts from a color to angle on the wheel.
     *
     * @param color RGB color as integer.
     * @return Position of this color on the wheel in radians.
     * @see ColorPicker#getTouchAngle(float, float)
     */
    public float getAngleFromColor(int color) {
        float[] HSL = new float[3];
        ColorUtils.colorToHSL(color, HSL);
        // TODO: Convert hue (degrees) to angle on wheel (radians).

        return (float) Math.toRadians(HSL[0]-90);
    }

    /**
     * Calculate the essential geometry given an event.
     *
     * @param event Motion event to compute geometry for, most likely a touch.
     * @return EssentialGeometry value.
     */
    @Override
    protected EssentialGeometry essentialGeometry(MotionEvent event) {
        // TODO:
        float dist = (float) Math.sqrt(Math.pow(Math.abs(event.getX() - mCenterX), 2) + Math.pow(Math.abs(event.getY() - mCenterY), 2));
        if(dist <= mRadius) {
            return EssentialGeometry.WHEEL;
        }
        return EssentialGeometry.OUTSIDE;
    }

}
