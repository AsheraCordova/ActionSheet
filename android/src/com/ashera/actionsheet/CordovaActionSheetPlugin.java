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
package com.ashera.actionsheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ashera.widget.PluginInvoker;

import android.graphics.Color;

public class CordovaActionSheetPlugin extends CordovaPlugin {

	public CordovaActionSheetPlugin() {
		super();
	}

	private static final String ACTION_SHOW = "show";

	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		if (ACTION_SHOW.equals(action)) {
			JSONObject options = args.optJSONObject(0);

			String title = options.optString("title");
			JSONArray buttons = options.optJSONArray("buttonLabels");

			boolean destructiveButtonLast = options.optBoolean("destructiveButtonLast", false);

			String addCancelButtonWithLabel = options.optString("addCancelButtonWithLabel");
			String addDestructiveButtonWithLabel = options.optString("addDestructiveButtonWithLabel");

			String titleTextColor = options.optString("titleTextColor");
			String titleBackgroundColor = options.optString("titleBackgroundColor");
			String titleFontFamily = options.optString("titleFontFamily");
			int titleTextSize = options.optInt("titleTextSize", -1);

			String parentFragmentId = options.optString("parentFragmentId");

			String cancelTextColor = options.optString("cancelTextColor");
			String cancelBackgroundColor = options.optString("cancelBackgroundColor");
			String cancelHighlightColor = options.optString("cancelHighlightColor");
			String cancelFontFamily = options.optString("cancelFontFamily");
			int cancelTextSize = options.optInt("cancelTextSize", -1);

			String otherTextColor = options.optString("otherTextColor");
			String otherBackgroundColor = options.optString("otherBackgroundColor");
			String otherHighlightColor = options.optString("otherHighlightColor");
			String otherFontFamily = options.optString("otherFontFamily");
			int otherTextSize = options.optInt("otherTextSize", -1);

			String destructiveTextColor = options.optString("destructiveTextColor");
			String destructiveBackgroundColor = options.optString("destructiveBackgroundColor");
			String destructiveHighlightColor = options.optString("destructiveHighlightColor");
			String destructiveFontFamily = options.optString("destructiveFontFamily");
			int destructiveTextSize = options.optInt("destructiveTextSize", -1);

			this.show(parentFragmentId, title, buttons, addCancelButtonWithLabel, addDestructiveButtonWithLabel,
					destructiveButtonLast, titleTextColor, titleBackgroundColor, titleFontFamily, titleTextSize,
					cancelTextColor, cancelBackgroundColor, cancelFontFamily, cancelTextSize, cancelHighlightColor,
					otherTextColor, otherBackgroundColor, otherFontFamily, otherTextSize, otherHighlightColor,
					destructiveTextColor, destructiveBackgroundColor, destructiveFontFamily, destructiveTextSize,
					destructiveHighlightColor, callbackContext);
			// need to return as this call is async.
			return true;
		}

		return false;
	}

	public synchronized void show(final String parentFragmentId, final String title, final JSONArray buttonLabels,
			final String addCancelButtonWithLabel, final String addDestructiveButtonWithLabel,
			final boolean destructiveButtonLast, final String titleTextColor, final String titleBackgroundColor,
			final String titleFontFamily, final int titleTextSize, final String cancelTextColor,
			final String cancelBackgroundColor, final String cancelFontFamily, final int cancelTextSize,
			final String cancelHighlightColor, final String otherTextColor, final String otherBackgroundColor,
			final String otherFontFamily, final int otherTextSize, final String otherHighlightColor,
			final String destructiveTextColor, final String destructiveBackgroundColor,
			final String destructiveFontFamily, final int destructiveTextSize, final String destructiveHighlightColor,
			final CallbackContext callbackContext) {
		final CordovaInterface cordova = this.cordova;
		final List<String> btns = new ArrayList<String>();

		if (addDestructiveButtonWithLabel != null && !addDestructiveButtonWithLabel.isEmpty()
				&& !destructiveButtonLast) {
			btns.add(addDestructiveButtonWithLabel);
		}
		if (buttonLabels != null) {
			for (int i = 0; i < buttonLabels.length(); i++) {
				btns.add(buttonLabels.optString(i));
			}
		}

		if (addDestructiveButtonWithLabel != null && !addDestructiveButtonWithLabel.isEmpty()
				&& destructiveButtonLast) {
			btns.add(addDestructiveButtonWithLabel);
		}

		Runnable runnable = new Runnable() {
			public void run() {
				com.baoyz.actionsheet.ActionSheet.Builder builder = com.baoyz.actionsheet.ActionSheet
						.createBuilder(cordova.getContext(), cordova.getActivity().getSupportFragmentManager(),
								parentFragmentId)
						.setOtherButtonTitles(btns.toArray(new String[0])).setCancelableOnTouchOutside(true);

				if (title != null) {
					builder.setTitle(title);
				}
				if (addCancelButtonWithLabel != null) {
					builder.setCancelButtonTitle(addCancelButtonWithLabel);
				}
				builder.setOtherButtonSpacing(0);
				builder.setCancelButtonMarginTop(10);
				builder.setCancelButtonBackground("slt_as_ios7_cancel_bt");
				builder.setOtherButtonBottomBackground("slt_as_ios7_other_bt_bottom");
				builder.setOtherButtonMiddleBackground("slt_as_ios7_other_bt_middle");
				builder.setOtherButtonSingleBackground("slt_as_ios7_other_bt_single");
				builder.setOtherButtonTopBackground("slt_as_ios7_other_bt_middle");
				builder.setTitleBackground("actionsheet_top_normal");

				// cancel button text color/bg/font/textsize
				if (cancelTextColor != null && !cancelTextColor.isEmpty()) {
					builder.setCancelTextColor(getColor(cancelTextColor, parentFragmentId));
				} else {
					builder.setCancelTextColor(getColor("#1E82FF", parentFragmentId));
				}

				if (cancelBackgroundColor != null && !cancelBackgroundColor.isEmpty()) {
					builder.setCancelBackgroundTint(getColor(cancelBackgroundColor, parentFragmentId));
				}

				if (cancelHighlightColor != null && !cancelHighlightColor.isEmpty()) {
					builder.setCancelHighlightTint(getColor(cancelHighlightColor, parentFragmentId));
				}

				if (cancelFontFamily != null && !cancelFontFamily.isEmpty()) {
					builder.setCancelFontFamily(cancelFontFamily);
				}

				builder.setCancelTextSize(cancelTextSize);

				// title button text color/bg/font/textsize
				if (titleTextColor != null && !titleTextColor.isEmpty()) {
					builder.setTitleTextColor(getColor(titleTextColor, parentFragmentId));
				} else {
					builder.setTitleTextColor(getColor("#000000", parentFragmentId));
				}

				if (titleBackgroundColor != null && !titleBackgroundColor.isEmpty()) {
					builder.setTitleBackgroundTint(getColor(titleBackgroundColor, parentFragmentId));
				}

				if (titleFontFamily != null && !titleFontFamily.isEmpty()) {
					builder.setTitleFontFamily(titleFontFamily);
				}

				builder.setTitleTextSize(titleTextSize);

				// other button text color/bg/font/textsize
				if (otherTextColor != null && !otherTextColor.isEmpty()) {
					builder.setOtherTextColor(getColor(otherTextColor, parentFragmentId));
				} else {
					builder.setOtherTextColor(getColor("#1E82FF", parentFragmentId));
				}

				if (otherBackgroundColor != null && !otherBackgroundColor.isEmpty()) {
					builder.setOtherBackgroundTint(getColor(otherBackgroundColor, parentFragmentId));
				}

				if (otherHighlightColor != null && !otherHighlightColor.isEmpty()) {
					builder.setOtherHighlightTint(getColor(otherHighlightColor, parentFragmentId));
				}

				if (otherFontFamily != null && !otherFontFamily.isEmpty()) {
					builder.setOtherFontFamily(otherFontFamily);
				}

				builder.setOtherTextSize(otherTextSize);

				// destructive button text color/bg/font/textsize
				if (destructiveTextColor != null && !destructiveTextColor.isEmpty()) {
					builder.setDestructiveTextColor(getColor(destructiveTextColor, parentFragmentId));
				} else {
					builder.setDestructiveTextColor(getColor("#1E82FF", parentFragmentId));
				}

				if (destructiveBackgroundColor != null && !destructiveBackgroundColor.isEmpty()) {
					builder.setDestructiveBackgroundTint(getColor(destructiveBackgroundColor, parentFragmentId));
				}

				if (destructiveHighlightColor != null && !destructiveHighlightColor.isEmpty()) {
					builder.setDestructiveHighlightTint(getColor(destructiveHighlightColor, parentFragmentId));
				}

				if (destructiveFontFamily != null && !destructiveFontFamily.isEmpty()) {
					builder.setDestructiveFontFamily(destructiveFontFamily);
				}

				builder.setDestructiveTextSize(destructiveTextSize);
				int destructiveButtonIndex = -1;

				if (addDestructiveButtonWithLabel != null && !addDestructiveButtonWithLabel.isEmpty()) {
					if (destructiveButtonLast) {
						destructiveButtonIndex = btns.size() - 1;
					} else {
						destructiveButtonIndex = 0;
					}
				}
				builder.setDestructiveButtonIndex(destructiveButtonIndex);

				builder.setListener(new com.baoyz.actionsheet.ActionSheet.ActionSheetListener() {
					@Override
					public void onDismiss(com.baoyz.actionsheet.ActionSheet actionSheet, boolean isCancel) {
						boolean hasCancelButton = addCancelButtonWithLabel != null && addCancelButtonWithLabel.isEmpty();
						callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, hasCancelButton ? btns.size() + 1 : -1));
					}

					@Override
					public void onOtherButtonClick(com.baoyz.actionsheet.ActionSheet actionSheet, int index) {
						callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, index + 1));
					}
				});
				builder.show();
			}
		};
		this.cordova.getActivity().runOnUiThread(runnable);
	}
	
    
    private int getColor(String color, String parentFragmentId) {
        return (Integer) PluginInvoker.getConverter("color").convertFrom(color, new HashMap<>(), com.ashera.core.FragmentRegistry.getInstance().find(parentFragmentId));
    }
}
