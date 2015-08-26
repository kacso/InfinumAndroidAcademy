package co.infinum.academy.danijel_sokac.boatit.Enum.errors;

import android.support.annotation.StringRes;

import co.infinum.academy.danijel_sokac.boatit.R;

/**
 * Created by Danijel on 22.7.2015..
 */
public enum InternetConnectionErrorsEnum implements ErrorsEnum {
    IMAGE_DOWNLOAD_ERROR(R.string.image_download_error),
    COMMENT_DOWNLOAD_ERROR(R.string.comment_download_error),
    BOAT_DOWNLOAD_ERROR(R.string.boat_download_error),
    LOGIN_CONNECTION_ERROR(R.string.login_connection_error),
    OFFLINE_MODE(R.string.offline_mode),
    RATING_ERROR(R.string.rating_error),
    COMMENT_POST_ERROR(R.string.comment_post_error);

    private int code;

    private InternetConnectionErrorsEnum(@StringRes int code) {
        this.code = code;
    }

//    @Override
//    public String toString() {
//        return R.
//    }

    @Override
    public ErrorTypeEnum getType() {
        return ErrorTypeEnum.INTERNET_ERROR;
    }

    @Override
    public int getId() {
        return code;
    }

}
