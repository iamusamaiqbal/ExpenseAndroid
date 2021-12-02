package com.example.moneywallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
  TabLayout tabLayout;
  ViewPager viewPager;
  TabItem tabItem1,tabItem2;
  PageAdapter pageAdapter;
  DrawerLayout drawerLayout;
  NavigationView navigationView;
  Toolbar toolbar;
  ActionBarDrawerToggle toggle;
  FloatingActionButton new_record_float;
  ImageView imageView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=findViewById(R.id.tab_main);
        viewPager=findViewById(R.id.viewpager_main);
        tabItem1=findViewById(R.id.tab_item_main_1);
        tabItem2=findViewById(R.id.tab_item_main_2);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view_1);
        toolbar=findViewById(R.id.tool_bar_main);
        new_record_float=findViewById(R.id.new_record_float);
        imageView2=findViewById(R.id.imageView2);


        new_record_float.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,new_record.class);
                startActivity(intent);
            }
        });

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        toggle=new ActionBarDrawerToggle(this ,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_Home:
                        Intent intent=new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "home clicked", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.nav_Record:
                        Intent record=new Intent(MainActivity.this,new_record.class);
                        startActivity(record);
                        Toast.makeText(MainActivity.this, "record clicked", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.nav_Budgets:
                        Intent bud=new Intent(MainActivity.this,Budget.class);
                        startActivity(bud);
                        Toast.makeText(MainActivity.this, "budget clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_Debts:
                        Intent debt=new Intent(MainActivity.this,debit.class);
                        startActivity(debt);
                        Toast.makeText(MainActivity.this, "debts clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_Goals:
                        Intent gool=new Intent(MainActivity.this,Goals.class);
                        startActivity(gool);
                        Toast.makeText(MainActivity.this, "goals clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_Statistic:
                        Intent stat=new Intent(MainActivity.this,statistic.class);
                        startActivity(stat);
                        Toast.makeText(MainActivity.this, "stat clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_shopping_list:
                        Intent sh=new Intent(MainActivity.this,shopping_list.class);
                        startActivity(sh);
                        Toast.makeText(MainActivity.this, "shopping list clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_warranties:
                        Intent warr=new Intent(MainActivity.this,warranty.class);
                        startActivity(warr);
                        Toast.makeText(MainActivity.this, "warranties clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_currency_rate:
                        Intent curency=new Intent(MainActivity.this,Currency_rates.class);
                        startActivity(curency);
                        Toast.makeText(MainActivity.this, "currency clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_export:
//                        Intent intent=new Intent(MainActivity.this,accounts.class);
//                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "export clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_location:
//                        Intent intent=new Intent(MainActivity.this,accounts.class);
//                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "location clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_setting:
                        Intent setting=new Intent(MainActivity.this,Setting.class);
                        startActivity(setting);
                        Toast.makeText(MainActivity.this, "setting clicked", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return true;
            }
        });



        pageAdapter=new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0||tab.getPosition()==1)
                    pageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    @Override
    public void onBackPressed() {
      if (drawerLayout.isDrawerOpen(GravityCompat.START)){
          drawerLayout.closeDrawer(GravityCompat.START);
      }else {
          super.onBackPressed();

      }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}