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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garbage);

        fm = getSupportFragmentManager();
        setUpFragments();
    }

    // for navigation, only landscape is handled
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
        }
    }

}


