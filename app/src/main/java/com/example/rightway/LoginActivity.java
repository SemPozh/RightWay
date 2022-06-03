package com.example.rightway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText loginEditText;
    EditText passwordEditText;
    Button authButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        authButton = findViewById(R.id.authButton);
        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        loginEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        authButton.setEnabled(false);

        EditText[] edList = {loginEditText, passwordEditText};
        CustomTextWatcher textWatcher = new CustomTextWatcher(edList, authButton);
        for (EditText editText : edList) {
            editText.addTextChangedListener(textWatcher);
            editText.setOnFocusChangeListener(textWatcher);
        }

        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}