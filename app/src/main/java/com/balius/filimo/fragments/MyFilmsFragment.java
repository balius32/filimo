package com.balius.filimo.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.balius.filimo.R;
import com.balius.filimo.adapter.TabsAdapter;
import com.balius.filimo.databinding.FragmentMyFilmsBinding;
import com.balius.filimo.fragments.myfilm.SavedFragment;
import com.balius.filimo.fragments.myfilm.WatchedFilmFragment;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;


public class MyFilmsFragment extends Fragment {
    FragmentMyFilmsBinding binding;

    public MyFilmsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyFilmsBinding.inflate(getLayoutInflater());

        //this fragment for myFilm pager
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new WatchedFilmFragment());
        fragments.add(new SavedFragment());

        binding.pager.setAdapter(new TabsAdapter(getActivity(), fragments));

        binding.navMyfilms.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.item_watched:
                        binding.pager.setCurrentItem(0);
                        binding.navMyfilms.getMenu().findItem(R.id.item_watched).setChecked(true);
                        break;
                    case R.id.item_save:
                        binding.pager.setCurrentItem(1);
                        binding.navMyfilms.getMenu().findItem(R.id.item_save).setChecked(true);
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
                        binding.navMyfilms.getMenu().findItem(R.id.item_watched).setChecked(true);
                        break;
                    case 1:
                        binding.navMyfilms.getMenu().findItem(R.id.item_save).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        return binding.getRoot();
    }
}