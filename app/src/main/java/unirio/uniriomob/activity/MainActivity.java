package unirio.uniriomob.activity;

import android.os.Bundle;
import android.os.Handler;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import unirio.uniriomob.fragment.HomeFragment;
import unirio.uniriomob.fragment.DocumentFragment;
import unirio.uniriomob.fragment.SyllabusFragment;
import unirio.uniriomob.fragment.SecretariatFragment;
import unirio.uniriomob.fragment.RestaurantFragment;
import unirio.uniriomob.fragment.BusFragment;
import unirio.uniriomob.fragment.NewsFragment;

import unirio.uniriomob.R;
import unirio.uniriomob.other.CircleTransform;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FloatingActionButton fab;

   // urls to load navigation header background image
    // and profile image

    private static final String urlNavHeaderBg = "https://image.freepik.com/vetores-gratis/decorativo-colorido-abstrato-vector-set-geometrica_293-914.jpg";
    private static final String urlProfileImg = "http://www.forpdi.org/img/unirio.png";

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_DOCUMENT = "document";
    private static final String TAG_SYLLABUS = "syllabus";
    private static final String TAG_SECRETARIAT = "secretariat";
    private static final String TAG_RESTAURANT = "restaurant";
    private static final String TAG_BUS = "bus";
    private static final String TAG_NEWS = "news";

    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.student_name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.student_code);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {

            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();

        }

    }


    /**
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */

    private void loadNavHeader() {

        // name, website
        txtName.setText("Marcos Henrique Bastos de Sá Silva");
        txtWebsite.setText("Matrícula: 20152210010");

        // loading header background image
        Glide.with(this).load(urlNavHeaderBg).crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imgNavHeaderBg);

        // Loading profile image
        Glide.with(this).load(urlProfileImg).crossFade().thumbnail(0.5f).fitCenter()
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imgProfile);

        // showing dot next to notifications label
        navigationView.getMenu().getItem(6).setActionView(R.layout.menu_dot);

    }

    /**
     *      * Returns respected fragment that user
     *      * selected from navigation menu
     *      
     */

    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {

            drawer.closeDrawers();

            // show or hide the fab button
            //toggleFab();

            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {

            @Override
            public void run() {

                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();

            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {

            mHandler.post(mPendingRunnable);

        }

        // show or hide the fab button
        //toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();

    }

    private Fragment getHomeFragment() {

        switch (navItemIndex) {

            case 0: // home
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;

            case 1: // document
                DocumentFragment documentFragment = new DocumentFragment();
                return documentFragment;

            case 2: // syllabus
                SyllabusFragment syllabusFragment = new SyllabusFragment();
                return syllabusFragment;

            case 3: // secretariat
                SecretariatFragment secretariatFragment = new SecretariatFragment();
                return secretariatFragment;

            case 4: // restaurant
                RestaurantFragment restaurantFragment = new RestaurantFragment();
                return restaurantFragment;

            case 5: // bus
                BusFragment busFragment = new BusFragment();
                return busFragment;

            case 6: // news
                NewsFragment newsFragment = new NewsFragment();
                return newsFragment;

            default:
                return new HomeFragment();

        }

    }


    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {

                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;

                    case R.id.nav_document:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_DOCUMENT;
                        break;

                    case R.id.nav_syllabus:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_SYLLABUS;
                        break;

                    case R.id.nav_secretariat:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_SECRETARIAT;
                        break;

                    case R.id.nav_restaurant:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_RESTAURANT;
                        break;

                    case R.id.nav_bus:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_BUS;
                        break;

                    case R.id.nav_news:
                        navItemIndex = 6;
                        CURRENT_TAG = TAG_NEWS;
                        break;

                    /*case R.id.nav_login:

                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        drawer.closeDrawers();
                        return true;*/

                    default:
                        navItemIndex = 0;
                        break;

                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                menuItem.setChecked(true);
                loadHomeFragment();
                return true;

            }

        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {

                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {

                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);

            }

        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }


    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawers();
            return;

        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {

                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }

        }

        super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        if (navItemIndex == 0) {

            getMenuInflater().inflate(R.menu.main, menu);

        }

        // when fragment is notifications, load the menu created for notifications
        if (navItemIndex == 6) {

            getMenuInflater().inflate(R.menu.notifications, menu);

        }

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            return true;

        }

        // user is in notifications fragment
        // and selected 'Mark all as Read'
        if (id == R.id.action_mark_all_read) {

            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();

        }

        // user is in notifications fragment
        // and selected 'Clear All'
        if (id == R.id.action_clear_notifications) {

            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();

        }

        return super.onOptionsItemSelected(item);

    }

    // show or hide the fab
    private void toggleFab() {

        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();

    }

}