/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.xcy.fzb.project_attache.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xcy.fzb.R;


/**
 * IconGenerator generates icons that contain text (or custom content) within an info
 * window-like shape.
 * <p/>
 * The icon {@link Bitmap}s generated by the factory should be used in conjunction with a {@link
 * com.baidu.mapapi.map.BitmapDescriptorFactory}.
 * <p/>
 * This class is not thread safe.
 *
 *
 * *图标生成器生成包含文本(或自定义内容)的图标
 * *还有形状。
 * * < p / >
 * *由工厂生成的{@link位图}应该与{@link结合使用
 * * com.baidu.mapapi.map.BitmapDescriptorFactory}。
 * * < p / >
 * 这个类不是线程安全的。
 */
public class IconGenerator {
    private final Context mContext;

    private ViewGroup mContainer;
    private RotationLayout mRotationLayout;
    private TextView mTextView;
    private View mContentView;

    private int mRotation;

    private float mAnchorU = 0.5f;
    private float mAnchorV = 1f;

    /**
     * Creates a new IconGenerator with the default style.
     *
     * 创建一个默认样式的新图标生成器。
     */
    public IconGenerator(Context context) {
        mContext = context;
        mContainer = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.text_bubble, null);
        mRotationLayout = (RotationLayout) mContainer.getChildAt(0);
        mContentView = mTextView = (TextView) mRotationLayout.findViewById(R.id.text);
        setStyle(STYLE_DEFAULT);
    }

    /**
     * Sets the text content, then creates an icon with the current style.
     *
     * @param text the text content to display inside the icon.
     *
     * *设置文本内容，然后创建一个当前样式的图标。
     * *
     * * @param text要显示在图标内的文本内容。
     */
    public Bitmap makeIcon(String text) {
        if (mTextView != null) {
            mTextView.setText(text);
        }

        return makeIcon();
    }

    /**
     * Creates an icon with the current content and style.
     * <p/>
     * This method is useful if a custom view has previously been set, or if text content is not
     * applicable.
     *
     * 创建当前内容和样式的图标。
     * * < p / >
     * *如果之前设置了自定义视图，或者没有设置文本内容，则此方法非常有用
     * *适用。
     */
    public Bitmap makeIcon() {
        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mContainer.measure(measureSpec, measureSpec);

        int measuredWidth = mContainer.getMeasuredWidth();
        int measuredHeight = mContainer.getMeasuredHeight();

        mContainer.layout(0, 0, measuredWidth, measuredHeight);

        if (mRotation == 1 || mRotation == 3) {
            measuredHeight = mContainer.getMeasuredWidth();
            measuredWidth = mContainer.getMeasuredHeight();
        }

        Bitmap r = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        r.eraseColor(Color.TRANSPARENT);

        Canvas canvas = new Canvas(r);

        if (mRotation != 0) {
            // do nothing
            if (mRotation == 1) {
                canvas.translate(measuredWidth, 0);
                canvas.rotate(90);
            } else if (mRotation == 2) {
                canvas.rotate(180, measuredWidth / 2, measuredHeight / 2);
            } else {
                canvas.translate(0, measuredHeight);
                canvas.rotate(270);
            }
        }
        mContainer.draw(canvas);
        return r;
    }

    /**
     * Sets the child view for the icon.
     * <p/>
     * If the view contains a {@link TextView} with the id "text", operations such as {@link
     * #setTextAppearance} and {@link #makeIcon(String)} will operate upon that {@link TextView}.
     *
     * *设置图标的子视图。
     * * < p / >
     * *如果视图包含id为“text”的{@link TextView}，则操作如{@link
     * * * setTextAppearance}和{@link #makeIcon(String)}将对该{@link TextView}进行操作。
     */
    public void setContentView(View contentView) {
        mRotationLayout.removeAllViews();
        mRotationLayout.addView(contentView);
        mContentView = contentView;
        final View view = mRotationLayout.findViewById(R.id.text);
        mTextView = view instanceof TextView ? (TextView) view : null;
    }

    /**
     * Rotates the contents of the icon.
     *
     * @param degrees the amount the contents should be rotated, as a multiple of 90 degrees.
     *
     * *旋转图标的内容。
     * *
     * * @param度应该将内容旋转为90度的倍数。
     */
    public void setContentRotation(int degrees) {
        mRotationLayout.setViewRotation(degrees);
    }

    /**
     * Rotates the icon.
     * 旋转图标。
     *
     * @param degrees the amount the icon should be rotated, as a multiple of 90 degrees.
     */
    public void setRotation(int degrees) {
        mRotation = ((degrees + 360) % 360) / 90;
    }


    /**
     * @return u coordinate of the anchor, with rotation applied.
     */
    public float getAnchorU() {
        return rotateAnchor(mAnchorU, mAnchorV);
    }

    /**
     * @return v coordinate of the anchor, with rotation applied.
     */
    public float getAnchorV() {
        return rotateAnchor(mAnchorV, mAnchorU);
    }

    /**
     * Rotates the anchor around (u, v) = (0, 0).
     */
    private float rotateAnchor(float u, float v) {
        switch (mRotation) {
            case 0:
                return u;
            case 1:
                return 1 - v;
            case 2:
                return 1 - u;
            case 3:
                return v;
            default:
        }
        throw new IllegalStateException();
    }

    /**
     * Sets the text color, size, style, hint color, and highlight color from the specified
     * <code>TextAppearance</code> resource.
     *
     * *设置文本的颜色、大小、样式、提示颜色和高亮颜色
     * * <代码> TextAppearance > < /代码资源。
     *
     * @param resid the identifier of the resource.
     */
    public void setTextAppearance(Context context, int resid) {
        if (mTextView != null) {
            mTextView.setTextAppearance(context, resid);
        }
    }

    /**
     * Sets the text color, size, style, hint color, and highlight color from the specified
     * <code>TextAppearance</code> resource.
     *
     * @param resid the identifier of the resource.
     */
    public void setTextAppearance(int resid) {
        setTextAppearance(mContext, resid);
    }

    /**
     * Sets the style of the icon. The style consists of a background and text appearance.
     */
    public void setStyle(int style) {
        setTextAppearance(mContext, getTextStyle(style));
    }

    /**
     * Set the background to a given Drawable, or remove the background.
     *
     * @param background the Drawable to use as the background, or null to remove the background.
     */
    @SuppressWarnings("deprecation")
    // View#setBackgroundDrawable is compatible with pre-API level 16 (Jelly Bean).
    public void setBackground(Drawable background) {
        mContainer.setBackgroundDrawable(background);

        // Force setting of padding.
        // setBackgroundDrawable does not call setPadding if the background has 0 padding.
        if (background != null) {
            Rect rect = new Rect();
            background.getPadding(rect);
            mContainer.setPadding(rect.left, rect.top, rect.right, rect.bottom);
        } else {
            mContainer.setPadding(0, 0, 0, 0);
        }
    }

    /**
     * Sets the padding of the content view. The default padding of the content view (i.e. text
     * view) is 5dp top/bottom and 10dp left/right.
     *
     * *设置内容视图的填充。内容视图的默认填充(即文本)
     * *视图)是5dp的顶部/底部和10dp的左侧/右侧。
     *
     * @param left   the left padding in pixels.
     * @param top    the top padding in pixels.
     * @param right  the right padding in pixels.
     * @param bottom the bottom padding in pixels.
     */
    public void setContentPadding(int left, int top, int right, int bottom) {
        mContentView.setPadding(left, top, right, bottom);
    }

    public static final int STYLE_DEFAULT = 1;
    public static final int STYLE_WHITE = 2;
    public static final int STYLE_RED = 3;
    public static final int STYLE_BLUE = 4;
    public static final int STYLE_GREEN = 5;
    public static final int STYLE_PURPLE = 6;
    public static final int STYLE_ORANGE = 7;

    private static int getStyleColor(int style) {
        switch (style) {
            default:
            case STYLE_DEFAULT:
            case STYLE_WHITE:
                return 0xffffffff;
            case STYLE_RED:
                return 0xffcc0000;
            case STYLE_BLUE:
                return 0xff0099cc;
            case STYLE_GREEN:
                return 0xff669900;
            case STYLE_PURPLE:
                return 0xff9933cc;
            case STYLE_ORANGE:
                return 0xffff8800;
        }
    }

    private static int getTextStyle(int style) {
        switch (style) {
            default:
            case STYLE_DEFAULT:
            case STYLE_WHITE:
                return R.style.Bubble_TextAppearance_Dark;
            case STYLE_RED:
            case STYLE_BLUE:
            case STYLE_GREEN:
            case STYLE_PURPLE:
            case STYLE_ORANGE:
                return R.style.Bubble_TextAppearance_Light;
        }
    }
}