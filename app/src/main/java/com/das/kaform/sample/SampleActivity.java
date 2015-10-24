package com.das.kaform.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.das.kaform.FormController;
import com.das.kaform.controllers.EditTextController;
import com.das.kaform.controllers.FormSectionController;
import com.das.kaform.controllers.SelectionController;
import com.das.kaform.validations.NumberField;
import com.das.kaform.validations.RequiredField;

import java.util.Arrays;

public class SampleActivity extends AppCompatActivity {

    FormController formController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setTitle("Simple Example");

        setupForm();
    }

    protected void setupForm() {
        formController = new FormController(this);

        FormSectionController section = new FormSectionController(this, "Personal Info");
        EditTextController et_fname = new EditTextController(this, "firstName", "First name");
        et_fname.setValidator(new NumberField("Must be a number"));
        section.addElement(et_fname);
        section.addElement(new EditTextController(this, "lastName", "Last name"));
        section.addElement(new SelectionController(this, "gender", "Gender", "Select", Arrays.asList("Male", "Female"), true));
        formController.addSection(section);

        ViewGroup containerView = (ViewGroup)findViewById(R.id.form_elements_container);
        formController.recreateViews(containerView);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_calc){
            if(formController.isValidInput()){

            }else{
                Toast.makeText(this,"ОШИБКА",Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
