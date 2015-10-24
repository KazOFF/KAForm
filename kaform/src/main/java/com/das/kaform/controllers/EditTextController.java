package com.das.kaform.controllers;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;

import com.das.kaform.validations.EditTextValidator;
import com.das.kaform.validations.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a field that allows free-form text.
 */
public class EditTextController extends LabeledFieldController {

    private final static int EDIT_TEXT_ID = 1001;

    private int inputType;
    private final String placeholder;
    private EditTextValidator validator;


    /**
     * Constructs a new instance of an edit text field.
     *
     * @param ctx           the Android context
     * @param name          the name of the field
     * @param labelText     the label to display beside the field. Set to {@code null} to not show a label.
     * @param placeholder   a placeholder text to show when the input field is empty. If null, no placeholder is displayed
     * @param inputType     the content type of the text box as a mask; possible values are defined by {@link InputType}.
     *                      For example, to enable multi-line, enable {@code InputType.TYPE_TEXT_FLAG_MULTI_LINE}.
     */
    public EditTextController(Context ctx, String name, String labelText, String placeholder, int inputType) {
        super(ctx, name, labelText);
        this.placeholder = placeholder;
        this.inputType = inputType;
    }

    /**
     * Constructs a new instance of an edit text field.
     *
     * @param ctx           the Android context
     * @param name          the name of the field
     * @param labelText     the label to display beside the field
     * @param placeholder   a placeholder text to show when the input field is empty. If null, no placeholder is displayed
     */
    public EditTextController(Context ctx, String name, String labelText, String placeholder) {
        this(ctx, name, labelText, placeholder, InputType.TYPE_CLASS_TEXT);
    }

    /**
     * Constructs a new instance of an edit text field.
     *
     * @param ctx           the Android context
     * @param name          the name of the field
     * @param labelText     the label to display beside the field
     */
    public EditTextController(Context ctx, String name, String labelText) {
        this(ctx, name, labelText, null, InputType.TYPE_CLASS_TEXT);
    }

    public EditTextController withValidator(EditTextValidator v){
        validator=v;
        return this;
    }

    /**
     * Returns the EditText view associated with this element.
     *
     * @return the EditText view associated with this element
     */
    public EditText getEditText() {
        return (EditText)getView().findViewById(EDIT_TEXT_ID);
    }

    /**
     * Returns a mask representing the content input type. Possible values are defined by {@link InputType}.
     *
     * @return a mask representing the content input type
     */
    public int getInputType() {
        return inputType;
    }

    private void setInputTypeMask(int mask, boolean enabled) {
        if (enabled) {
            inputType = inputType | mask;
        } else {
            inputType = inputType & ~mask;
        }
        if (isViewCreated()) {
            getEditText().setInputType(inputType);
        }
    }

    /**
     * Indicates whether this text box has multi-line enabled.
     *
     * @return  true if this text box has multi-line enabled, or false otherwise
     */
    public boolean isMultiLine() {
        return (inputType | InputType.TYPE_TEXT_FLAG_MULTI_LINE) != 0;
    }

    /**
     * Enables or disables multi-line input for the text field. Default is false.
     *
     * @param multiLine if true, multi-line input is allowed, otherwise, the field will only allow a single line.
     */
    public void setMultiLine(boolean multiLine) {
        setInputTypeMask(InputType.TYPE_TEXT_FLAG_MULTI_LINE, multiLine);
    }

    @Override
    protected View createFieldView() {
        final EditText editText = new EditText(getContext());
        editText.setId(EDIT_TEXT_ID);

        editText.setSingleLine(!isMultiLine());
        if (placeholder != null) {
            editText.setHint(placeholder);
        }
        editText.setInputType(inputType);
        refresh(editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                getModel().setValue(getName(), editText.getText().toString());
            }
        });

        return editText;
    }

    private void refresh(EditText editText) {
        if (getModel()==null)
            return;
        Object value = getModel().getValue(getName());
        String valueStr = value != null ? value.toString() : "";
        if (!valueStr.equals(editText.getText().toString()))
            editText.setText(valueStr);
    }

    @Override
    public void refresh() {
        refresh(getEditText());
    }

    public boolean isValidInput() {
        if(validator==null)
            return true;

        Object value = getModel().getValue(getName());
        boolean result = true;

        if(!validator.validate(value)) {
            result = false;
            getEditText().setError(validator.getErrorMessage());
        }
        return result;
    }

    public EditTextValidator getValidator(){
        return validator;
    }


    public void setValidator(EditTextValidator v){
        this.validator = v;
        getEditText().setInputType(v.getInputType());
    }
}