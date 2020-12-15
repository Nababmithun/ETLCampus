package com.bipul.nrsingdidristict.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.adapter.SliderAdapterExample;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //slider
    private SliderView sliderView;
    private SliderAdapterExample sliderAdapterExample;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorChangeStatusBar();
        initImageSlider();
        getNavigationBar();
    }

    private void getNavigationBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }


    public void colorChangeStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.gray_dark));
        }
    }

    public void initImageSlider() {
        sliderView = findViewById(R.id.imageSlider);
        sliderAdapterExample = new SliderAdapterExample(this);
        sliderView.setSliderAdapter(sliderAdapterExample);

        /*-------------initBanner---start----------*/
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
       /* sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);*/
        sliderView.setScrollTimeInSec(3); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        /*-------------initBanner---end----------*/
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            //   overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        } else if (id == R.id.nav_stuff) {
            startActivity(new Intent(MainActivity.this, EmployeeActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        } else if (id == R.id.nav_appointment) {
            startActivity(new Intent(MainActivity.this, AppointmentActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }
        else if (id == R.id.nav_gonosunani) {
            startActivity(new Intent(MainActivity.this, PublicHeadingSaveActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }
        else if (id == R.id.nav_complain) {
            startActivity(new Intent(MainActivity.this, ComplainActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (id == R.id.nav_information) {
            startActivity(new Intent(MainActivity.this, SetInformationActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        } else if (id == R.id.daily_activity) {
             startActivity(new Intent(MainActivity.this, DailyWorkActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}