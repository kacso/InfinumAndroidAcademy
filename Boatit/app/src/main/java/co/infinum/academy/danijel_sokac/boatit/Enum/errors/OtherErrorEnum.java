package co.infinum.academy.danijel_sokac.boatit.Enum.errors;

import android.support.annotation.StringRes;

import co.infinum.academy.danijel_sokac.boatit.R;

/**
 * Created by Danijel on 23.7.2015..
 */
public enum OtherErrorEnum implements ErrorsEnum{
    LOGIN_DATA_EMPTY(R.string.login_data_empty),
    LOGIN_FAILURE(R.string.login_failure),
    REGISTER_PASSWORD_NOT_MATCHED(R.string.register_passwords_not_matched),
    REGISTER_FAILURE(R.string.register_failure);

    private int code;

    private OtherErrorEnum(@StringRes int code) {
        this.code = code;
    }

    @Override
    public ErrorTypeEnum getType() {
        return ErrorTypeEnum.INTERNET_ERROR;
    }

    @Override
    public int getId() {
        return code;
    }
}
