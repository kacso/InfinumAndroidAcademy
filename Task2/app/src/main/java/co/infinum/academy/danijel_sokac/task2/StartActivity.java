package co.infinum.academy.danijel_sokac.task2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class StartActivity extends AppCompatActivity {
    public static final String URL = "URL";
    private EditText urlElement;
    private UrlParser urlParser = new UrlParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        urlElement = (EditText)findViewById(R.id.startURL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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

    public void onOpenWebviewClick(View view) {
        Intent intent = new Intent(StartActivity.this, BrowserActivity.class);
        intent.putExtra(URL, urlParser.parseUrl(urlElement.getText().toString()));
        startActivity(intent);
//        finish();
    }
    public void onOpenBrowserClick (View view) {
        try {
            String url = urlParser.parseUrl(urlElement.getText().toString());
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.putExtra(URL, urlParser.parseUrl(urlElement.getText().toString()));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No application can handle this request."
                    + " Please install a webbrowser", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
