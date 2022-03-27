package dk.itu.garbage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;


public class GarbageActivity extends AppCompatActivity {
    //GUI moved to Fragments

    //Using Fragments
    private FragmentManager fm;
    Fragment fragmentUI, fragmentList;

    //db
    //private final static ItemsViewModel itemDB = new ItemsViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garbage);

        fm = getSupportFragmentManager();
        setUpFragments();

        //initialising db with context
        //itemDB.initialize(GarbageActivity.this);
    }

    private void setUpFragments() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentUI = fm.findFragmentById(R.id.container_ui_land);
            fragmentList = fm.findFragmentById(R.id.container_list);
            if ((fragmentUI == null) && (fragmentList == null)) {
                Fragment fragmentUI = new UIFragment();
                Fragment fragmentList = new ListFragment();
                fm.beginTransaction()
                        .add(R.id.container_ui_land, fragmentUI)
                        .add(R.id.container_list, fragmentList)
                        .commit();
            }
        } else {
            // for portrait, only show UI
            if (fragmentUI == null) {
                fragmentUI = new UIFragment();
                fm.beginTransaction()
                        .add(R.id.container_ui, fragmentUI)
                        .commit();
            }
        }
    }

}


