package com.balius.filimo.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import org.jetbrains.annotations.NotNull;

public class PersianTextView extends AppCompatTextView {

    public PersianTextView(@NonNull @NotNull Context context) {
        super(context);
        setFont(context);
    }



    public PersianTextView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public PersianTextView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }


    private void setFont(@NonNull @NotNull Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSans_Bold.ttf");
        setTypeface(typeface);
    }

}
