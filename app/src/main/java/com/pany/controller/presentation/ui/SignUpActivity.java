package com.pany.controller.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.pany.controller.R;
import com.pany.controller.data.api.ApiService;
import com.pany.controller.data.api.RetrofitClient;
import com.pany.controller.data.models.SignUpRequest;
import com.pany.controller.data.models.TokenResponse;
import com.pany.controller.utils.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText usernameInput;
    private EditText passwordInput;
    private Button signUpButton;
    private TextView loginLink;

    private ApiService apiService;
    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailInput = findViewById(R.id.email_input);
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        signUpButton = findViewById(R.id.signup_button);
        loginLink = findViewById(R.id.login_link);

        apiService = RetrofitClient.getApiService(this);
        tokenManager = new TokenManager(this);

        signUpButton.setOnClickListener(v -> signUp());

        loginLink.setOnClickListener(v -> {
            finish();
        });
    }

    private void signUp() {
        String email = emailInput.getText().toString().trim();
        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        signUpButton.setEnabled(false);

        SignUpRequest request = new SignUpRequest(email, username, password);

        apiService.signUp(request).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                signUpButton.setEnabled(true);

                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getAccessToken();
                    tokenManager.saveToken(token);
                    Toast.makeText(SignUpActivity.this, "Account created!", Toast.LENGTH_SHORT).show();
                    goToMain();
                } else {
                    Toast.makeText(SignUpActivity.this, "Email or username already exists", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                signUpButton.setEnabled(true);
                Toast.makeText(SignUpActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void goToMain() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}