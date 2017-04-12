package utils;

import android.content.Context;
import android.graphics.Typeface;


public class FontUtils {
    private final static String BOLD = "NotoSans-Bold.ttf";
    private final static String DEFAULT = "NotoSans-Regular.ttf";


    public static Typeface getBoldTypeFace(Context context ) {
        return getMainTypeFace(context, BOLD);
    }

    public static Typeface getDefaultTypeFace(Context context) {
        return getMainTypeFace(context, DEFAULT);
    }

    private static Typeface getMainTypeFace(Context context, String fontName) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);
    }
}
