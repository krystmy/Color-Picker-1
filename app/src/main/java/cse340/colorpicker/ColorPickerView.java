package cse340.colorpicker;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * This is a subclass of ColorPicker, that is, this View implements a ColorPicker.
 *
 * There are several class fields, enums, callback classes, and helper functions which have
 * been implemented for you.
 *
 * PLEASE READ ColorPicker.java to learn about these.
 */
public class ColorPickerView extends ColorPicker {
    public ColorPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        // TODO
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        EssentialGeometry geometry = essentialGeometry(event);

        // switch (mState) {
        //     case START:
        //         // TODO
        //         return true;
        //     case INSIDE:
        //         // TODO
        //         return true;
        //     default:
        //         return false;
        // }

        return true;
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
        return 0f;
    }

    /**
     * Calculate the essential geometry given an event.
     *
     * @param event Motion event to compute geometry for, most likely a touch.
     * @return EssentialGeometry value.
     */
    @Override
    protected EssentialGeometry essentialGeometry(MotionEvent event) {
        // TODO
        return null;
    }
}
