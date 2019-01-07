package id.devloved.metube;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import id.devloved.metube.fragments.ChannelFragment;
import id.devloved.metube.fragments.PlaylistFragment;
import id.devloved.metube.fragments.SettingFragment;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigation);

        final ChannelFragment channelFragment = new ChannelFragment();
        final PlaylistFragment playlistFragment = new PlaylistFragment();
        final SettingFragment settingFragment = new SettingFragment();

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
                }else if (id==R.id.navigation_setting){
                    setFragment(settingFragment);
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

        int id=item.getItemId();
        if (id==R.id.version){
            Toast.makeText(this,"Version 1.0",Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
