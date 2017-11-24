package zzj.product.com.memorynotes.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import zzj.product.com.memorynotes.R;
import zzj.product.com.memorynotes.views.dialogs.BookAddDialog;
import zzj.product.com.memorynotes.views.fragment.AllNotesListFragment;
import zzj.product.com.memorynotes.views.fragment.BaseFragment;
import zzj.product.com.memorynotes.views.fragment.BookListFragment;

/**
 * 主页
 */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    private NavigationView navigationView;
    private int nav_selected_id = R.id.nav_all_notes;

    private Fragment currentFragment;
    private BaseFragment allNotesFragment;
    private BaseFragment bookListsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:跳转到编辑界面
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        allNotesFragment = new AllNotesListFragment();
        setCurrentFragment(allNotesFragment, "notes");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //若当前不是allNotesFragment，按返回键先返回allNotesFragment，同时更新相关内容（navigationview和toolbar）
            if (!allNotesFragment.isVisible()) {
                setCurrentFragment(allNotesFragment, "notes");
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        switch (nav_selected_id) {
            case R.id.nav_all_notes:
                getMenuInflater().inflate(R.menu.toolbar_all_notes_menu, menu);
                break;
            case R.id.nav_notebook:
                getMenuInflater().inflate(R.menu.toolbar_notebook_menu, menu);
                break;
            case R.id.nav_settings:
                break;
            default:
                break;
        }
        return super.onPrepareOptionsMenu(menu);
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
        } else if (id == R.id.action_search) {

        } else if (id == R.id.action_add_book) {
            //能点击到这个按钮，当前一定是AllNotesListFragment了。
            try {
                if (currentFragment != null) {
                    BookAddDialog dialog = new BookAddDialog();
                    dialog.show(getSupportFragmentManager(), "addbook");
                    //((BookListFragment) currentFragment).getPresenter().addBook("test book");
                }
            } catch (ClassCastException e) {
                Log.e(TAG, "AllNotesListFragment cast exception");
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        nav_selected_id = item.getItemId();

        if (nav_selected_id == R.id.nav_all_notes) {
            if (allNotesFragment == null)
                allNotesFragment = new AllNotesListFragment();
            setCurrentFragment(allNotesFragment, "notes");
        } else if (nav_selected_id == R.id.nav_notebook) {
            if (bookListsFragment == null)
                bookListsFragment = new BookListFragment();
            setCurrentFragment(bookListsFragment, "book");
        } else if (nav_selected_id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setCurrentFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (currentFragment != null)
            ft.hide(currentFragment);
        if (!fragment.isAdded())
            ft.add(R.id.contentPanel, fragment, tag);
        ft.show(fragment);
        ft.commit();
        currentFragment = fragment;

        if (fragment instanceof AllNotesListFragment) {
            nav_selected_id = R.id.nav_all_notes;
            getSupportActionBar().setTitle(R.string.navigation_drawer_all_notes);
        } else if (fragment instanceof BookListFragment) {
            nav_selected_id = R.id.nav_notebook;
            getSupportActionBar().setTitle(R.string.navigation_drawer_notebook);
        }
        navigationView.setCheckedItem(nav_selected_id);
        invalidateOptionsMenu();

    }

}
