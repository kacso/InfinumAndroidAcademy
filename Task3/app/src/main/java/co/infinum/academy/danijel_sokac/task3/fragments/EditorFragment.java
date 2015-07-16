package co.infinum.academy.danijel_sokac.task3.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import co.infinum.academy.danijel_sokac.task3.Enums.ColorEnum;
import co.infinum.academy.danijel_sokac.task3.Manager.FileManager;
import co.infinum.academy.danijel_sokac.task3.R;
import co.infinum.academy.danijel_sokac.task3.activites.EditorActivity;
import co.infinum.academy.danijel_sokac.task3.activites.SettingsActivity;
import co.infinum.academy.danijel_sokac.task3.interfaces.AlertAction;

/**
 * Created by Danijel on 11.7.2015..
 */
public class EditorFragment extends Fragment {
    private String filePath;
    private EditText titleEditText, contentEditText;
    private FloatingActionButton saveButton;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            filePath = getArguments().getString(EditorActivity.PATH);
        } else {
            filePath = "";
        }
        return inflater.inflate(R.layout.activity_editor, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        saveButton = (FloatingActionButton)view.findViewById(R.id.save_file);

        saveButton.setVisibility(View.GONE);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked(v);
            }
        });

        titleEditText = (EditText)getActivity().findViewById(R.id.title);
        contentEditText = (EditText) getActivity().findViewById(R.id.content);

        String color = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(SettingsActivity.COLOR, "");
        if (!color.isEmpty()) {
            titleEditText.setTextColor(ColorEnum.valueOf(color).color);
            contentEditText.setTextColor(ColorEnum.valueOf(color).color);
        }

        if (!filePath.equals("")) {
            //Open file and display content
            try {
                FileManager fileManager = new FileManager();
                titleEditText.setText(fileManager.getFileTitle(filePath));
                String content = fileManager.readFile(filePath);
                contentEditText.setText(content);

            } catch (Exception e) {
                Toast.makeText(getActivity(), "Error while reading file", Toast.LENGTH_LONG);
                return;
            }

        }

        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count != 0 && !saveButton.isShown())
                    saveButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        contentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count != 0 && !saveButton.isShown())
                    saveButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void onSaveClicked (View v) {
//        Toast.makeText(EditorActivityOld.this, "Saving...", Toast.LENGTH_SHORT).show();
        try {
            FileManager fileManager = new FileManager();
            fileManager.saveFile(titleEditText.getText().toString(), contentEditText.getText().toString());
            Toast.makeText(getActivity(), "Saved.", Toast.LENGTH_LONG).show();
            saveButton.setVisibility(View.GONE);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error while saving file.", Toast.LENGTH_LONG).show();
        }
    }

    public boolean allowBackPressed() {
        return !saveButton.isShown();
    }

    public void destroyFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    public void alertChangesUnsaved(final AlertAction action) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Document has been modified");
        alertDialog.setMessage("Do you realy want to exit?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        action.action();
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
