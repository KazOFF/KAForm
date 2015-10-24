package com.das.kaform.validations;

import android.content.res.Resources;
import android.text.TextUtils;

/**
 * Represents a validation error where input is missing for a required field.
 */
public class RequiredField extends Validator {
    @Override
    public boolean validate(Object value) {
        if (value == null || (value instanceof String && TextUtils.isEmpty((String) value)))
            return false;
        else
            return true;
    }

    @Override
    public String getErrorMessage() {
        return "Can't be empty";
    }
}
