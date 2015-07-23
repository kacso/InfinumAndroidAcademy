package co.infinum.academy.danijel_sokac.boatit.mvp.views;

import android.graphics.Bitmap;
import android.view.View;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Enum.errors.ErrorsEnum;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;

/**
 * Created by Danijel on 22.7.2015..
 */
public interface BoatDetailsView extends BaseView {
    public void onBoatTitleReceived(String title);
}
