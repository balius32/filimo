package com.balius.filimo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.balius.filimo.R;
import com.balius.filimo.databinding.ActivityPhoneSighnUpBinding;

public class PhoneSighnupActivity extends AppCompatActivity {
    ActivityPhoneSighnUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneSighnUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAddAccount.setOnClickListener(view -> {
            String phone = binding.edtPhone.getText().toString();

            if (phone.isEmpty()) {
                Toast.makeText(PhoneSighnupActivity.this, R.string.pls_enter_phone, Toast.LENGTH_SHORT).show();

            } else {
                Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });

        binding.imgBack.setOnClickListener(view -> finish());


    }
}