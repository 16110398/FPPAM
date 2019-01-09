package com.listyawan.mytube;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.listyawan.mytube.fragments.ChannelFragment;
import com.listyawan.mytube.fragments.FavoriteFragment;
import com.listyawan.mytube.fragments.PlaylistFragment;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigation);

        final ChannelFragment channelFragment = new ChannelFragment();
        final PlaylistFragment playlistFragment = new PlaylistFragment();
        final FavoriteFragment favoriteFragment = new FavoriteFragment();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.navigation_channel){
                    setFragment(channelFragment);
                    return true;
                }else if (id==R.id.navigation_playlist){
                    setFragment(playlistFragment);
                    return true;
                }else if (id==R.id.navigation_favorite){
                    setFragment(favoriteFragment);
                    return true;
                }
                return false;
            }
        });

        navigationView.setSelectedItemId(R.id.navigation_channel);
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.setting:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);

                break;
            case R.id.version:
                Toast.makeText(this, "Version 1.0", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
