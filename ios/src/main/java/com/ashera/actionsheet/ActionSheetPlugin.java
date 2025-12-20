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

import java.util.HashMap;
import java.util.Map;

import com.ashera.core.IFragment;
import com.ashera.model.FontDescriptor;
import com.ashera.widget.PluginInvoker;
import com.google.j2objc.annotations.Property;

/*-[
#include "IBActionSheet.h"
]-*/


public class ActionSheetPlugin {
	public static void initPlugin() {
		// start - widgets
		// end - widgets
	}

	private String parentFragmentId;
	private @Property Object uiActionSheet;

	public ActionSheetPlugin(String parentFragmentId, Object uiActionSheet) {
		super();
		this.parentFragmentId = parentFragmentId;
		this.uiActionSheet = uiActionSheet;
	}

	public void setTitleTextColor(String titleTextColor) {
		if (titleTextColor != null && !titleTextColor.isEmpty()) {
			Object titleTextColorObj = getTextColor(titleTextColor);
			nativeSetTitleTextColor(titleTextColorObj);
		}
	}

	public void setTitleBackgroundColor(String titleBackgroundColor) {
		if (titleBackgroundColor != null && !titleBackgroundColor.isEmpty()) {
			Object titleBgObj = getTextColor(titleBackgroundColor);
			nativeSetTitleBackgroundColor(titleBgObj);
		}
	}

	public void setTitleFontFamily(String titleFontFamily, int titleTextSize) {
		if (titleFontFamily != null && !titleFontFamily.isEmpty()) {
			FontDescriptor fontDescriptor = getFontFamilyDescriptor(titleFontFamily);
			float textSize = (float) getTextSize(titleTextSize);
			nativeSetTitleFontFamily(fontDescriptor.getName(), textSize);
		}
	}

	public void setTitleTextSize(int titleTextSize) {
		float textSize = (float) getTextSize(titleTextSize);
		nativeSetTitleTextSize(textSize);
	}

	private native void nativeSetTitleTextColor(Object titleTextColor)/*-[
		[((IBActionSheet*)self.uiActionSheet) setTitleTextColor: (UIColor*) titleTextColor];
	]-*/;

	private native void nativeSetTitleBackgroundColor(Object bgColor)/*-[
		[((IBActionSheet*)self.uiActionSheet) setTitleBackgroundColor: (UIColor*) bgColor];
	]-*/;

	private native void nativeSetTitleTextSize(float value)/*-[
		IBActionSheet* uiActionSheet = ((IBActionSheet*)self.uiActionSheet);
		[uiActionSheet setFont: [uiActionSheet.titleView.titleLabel.font fontWithSize: value]];
	]-*/;

	private native void nativeSetTitleFontFamily(String fontName, float textSize)/*-[
		[((IBActionSheet*)self.uiActionSheet) setTitleFont: [UIFont fontWithName:fontName size:textSize]];
	]-*/;

	private FontDescriptor getFontFamilyDescriptor(String fontName) {
		Map<String, com.ashera.model.FontDescriptor> fontDescriptors = (Map<String, com.ashera.model.FontDescriptor>) PluginInvoker
				.getConverter("font").convertFrom(fontName, new HashMap<>(), getMyParentFragment(parentFragmentId));
		String weight = "400";
		String fontStyle = "normal";
		com.ashera.model.FontDescriptor fontDescriptor = fontDescriptors.get(fontStyle + "_" + weight);
		return fontDescriptor;
	}

	private Object getTextSize(int textSize) {
		Object objValue = PluginInvoker.getConverter("dimensionsp").convertFrom(textSize + "sp", new HashMap<>(),
				getMyParentFragment(parentFragmentId));
		return objValue;
	}

	private Object getTextColor(String color) {
		Object objValue = PluginInvoker.getConverter("color").convertFrom(color, new HashMap<>(),
				getMyParentFragment(parentFragmentId));
		return objValue;
	}

	public IFragment getMyParentFragment(String fragmentId) {
		return com.ashera.core.FragmentRegistry.getInstance().find(fragmentId);
	}

	public void setCancelTextColor(String cancelTextColor) {
		if (cancelTextColor != null && !cancelTextColor.isEmpty()) {
			Object cancelTextColorObj = getTextColor(cancelTextColor);
			nativeSetCancelTextColor(cancelTextColorObj);
		}

	}

	public void setCancelBackgroundColor(String cancelBackgroundColor) {
		if (cancelBackgroundColor != null && !cancelBackgroundColor.isEmpty()) {
			Object cancelBgObj = getTextColor(cancelBackgroundColor);
			nativeSetCancelBackgroundColor(cancelBgObj);
		}
	}

	public void setCancelHighlightColor(String cancelBackgroundColor) {
		if (cancelBackgroundColor != null && !cancelBackgroundColor.isEmpty()) {
			Object cancelBgObj = getTextColor(cancelBackgroundColor);
			nativeSetCancelHighlightColor(cancelBgObj);
		}
	}

	public void setCancelFontFamily(String cancelFontFamily, int cancelTextSize) {
		if (cancelFontFamily != null && !cancelFontFamily.isEmpty()) {
			FontDescriptor fontDescriptor = getFontFamilyDescriptor(cancelFontFamily);
			float textSize = (float) getTextSize(cancelTextSize);
			nativeSetCancelFontFamily(fontDescriptor.getName(), textSize);
		}
	}

	public void setCancelTextSize(int cancelTextSize) {
		float textSize = (float) getTextSize(cancelTextSize);
		nativeSetCancelTextSize(textSize);
	}

	private native void nativeSetCancelTextColor(Object cancelTextColor)/*-[
		[((IBActionSheet*)self.uiActionSheet) setCancelButtonTextColor: (UIColor*) cancelTextColor];
	]-*/;

	private native void nativeSetCancelBackgroundColor(Object bgColor)/*-[
		[((IBActionSheet*)self.uiActionSheet) setCancelButtonBackgroundColor: (UIColor*) bgColor];
	]-*/;

	private native void nativeSetCancelHighlightColor(Object bgColor)/*-[
		[((IBActionSheet*)self.uiActionSheet) setCancelButtonHighlightColor: (UIColor*) bgColor];
	]-*/;

	private native void nativeSetCancelTextSize(float value)/*-[
		IBActionSheet* uiActionSheet = ((IBActionSheet*)self.uiActionSheet);
		UIButton* btn = [uiActionSheet getCancelButton];
		if (btn != nil) {
			[uiActionSheet setFont: [btn.font fontWithSize: value]];
		}
	]-*/;

	private native void nativeSetCancelFontFamily(String fontName, float textSize)/*-[
		[((IBActionSheet*)self.uiActionSheet) setCancelButtonFont: [UIFont fontWithName:fontName size:textSize]];
	]-*/;

	public void setDestructiveTextColor(String destructiveTextColor) {
		if (destructiveTextColor != null && !destructiveTextColor.isEmpty()) {
			Object destructiveTextColorObj = getTextColor(destructiveTextColor);
			nativeSetDestructiveTextColor(destructiveTextColorObj);
		}

	}

	public void setDestructiveBackgroundColor(String destructiveBackgroundColor) {
		if (destructiveBackgroundColor != null && !destructiveBackgroundColor.isEmpty()) {
			Object destructiveBgObj = getTextColor(destructiveBackgroundColor);
			nativeSetDestructiveBackgroundColor(destructiveBgObj);
		}
	}

	public void setDestructiveHighlightColor(String destructiveBackgroundColor) {
		if (destructiveBackgroundColor != null && !destructiveBackgroundColor.isEmpty()) {
			Object destructiveBgObj = getTextColor(destructiveBackgroundColor);
			nativeSetDestructiveHighlightColor(destructiveBgObj);
		}
	}

	public void setDestructiveFontFamily(String destructiveFontFamily, int destructiveTextSize) {
		if (destructiveFontFamily != null && !destructiveFontFamily.isEmpty()) {
			FontDescriptor fontDescriptor = getFontFamilyDescriptor(destructiveFontFamily);
			float textSize = (float) getTextSize(destructiveTextSize);
			nativeSetDestructiveFontFamily(fontDescriptor.getName(), textSize);
		}
	}

	public void setDestructiveTextSize(int destructiveTextSize) {
		float textSize = (float) getTextSize(destructiveTextSize);
		nativeSetDestructiveTextSize(textSize);
	}

	private native void nativeSetDestructiveTextColor(Object destructiveTextColor)/*-[
		[((IBActionSheet*)self.uiActionSheet) setDestructiveButtonTextColor: (UIColor*) destructiveTextColor];
	]-*/;

	private native void nativeSetDestructiveBackgroundColor(Object bgColor)/*-[
		[((IBActionSheet*)self.uiActionSheet) setDestructiveButtonBackgroundColor: (UIColor*) bgColor];
	]-*/;

	private native void nativeSetDestructiveHighlightColor(Object bgColor)/*-[
		[((IBActionSheet*)self.uiActionSheet) setDestructiveButtonHighlightColor: (UIColor*) bgColor];
	]-*/;

	private native void nativeSetDestructiveTextSize(float value)/*-[
		IBActionSheet* uiActionSheet = ((IBActionSheet*)self.uiActionSheet);
		UIButton* btn = [uiActionSheet getDestructiveButton];
		if (btn != nil) {
			[uiActionSheet setFont: [btn.font fontWithSize: value]];
		}
	]-*/;

	private native void nativeSetDestructiveFontFamily(String fontName, float textSize)/*-[
		[((IBActionSheet*)self.uiActionSheet) setDestructiveButtonFont: [UIFont fontWithName:fontName size:textSize]];
	]-*/;

	public void setOtherTextColor(String otherTextColor) {
		if (otherTextColor != null && !otherTextColor.isEmpty()) {
			Object otherTextColorObj = getTextColor(otherTextColor);
			nativeSetOtherTextColor(otherTextColorObj);
		}
	}

	public void setOtherBackgroundColor(String otherBackgroundColor) {
		if (otherBackgroundColor != null && !otherBackgroundColor.isEmpty()) {
			Object otherBgObj = getTextColor(otherBackgroundColor);
			nativeSetOtherBackgroundColor(otherBgObj);
		}
	}

	public void setOtherHighlightColor(String otherBackgroundColor) {
		if (otherBackgroundColor != null && !otherBackgroundColor.isEmpty()) {
			Object otherBgObj = getTextColor(otherBackgroundColor);
			nativeSetOtherHighlightColor(otherBgObj);
		}
	}

	public void setOtherFontFamily(String otherFontFamily, int otherTextSize) {
		if (otherFontFamily != null && !otherFontFamily.isEmpty()) {
			FontDescriptor fontDescriptor = getFontFamilyDescriptor(otherFontFamily);
			float textSize = (float) getTextSize(otherTextSize);
			nativeSetOtherFontFamily(fontDescriptor.getName(), textSize);
		}
	}

	public void setOtherTextSize(int otherTextSize) {
		float textSize = (float) getTextSize(otherTextSize);
		nativeSetOtherTextSize(textSize);
	}

	private native void nativeSetOtherTextColor(Object otherTextColor)/*-[
		[((IBActionSheet*)self.uiActionSheet) setOtherButtonTextColor: (UIColor*) otherTextColor];
	]-*/;

	private native void nativeSetOtherBackgroundColor(Object bgColor)/*-[
		[((IBActionSheet*)self.uiActionSheet) setOtherButtonBackgroundColor: (UIColor*) bgColor];
	]-*/;

	private native void nativeSetOtherHighlightColor(Object bgColor)/*-[
		[((IBActionSheet*)self.uiActionSheet) setOtherButtonHighlightColor: (UIColor*) bgColor];
	]-*/;

	private native void nativeSetOtherTextSize(float value)/*-[
		IBActionSheet* uiActionSheet = ((IBActionSheet*)self.uiActionSheet);
		[((IBActionSheet*)self.uiActionSheet) setOtherButtonTextSize: value];
	]-*/;

	private native void nativeSetOtherFontFamily(String fontName, float textSize)/*-[
		[((IBActionSheet*)self.uiActionSheet) setOtherButtonFont: [UIFont fontWithName:fontName size:textSize]];
	]-*/;

}
