package co.infinum.academy.danijel_sokac.task3.Implementation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import co.infinum.academy.danijel_sokac.task3.R;
import co.infinum.academy.danijel_sokac.task3.activites.EditorActivity;
import co.infinum.academy.danijel_sokac.task3.fragments.EditorFragment;
import co.infinum.academy.danijel_sokac.task3.interfaces.AlertAction;

/**
 * Created by Danijel on 16.7.2015..
 */
public class OpenFileAction implements AlertAction{
    String path;
    Context context;
    FragmentManager fm;
    Fragment fragment;
    public OpenFileAction (Context context, FragmentManager fm, Fragment f, String path) {
        this.path = path;
        this.context = context;
        this.fm = fm;
        this.fragment = f;
    }
    @Override
    public void action() {
        Bundle args = new Bundle();
        args.putString(EditorActivity.PATH, path);
//        fragment.getArguments().putAll(args);
        fragment.setArguments(args);

        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.editor, fragment);

        ft.addToBackStack(null);
        ft.commit();
    }
}
