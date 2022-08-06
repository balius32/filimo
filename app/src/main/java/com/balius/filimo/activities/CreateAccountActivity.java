package com.balius.filimo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.balius.filimo.R;
import com.balius.filimo.databinding.ActivityCreateAccountBinding;
import com.balius.filimo.model.sighnup.Sighnup;
import com.balius.filimo.model.sighnup.SighnupModel;
import com.balius.filimo.webservice.IResponseListener;
import com.balius.filimo.webservice.WebserviceCaller;

import java.util.List;
import java.util.Objects;

public class CreateAccountActivity extends AppCompatActivity {

    ActivityCreateAccountBinding binding;
    WebserviceCaller webserviceCaller;
    Bundle bundle;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        webserviceCaller = new WebserviceCaller();

        bundle = getIntent().getExtras();
        phone = bundle.getString("phone");


        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");
        binding.btnAddAccount.setTypeface(typeface);
        binding.edtEmail.setTypeface(typeface);
        binding.edtPassword.setTypeface(typeface);
        binding.edtUsername.setTypeface(typeface);


        binding.btnAddAccount.setOnClickListener(view -> add());

        binding.imgBack.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });
    }

    private void add() {
        binding.cardCreateAccount.setVisibility(View.INVISIBLE);
        binding.progressCreateAccount.setVisibility(View.VISIBLE);

        String name = Objects.requireNonNull(binding.edtUsername.getText()).toString();
        String email = Objects.requireNonNull(binding.edtEmail.getText()).toString();
        String password = Objects.requireNonNull(binding.edtPassword.getText()).toString();


        webserviceCaller.sighnup(name, email, password, phone, new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {

                if (responseMessage != null) {

                    SighnupModel sighnupModel = (SighnupModel) responseMessage;

                    Sighnup sighnupObject = sighnupModel.getAllInOneVideo().get(0);

                    if (sighnupObject.getSuccess().toString().equals("1")) {

                        Toast.makeText(CreateAccountActivity.this, R.string.account_created, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        binding.progressCreateAccount.setVisibility(View.GONE);

                    } else {
                        Toast.makeText(CreateAccountActivity.this, R.string.email_duplicate, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    binding.toolbar.setVisibility(View.GONE);
                    binding.cardCreateAccount.setVisibility(View.GONE);
                    binding.constraintNoSignal.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(String onErrorMessage) {
                Toast.makeText(CreateAccountActivity.this, R.string.accountnotcreated, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.cardCreateAccount.setVisibility(View.VISIBLE);
    }

}