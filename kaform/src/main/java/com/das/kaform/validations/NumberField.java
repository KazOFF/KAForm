package com.das.kaform.validations;

import android.content.res.Resources;
import android.text.InputType;

/**
 * Represents a validation error where input is missing for a required field.
 */
public class NumberField extends EditTextValidator {

    public NumberField(String s) {
        super();
        msg=s;
    }

    public NumberField(){
        msg = "Must be a number";
    }

    @Override
    public boolean validate(Object value) {
        try{
            double d = Double.parseDouble((String) value);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return "Must be a number";
    }

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED;
    }
}
