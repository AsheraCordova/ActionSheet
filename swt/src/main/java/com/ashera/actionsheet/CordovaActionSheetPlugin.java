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
import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaActionSheetPlugin extends CordovaPlugin {
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		ActionSheetPlugin.initPlugin();
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
			int titleTextSize = options.optInt("titleTextSize", 16);

			String parentFragmentId = options.optString("parentFragmentId");

			String cancelTextColor = options.optString("cancelTextColor");
			String cancelBackgroundColor = options.optString("cancelBackgroundColor");
			String cancelHighlightColor = options.optString("cancelHighlightColor");
			String cancelFontFamily = options.optString("cancelFontFamily");
			int cancelTextSize = options.optInt("cancelTextSize", 16);

			String otherTextColor = options.optString("otherTextColor");
			String otherBackgroundColor = options.optString("otherBackgroundColor");
			String otherHighlightColor = options.optString("otherHighlightColor");
			String otherFontFamily = options.optString("otherFontFamily");
			int otherTextSize = options.optInt("otherTextSize", 16);

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
		final List<String> btns = new ArrayList<String>();

		int destructiveButtonIndex = -1;
		if (addDestructiveButtonWithLabel != null && !addDestructiveButtonWithLabel.isEmpty()
				&& !destructiveButtonLast) {
			destructiveButtonIndex = 0;
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
			destructiveButtonIndex = btns.size();
		}
		Display display = Display.getCurrent();
		Shell parentShell = display.getActiveShell();
		SWTActionSheet.show(parentShell, title, btns.toArray(new String[0]), 2, new ActionSheetListener() {
			@Override
			public void onSelect(int index, String title) {
				callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, index));
			}

			@Override
			public void onCancel() {
				boolean hasCancelButton = addCancelButtonWithLabel != null && !addCancelButtonWithLabel.isEmpty();
				callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, hasCancelButton ? btns.size() + 1 : -1));
			}
		}, parentFragmentId, addCancelButtonWithLabel, titleTextColor, titleBackgroundColor, titleFontFamily,
				titleTextSize, cancelTextColor, cancelBackgroundColor, cancelFontFamily, cancelTextSize,
				cancelHighlightColor, otherTextColor, otherBackgroundColor, otherFontFamily, otherTextSize,
				otherHighlightColor, destructiveButtonIndex, destructiveTextColor, destructiveBackgroundColor, destructiveFontFamily,
				destructiveTextSize, destructiveHighlightColor);

	}
}
