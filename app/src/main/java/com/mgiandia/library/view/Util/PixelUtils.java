package com.mgiandia.library.view.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class PixelUtils
{
    /**
     * Σχεδιάζει ένα oval σχήμα ενός χρώματος και δύο γράμματα στο κέντρο του.
     * @param context Το context που αφορά το συγκεκριμένο activity
     * @param iv Το ImageView πάνω στο οποίο θα τοποθετηθεί η εικόνα
     * @param name Το string από το οποίο θα εξαχθούν τα δύο γράμματα
     * @param colorResourceList Τα πιθανά χρώματα του oval
     */
    public static void drawInitialsImage(Context context, ImageView iv, String name, int[] colorResourceList)
    {
        int viewWidth = iv.getLayoutParams().width;
        int viewHeigth = iv.getLayoutParams().height;

        Bitmap bitmap = Bitmap.createBitmap(viewWidth, viewHeigth, Bitmap.Config.ARGB_8888);

        Paint colorPaint = new Paint();
        colorPaint.setColor(context.getResources().getColor(colorResourceList[Math.abs(name.hashCode()) % colorResourceList.length]));

        Canvas canvas = new Canvas(bitmap);
        canvas.translate(viewWidth/2f, viewHeigth/2f);
        canvas.drawCircle(0, 0, 90, colorPaint);

        iv.setImageBitmap(bitmap);
    }
}
