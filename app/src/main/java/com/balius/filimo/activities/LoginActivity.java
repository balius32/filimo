package com.balius.filimo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.balius.filimo.R;
import com.balius.filimo.database.Db;
import com.balius.filimo.databinding.ActivityLoginBinding;
import com.balius.filimo.model.login.Login;
import com.balius.filimo.model.login.LoginModel;
import com.balius.filimo.model.sighnup.SighnupModel;
import com.balius.filimo.webservice.IResponseListener;
import com.balius.filimo.webservice.WebserviceCaller;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    WebserviceCaller webserviceCaller;
    Db db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        webserviceCaller = new WebserviceCaller();
        db = Db.getInstance(getApplicationContext());


        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnSighnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();


                webserviceCaller.login(email, password, new IResponseListener() {
                    @Override
                    public void onSuccess(Object responseMessage) {
                        LoginModel loginModel = new LoginModel();
                        loginModel = (LoginModel) responseMessage;
                        List<Login> loginList = loginModel.getAllInOneVideo();
                        Login login = new Login();

                        login = loginList.get(0);

                        if (login.getSuccess().toString().equals("1")) {

                            Toast.makeText(LoginActivity.this, "login succussfuly", Toast.LENGTH_SHORT).show();
                            long result = db.iDao().addAccount(login);

                            Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                            intent.putExtra("login",login);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginActivity.this, "Login faild", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(String onErrorMessage) {
                        Log.e("","");
                    }
                });




            }
        });

        binding.txtForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ForgetPassActivity.class);
                startActivity(intent);
            }
        });
    }
}