package co.infinum.academy.danijel_sokac.task3.Implementation;

import android.content.Context;
import android.support.v4.app.Fragment;

import co.infinum.academy.danijel_sokac.task3.interfaces.AlertAction;

/**
 * Created by Danijel on 16.7.2015..
 */
public class DestroyFragmentAction implements AlertAction {
    Context context;
    Fragment fragment;
    public DestroyFragmentAction (Context c, Fragment f) {
        context = context;
        fragment = f;
    }
    @Override
    public void action() {
        fragment.getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();

    }
}
