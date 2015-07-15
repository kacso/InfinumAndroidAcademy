package co.infinum.academy.danijel_sokac.task3.Enums;

import android.graphics.Color;

import co.infinum.academy.danijel_sokac.task3.R;

/**
 * Created by Danijel on 15.7.2015..
 */
public enum ColorEnum {
    Black(Color.BLACK), Red(Color.RED), Blue(Color.BLUE), Green(Color.GREEN);
    public final int color;
    private ColorEnum(int color) {
        this.color = color;
    }
    public static int indexOf(String color) {
        switch (color) {
            case "Black": return 0;
            case "Red": return 1;
            case "Blue": return 2;
            case "Green": return 3;
            default: return 0;
        }
    }
}
