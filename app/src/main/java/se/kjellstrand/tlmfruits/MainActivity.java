package se.kjellstrand.tlmfruits;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import se.kjellstrand.tlmfruits.about.AboutFragment;
import se.kjellstrand.tlmfruits.entries.EntriesFragment;
import se.kjellstrand.tlmfruits.entry.EntryActivity;

public class MainActivity extends
        AppCompatActivity implements
        AboutFragment.OnAboutFragmentInteractionListener,
        EntriesFragment.OnEntriesFragmentInteractionListener {

    private Fragment aboutFragment = new AboutFragment();
    private Fragment entriesFragment = new EntriesFragment();

    private android.support.v4.app.FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {

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

        fragmentManager = getSupportFragmentManager();

        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager.beginTransaction()
                .add(R.id.content_frame, entriesFragment)
                .commit();

        navigation.setSelectedItemId(R.id.navigation_entries);
    }

    @Override
    public void onAboutFragmentInteraction() {

    }

    @Override
    public void onEntriesFragmentInteraction(String id) {
        EntryActivity.start(this, id);
    }
}
