package cse340.colorpicker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity which instantiates a ColorPicker and sets it as the content.
 *
 * Also specifies auxiliary functions, e.g., colorToString(int)
 */
@SuppressLint("Registered")
public class PickerActivity extends AppCompatActivity {
    /** This is our instantiated color picker. Subclasses such as MainActivity inherit this field */
    protected ColorPickerView colorPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set main activity as content view.
        setContentView(R.layout.activity_main);

        // Color picker exists in activity_main.xml, find it by ID.
        colorPicker = findViewById(R.id.colorPicker);


    }

    /***
     * Generates a hexadecimal representation of the color as a string.
     *
     * @param color RGB color as integer.
     * @return Hexadecimal string representing color.
     */
    public String colorToString(@ColorInt int color) {
        return String.format("RGB #%06X", (0xFFFFFF & color));
    }
}
