package com.example.rightway;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CustomTextWatcher implements TextWatcher, View.OnFocusChangeListener {

    View v;
    EditText[] editTextList;
    TextView[] errorList;

    public CustomTextWatcher(EditText[] editTextList, View v, TextView[] errorList) {
        this.v = v;
        this.editTextList = editTextList;
        this.errorList = errorList;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        for (EditText editText : editTextList) {
            if (editText.getText().toString().trim().length() <= 0) {
                v.setEnabled(false);
                v.setBackgroundResource(R.drawable.button_inactive);
                break;
            } else{
                v.setEnabled(true);
                v.setBackgroundResource(R.drawable.button_active);
            }
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus){
            view.setBackgroundResource(R.drawable.edit_text_focused);
            if (view.getId() == R.id.emailEditText){
                ((EditText) view).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_cross, 0);
            } else {
                ((EditText) view).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0);
            }
        } else {
            view.setBackgroundResource(R.drawable.edit_text_default);
            ((EditText) view).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        }

        for (TextView textView : errorList){
            textView.setVisibility(View.INVISIBLE);
        }

    }
}
