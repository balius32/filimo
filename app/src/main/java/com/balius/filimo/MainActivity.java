package com.balius.filimo;

import android.content.Intent;
import android.content.LocusId;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.balius.filimo.activities.ProfileActivity;
import com.balius.filimo.activities.SearchActivity;
import com.balius.filimo.activities.UserProfileActivity;
import com.balius.filimo.adapter.TabsAdapter;
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

        binding.pager.setAdapter(new TabsAdapter(MainActivity.this, fragments));


        binding.navBottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.item_vitrin:
                        binding.pager.setCurrentItem(0);
                        binding.navBottom.getMenu().findItem(R.id.item_vitrin).setChecked(true);
                        break;
                    case R.id.item_category:
                        binding.pager.setCurrentItem(1);
                        binding.navBottom.getMenu().findItem(R.id.item_category).setChecked(true);
                        break;
                    case R.id.item_myfilms:
                        binding.pager.setCurrentItem(2);
                        binding.navBottom.getMenu().findItem(R.id.item_myfilms).setChecked(true);
                        break;
                }

                return false;
            }
        });

        binding.pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        binding.navBottom.getMenu().findItem(R.id.item_vitrin).setChecked(true);
                        break;
                    case 1:
                        binding.navBottom.getMenu().findItem(R.id.item_category).setChecked(true);
                        break;
                    case 2:
                        binding.navBottom.getMenu().findItem(R.id.item_myfilms).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);

            }
        });

        binding.imgAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Login> loginList = db.iDao().getAllAccount();
                Login login = new Login();

                if (loginList.size()>0){
                    login = loginList.get(0);
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.putExtra("login",login);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else{
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


    }
}