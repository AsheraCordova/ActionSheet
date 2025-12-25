//start - license
/*
 * Copyright (c) 2025 Ashera Cordova
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
//end - license
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 baoyongzhang <baoyz94@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.baoyz.actionsheet;

import java.util.HashMap;
import java.util.List;

import com.ashera.core.IFragment;
import com.ashera.widget.PluginInvoker;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * android-ActionSheet Created by baoyz on 15/6/30.
 */
@SuppressWarnings("ResourceType")
public class ActionSheet extends Fragment implements View.OnClickListener {
    private static final String ARG_OTHER_TEXTCOLOR = "ARG_OTHER_TEXTCOLOR";
    private static final String ARG_OTHER_BG_TINT = "ARG_OTHER_BG_TINT";
    private static final String ARG_OTHER_FONT_FAMILY = "ARG_OTHER_FONT_FAMILY";
    private static final String ARG_OTHER_TEXT_SIZE = "ARG_OTHER_TEXT_SIZE";
    private static final String ARG_OTHER_HIGHLIGHT_TINT = "ARG_OTHER_HIGHLIGHT_TINT";

    private static final String ARG_DESTRUCTIVE_TEXTCOLOR = "ARG_DESTRUCTIVE_TEXTCOLOR";
    private static final String ARG_DESTRUCTIVE_BG_TINT = "ARG_DESTRUCTIVE_BG_TINT";
    private static final String ARG_DESTRUCTIVE_FONT_FAMILY = "ARG_DESTRUCTIVE_FONT_FAMILY";
    private static final String ARG_DESTRUCTIVE_TEXT_SIZE = "ARG_DESTRUCTIVE_TEXT_SIZE";
    private static final String ARG_DESTRUCTIVE_HIGHLIGHT_TINT = "ARG_DESTRUCTIVE_HIGHLIGHT_TINT";
    private static final String ARG_DESTRUCTIVE_BTN_INDEX = "ARG_DESTRUCTIVE_BTN_INDEX";

    private static final String ARG_PARENT_FRAGMENT_ID = "ARG_PARENT_PARENT_FRAGMENT_ID";
    private static final String ARG_TITLE_BG = "ARG_TITLE_BG";
    private static final String ARG_TITLE_TEXTCOLOR = "ARG_TITLE_TEXTCOLOR";
    private static final String ARG_TITLE_BG_TINT = "ARG_TITLE_BG_TINT";
    private static final String ARG_TITLE_FONT_FAMILY = "ARG_TITLE_FONT_FAMILY";
    private static final String ARG_TITLE_TEXT_SIZE = "ARG_TITLE_TEXT_SIZE";

    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String ARG_TITLE_MARGIN_BOTTOM = "ARG_TITLE_MARGIN_BOTTOM";
    private static final String ARG_TITLE_PADDING = "ARG_TITLE_PADDING";

    private static final String ARG_CANCEL_BUTTON_TITLE = "cancel_button_title";
    private static final String ARG_OTHER_BUTTON_TITLES = "other_button_titles";
    private static final String ARG_CANCELABLE_ONTOUCHOUTSIDE = "cancelable_ontouchoutside";
    private static final String ARG_CANCEL_HIGHLIGHT_TINT = "ARG_CANCEL_HIGHLIGHT_TINT";
    private static final String ARG_CANCEL_BG = "ARG_CANCEL_BG";
    private static final String ARG_OTHERBUTTON_TOP_BG = "ARG_OTHERBUTTON_TOP_BG";
    private static final String ARG_OTHERBUTTON_MIDDLE_BG = "ARG_OTHERBUTTON_MIDDLE_BG";
    private static final String ARG_CANCEL_BTN_TXT_COLOR = "ARG_CANCEL_BTN_TXT_COLOR";

    private static final String ARG_PADDING = "ARG_PADDING";
    private static final String ARG_OTHERBUTTON_SINGLE_BG = "ARG_OTHERBUTTON_SINGLE_BG";
    private static final String ARG_OTHERBUTTON_BOTTOM_BG = "ARG_OTHERBUTTON_BOTTOM_BG";
    private static final String ARG_OTHERBUTTON_SPACING = "ARG_OTHERBUTTON_SPACING";
    private static final String ARG_CANCEL_BTN_TOP_MARGIN = "ARG_CANCEL_BTN_TOP_MARGIN";
    private static final int CANCEL_BUTTON_ID = 100;
    private static final int BG_VIEW_ID = 10;
    private static final int TITLE_VIEW_ID = 11;
    private static final int TRANSLATE_DURATION = 200;
    private static final int ALPHA_DURATION = 300;
    private static final String EXTRA_DISMISSED = "extra_dismissed";
    public static final String ARG_BG = "ARG_BG";

    private static final String ARG_CANCEL_TEXTCOLOR = "ARG_CANCEL_TEXTCOLOR";
    private static final String ARG_CANCEL_BG_TINT = "ARG_CANCEL_BG_TINT";
    private static final String ARG_CANCEL_FONT_FAMILY = "ARG_CANCEL_FONT_FAMILY";
    private static final String ARG_CANCEL_TEXT_SIZE = "ARG_CANCEL_TEXT_SIZE";

    private boolean mDismissed = true;
    private ActionSheetListener mListener;
    private View mView;
    private LinearLayout mPanel;
    private ViewGroup mGroup;
    private View mBg;
    private boolean isCancel = true;
    private FragmentManager fragmentManager;

    public void show(final FragmentManager manager, final String tag) {
        if (!mDismissed || manager.isDestroyed()) {
            return;
        }
        this.fragmentManager = manager;
        mDismissed = false;
        new Handler().post(new Runnable() {
            public void run() {
                FragmentTransaction ft = manager.beginTransaction();
                ft.add(ActionSheet.this, tag);
                //ft.addToBackStack(null);
                ft.commitAllowingStateLoss();
            }
        });
    }

    public void dismiss() {
        if (mDismissed) {
            return;
        }
        mDismissed = true;
        new Handler().post(new Runnable() {
            public void run() {
                //ActionSheet.this.fragmentManager.popBackStack();
                FragmentTransaction ft = ActionSheet.this.fragmentManager.beginTransaction();
                ft.remove(ActionSheet.this);
                ft.commitAllowingStateLoss();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(EXTRA_DISMISSED, mDismissed);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mDismissed = savedInstanceState.getBoolean(EXTRA_DISMISSED);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            View focusView = getActivity().getCurrentFocus();
            if (focusView != null) {
                imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
            }
        }

        mView = createView();
        mGroup = (ViewGroup) getActivity().getWindow().getDecorView();

        createItems();

        mGroup.addView(mView);
        mBg.startAnimation(createAlphaInAnimation());
        mPanel.startAnimation(createTranslationInAnimation());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        mPanel.startAnimation(createTranslationOutAnimation());
        mBg.startAnimation(createAlphaOutAnimation());
        mView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mGroup.removeView(mView);
            }
        }, ALPHA_DURATION);
        if (mListener != null) {
            mListener.onDismiss(this, isCancel);
        }
        super.onDestroyView();
    }

    private Animation createTranslationInAnimation() {
        int type = TranslateAnimation.RELATIVE_TO_SELF;
        TranslateAnimation an = new TranslateAnimation(type, 0, type, 0, type, 1, type, 0);
        an.setDuration(TRANSLATE_DURATION);
        return an;
    }

    private Animation createAlphaInAnimation() {
        AlphaAnimation an = new AlphaAnimation(0, 1);
        an.setDuration(ALPHA_DURATION);
        return an;
    }

    private Animation createTranslationOutAnimation() {
        int type = TranslateAnimation.RELATIVE_TO_SELF;
        TranslateAnimation an = new TranslateAnimation(type, 0, type, 0, type, 0, type, 1);
        an.setDuration(TRANSLATE_DURATION);
        an.setFillAfter(true);
        return an;
    }

    private Animation createAlphaOutAnimation() {
        AlphaAnimation an = new AlphaAnimation(1, 0);
        an.setDuration(ALPHA_DURATION);
        an.setFillAfter(true);
        return an;
    }

    private View createView() {
        FrameLayout parent = new FrameLayout(getActivity());
        parent.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        mBg = new View(getActivity());
        mBg.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        mBg.setBackgroundColor(Color.argb(136, 0, 0, 0));
        mBg.setId(ActionSheet.BG_VIEW_ID);
        mBg.setOnClickListener(this);

        mPanel = new LinearLayout(getActivity());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        mPanel.setLayoutParams(params);
        mPanel.setOrientation(LinearLayout.VERTICAL);
        parent.setPadding(0, 0, 0, getNavBarHeight(getActivity()));
        parent.addView(mBg);
        parent.addView(mPanel);
        return parent;
    }

    public int getNavBarHeight(Context context) {
        int navigationBarHeight = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Resources rs = context.getResources();
            int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
            if (id > 0) {
                navigationBarHeight = rs.getDimensionPixelSize(id);
            }
        }
        return navigationBarHeight;
    }

    private void createItems() {
        String title = getTitle();
        if (title != null) {
            android.widget.TextView titleTextView = new android.widget.TextView(getActivity());
            titleTextView.setBackground(getTitleBackground());
            titleTextView.setText(title);
            titleTextView.setOnClickListener(this);
            titleTextView.setId(TITLE_VIEW_ID);
            titleTextView.setGravity(Gravity.CENTER);
            titleTextView.setTextColor(this.getTitleTextColor());
            titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.getTitleTextSize());
            if (getTitleBackgroundTint() != null) {
                titleTextView.setBackgroundTintList(ColorStateList.valueOf(getTitleBackgroundTint()));
            }
            Typeface titleFontFamily = getTitleFontFamily();
            if (titleFontFamily != null) {
                titleTextView.setTypeface(titleFontFamily);
            }

            titleTextView.setPadding(getTitlePadding(), getTitlePadding(), getTitlePadding(), getTitlePadding());
            LinearLayout.LayoutParams params = createButtonLayoutParams();
            params.bottomMargin = this.getTitleMarginBottom();
            mPanel.addView(titleTextView, params);
        }

        String[] titles = getOtherButtonTitles();
        if (titles != null) {
            for (int i = 0; i < titles.length; i++) {
                Button bt = new Button(getActivity());
                bt.setAllCaps(false);
                bt.setId(CANCEL_BUTTON_ID + i + 1);
                bt.setOnClickListener(this);
                bt.setBackground(getOtherButtonBg(titles, i));
                bt.setText(titles[i]);
                int destructiveButtonIndex = getDestructiveButtonIndex();
                if (destructiveButtonIndex != -1 && destructiveButtonIndex  == i) {
                    bt.setTextColor(this.getDestructiveTextColor());
                    bt.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.getDestructiveTextSize());
                    if (getDestructiveHighlightTint() != null && getDestructiveBackgroundTint() != null) {
                        // Define the state sets
                        ColorStateList colorStateList = getColorStateList(getDestructiveHighlightTint(), getDestructiveBackgroundTint());
                        bt.setBackgroundTintList(colorStateList);
                    } else if (getDestructiveBackgroundTint() != null) {
                        bt.setBackgroundTintList(ColorStateList.valueOf(getDestructiveBackgroundTint()));
                    }

                    Typeface titleFontFamily = getDestructiveFontFamily();
                    if (titleFontFamily != null) {
                        bt.setTypeface(titleFontFamily);
                    }
                }  else {
                    bt.setTextColor(this.getOtherTextColor());
                    bt.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.getOtherTextSize());
                    if (getOtherHighlightTint() != null && getOtherBackgroundTint() != null) {
                        // Define the state sets
                        ColorStateList colorStateList = getColorStateList(getOtherHighlightTint(), getOtherBackgroundTint());
                        bt.setBackgroundTintList(colorStateList);
                    } else if (getOtherBackgroundTint() != null) {
                        bt.setBackgroundTintList(ColorStateList.valueOf(getOtherBackgroundTint()));
                    }

                    Typeface titleFontFamily = getOtherFontFamily();
                    if (titleFontFamily != null) {
                        bt.setTypeface(titleFontFamily);
                    }
                }

                if (i > 0) {
                    LinearLayout.LayoutParams params = createButtonLayoutParams();
                    params.topMargin = this.getOtherButtonSpacing();
                    mPanel.addView(bt, params);
                } else {
                    mPanel.addView(bt);
                }
            }
        }
        if (getCancelButtonTitle() != null && !getCancelButtonTitle().isEmpty()) {
	        Button bt = new Button(getActivity());
	        bt.setAllCaps(false);
	        bt.getPaint().setFakeBoldText(true);
	
	        bt.setId(ActionSheet.CANCEL_BUTTON_ID);
	        bt.setBackground(this.getCancelButtonBackground());
	        bt.setText(getCancelButtonTitle());
	        bt.setTextColor(this.getCancelButtonTextColor());
	        bt.setOnClickListener(this);
	
	        bt.setTextColor(this.getCancelTextColor());
	        bt.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.getCancelTextSize());
	        if (getCancelHighlightTint() != null && getCancelBackgroundTint() != null) {
	            // Define the state sets
	            ColorStateList colorStateList = getColorStateList(getCancelHighlightTint(), getCancelBackgroundTint());
	            bt.setBackgroundTintList(colorStateList);
	        }
	        else if (getCancelBackgroundTint() != null) {
	            bt.setBackgroundTintList(ColorStateList.valueOf(getCancelBackgroundTint()));
	        }
	        Typeface titleFontFamily = getCancelFontFamily();
	        if (titleFontFamily != null) {
	            bt.setTypeface(titleFontFamily);
	        }
	        LinearLayout.LayoutParams params = createButtonLayoutParams();
	        params.topMargin = this.getCancelButtonMarginTop();
	        mPanel.addView(bt, params);
        }

        mPanel.setBackground(this.getBackground());
        mPanel.setPadding(this.getPadding(), this.getPadding(), this.getPadding(), this.getPadding());
    }

    private ColorStateList getColorStateList(int pressedColor, int defaultColor) {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_pressed}, // pressed state
                new int[]{} // default state (empty array)
        };

        // Define the colors corresponding to the state sets
        int[] colors = new int[]{
                pressedColor,
                defaultColor
        };

        // Create the ColorStateList
        ColorStateList colorStateList = new ColorStateList(states, colors);
        return colorStateList;
    }

    public LinearLayout.LayoutParams createButtonLayoutParams() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        return params;
    }

    private Drawable getOtherButtonBg(String[] titles, int i) {
        if (titles.length == 1) {
            return this.getOtherButtonSingleBackground();
        }
        if (titles.length == 2) {
            switch (i) {
                case 0:
                    return this.getOtherButtonTopBackground();
                case 1:
                    return this.getOtherButtonBottomBackground();
            }
        }
        if (titles.length > 2) {
            if (i == 0) {
                return this.getOtherButtonTopBackground();
            }
            if (i == (titles.length - 1)) {
                return this.getOtherButtonBottomBackground();
            }
            return this.getOtherButtonMiddleBackground();
        }
        return null;
    }

    private String getCancelButtonTitle() {
        return getArguments().getString(ARG_CANCEL_BUTTON_TITLE);
    }

    private String[] getOtherButtonTitles() {
        return getArguments().getStringArray(ARG_OTHER_BUTTON_TITLES);
    }

    private boolean getCancelableOnTouchOutside() {
        return getArguments().getBoolean(ARG_CANCELABLE_ONTOUCHOUTSIDE);
    }

    public void setActionSheetListener(ActionSheetListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ActionSheet.BG_VIEW_ID && !getCancelableOnTouchOutside()) {
            return;
        }
        if (v.getId() == ActionSheet.TITLE_VIEW_ID) {
            return;
        }
        dismiss();
        if (v.getId() != ActionSheet.CANCEL_BUTTON_ID && v.getId() != ActionSheet.BG_VIEW_ID) {
            if (mListener != null) {
                mListener.onOtherButtonClick(this, v.getId() - CANCEL_BUTTON_ID - 1);
            }
            isCancel = false;
        }
    }

    public static Builder createBuilder(Context context, FragmentManager fragmentManager, String parentFragmentId) {
        return new Builder(context, fragmentManager, parentFragmentId);
    }

    public static class Builder {
        private final String parentFragmentId;
        private Context mContext;
        private FragmentManager mFragmentManager;
        private String mCancelButtonTitle;
        private String[] mOtherButtonTitles;
        private String mTag = "actionSheet";
        private boolean mCancelableOnTouchOutside;
        private ActionSheetListener mListener;

        private String title;

        private int titlePadding = -1;
        private int titleMarginBottom = -1;
        private Integer titleTextColor;
        private String titleBackground;
        private Integer titleBackgroundTint;
        private String titleFontFamily;
        private float titleTextSize = -1;
        private String background;
        private String cancelButtonBackground;
        private String otherButtonTopBackground;
        private String otherButtonMiddleBackground;
        private String otherButtonBottomBackground;
        private String otherButtonSingleBackground;
        private Integer cancelButtonTextColor;
        private int padding = -1;
        private int otherButtonSpacing = -1;
        private int cancelButtonMarginTop = -1;
        private Integer cancelTextColor;
        private Integer cancelBackgroundTint;
        private String cancelFontFamily;
        private float cancelTextSize = -1;
        private Integer cancelHighlightTint;

        private Integer otherTextColor;
        private Integer otherBackgroundTint;
        private String otherFontFamily;
        private float otherTextSize = -1;
        private Integer otherHighlightTint;

        private Integer destructiveTextColor;
        private Integer destructiveBackgroundTint;
        private String destructiveFontFamily;
        private float destructiveTextSize = -1;
        private Integer destructiveHighlightTint;
        private int destructiveButtonIndex = -1;

        public Builder setDestructiveHighlightTint(Integer destructiveHighlightTint) {
            this.destructiveHighlightTint = destructiveHighlightTint;
            return this;
        }
        public Builder setDestructiveBackgroundTint(Integer destructiveBackgroundTint) {
            this.destructiveBackgroundTint = destructiveBackgroundTint;
            return this;
        }

        public Builder setDestructiveFontFamily(String destructiveFontFamily) {
            this.destructiveFontFamily = destructiveFontFamily;
            return this;
        }

        public Builder setDestructiveTextSize(int destructiveTextSize) {
            this.destructiveTextSize = sp2px(destructiveTextSize);
            return this;
        }

        public Builder setDestructiveButtonIndex(int index) {
            this.destructiveButtonIndex = index;
            return this;
        }


        public Builder setDestructiveTextColor(int destructiveTextColor) {
            this.destructiveTextColor = destructiveTextColor;
            return this;
        }

        public Builder setCancelHighlightTint(Integer cancelHighlightTint) {
            this.cancelHighlightTint = cancelHighlightTint;
            return this;
        }

        public Builder setOtherHighlightTint(Integer otherHighlightTint) {
            this.otherHighlightTint = otherHighlightTint;
            return this;
        }
        public Builder setOtherBackgroundTint(Integer otherBackgroundTint) {
            this.otherBackgroundTint = otherBackgroundTint;
            return this;
        }

        public Builder setOtherFontFamily(String otherFontFamily) {
            this.otherFontFamily = otherFontFamily;
            return this;
        }

        public Builder setOtherTextSize(int otherTextSize) {
            this.otherTextSize = sp2px(otherTextSize);
            return this;
        }


        public Builder setOtherTextColor(int otherTextColor) {
            this.otherTextColor = otherTextColor;
            return this;
        }

        public Builder setBackground(String background) {
            this.background = background;
            return this;
        }

        public Builder setCancelBackgroundTint(Integer cancelBackgroundTint) {
            this.cancelBackgroundTint = cancelBackgroundTint;
            return this;
        }

        public Builder setCancelFontFamily(String cancelFontFamily) {
            this.cancelFontFamily = cancelFontFamily;
            return this;
        }

        public Builder setCancelTextSize(int cancelTextSize) {
            this.cancelTextSize = sp2px(cancelTextSize);
            return this;
        }

        public Builder setCancelButtonBackground(String cancelButtonBackground) {
            this.cancelButtonBackground = cancelButtonBackground;
            return this;
        }

        public Builder setOtherButtonTopBackground(String otherButtonTopBackground) {
            this.otherButtonTopBackground = otherButtonTopBackground;
            return this;
        }

        public Builder setOtherButtonMiddleBackground(String otherButtonMiddleBackground) {
            this.otherButtonMiddleBackground = otherButtonMiddleBackground;
            return this;
        }

        public Builder setOtherButtonBottomBackground(String otherButtonBottomBackground) {
            this.otherButtonBottomBackground = otherButtonBottomBackground;
            return this;
        }

        public Builder setOtherButtonSingleBackground(String otherButtonSingleBackground) {
            this.otherButtonSingleBackground = otherButtonSingleBackground;
            return this;
        }


        public Builder setTitleBackgroundTint(Integer titleBackgroundTint) {
            this.titleBackgroundTint = titleBackgroundTint;
            return this;
        }

        public Builder setTitleFontFamily(String titleFontFamily) {
            this.titleFontFamily = titleFontFamily;
            return this;
        }

        public Builder setCancelButtonTextColor(Integer cancelButtonTextColor) {
            this.cancelButtonTextColor = cancelButtonTextColor;
            return this;
        }

        public Builder setPadding(int padding) {
            this.padding = (int) dp2px(padding);
            return this;
        }


        public Builder setOtherButtonSpacing(int otherButtonSpacing) {
            this.otherButtonSpacing =  (int) dp2px(otherButtonSpacing);
            return this;
        }

        public Builder setCancelButtonMarginTop(int cancelButtonMarginTop) {
            this.cancelButtonMarginTop = (int) dp2px(cancelButtonMarginTop);
            return this;
        }

        public Builder(Context context, FragmentManager fragmentManager, String parentFragmentId) {
            mContext = context;
            mFragmentManager = fragmentManager;
            this.parentFragmentId = parentFragmentId;
        }

        public Builder setCancelButtonTitle(String title) {
            mCancelButtonTitle = title;
            return this;
        }

        public Builder setCancelButtonTitle(int strId) {
            return setCancelButtonTitle(mContext.getString(strId));
        }

        public Builder setCancelTextColor(int cancelTextColor) {
            this.cancelTextColor = cancelTextColor;
            return this;
        }

        public Builder setOtherButtonTitles(String... titles) {
            mOtherButtonTitles = titles;
            return this;
        }

        public Builder setTag(String tag) {
            mTag = tag;
            return this;
        }

        public Builder setListener(ActionSheetListener listener) {
            this.mListener = listener;
            return this;
        }

        public Builder setCancelableOnTouchOutside(boolean cancelable) {
            mCancelableOnTouchOutside = cancelable;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTitlePadding(int titlePadding) {
            this.titlePadding = (int) dp2px(titlePadding);
            return this;
        }

        public Builder setTitleMarginBottom(int titleMarginBottom) {
            this.titleMarginBottom = (int) dp2px(titleMarginBottom);
            return this;
        }

        public Builder setTitleTextColor(int titleTextColor) {
            this.titleTextColor = titleTextColor;
            return this;
        }

        public Builder setTitleTextSize(int titleTextSize) {
            this.titleTextSize = sp2px(titleTextSize);
            return this;
        }

        public Builder setTitleBackground(String titleBackground) {
            this.titleBackground = titleBackground;
            return this;
        }

        public Bundle prepareArguments() {
            Bundle bundle = new Bundle();
            bundle.putString(ARG_CANCEL_BUTTON_TITLE, mCancelButtonTitle);
            bundle.putStringArray(ARG_OTHER_BUTTON_TITLES, mOtherButtonTitles);
            bundle.putBoolean(ARG_CANCELABLE_ONTOUCHOUTSIDE, mCancelableOnTouchOutside);

            if (cancelButtonTextColor == null) {
                cancelButtonTextColor = Color.WHITE;
            }

            if (padding == -1) {
                padding = (int) dp2px(20);
            }

            if (otherButtonSpacing == -1) {
                otherButtonSpacing = (int) dp2px(2);
            }

            if (cancelButtonMarginTop == -1) {
                cancelButtonMarginTop = (int) dp2px(10);
            }

            if (titleTextSize == -1) {
                titleTextSize = sp2px(16);
            }

            if (titlePadding == -1) {
                titlePadding = (int) dp2px(20);
            }

            if (titleMarginBottom == -1) {
                titleMarginBottom = (int) dp2px(0);
            }

            if (titleTextColor == null) {
                titleTextColor = Color.BLACK;
            }

            if (cancelTextSize == -1) {
                cancelTextSize = sp2px(16);
            }

            if (otherTextSize == -1) {
                otherTextSize = sp2px(16);
            }

            if (destructiveTextSize == -1) {
                destructiveTextSize = sp2px(16);
            }

            bundle.putString(ARG_BG, background);
            bundle.putString(ARG_CANCEL_BG, cancelButtonBackground);
            bundle.putString(ARG_OTHERBUTTON_TOP_BG, otherButtonTopBackground);
            bundle.putString(ARG_OTHERBUTTON_MIDDLE_BG, otherButtonMiddleBackground);
            bundle.putString(ARG_OTHERBUTTON_SINGLE_BG, otherButtonSingleBackground);
            bundle.putString(ARG_OTHERBUTTON_BOTTOM_BG, otherButtonBottomBackground);
            bundle.putInt(ARG_CANCEL_BTN_TXT_COLOR, cancelButtonTextColor);
            bundle.putInt(ARG_PADDING, padding);
            bundle.putInt(ARG_OTHERBUTTON_SPACING, otherButtonSpacing);
            bundle.putInt(ARG_CANCEL_BTN_TOP_MARGIN, cancelButtonMarginTop);

            bundle.putString(ARG_TITLE, title);
            bundle.putString(ARG_PARENT_FRAGMENT_ID, parentFragmentId);
            bundle.putFloat(ARG_TITLE_TEXT_SIZE, titleTextSize);
            bundle.putInt(ARG_TITLE_PADDING, titlePadding);
            bundle.putInt(ARG_TITLE_MARGIN_BOTTOM, titleMarginBottom);
            bundle.putString(ARG_TITLE_BG, titleBackground);
            bundle.putInt(ARG_TITLE_TEXTCOLOR, titleTextColor);
            bundle.putSerializable(ARG_TITLE_BG_TINT, titleBackgroundTint);
            bundle.putString(ARG_TITLE_FONT_FAMILY, titleFontFamily);

            bundle.putFloat(ARG_CANCEL_TEXT_SIZE, cancelTextSize);
            bundle.putInt(ARG_CANCEL_TEXTCOLOR, cancelTextColor);
            bundle.putSerializable(ARG_CANCEL_BG_TINT, cancelBackgroundTint);
            bundle.putString(ARG_CANCEL_FONT_FAMILY, cancelFontFamily);
            bundle.putSerializable(ARG_CANCEL_HIGHLIGHT_TINT, cancelHighlightTint);

            bundle.putFloat(ARG_OTHER_TEXT_SIZE, otherTextSize);
            bundle.putInt(ARG_OTHER_TEXTCOLOR, otherTextColor);
            bundle.putSerializable(ARG_OTHER_BG_TINT, otherBackgroundTint);
            bundle.putString(ARG_OTHER_FONT_FAMILY, otherFontFamily);
            bundle.putSerializable(ARG_OTHER_HIGHLIGHT_TINT, otherHighlightTint);

            bundle.putFloat(ARG_DESTRUCTIVE_TEXT_SIZE, destructiveTextSize);
            bundle.putInt(ARG_DESTRUCTIVE_TEXTCOLOR, destructiveTextColor);
            bundle.putSerializable(ARG_DESTRUCTIVE_BG_TINT, destructiveBackgroundTint);
            bundle.putString(ARG_DESTRUCTIVE_FONT_FAMILY, destructiveFontFamily);
            bundle.putSerializable(ARG_DESTRUCTIVE_HIGHLIGHT_TINT, destructiveHighlightTint);
            bundle.putInt(ARG_DESTRUCTIVE_BTN_INDEX, destructiveButtonIndex);
            return bundle;
        }

        private float dp2px(float dp) {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                    mContext.getResources().getDisplayMetrics());
        }
        private float sp2px(float sp) {
        	return (float) PluginInvoker.getConverter("dimensionsp").convertFrom(sp + "sp", new HashMap<>(), com.ashera.core.FragmentRegistry.getInstance().find(parentFragmentId));
        }

        public ActionSheet show() {
            ActionSheet actionSheet = (ActionSheet) Fragment.instantiate(mContext, ActionSheet.class.getName(),
                    prepareArguments());
            actionSheet.setActionSheetListener(mListener);
            actionSheet.show(mFragmentManager, mTag);
            return actionSheet;
        }

    }

    public static interface ActionSheetListener {
        void onDismiss(ActionSheet actionSheet, boolean isCancel);

        void onOtherButtonClick(ActionSheet actionSheet, int index);
    }

    public Drawable getBackground() {
        return getDrawable(ARG_BG, Color.TRANSPARENT);
    }

    private Drawable getDrawable(String key, int defaultColor) {
        String value = getArguments().getString(key);
        if (value == null) {

            return new ColorDrawable(defaultColor);
        }

        Activity context = getActivity();

        int id = context.getResources().getIdentifier(value, "drawable", context.getPackageName());
        return context.getResources().getDrawable(id);
    }

    public Drawable getCancelButtonBackground() {
        return getDrawable(ARG_CANCEL_BG, Color.BLACK);
    }

    public Drawable getOtherButtonTopBackground() {
        return getDrawable(ARG_OTHERBUTTON_TOP_BG, Color.GRAY);
    }

    public Drawable getOtherButtonMiddleBackground() {
        return getDrawable(ARG_OTHERBUTTON_MIDDLE_BG, Color.GRAY);
    }

    public Drawable getOtherButtonBottomBackground() {
        return getDrawable(ARG_OTHERBUTTON_BOTTOM_BG, Color.GRAY);
    }

    public Drawable getOtherButtonSingleBackground() {
        return getDrawable(ARG_OTHERBUTTON_SINGLE_BG, Color.GRAY);
    }

    public int getPadding() {
        return getArguments().getInt(ARG_PADDING);
    }

    public int getOtherButtonSpacing() {
        return getArguments().getInt(ARG_OTHERBUTTON_SPACING);
    }

    public int getCancelButtonMarginTop() {
        return getArguments().getInt(ARG_CANCEL_BTN_TOP_MARGIN);
    }

    public int getCancelButtonTextColor() {
        return getArguments().getInt(ARG_CANCEL_BTN_TXT_COLOR);
    }

    public String getTitle() {
        return getArguments().getString(ARG_TITLE);
    }

    public int getTitlePadding() {
        return getArguments().getInt(ARG_TITLE_PADDING);
    }

    public int getTitleMarginBottom() {
        return getArguments().getInt(ARG_TITLE_MARGIN_BOTTOM);
    }

    public float getTitleTextSize() {
        return getArguments().getFloat(ARG_TITLE_TEXT_SIZE);
    }

    public Drawable getTitleBackground() {
        return getDrawable(ARG_TITLE_BG, Color.GRAY);
    }

    public Integer getTitleBackgroundTint() {
        return (Integer) getArguments().getSerializable(ARG_TITLE_BG_TINT);
    }

    public int getCancelTextColor() {
        return getArguments().getInt(ARG_CANCEL_TEXTCOLOR);
    }

    public Integer getCancelBackgroundTint() {
        return (Integer) getArguments().getSerializable(ARG_CANCEL_BG_TINT);
    }

    public Typeface getCancelFontFamily() {
        return getTypeface(ARG_CANCEL_FONT_FAMILY);
    }

    public float getCancelTextSize() {
        return getArguments().getFloat(ARG_CANCEL_TEXT_SIZE);
    }

    public Integer getCancelHighlightTint() {
        return (Integer) getArguments().getSerializable(ARG_CANCEL_HIGHLIGHT_TINT);
    }

    public Typeface getTitleFontFamily() {
        return getTypeface(ARG_TITLE_FONT_FAMILY);
    }

    private Typeface getTypeface(String argName) {
        String fontFamily = getArguments().getString(argName);
        if (fontFamily == null) {
            return null;
        }
        return (Typeface) PluginInvoker.getConverter("font").convertFrom(fontFamily, new HashMap<>(), getMyParentFragment());
    }
    
    public int getTitleTextColor() {
        return getArguments().getInt(ARG_TITLE_TEXTCOLOR);
    }

    public int getOtherTextColor() {
        return getArguments().getInt(ARG_OTHER_TEXTCOLOR);
    }

    public Integer getOtherHighlightTint() {
        return (Integer) getArguments().getSerializable(ARG_OTHER_HIGHLIGHT_TINT);
    }
    public Integer getOtherBackgroundTint() {
        return (Integer) getArguments().getSerializable(ARG_OTHER_BG_TINT);
    }

    public Typeface getOtherFontFamily() {
        return getTypeface(ARG_OTHER_FONT_FAMILY);
    }

    public float getOtherTextSize() {
        return getArguments().getFloat(ARG_OTHER_TEXT_SIZE);
    }
    public IFragment getMyParentFragment() {
//        return findFragmentByIdRecursive(requireActivity().getSupportFragmentManager(),
//                getArguments().getString(ARG_PARENT_FRAGMENT_ID));
    	return com.ashera.core.FragmentRegistry.getInstance().find(getArguments().getString(ARG_PARENT_FRAGMENT_ID));
    }

    public int getDestructiveTextColor() {
        return getArguments().getInt(ARG_DESTRUCTIVE_TEXTCOLOR);
    }

    public Integer getDestructiveHighlightTint() {
        return (Integer) getArguments().getSerializable(ARG_DESTRUCTIVE_HIGHLIGHT_TINT);
    }
    public Integer getDestructiveBackgroundTint() {
        return (Integer) getArguments().getSerializable(ARG_DESTRUCTIVE_BG_TINT);
    }

    public Typeface getDestructiveFontFamily() {
        return getTypeface(ARG_DESTRUCTIVE_FONT_FAMILY);
    }

    public float getDestructiveTextSize() {
        return getArguments().getFloat(ARG_DESTRUCTIVE_TEXT_SIZE);
    }

    public int getDestructiveButtonIndex() {
        return getArguments().getInt(ARG_DESTRUCTIVE_BTN_INDEX);
    }


    public static Fragment findFragmentByIdRecursive(
            FragmentManager fragmentManager,
            String parentFragmentId
    ) {
        // Try to find directly in this FragmentManager
        for (Fragment f : fragmentManager.getFragments()) {
            if (f instanceof IFragment) {
                if (parentFragmentId.equals(((IFragment)f).getUId())) {
                    return f;
                }
            }
        }

        // Otherwise, check all fragments and recurse into their child managers
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment f : fragments) {
            if (f != null) {
                Fragment result = findFragmentByIdRecursive(f.getChildFragmentManager(), parentFragmentId);
                if (result != null) return result;
            }
        }

        // Not found
        return null;
    }
}