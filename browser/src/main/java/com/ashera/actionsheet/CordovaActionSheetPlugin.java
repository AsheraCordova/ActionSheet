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
import java.util.Map;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;

import com.ashera.core.IFragment;
import com.ashera.layout.ViewImpl;
import com.ashera.widget.PluginInvoker;

public class CordovaActionSheetPlugin extends CordovaPlugin {
	private static final int DEFAULT_TEXT_SIZE = 16;

	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		ActionSheetPlugin.initPlugin();
	}

	private static final String ACTION_SHOW = "show";

	@Override
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
			int titleTextSize = options.optInt("titleTextSize", DEFAULT_TEXT_SIZE);

			String parentFragmentId = options.optString("parentFragmentId");

			String cancelTextColor = options.optString("cancelTextColor");
			String cancelBackgroundColor = options.optString("cancelBackgroundColor");
			String cancelHighlightColor = options.optString("cancelHighlightColor");
			String cancelFontFamily = options.optString("cancelFontFamily");
			int cancelTextSize = options.optInt("cancelTextSize", DEFAULT_TEXT_SIZE);

			String otherTextColor = options.optString("otherTextColor");
			String otherBackgroundColor = options.optString("otherBackgroundColor");
			String otherHighlightColor = options.optString("otherHighlightColor");
			String otherFontFamily = options.optString("otherFontFamily");
			int otherTextSize = options.optInt("otherTextSize", DEFAULT_TEXT_SIZE);

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
			final boolean destructiveButtonLast, String titleTextColor, final String titleBackgroundColor,
			final String titleFontFamily, final int titleTextSize, String cancelTextColor,
			final String cancelBackgroundColor, final String cancelFontFamily, final int cancelTextSize,
			final String cancelHighlightColor, String otherTextColor, final String otherBackgroundColor,
			final String otherFontFamily, final int otherTextSize, final String otherHighlightColor,
			String destructiveTextColor, final String destructiveBackgroundColor,
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

		HTMLDocument document = Window.current().getDocument();
		HTMLElement actionSheetContainer = getShadowRootElementById("actionSheetProxyContainer");
		if (actionSheetContainer == null) {
			HTMLElement shadowRoot = getShadowRoot();
			actionSheetContainer = document.createElement("div");
			actionSheetContainer.setAttribute("class", "action-sheet-container");
			actionSheetContainer.setAttribute("id", "actionSheetProxyContainer");

			shadowRoot.appendChild(actionSheetContainer);
		}

		actionSheetContainer.clear();
		removeHide(actionSheetContainer);

		HTMLElement panel = document.createElement("div");
		panel.setAttribute("class", "action-sheet-panel");
		panel.setAttribute("id", "actionSheetPanel");
		actionSheetContainer.appendChild(panel);

		int position = 1;
		if (title != null && !title.isEmpty()) {
			HTMLElement titleElement = document.createElement("h3");
			titleElement.setAttribute("class", "action-sheet-title");
			titleElement.setInnerHTML(title);
			panel.appendChild(titleElement);

			// title button text color/bg/font/textsize
			if (titleTextColor == null || titleTextColor.isEmpty()) {
				titleTextColor = "#000000";
			}
			customizeHtmlElement(titleElement, titleTextColor, titleBackgroundColor, "", titleFontFamily, titleTextSize,
					parentFragmentId);
		}

		for (int i = 0; i < btns.size(); i++) {
			HTMLElement btn = document.createElement("button");
			btn.setAttribute("value", position + "");
			btn.setAttribute("class", "action-sheet-button action-sheet-normal-button");
			btn.setInnerHTML(btns.get(i));
			addClickListener(callbackContext, btn);
			panel.appendChild(btn);
			position++;
			
			// other button text color/bg/font/textsize
			if (otherTextColor == null || otherTextColor.isEmpty()) {
				otherTextColor = "#1E82FF";
			}

			customizeHtmlElement(btn, otherTextColor, otherBackgroundColor, otherHighlightColor, otherFontFamily, otherTextSize,
					parentFragmentId);
		}

		boolean hasCancelButton = addCancelButtonWithLabel != null && !addCancelButtonWithLabel.isEmpty();
		if (hasCancelButton) {
			HTMLElement btn = document.createElement("button");
			btn.setAttribute("value", position + "");
			btn.setAttribute("class", "action-sheet-button action-sheet-cancel-button action-sheet-cancel-wrap");
			btn.setInnerHTML(addCancelButtonWithLabel);
			addClickListener(callbackContext, btn);
			actionSheetContainer.appendChild(btn);

			// cancel button text color/bg/font/textsize
			if (cancelTextColor == null || cancelTextColor.isEmpty()) {
				cancelTextColor = "#1E82FF";
			}
			customizeHtmlElement(btn, cancelTextColor, cancelBackgroundColor, cancelHighlightColor, cancelFontFamily, cancelTextSize,
					parentFragmentId);
		}

		addClickListener(callbackContext, actionSheetContainer);
		actionSheetContainer.setAttribute("value", hasCancelButton ? position + "" : "-1");
		position++;

		if (destructiveButtonIndex != -1) {
			// destructive button text color/bg/font/textsize
			HTMLElement btn = (HTMLElement) panel.getChildNodes().get(destructiveButtonIndex);

			if (destructiveTextColor == null || destructiveTextColor.isEmpty()) {
				destructiveTextColor = "#1E82FF";
			}
			customizeHtmlElement(btn, destructiveTextColor, destructiveBackgroundColor, destructiveHighlightColor, destructiveFontFamily, destructiveTextSize,
					parentFragmentId);
		}

	}

	private void customizeHtmlElement(HTMLElement htmlElement, String textColor, String backgroundColor,
			String highlightColor, String fontFamily, int textSize, final String parentFragmentId) {
		setTextColor(htmlElement, textColor, parentFragmentId);

		if (backgroundColor != null && !backgroundColor.isEmpty()) {
			htmlElement.getStyle().setProperty("background-color", backgroundColor);
		}

		if (highlightColor != null && !highlightColor.isEmpty()) {
			setTextColorHighlight(htmlElement, highlightColor);
		}

		if (fontFamily != null && !fontFamily.isEmpty()) {
			setFontFamily(htmlElement, fontFamily, parentFragmentId);
		}

		setTextSize(htmlElement, textSize, parentFragmentId);
	}

	private void setTextColorHighlight(HTMLElement htmlElement, String textColorHightlight) {
		String colorCss = "textcolorhighlight_" + textColorHightlight.replaceAll("#", "");
		if (htmlElement.getClassName().indexOf("textcolorhighlight_") != -1) {
			htmlElement.setClassName(htmlElement.getClassName().replaceAll("textcolorhighlight_([0-9|A-Z])*\\s?", ""));
		}
		if (htmlElement.getClassName().indexOf(colorCss) == -1) {
			htmlElement.setClassName(colorCss + " " + (htmlElement.getClassName() == null ? "" : htmlElement.getClassName()));
		}
	}

	private void setFontFamily(HTMLElement htmlElement, String fontName, final String parentFragmentId) {
		Map<String, com.ashera.model.FontDescriptor> fontDescriptors = (Map<String, com.ashera.model.FontDescriptor>) PluginInvoker
				.getConverter("font").convertFrom(fontName, new HashMap<>(), getMyParentFragment(parentFragmentId));
		String weight = "400";
		String fontStyle = "normal";
		com.ashera.model.FontDescriptor fontDescriptor = fontDescriptors.get(fontStyle + "_" + weight);
		htmlElement.getStyle().setProperty("font-family", fontDescriptor.getName());
	}
	
	private void setTextSize(HTMLElement htmlElement, int textSize, final String parentFragmentId) {
		Object objValue = PluginInvoker.getConverter("dimensionsp").convertFrom(textSize + "sp", new HashMap<>(), getMyParentFragment(parentFragmentId));
		htmlElement.getStyle().setProperty("font-size", ((float)objValue * getMyParentFragment(parentFragmentId).getRootActivity().getScaleFactor()) + "px" );
	}
	
	private void setTextColor(HTMLElement htmlElement, String color, final String parentFragmentId) {
		Object objValue = PluginInvoker.getConverter("color").convertFrom(color, new HashMap<>(), getMyParentFragment(parentFragmentId));
		htmlElement.getStyle().setProperty("color", (String) objValue);
	}

	private void removeHide(HTMLElement actionSheetContainer2) {
		actionSheetContainer2.setClassName(
				actionSheetContainer2.getClassName().replace(" hide", "").replace("hide ", "").replace("hide", ""));
	}

	private void addClickListener(final CallbackContext callbackContext, HTMLElement btn) {
		btn.addEventListener("click", (e) -> {
			e.preventDefault();
			e.stopPropagation();

			HTMLElement container = getShadowRootElementById("actionSheetProxyContainer");
			HTMLElement panel = getShadowRootElementById("actionSheetPanel");

			if (container == null || panel == null)
				return;

			// Add animation classes
			panel.setClassName(panel.getClassName() + " hide");
			container.setClassName(container.getClassName() + " hide");
			callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, btn.getAttribute("value")));

		});
	}

	public IFragment getMyParentFragment(String fragmentId) {
		return com.ashera.core.FragmentRegistry.getInstance().find(fragmentId);
	}

	@org.teavm.jso.JSBody(params = {}, script = "return window.shadowRoot;")
	private static native HTMLElement getShadowRoot();

	@org.teavm.jso.JSBody(params = { "id" }, script = "return window.shadowRoot.getElementById(id);")
	private static native HTMLElement getShadowRootElementById(String id);
}
