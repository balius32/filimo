package com.balius.filimo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.balius.filimo.MainActivity;
import com.balius.filimo.R;
import com.balius.filimo.database.Db;
import com.balius.filimo.databinding.ActivityProfileBinding;
import com.balius.filimo.model.login.Login;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    Bundle bundle;
    Login login;
    Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = Db.getInstance(getApplicationContext());

        bundle = getIntent().getExtras();
        login = bundle.getParcelable("login");

        binding.txtName.setText(login.getName());

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        binding.cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.iDao().logout(login.getUserId());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });



    }
}