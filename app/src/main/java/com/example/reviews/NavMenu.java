package com.example.reviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.reviews.fragments.FoodFragment;
import com.example.reviews.fragments.MovieFragment;
import com.example.reviews.fragments.MusicFragment;
import com.example.reviews.fragments.TvFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class NavMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);





        UpdateNavHeader();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content,new MovieFragment());
        ft.commit();
        navigationView.setCheckedItem(R.id.nav_movie);
        setTitle("Movies");




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_menu, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {




        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_movie) {

            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content,new MovieFragment());
            ft.commit();
            setTitle(R.string.menu_movie);





        } else if (id == R.id.nav_TVshows) {

            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content,new TvFragment());
            ft.commit();
            setTitle(R.string.menu_TVshows);

        }else if(id==R.id.nav_Food){
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content,new FoodFragment());
            ft.commit();
            setTitle(R.string.menu_foods);
        }else if(id==R.id.nav_music){
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content,new MusicFragment());
            ft.commit();
            setTitle("Music");

        }
        else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_signout) {
            signOut();


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void UpdateNavHeader(){

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.nav_user_name);
        TextView navUserMail = headerView.findViewById(R.id.nav_user_email);
       CircleImageView navUserPhoto = headerView.findViewById(R.id.nav_user_photo);

        navUserMail.setText(currentUser.getEmail());
        navUsername.setText(currentUser.getDisplayName());
        String imgurl = currentUser.getPhotoUrl().toString();
        Glide.with(this).load(imgurl).into(navUserPhoto);

    }


    //signout start

    private void signOut() {
        // Firebase sign out
      mAuth.signOut();

        // Google sign out
     mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent=new Intent(NavMenu.this,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        Toast.makeText(NavMenu.this,"Signed Out successfully",Toast.LENGTH_SHORT).show();
                    }
                });


    }//signout end


}
