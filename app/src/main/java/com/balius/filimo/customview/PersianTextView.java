package com.balius.filimo.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import org.jetbrains.annotations.NotNull;

public class PersianTextView extends AppCompatTextView {


    public PersianTextView(@NonNull Context context) {
        super(context);
        setfont(context);
    }

    private void setfont(@NonNull Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansMobile.ttf");
        setTypeface(typeface);
    }

    public PersianTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setfont(context);
    }

    public PersianTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setfont(context);
    }
}
