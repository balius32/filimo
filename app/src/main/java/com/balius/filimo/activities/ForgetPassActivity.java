package com.balius.filimo.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.balius.filimo.MainActivity;
import com.balius.filimo.R;
import com.balius.filimo.databinding.ActivityForgetPassBinding;
import com.balius.filimo.model.sighnup.Sighnup;
import com.balius.filimo.model.sighnup.SighnupModel;
import com.balius.filimo.webservice.IResponseListener;
import com.balius.filimo.webservice.WebserviceCaller;

import java.util.List;

public class ForgetPassActivity extends AppCompatActivity {

    ActivityForgetPassBinding binding;
    WebserviceCaller webserviceCaller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        webserviceCaller = new WebserviceCaller();

        binding.imgBack.setOnClickListener(view -> finish());

        binding.btnForgetPass.setOnClickListener(view ->
                webserviceCaller.forgetPass(binding.edtEmail.getText().toString(), new IResponseListener() {
                    @Override
                    public void onSuccess(Object responseMessage) {
                        SighnupModel sighnupModel = (SighnupModel) responseMessage;
                        Sighnup sighnup = sighnupModel.getAllInOneVideo().get(0);

                        if (sighnup.getSuccess().equals("1")) {
                            AlertDialog.Builder alert = new AlertDialog.Builder(ForgetPassActivity.this);
                            alert.setMessage(R.string.email_forget_sent)
                                    .setTitle("success")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                            alert.show();
                        }
                    }

                    @Override
                    public void onFailure(String onErrorMessage) {

                    }
                }));




        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");
        binding.edtEmail.setTypeface(typeface);
        binding.btnForgetPass.setTypeface(typeface);

    }
}