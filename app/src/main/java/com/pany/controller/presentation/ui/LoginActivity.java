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
import com.pany.controller.data.models.SignInRequest;
import com.pany.controller.data.models.TokenResponse;
import com.pany.controller.utils.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private TextView signUpLink;

    private ApiService apiService;
    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);
        signUpLink = findViewById(R.id.signup_link);

        apiService = RetrofitClient.getApiService(this);
        tokenManager = new TokenManager(this);

        if (tokenManager.isLoggedIn()) {
            goToMain();
            return;
        }

        loginButton.setOnClickListener(v -> login());

        signUpLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

    }

    private void login() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        loginButton.setEnabled(false);

        SignInRequest request = new SignInRequest(email, password);

        apiService.signIn(request).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                loginButton.setEnabled(true);

                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getAccessToken();
                    tokenManager.saveToken(token);
                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    goToMain();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                loginButton.setEnabled(true);
                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}