package com.example.ezzetecschool;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //toolbar....................
        Toolbar toolbar = findViewById(R.id.toolbard);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drowar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }

  

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drowar);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //MenuItem..................................


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);


        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, MainActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    //NavigationItem...............................................


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent=new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_statement) {

            startActivity(new Intent(MainActivity.this, MainActivity.class));



        }else if (id==R.id.onlinenews){
            startActivity(new Intent(MainActivity.this, MainActivity.class));


        }else if (id==R.id.websidelist)
        {
            startActivity(new Intent(MainActivity.this, MainActivity.class));


        }else if (id==R.id.contract)
        {
            startActivity(new Intent(MainActivity.this, MainActivity.class));

        }
        else if (id==R.id.nav_share)
        {

            Intent browserIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://web.facebook.com/etlcampus"));
            startActivity(browserIntent);


        }else if (id==R.id.nav_share)
        {

            Intent i =new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String subject="World News BD";
            String body="NewsPaper app";
            i.putExtra(Intent.EXTRA_SUBJECT,subject);
            i.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(Intent.createChooser(i,"share Via"));



        }
        else if (id==R.id.logouttt)
        {

            System.exit(1);

        }

        DrawerLayout drawer = findViewById(R.id.drowar);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void ebook(View view) {

        Intent intent=new Intent(MainActivity.this, EbookActivity.class);
        startActivity(intent);



    }
}
