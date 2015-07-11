package co.infinum.academy.danijel_sokac.task3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;


public class EditorActivity extends AppCompatActivity {
    EditText titleEditText, contentEditText;
    MenuItem saveItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        saveItem = (MenuItem) findViewById(R.id.save);

        titleEditText = (EditText)findViewById(R.id.title);
        contentEditText = (EditText) findViewById(R.id.content);

        String filePath = getIntent().getStringExtra("FILE_PATH");
        if (!filePath.equals("")) {
            //Open file and display content
            try {
                FileManager fileManager = new FileManager();
                titleEditText.setText(fileManager.getFileTitle(filePath));
                String content = fileManager.readFile(filePath);
                contentEditText.setText(content);

            } catch (Exception e) {
                Toast.makeText(EditorActivity.this, "Error while reading file", Toast.LENGTH_LONG);
                return;
            }

        }

        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!saveItem.isEnabled())
                    saveItem.setEnabled(true);
            }
        });

        contentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!saveItem.isEnabled())
                    saveItem.setEnabled(true);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        saveItem = menu.findItem(R.id.save);
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

    public void onSaveClicked (MenuItem item) {
//        Toast.makeText(EditorActivity.this, "Saving...", Toast.LENGTH_SHORT).show();
        try {
            FileManager fileManager = new FileManager();
            fileManager.saveFile(titleEditText.getText().toString(), contentEditText.getText().toString());
            Toast.makeText(EditorActivity.this, "Saved.", Toast.LENGTH_LONG).show();
            saveItem.setEnabled(false);
        } catch (Exception e) {
            Toast.makeText(EditorActivity.this, "Error while saving file.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (saveItem.isEnabled()) {
            alertChangesUnsaved();
        } else {
            Intent intent = new Intent(EditorActivity.this, FilesActivity.class);
            startActivity(intent);
//            super.onBackPressed();
        }
    }

    private void alertChangesUnsaved() {
        AlertDialog alertDialog = new AlertDialog.Builder(EditorActivity.this).create();
        alertDialog.setTitle("Document has been modified");
        alertDialog.setMessage("Do you realy want to exit?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(EditorActivity.this, FilesActivity.class);
                        startActivity(intent);
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
