package com.das.kaform.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.das.kaform.R;

/**
 * Represents a field that displays a value.
 * <p/>
 * For the field value, the associated FormModel can return a string or any object. The value's {@code toString} method
 * will be used to display the value.
 */
public class ValueController extends LabeledFieldController {

    /**
     * Constructs a new instance of a value field.
     *
     * @param ctx               the Android context
     * @param name              the name of the field
     * @param labelText         the label to display beside the field. Set to {@code null} to not show a label.
     */
    public ValueController(Context ctx, String name, String labelText) {
        super(ctx, name, labelText);
    }

    @Override
    protected View createFieldView() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final TextView textView = (TextView)layoutInflater.inflate(R.layout.kaform_value_field, null);
        refresh(textView);

        return textView;
    }

    private TextView getTextView() {
        return (TextView)getView().findViewById(R.id.value_text);
    }

    private void refresh(TextView textView) {
        Object value = getModel().getValue(getName());
        textView.setText(value != null ? value.toString() : "");
    }

    public void refresh() {
        refresh(getTextView());
    }

}