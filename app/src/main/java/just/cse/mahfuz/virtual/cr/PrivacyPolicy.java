package just.cse.mahfuz.virtual.cr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class PrivacyPolicy extends AppCompatActivity {

    WebView webview;
    SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);


        final ProgressBar loading = (ProgressBar) findViewById(R.id.loading);
        webview = (WebView)  findViewById(R.id.webview);
        mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        //webview.getSettings().setPluginsEnabled(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webview.setScrollbarFadingEnabled(false);




        webview.setWebViewClient(new WebViewClient() {

                                     public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                         view.loadUrl(url);
                                         if (url.startsWith("tel:")) {
                                             Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                                             startActivity(intent);
                                             return true;
                                         }
                                         return false;

                                     }

                                 }


        );


        webview.loadUrl("https://sites.google.com/view/just-cse-mahfuz-virtual-cr");



//loading bar
        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100 && loading.getVisibility() == ProgressBar.GONE) {
                    loading.setVisibility(ProgressBar.VISIBLE);
                }

                loading.setProgress(progress);
                if (progress == 100) {
                    loading.setVisibility(ProgressBar.GONE);
                    mySwipeRefreshLayout.setRefreshing(false);

                }
            }
        });


        webview.canGoBack();

        webview.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && webview.canGoBack()) {
                    webview.goBack();
                    return true;
                }
                return false;
            }
        });




        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        webview.reload();

                    }
                }
        );



    }
}
