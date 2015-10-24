package com.das.kaform.controllers;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.das.kaform.R;
import com.das.kaform.FormElementController;
import com.das.kaform.validations.RequiredField;
import com.das.kaform.validations.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that represents a generic form field with an associated label.
 */
public abstract class LabeledFieldController extends FormElementController {
    private final String labelText;
    private View fieldView;


    /**
     * Creates a labeled field.
     *
     * @param ctx           the Android context
     * @param name          the name of the field
     * @param labelText     the label to display beside the field. If null, no label is displayed and the field will
     *                      occupy the entire length of the row.
     */
    public LabeledFieldController(Context ctx, String name, String labelText) {
        super(ctx, name);
        this.labelText = labelText;
    }

    /**
     * Returns the associated label for this field.
     *
     * @return the associated label for this field
     */
    public String getLabel() {
        return labelText;
    }

    /**
     * Returns the associated view for the field (without the label view) of this element.
     *
     * @return          the view for this element
     */
    public View getFieldView() {
        if (fieldView == null) {
            fieldView = createFieldView();
        }
        return fieldView;
    }

    /**
     * Constructs the view associated with this field without the label. It will be used to combine with the label.
     *
     * @return          the newly created view for this field
     */
    protected abstract View createFieldView();

    @Override
    protected View createView() {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.kaform_form_labeled_element, null);

        TextView label = (TextView)view.findViewById(R.id.field_label);
        if (labelText == null) {
            label.setVisibility(View.GONE);
        } else {
            label.setText(labelText);
        }

        FrameLayout container = (FrameLayout)view.findViewById(R.id.field_container);
        container.addView(getFieldView());

        return view;
    }

}