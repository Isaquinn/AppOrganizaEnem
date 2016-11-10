package com.example.isaquearaujo.apporganizaenem;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class TelaPrincipal extends FragmentActivity implements View.OnClickListener, Runnable{
    //public static  int number = 0;
    public static ProgressDialog progress;
    public static ViewPager viewpagerprincipal;
    private ListView mDrawerList;
    private RelativeLayout mDrawerPane;
    private DrawerLayout mDrawerLayout;
    ArrayList < NavItem > mNavItems = new ArrayList < NavItem > ();
    Typeface typeface;
    Typeface typeface2;
    TextView titleView;
    TextView teste;
    boolean gambiarra = true;
    public  static int viewpagerdicas = 0;
    int clickopenmenu = 0;
    Button openmenu;
    TextView tv;
    TextView tv1, tv2, tv3, tv4, tv5;
    DrawerListAdapter adapterdrawer;
    boolean validate = true;
    private SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        progress = new ProgressDialog(TelaPrincipal.this,R.style.full_screen_dialog) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.custom_progressdialog);
                getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
            }
        };
        progress.show();
        openmenu = (Button)findViewById(R.id.openmenu);
        openmenu.setOnClickListener(this);
        mNavItems.add(new NavItem("Inicio", R.drawable.iconelivrosp));
        mNavItems.add(new NavItem("Dicas", R.drawable.iconedicas));
        mNavItems.add(new NavItem("Conquistas", R.drawable.iconeconquistas));
        mNavItems.add(new NavItem("Configurações", R.drawable.iconeconfiguracoes));
        mNavItems.add(new NavItem("Sair", R.drawable.iconesair));
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerList.setDivider(null);
        adapterdrawer = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapterdrawer);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Semibold.ttf");
        typeface2 = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        final Padrao fragment = new Padrao();
        settings = getSharedPreferences(com.example.isaquearaujo.apporganizaenem.Principal.PREFS_NAME, 0);
        viewpagerprincipal = (ViewPager)findViewById(R.id.viewpagerprincipal);
        viewpagerprincipal.setAdapter(new NavigateTelaPrincipal(getSupportFragmentManager()));
        viewpagerprincipal.setOffscreenPageLimit(3);
        viewpagerprincipal.setCurrentItem(0);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<? > parent, View view, int position, long id)
            {
                //aqui tem que mecher de novo

                if(teste != null)
                {
                    teste.setTextColor(Color.parseColor("#79a2b8"));
                    teste.setTypeface(typeface);
                }

                tv = (TextView) view.findViewById(R.id.title);
                teste = tv;
                tv.setTypeface(typeface2);
                tv.setTextColor(Color.parseColor("#0c4a66"));
                adapterdrawer.notifyDataSetChanged();
                for (int i = 0; i < mDrawerList.getChildCount(); i++)
                {
                    if (position == i)
                    {
                        mDrawerList.getChildAt(i).setBackgroundColor(Color.parseColor("#DCDCDC"));
                    }
                    else
                    {
                        mNavItems.get(0).mIcon = R.drawable.iconelivros;
                        mNavItems.get(1).mIcon = R.drawable.iconedicas;
                        mNavItems.get(2).mIcon = R.drawable.iconeconquistas;
                        mNavItems.get(3).mIcon = R.drawable.iconeconfiguracoes;
                        mNavItems.get(4).mIcon = R.drawable.iconesair;
                        mDrawerList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
                if (position == 0)
                {
                    ViewPager viewPager2 = (ViewPager)findViewById(R.id.viewpagerprincipal);
                    viewPager2.setCurrentItem(0, false);
                    ViewPager viewPager = (ViewPager)findViewById(R.id.transicaopadrao);
                    viewPager.setCurrentItem(0,false);
                    //number = 0;
                    gambiarra = true;
                    mNavItems.get(0).mIcon = R.drawable.iconelivrosp;
                }
                else if (position == 1)
                {
                    ViewPager viewPager2 = (ViewPager)findViewById(R.id.viewpagerprincipal);
                    viewPager2.setCurrentItem(0, false);
                    ViewPager viewPager = (ViewPager)findViewById(R.id.transicaopadrao);
                    viewPager.setCurrentItem(1, false);
                    TelaDicas.transicaodicas.setCurrentItem(0,false);
                    gambiarra =false;
                    mNavItems.get(1).mIcon = R.drawable.iconedicasp;
                }
                else if (position == 2)
                {
                    ViewPager viewPager2 = (ViewPager)findViewById(R.id.viewpagerprincipal);
                    viewPager2.setCurrentItem(0, false);
                    ViewPager viewPager = (ViewPager)findViewById(R.id.transicaopadrao);
                    viewPager.setCurrentItem(2, false);
                    ScrollView sv = (ScrollView) findViewById(R.id.scrollView);
                    sv.setScrollY(0);
                    gambiarra =false;
                    mNavItems.get(2).mIcon = R.drawable.iconeconquistasp;
                }
                else if (position == 3)
                {
                    ViewPager viewPager = (ViewPager)findViewById(R.id.viewpagerprincipal);
                    viewPager.setCurrentItem(1, false);
                    gambiarra =false;
                    mNavItems.get(3).mIcon = R.drawable.iconeconfiguracoesp;
                }
                else if (position == 4)
                {
                    gambiarra =false;
                    mNavItems.get(4).mIcon = R.drawable.iconesairp;
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("email", "");
                    editor.putString("senha","");
                    editor.commit();
                    Intent intent = new Intent(TelaPrincipal.this, TelaLogin.class);
                    startActivity(intent);
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
               // adapterdrawer.notifyDataSetChanged();
            }
        });
        run();

        //number = 0;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.openmenu)
        {
            clickopenmenu += 1;
            if (clickopenmenu == 1)
            {
                mDrawerLayout.openDrawer(mDrawerPane);
            }
            else if (clickopenmenu == 2)
            {
                mDrawerLayout.closeDrawer(mDrawerPane);
                clickopenmenu = 0;
            }
        }
    }

    class NavItem
    {
        String mTitle;
        int mIcon;
        public NavItem(String title, int icon)
        {
            mTitle = title;
            mIcon = icon;
        }
    }
    class DrawerListAdapter extends BaseAdapter
    {
        Context mContext;
        ArrayList< NavItem > mNavItems;
        public DrawerListAdapter(Context context, ArrayList < NavItem > navItems)
        {
            mContext = context;
            mNavItems = navItems;
        }
        @Override
        public int getCount()
        {
            return mNavItems.size();
        }
        @Override
        public Object getItem(int position)
        {
            return mNavItems.get(position);
        }
        @Override
        public long getItemId(int position)
        {
            return 0;
        }
        @Override
        public View getView(final int position, View convertView,  final ViewGroup parent)
        {
            View view;
            if (convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_item, null);
            }
            else
            {
                view = convertView;
            }
            titleView = (TextView) view.findViewById(R.id.title);
            ImageView iconView = (ImageView) view.findViewById(R.id.icon);
            //titleView.setTypeface(typeface);
            if(gambiarra == true)
            {
                titleView.setTypeface(typeface);
                if (position == 0) {
                    view.setBackgroundColor(Color.parseColor("#DCDCDC"));
                    titleView.setTextColor(Color.parseColor("#0c4a66"));
                    titleView.setTypeface(typeface2);
                }

            }
            else if(gambiarra == false)
            {
                if (position == 0) {
                    view.setBackgroundColor(Color.TRANSPARENT);
                    titleView.setTextColor(Color.parseColor("#79a2b8"));
                    titleView.setTypeface(typeface);
                }


            }

            titleView.setText(mNavItems.get(position).mTitle);
            iconView.setImageResource(mNavItems.get(position).mIcon);
            titleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ListView) parent).performItemClick(v, position, 0);
                }
            });
            return view;
        }
    }
    @Override
    public void onBackPressed()
    {

        if (viewpagerprincipal.getCurrentItem() == 1)
        {
            gambiarra = true;
            viewpagerprincipal.setCurrentItem(0, false);
            tv.setTypeface(typeface);
            tv.setTextColor(Color.parseColor("#79a2b8"));
            tv1 = (TextView)findViewById(R.id.title);
            tv1.setTextColor(Color.parseColor("#0c4a66"));
            tv1.setTypeface(typeface2);
            adapterdrawer.notifyDataSetChanged();
            mDrawerList.getChildAt(0).setBackgroundColor(Color.parseColor("#DCDCDC"));
            mDrawerList.getChildAt(1).setBackgroundColor(Color.TRANSPARENT);
            mDrawerList.getChildAt(2).setBackgroundColor(Color.TRANSPARENT);
            mDrawerList.getChildAt(3).setBackgroundColor(Color.TRANSPARENT);
            mDrawerList.getChildAt(4).setBackgroundColor(Color.TRANSPARENT);
            mNavItems.get(3).mIcon = R.drawable.iconeconfiguracoes;
            mNavItems.get(0).mIcon = R.drawable.iconelivrosp;
        }
        if (viewpagerprincipal.getCurrentItem() == 2)
        {
            viewpagerprincipal.setCurrentItem(1, false);
            //login2(nomeusuario);
        }
        if (viewpagerprincipal.getCurrentItem() == 0 && Padrao.transicaopadrao.getCurrentItem() == 0 && TelaMaterias.materias.getCurrentItem() != 0)
        {
            if (TelaMaterias.materias.getCurrentItem() == 1)
            {
                TelaMaterias.materias.setCurrentItem(0);
            }
            if (TelaMaterias.materias.getCurrentItem() == 2)
            {
                TelaMaterias.materias.setCurrentItem(1);
            }
        }
        if (viewpagerprincipal.getCurrentItem() == 0 && Padrao.transicaopadrao.getCurrentItem() == 1 && TelaDicas.transicaodicas.getCurrentItem() == 0)
        {
            gambiarra = true;
            Padrao.transicaopadrao.setCurrentItem(0, false);
            tv.setTypeface(typeface);
            tv.setTextColor(Color.parseColor("#79a2b8"));
            tv1 = (TextView)findViewById(R.id.title);
            tv1.setTextColor(Color.parseColor("#0c4a66"));
            tv1.setTypeface(typeface2);
            adapterdrawer.notifyDataSetChanged();
            mDrawerList.getChildAt(0).setBackgroundColor(Color.parseColor("#DCDCDC"));
            mDrawerList.getChildAt(1).setBackgroundColor(Color.TRANSPARENT);
            mDrawerList.getChildAt(2).setBackgroundColor(Color.TRANSPARENT);
            mDrawerList.getChildAt(3).setBackgroundColor(Color.TRANSPARENT);
            mDrawerList.getChildAt(4).setBackgroundColor(Color.TRANSPARENT);
            mNavItems.get(1).mIcon = R.drawable.iconedicas;
            mNavItems.get(0).mIcon = R.drawable.iconelivrosp;
        }
        else if (viewpagerprincipal.getCurrentItem() == 0 && Padrao.transicaopadrao.getCurrentItem() == 1 && TelaDicas.transicaodicas.getCurrentItem() == 1)
        {
            TelaDicas.transicaodicas.setCurrentItem(0, false);
        }
        else if (viewpagerprincipal.getCurrentItem() == 0 && Padrao.transicaopadrao.getCurrentItem() == 1 && TelaDicas.transicaodicas.getCurrentItem() == 2)
        {
            TelaDicas.transicaodicas.setCurrentItem(0, false);
        }
        else if (viewpagerprincipal.getCurrentItem() == 0 && Padrao.transicaopadrao.getCurrentItem() == 1 && TelaDicas.transicaodicas.getCurrentItem() == 3)
        {
            TelaDicas.transicaodicas.setCurrentItem(0, false);
        }
        else if (viewpagerprincipal.getCurrentItem() == 0 && Padrao.transicaopadrao.getCurrentItem() == 1 && TelaDicas.transicaodicas.getCurrentItem() == 4)
        {
            TelaDicas.transicaodicas.setCurrentItem(0, false);
        }
        else if (viewpagerprincipal.getCurrentItem() == 0 && Padrao.transicaopadrao.getCurrentItem() == 1 && TelaDicas.transicaodicas.getCurrentItem() == 5 && viewpagerdicas == 1)
        {
            TelaDicas.transicaodicas.setCurrentItem(1, false);
            TelaMostrarFilmesELivros.sinopse.setText("");
            TelaMostrarFilmesELivros.mostrarmateriaRelacionada.setText("");
            TelaMostrarFilmesELivros.mostrarproducao.setText("");
            TelaMostrarFilmesELivros.Producaoclick = 0;
            TelaMostrarFilmesELivros.Materiarelacionadaclick = 0;
            TelaMostrarFilmesELivros.Descricaoclick = 0;
            TelaMostrarFilmesELivros.Descricao.setTypeface(typeface);
            TelaMostrarFilmesELivros.MateriaRelacionada.setTypeface(typeface);
            TelaMostrarFilmesELivros.Producao.setTypeface(typeface);
        }
        else if (viewpagerprincipal.getCurrentItem() == 0 && Padrao.transicaopadrao.getCurrentItem() == 1 && TelaDicas.transicaodicas.getCurrentItem() == 5 && viewpagerdicas == 2)
        {
            TelaDicas.transicaodicas.setCurrentItem(2, false);
            TelaMostrarFilmesELivros.sinopse.setText("");
            TelaMostrarFilmesELivros.mostrarmateriaRelacionada.setText("");
            TelaMostrarFilmesELivros.mostrarproducao.setText("");
            TelaMostrarFilmesELivros.Producaoclick = 0;
            TelaMostrarFilmesELivros.Materiarelacionadaclick = 0;
            TelaMostrarFilmesELivros.Descricaoclick = 0;
            TelaMostrarFilmesELivros.Descricao.setTypeface(typeface);
            TelaMostrarFilmesELivros.MateriaRelacionada.setTypeface(typeface);
            TelaMostrarFilmesELivros.Producao.setTypeface(typeface);
        }
        else if (viewpagerprincipal.getCurrentItem() == 0 && Padrao.transicaopadrao.getCurrentItem() == 2)
        {
            gambiarra = true;
            Padrao.transicaopadrao.setCurrentItem(0, false);
            tv.setTypeface(typeface);
            tv.setTextColor(Color.parseColor("#79a2b8"));
            tv1 = (TextView)findViewById(R.id.title);
            tv1.setTextColor(Color.parseColor("#0c4a66"));
            tv1.setTypeface(typeface2);
            adapterdrawer.notifyDataSetChanged();
            mDrawerList.getChildAt(0).setBackgroundColor(Color.parseColor("#DCDCDC"));
            mDrawerList.getChildAt(1).setBackgroundColor(Color.TRANSPARENT);
            mDrawerList.getChildAt(2).setBackgroundColor(Color.TRANSPARENT);
            mDrawerList.getChildAt(3).setBackgroundColor(Color.TRANSPARENT);
            mDrawerList.getChildAt(4).setBackgroundColor(Color.TRANSPARENT);
            mNavItems.get(2).mIcon = R.drawable.iconeconquistas;
            mNavItems.get(0).mIcon = R.drawable.iconelivrosp;
        }
        else
        {
            //super.onBackPressed(); // This will pop the Activity from the stack.
        }
    }
    @Override
    public void run()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    if(mDrawerLayout.isDrawerOpen(mDrawerPane))
                    {
                        clickopenmenu = 1;
                    }
                    else
                    {
                        clickopenmenu = 0;
                    }
                }
            }
        }).start();
    }
}
