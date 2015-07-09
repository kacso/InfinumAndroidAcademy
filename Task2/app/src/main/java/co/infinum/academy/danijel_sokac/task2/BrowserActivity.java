package co.infinum.academy.danijel_sokac.task2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebIconDatabase;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Stack;


public class BrowserActivity extends AppCompatActivity {
    private EditText urlElement;
    private String currentUrl = "";
    private WebView browserWindow;
    private MenuItem backItem, forwardItem;
    private ProgressBar spinner;
    private ImageButton abort;
    private ImageButton refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        spinner = (ProgressBar)findViewById(R.id.loading);
        backItem = (MenuItem)findViewById(R.id.back);
        forwardItem = (MenuItem) findViewById(R.id.forward);
        abort = (ImageButton)findViewById(R.id.abort);
        refresh = (ImageButton)findViewById(R.id.refresh);

        browserWindow = (WebView) findViewById(R.id.browserWindow);

        browserWindow.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loadUrl(url);
                return true;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                spinner.setVisibility(View.VISIBLE);
                abort.setVisibility(View.VISIBLE);
                refresh.setVisibility(View.GONE);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                spinner.setVisibility(View.GONE);

                abort.setVisibility(View.GONE);
                refresh.setVisibility(View.VISIBLE);
                setMenuItemsEnableStatus();
                setTitle(view.getTitle());

                Bitmap b = view.getFavicon();
                if (b != null) {
                    Drawable d = new BitmapDrawable(getResources(), b);

                    try {
                        getActionBar().setIcon(d);
                    } catch (Exception e) {
                        
                    }
                }
            }
        });

        urlElement = (EditText) findViewById(R.id.pageUrl);

        urlElement.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    currentUrl = urlElement.getText().toString();
                    loadUrl(currentUrl);
                }
                return false;
            }
        });

        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey(StartActivity.URL)) {
            currentUrl = getIntent().getStringExtra(StartActivity.URL);
            urlElement.setText(currentUrl);
            browserWindow.loadUrl(currentUrl);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browser, menu);
        return true;
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
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        browserWindow.saveState(outState);
    }

    private void setMenuItemsEnableStatus () {
//        backItem.setEnabled(browserWindow.canGoBack());
        findViewById(R.id.back).setEnabled(browserWindow.canGoBack());
        findViewById(R.id.forward).setEnabled(browserWindow.canGoForward());
//        forwardItem.setEnabled(browserWindow.canGoForward());
    }

    public void onBackClicked(MenuItem item) {
        browserWindow.goBack();
    }

    public void onForwardClicked(MenuItem item) {
        browserWindow.goForward();
    }

    public void onGoClicked(View view) {
        currentUrl = urlElement.getText().toString();
        loadUrl(currentUrl);
    }

    private void loadUrl(String url) {
        url = new UrlParser().parseUrl(url);
        urlElement.setText(url);
        currentUrl = url;
        browserWindow.loadUrl(url);
    }

    public void onRefreshClicked(View imgBtn) {
        browserWindow.reload();
    }

    public void onAbortClicked (View imgBtn) {
        browserWindow.stopLoading();
    }


}
