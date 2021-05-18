package pk.asghar.talha;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_apps);

        webView = new WebView(this);
        webView.loadUrl("https://my.kfueit.edu.pk/users/testtable");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
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
                handler.proceed();
            }
        });

    }

    public void showDrawer(android.view.View view){
        setContentView(R.layout.activity_apps);
    }


}
