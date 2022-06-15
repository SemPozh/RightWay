package com.example.rightway.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rightway.CustomTextWatcher;
import com.example.rightway.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    Button authButton;
    TextView authErrorTextView;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        authButton = findViewById(R.id.authButton);
        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        emailEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        authErrorTextView = findViewById(R.id.authErrorTextView);

        authButton.setEnabled(false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        FirebaseUser currentUser = auth.getCurrentUser();

        EditText[] edList = {emailEditText, passwordEditText};
        TextView[] errorList = {authErrorTextView};

        CustomTextWatcher textWatcher = new CustomTextWatcher(edList, authButton, errorList);
        for (EditText editText : edList) {
            editText.addTextChangedListener(textWatcher);
            editText.setOnFocusChangeListener(textWatcher);
        }

        authButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }).addOnFailureListener(e -> {
                authError("Неверная почта или пароль");
                Log.d("authError", e.toString());
            });
        });
    }

    private void authError(String errorText){
        authErrorTextView.setVisibility(View.VISIBLE);
        authErrorTextView.setText(errorText);
    }
}