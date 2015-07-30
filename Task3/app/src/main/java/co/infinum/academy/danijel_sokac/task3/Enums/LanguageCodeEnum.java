package co.infinum.academy.danijel_sokac.task3.Enums;

/**
 * Created by Danijel on 13.7.2015..
 */
public class LanguageCodeEnum {
    public enum Language {
        English("en"), Hrvatski("hr"), Deutsch("de"),Italiano("it"), Espanol("es");

        public final String code;
        private Language(String code) {
            this.code = code;
        }
    }

    public static int indexOf(String code) {
        switch (code) {
            case "english": return 0;
            case "hrvatski": return 1;
            case "deutsch": return 2;
            case "italiano": return 3;
            case "espaňol": return 4;
            default: return 0;
        }
    }
}
