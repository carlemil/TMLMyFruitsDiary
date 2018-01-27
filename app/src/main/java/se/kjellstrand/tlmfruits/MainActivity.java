package se.kjellstrand.tlmfruits;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import se.kjellstrand.tlmfruits.about.AboutFragment;
import se.kjellstrand.tlmfruits.entries.EntriesFragment;

public class MainActivity extends
        AppCompatActivity implements
        AboutFragment.OnAboutFragmentInteractionListener,
        EntriesFragment.OnEntriesFragmentInteractionListener {

    private Fragment aboutFragment = new AboutFragment();
    private Fragment entriesFragment = new EntriesFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        switch (item.getItemId()) {
            case R.id.navigation_about:
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, aboutFragment)
                        .commit();
                return true;
            case R.id.navigation_entries:
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, entriesFragment)
                        .commit();
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onAboutFragmentInteraction() {

    }

    @Override
    public void onEntriesFragmentInteraction() {

    }
}
