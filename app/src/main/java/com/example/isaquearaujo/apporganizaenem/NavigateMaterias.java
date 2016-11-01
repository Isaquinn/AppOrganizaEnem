package com.example.isaquearaujo.apporganizaenem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Isaque Araujo on 28/10/2016.
 */

public class NavigateMaterias extends FragmentPagerAdapter {
    private String fragments[] = {
            "Hoje", "Pendente", "Feito"
    };
    public NavigateMaterias(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int pos) {
        switch(pos) {
            case 0: return new TelaMateriaDoDia();
            case 1: return new TelaMateriaPendente();
            case 2: return new TelaMateriaFeita();
        }
        return null;
    }
    @Override
    public int getCount()
    {
        return fragments.length;
    }
    @Override
    public CharSequence getPageTitle(int position)
    {
        return fragments[position];
    }

}
