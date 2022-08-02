package com.balius.filimo.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class PersianBoldTextView extends AppCompatTextView {


    private void setfont(@NonNull Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansMobile_Bold.ttf");
        setTypeface(typeface);
    }

    public PersianBoldTextView(@NonNull Context context) {
        super(context);
        setfont(context);
    }

    public PersianBoldTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setfont(context);
    }

    public PersianBoldTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setfont(context);
    }
}
