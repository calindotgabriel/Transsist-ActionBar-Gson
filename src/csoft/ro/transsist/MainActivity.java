package csoft.ro.transsist;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import java.util.ArrayList;

public class MainActivity extends SherlockFragmentActivity {
    /**
     *
     */
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        loadDrawer();
    }

    public void loadDrawer () {
        final String PROGRAM = "Program";
        final String HARTA = "Harta";

        mDrawerLayout =
                (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList =
                (ListView) findViewById(R.id.list);
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        ArrayList<Item> drawerItems = new ArrayList<Item>();
        drawerItems.add(new Item(PROGRAM));
        drawerItems.add(new Item(HARTA));

        DrawerItemAdapter adapter =
                new DrawerItemAdapter(this, drawerItems);
        mDrawerList.setAdapter(adapter);

        mDrawerToggle =
                new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.app_name, R.string. app_name) {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                    }
                };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            displayView(position);
        }
    }

    public void displayView(int position) {

        checkAndCloseDrawer(position);

        Fragment fragment = null;

        switch (position) {
            case 1: // check if we already have a mapfragment in our frame
                if (getSupportFragmentManager().findFragmentById(R.id.frame_container)
                        instanceof MapFragment)
                    checkAndCloseDrawer(position);
                else
                    fragment = new MapFragment();
                break;
            default:
                Toast.makeText(this, "Clicked " + position, Toast.LENGTH_SHORT).show();
                break;
        }

        if (fragment != null) {
           getSupportFragmentManager().beginTransaction().
                   replace(R.id.frame_container, fragment).commit();
        }


    }

    @Override
    public boolean onMenuItemSelected(int featureId, com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                    mDrawerLayout.closeDrawer(mDrawerList);
                } else {
                    mDrawerLayout.openDrawer(mDrawerList);
                }
                return true;

        }
        return true;
    }

    public void checkAndCloseDrawer(int position) {
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        mDrawerLayout.closeDrawer(mDrawerList);
    }


}
