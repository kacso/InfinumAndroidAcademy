package co.infinum.academy.danijel_sokac.task3.activites;

/**
 * Created by Danijel on 11.7.2015..
 */

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.widget.EditText;
import android.widget.Toast;

import co.infinum.academy.danijel_sokac.task3.FileManager;
import co.infinum.academy.danijel_sokac.task3.R;
import co.infinum.academy.danijel_sokac.task3.fragments.EditorFragment;
import co.infinum.academy.danijel_sokac.task3.fragments.FilesFragment;
import co.infinum.academy.danijel_sokac.task3.interfaces.FileListener;

public class EditorActivity extends AppCompatActivity implements FileListener {
    public static final String PATH = "PATH";
    EditText titleEditText, contentEditText;
    MenuItem saveItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_fragments);


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
//        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270)
//            //landscape
//            ft.replace(R.id.files, new FilesFragment());
//        else
            //portrait
            ft.replace(R.id.files, new FilesFragment());
//        ft.replace(R.id.files, new FilesFragment());
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
//        saveItem = menu.findItem(R.id.save);
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
            //TODO open settings activity
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOpenFileClicked(String path) {
        createEditorFragment(path);
    }

    @Override
    public void onNewFileClicked() {
        createEditorFragment("");
    }

    private void createEditorFragment(String path) {
        EditorFragment editorFragment = new EditorFragment();

        Bundle args = new Bundle();
        args.putString(PATH, path);
        editorFragment.setArguments(args);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

//        int rotation = getWindowManager().getDefaultDisplay().getRotation();
//        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270)
//            //portrait
//            ft.replace(R.id.container, editorFragment);
//        else
            ft.replace(R.id.editor, editorFragment);

        ft.addToBackStack(null);
        ft.commit();
    }

    public void onSaveClicked (MenuItem item) {
//        Toast.makeText(EditorActivityOld.this, "Saving...", Toast.LENGTH_SHORT).show();
        try {
            FileManager fileManager = new FileManager();
            fileManager.saveFile(titleEditText.getText().toString(), contentEditText.getText().toString());
            Toast.makeText(this, "Saved.", Toast.LENGTH_LONG).show();
            saveItem.setEnabled(false);
        } catch (Exception e) {
            Toast.makeText(this, "Error while saving file.", Toast.LENGTH_LONG).show();
        }
    }
}

