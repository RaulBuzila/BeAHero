package com.example.raulbuzila.beahero;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    Button bRegister;
    Button bLogin;
    Button bDonate;
    Button bInformation;
    UserLocalStore localDB;

    Intent intentGoToContactPage;
    Intent intentGoToUtilsInfoPage;
    Intent intentGoToDonateActivity;
    Intent intentGoToHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sugestii la: contact@BeAHero.com", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bRegister = (Button) findViewById(R.id.createAccountButton);
        bRegister.setOnClickListener(this);

        bLogin = (Button) findViewById(R.id.loginButton);
        bLogin.setOnClickListener(this);

        bDonate = (Button)findViewById(R.id.donateButton);
        bDonate.setOnClickListener(this);

        bInformation = (Button)findViewById(R.id.informationButton);
        bInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentGoToUtilsInfoPage);
            }
        });

        localDB = new UserLocalStore(this);

        if(Authenticate()) {
            HandleAuthenticatedUser();
        }
        //Declare Intent
        intentGoToContactPage = new Intent(HomeActivity.this, ContactsActivity.class);
        intentGoToUtilsInfoPage  = new Intent(HomeActivity.this, UtilsInfoActivity.class);
        intentGoToDonateActivity = new Intent(HomeActivity.this, DonateActivity.class);
        intentGoToHistory = new Intent(this,HistoryActivity.class);
    }

    public void HandleAuthenticatedUser(){
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        TextView txt = (TextView)navView.getHeaderView(0).findViewById(R.id.textView);
        User use =  localDB.GetLoggedInUser();
        txt.setText(use.name);

        bRegister.setVisibility(View.GONE);
        bLogin.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            HandleLogout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private  void HandleLogout(){
        localDB.ClearUserData();
        localDB.SetUserLoggedIn(false);
        bRegister.setVisibility(View.VISIBLE);
        bLogin.setVisibility(View.VISIBLE);

        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        TextView txt = (TextView)navView.getHeaderView(0).findViewById(R.id.textView);
        txt.setText("");
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // nimic
        } else if (id == R.id.nav_istoric) {
            startActivity(intentGoToHistory);
        } else if (id == R.id.nav_account) {
            startActivity(new Intent(this, MyAccountActivity.class));
        } else if (id == R.id.nav_info) {
            startActivity(intentGoToUtilsInfoPage);
        } else if (id == R.id.nav_centre) {

        } else if (id == R.id.nav_contact) {
            startActivity(intentGoToContactPage);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean Authenticate(){
        return  localDB.GetUserLoggedIn();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.createAccountButton:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.loginButton:
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.donateButton:
                startActivity(new Intent(HomeActivity.this, DonateActivity.class));
                break;
        }
    }
}
