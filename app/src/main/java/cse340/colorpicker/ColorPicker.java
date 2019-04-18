package cse340.colorpicker;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * This is an abstract class which serves to provide an interface for a general ColorPicker
 * which is a view that allows users to choose colors and provides a method to register
 * event listeners.
 */
public abstract class ColorPicker extends AppCompatImageView {
    /** The default color that the ColorPicker should display when launched. */
    @ColorInt
    public static final int DEFAULT_COLOR = Color.RED;

    /**
     * The current color selected in the ColorPicker. Not necessarily the last
     * color that was sent to the listeners.
     */
    @ColorInt
    protected int mCurrentColor;

    /** Helper fields for keeping track of view geometry. */
    protected float mCenterX, mCenterY, mRadius;

    /** Ratio between width of the thumb handle and mRadius. f */
    protected static final float RADIUS_TO_THUMB_RATIO = 0.085f;

    /** Used the state to keep track of the PPS state for ColorPicker. */
    protected enum State { START, INSIDE }
    protected State mState;

    /** @see ColorPicker#essentialGeometry(MotionEvent)  */
    protected enum EssentialGeometry { WHEEL, OUTSIDE }

    /** Currently registered ColorListener instance or null. */
    @Nullable
    protected ColorListener mColorListener;

    /**
     * Class which defines a listener to be called when a new color is selected.
     */
    public interface ColorListener {
        void onColorSelected(@ColorInt int color);
    }

    /**
     * Registers a new listener for the color of this ColorPicker, or replaces
     * any existing listener.
     *
     * @param colorListener New listener or null.
     */
    public final void setColorListener(@Nullable ColorListener colorListener) {
        mColorListener = colorListener;
    }

    /**
     * Calculate the essential geometry given an event.
     *
     * @param event Motion event to compute geometry for, most likely a touch.
     * @return EssentialGeometry value.
     */
    protected abstract EssentialGeometry essentialGeometry(MotionEvent event);

    /***
     * Calculate the angle of the selection on color wheel given a touch.
     *
     * @param touchX Horizontal position of the touch event.
     * @param touchY Vertical position of the touch event.
     * @return Angle of the touch, in radians.
     */
    protected float getTouchAngle(float touchX, float touchY) {
        // NOTE: This function REQUIRES that you properly use mCenterX, mCenterY, etc.

        // Assumes (for cardinal directions on the color wheel):
        // [ E => 0, South => Pi/2, W => -Pi, N => -Pi/2 ]

        // However, you can override this function with your own angle mappings if you desire.
        // You will need to adjust getAngleFromColor and getColorFromAngle accordingly.
        return (float) Math.atan2(touchY - mCenterY, touchX - mCenterX);
    }

    /**
     * Converts an angle on the wheel to a color.
     *
     * @param angle Position on the wheel in radians.
     * @return Color corresponding to that position as RGB.
     * @see ColorPicker#getTouchAngle(float, float)
     */
    @ColorInt
    public int getColorFromAngle(double angle) {
        float hue = ((float) Math.toDegrees(angle) + 360 + 90) % 360;
        return Color.HSVToColor(new float[]{ hue, 1f, 1f });
    }

    /** From here on out, this is boilerplate. */
    public ColorPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        setImageResource(R.drawable.color_wheel);
    }
}
