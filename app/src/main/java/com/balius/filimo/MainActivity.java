package com.balius.filimo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.balius.filimo.activities.ProfileActivity;
import com.balius.filimo.activities.SearchActivity;
import com.balius.filimo.activities.UserProfileActivity;
import com.balius.filimo.database.Db;
import com.balius.filimo.databinding.ActivityMainBinding;
import com.balius.filimo.fragments.CategoryFragment;
import com.balius.filimo.fragments.MyFilmsFragment;
import com.balius.filimo.fragments.VitrinFragment;
import com.balius.filimo.model.login.Login;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = Db.getInstance(getApplicationContext());

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new VitrinFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new MyFilmsFragment());

      //  binding.pager.setAdapter(new TabsAdapter(MainActivity.this, fragments));

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new VitrinFragment()).commit();
        binding.navBottom.getMenu().findItem(R.id.item_vitrin).setChecked(true);
        binding.toolbar.setVisibility(View.GONE);
        binding.navBottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.item_vitrin:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new VitrinFragment()).commit();
                        binding.navBottom.getMenu().findItem(R.id.item_vitrin).setChecked(true);

                        break;
                    case R.id.item_category:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new CategoryFragment()).commit();
                        binding.navBottom.getMenu().findItem(R.id.item_category).setChecked(true);
                        binding.toolbar.setVisibility(View.VISIBLE);
                        break;
                    case R.id.item_myfilms:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new MyFilmsFragment()).commit();
                        binding.navBottom.getMenu().findItem(R.id.item_myfilms).setChecked(true);
                        binding.toolbar.setVisibility(View.VISIBLE);
                        break;
                }

                return false;
            }
        });







        binding.imgAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Login> loginList = db.iDao().getAllAccount();
                Login login = new Login();

                if (loginList.size() > 0) {
                    login = loginList.get(0);
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.putExtra("login", login);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                    startActivity(intent);
                }

            }
        });

        binding.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });


        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {//  binding.tv1.setText("refreshed");

                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                swipeRefreshLayout.setRefreshing(false);}
        }
        );


    }
}