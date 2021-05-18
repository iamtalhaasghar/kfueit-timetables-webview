package pk.asghar.talha;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class apps extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView webView;
    ConstraintLayout contentDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        webView = new WebView(this);
        contentDisplay = (ConstraintLayout) findViewById(R.id.content_display);
        contentDisplay.addView(webView, ConstraintLayout.LayoutParams.MATCH_PARENT);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(true);

        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                super.shouldOverrideUrlLoading(view, url);
                webView.loadUrl(url);
                return true;
            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                String message = String.format("<html><head><style>h5,h6{text-align:center;}</style></head><body>" +
                        "<h5>Internet Connection Problem.<br>Make sure you are connected to internet<br> " +
                        "and then try again.</h5><h6>%s</h6></body></html>", description, errorCode);
                webView.loadData(message, "text/html", "utf-8");
            }
            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                // for SSLErrorHandler
                handler.proceed();
            }
        });
        webView.loadUrl("https://www.kfueit.edu.pk/new-update?1=1&menu=news-updates");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.apps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            webView.loadUrl("");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home_page) {

            webView.loadUrl("https://www.kfueit.edu.pk");

        }

        else if (id == R.id.time_table) {

            webView.loadUrl("https://my.kfueit.edu.pk/users/testtable");

        } else if (id == R.id.my_kfueit) {

            webView.loadUrl("https://my.kfueit.edu.pk/");

        } else if (id == R.id.merit_lists) {

            webView.loadUrl("https://www.kfueit.edu.pk/merit-lists-1?1=1&menu=news-updates");

        } else if (id == R.id.latest_news) {

            webView.loadUrl("https://www.kfueit.edu.pk/new-update?1=1&menu=news-updates");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
