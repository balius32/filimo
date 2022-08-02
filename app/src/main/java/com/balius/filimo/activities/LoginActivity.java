package com.balius.filimo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.balius.filimo.R;
import com.balius.filimo.database.Db;
import com.balius.filimo.databinding.ActivityLoginBinding;
import com.balius.filimo.model.login.Login;
import com.balius.filimo.model.login.LoginModel;
import com.balius.filimo.webservice.IResponseListener;
import com.balius.filimo.webservice.WebserviceCaller;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    WebserviceCaller webserviceCaller;
    Db db;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        webserviceCaller = new WebserviceCaller();
        db = Db.getInstance(getApplicationContext());


        binding.imgBack.setOnClickListener(view -> finish());


        binding.btnSighnup.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PhoneSighnupActivity.class);
            startActivity(intent);
        });


        binding.btnLogin.setOnClickListener(view -> {
            binding.cardLogin.setVisibility(View.INVISIBLE);
            binding.cardCreateAccount.setVisibility(View.INVISIBLE);
            binding.progressLogin.setVisibility(View.VISIBLE);
             email = binding.edtEmail.getText().toString();
           password = binding.edtPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(LoginActivity.this, R.string.email_pass_email, Toast.LENGTH_SHORT).show();
            }else {
                login();
            }
        });



        binding.txtForgetPass.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),ForgetPassActivity.class);
            startActivity(intent);
        });
    }

    private  void login(){
        webserviceCaller.login(email, password, new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                LoginModel loginModel  = (LoginModel) responseMessage;;
                Login login = loginModel.getAllInOneVideo().get(0);

                if (login.getSuccess().equals("1")) {

                    Toast.makeText(LoginActivity.this, "login succussfuly", Toast.LENGTH_SHORT).show();
                    long result = db.iDao().addAccount(login);

                    Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                    intent.putExtra("login",login);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    binding.progressLogin.setVisibility(View.GONE);

                } else {
                    Toast.makeText(LoginActivity.this, "Login faild", Toast.LENGTH_SHORT).show();
                    finish();
                    binding.progressLogin.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(String onErrorMessage) {
                Log.e("","");

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        binding.cardLogin.setVisibility(View.VISIBLE);
        binding.cardCreateAccount.setVisibility(View.VISIBLE);

    }
}