package com.example.rightway.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rightway.CustomTextWatcher;
import com.example.rightway.EmailValidator;
import com.example.rightway.Models.User;
import com.example.rightway.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity  {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    EditText emailEditText;
    EditText passwordEditText;
    Button authButton;
    TextView haveAnAccount;
    TextView passwordError;
    TextView emailError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        authButton = findViewById(R.id.authButton);
        haveAnAccount = findViewById(R.id.haveAnAccount);
        passwordError = findViewById(R.id.passwordError);
        emailError = findViewById(R.id.emailError);

        EditText[] edList = {emailEditText, passwordEditText};
        TextView[] errorList = {emailError, passwordError};
        CustomTextWatcher textWatcher = new CustomTextWatcher(edList, authButton, errorList);
        for (EditText editText : edList) {
            editText.addTextChangedListener(textWatcher);
            editText.setOnFocusChangeListener(textWatcher);
        }

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        authButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            EmailValidator emailValidator = new EmailValidator();
            if (!emailValidator.validateEmail(email)){
                incorrectForm("Неверный формат почты", emailError, emailEditText);
            }

            if (password.length() < 6){
                incorrectForm("Пароль должен содержать хотя бы 6 символов", passwordError, passwordEditText);
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
                    User user = new User(email, password);
                    users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener(unused -> {
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    });
                }).addOnFailureListener(e -> {
                    if (String.valueOf(e).contains("The email address is already in use")){
                        incorrectForm("Пользователь с такой почтой уже существует", emailError, emailEditText);
                    }
                });
            }
        });

        haveAnAccount.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

    }



    private void incorrectForm(String errorText, TextView errorTextView, EditText editText){
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(errorText);
        editText.setBackgroundResource(R.drawable.edit_text_error);
    }


}