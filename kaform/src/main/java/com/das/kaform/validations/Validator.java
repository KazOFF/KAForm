package com.das.kaform.validations;

import android.content.res.Resources;

/**
 * Represents a validation error of a form's input field.
 */
public abstract class Validator {
     protected String msg;
     public abstract boolean validate(Object value);
     public abstract String getErrorMessage();
}
