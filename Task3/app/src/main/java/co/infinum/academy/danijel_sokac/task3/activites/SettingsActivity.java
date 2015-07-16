package co.infinum.academy.danijel_sokac.task3.activites;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

import co.infinum.academy.danijel_sokac.task3.Enums.ColorEnum;
import co.infinum.academy.danijel_sokac.task3.Enums.LanguageCodeEnum;
import co.infinum.academy.danijel_sokac.task3.R;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String LANGUAGE = "LANGUAGE";
    public static final String COLOR = "COLOR";
    Spinner languageSpinner;
    Spinner colorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        languageSpinner = (Spinner) findViewById(R.id.language);
        Locale locale = getResources().getConfiguration().locale;
        String langCode = locale.getDisplayLanguage().toString();
        languageSpinner.setSelection(LanguageCodeEnum.indexOf(langCode));
        String spinnerLangCode = LanguageCodeEnum.Language
                .valueOf(languageSpinner.getSelectedItem().toString()).toString();
        String savedLangCode = PreferenceManager.getDefaultSharedPreferences(this).getString(LANGUAGE, "");
        if (!savedLangCode.isEmpty() && !savedLangCode.toLowerCase().equals(langCode.toLowerCase())) {
            languageSpinner.setSelection(LanguageCodeEnum.indexOf(savedLangCode));
            setLanguage(savedLangCode);
            Intent refresh = new Intent(this, SettingsActivity.class);
            startActivity(refresh);
            finish();

        } else if (savedLangCode.isEmpty()){
            languageSpinner.setSelection(LanguageCodeEnum.indexOf(langCode.toLowerCase()));
        } else {
            languageSpinner.setSelection(LanguageCodeEnum.indexOf(savedLangCode.toLowerCase()));
        }

        languageSpinner.setOnItemSelectedListener(this);

        colorSpinner = (Spinner) findViewById(R.id.color);
        String savedColor = PreferenceManager.getDefaultSharedPreferences(this).getString(COLOR, "Black");

        colorSpinner.setSelection(ColorEnum.indexOf(savedColor));
        colorSpinner.setBackgroundColor(ColorEnum.valueOf(savedColor).color);
        colorSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.language) {
            String lang = parent.getItemAtPosition(position).toString();
            Locale locale = getResources().getConfiguration().locale;
            if (!locale.getDisplayLanguage().toString().equals(lang)) {
                Toast.makeText(this, lang, Toast.LENGTH_SHORT).show();
//                languageSpinner.setSelection(position);

                setLanguage(lang);
//                Intent refresh = new Intent(this, SettingsActivity.class);
//                startActivity(refresh);
//                finish();
            }
        }
        else if (parent.getId() == R.id.color) {

//            String color = parent.getItemAtPosition(position).toString();
            String color = ColorEnum.colorAtIndex(position);
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                    .putString(COLOR, color).apply();
            colorSpinner.setBackgroundColor(ColorEnum.valueOf(color).color);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void setLanguage(String lang) {
        Locale myLocale = new Locale(LanguageCodeEnum.Language.valueOf(lang).code);
        Locale.setDefault(myLocale);

        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString(LANGUAGE, lang).apply();

    }
}
