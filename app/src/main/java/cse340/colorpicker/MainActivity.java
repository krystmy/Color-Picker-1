package cse340.colorpicker;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * This subclasses PickerActivity and as such is a PickerActivity. It inherits a field
 * `colorPicker` which contains a pre-ins positioned ColorPickerView. Do NOT create your
 * own ColorPickerView.
 *
 * We encourage you to read and understand PickerActivity as it is fairly simple.
 *
 * Here you will attach a ColorListener callback and add bundle support.
 */
public class MainActivity extends PickerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View colorView = findViewById(R.id.colorResult);
        final TextView labelView = findViewById(R.id.colorTextView);

        labelView.setText(colorToString(colorPicker.mCurrentColor));
        colorView.setBackgroundColor(colorPicker.mCurrentColor);
        // TODO: Register callback to update {color,label}View when color changed.
        // TIP: To get the ColorPickerView, just use `colorPicker` without qualifiers.
        // TIP: See PickerActivity#colorToString(int), which you can call without qualifiers

        setStartingColor(savedInstanceState);
    }

    /**
     * Sets the starting color of this Activity's ColorPicker.
     *
     * @param state Bundled state to extract previous color from or null for default.
     */
    private void setStartingColor(Bundle state) {
        // TODO: Set ColorPicker color from state.
        if(state == null) {
        }
        // HINT: If state == null, then there was no saved state.
        //       In this case, use ColorPicker.DEFAULT_COLOR
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // TODO: get current color and save to bundle.
    }
}
