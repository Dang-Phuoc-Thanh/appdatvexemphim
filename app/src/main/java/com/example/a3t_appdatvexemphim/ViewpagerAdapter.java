package com.example.a3t_appdatvexemphim;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.a3t_appdatvexemphim.LichSu.Fragment_chuadung;
import com.example.a3t_appdatvexemphim.LichSu.Fragment_dadung;

public class ViewpagerAdapter extends FragmentStateAdapter {
    public ViewpagerAdapter (@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity );
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Fragment_chuadung();
            case 1:
                return new Fragment_dadung();
            default:
                return  new Fragment_chuadung();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
