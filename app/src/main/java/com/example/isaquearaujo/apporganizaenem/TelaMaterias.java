package com.example.isaquearaujo.apporganizaenem;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TelaMaterias extends Fragment {
    public static  ViewPager materias;
    public static TabLayout tabLayout;
    Typeface font;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_materias, container, false);
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Regular.ttf");
        materias = (ViewPager)rootView.findViewById(R.id.viewPager);
        tabLayout = (TabLayout)rootView.findViewById(R.id.tabLayout);
        materias.setAdapter(new NavigateMaterias(getActivity().getSupportFragmentManager()));
        tabLayout.setupWithViewPager(materias);
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#ffffff"));
        materias.setOffscreenPageLimit(3);
        materias.setCurrentItem(0);
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++)
        {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++)
            {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView)
                {
                    ((TextView) tabViewChild).setTypeface(font, Typeface.NORMAL);
                    ((TextView) tabViewChild).setAllCaps(false);
                }
            }
        }
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
}
