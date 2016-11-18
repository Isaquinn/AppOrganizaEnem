package com.example.isaquearaujo.apporganizaenem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Isaque Araujo on 28/10/2016.
 */

public class NavigateTelaDicas extends FragmentPagerAdapter {
    public NavigateTelaDicas(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int pos) {
        switch(pos) {
            case 0: return new SelecaoDicas();
            case 1: return new TelaFilmes();
            case 2: return new TelaLivros();
            case 3: return new TelaRelaxamento();
            case 4: return new TelaMente();
            case 5: return new TelaMostrarFilmesELivros();
            case 6: return new TelaMostrarGif();
        }
        return null;
    }
    @Override
    public int getCount() {
        return 7;
    }
}
