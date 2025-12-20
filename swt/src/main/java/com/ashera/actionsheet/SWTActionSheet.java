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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.ashera.core.IFragment;
import com.ashera.widget.PluginInvoker;

public class SWTActionSheet {

	public static void show(Shell parent, String title, String[] actions, int destructiveIndex,
			ActionSheetListener listener, final String parentFragmentId, final String addCancelButtonWithLabel,
			final String titleTextColor, final String titleBackgroundColor, final String titleFontFamily,
			final int titleTextSize, String cancelTextColor, final String cancelBackgroundColor,
			final String cancelFontFamily, final int cancelTextSize, final String cancelHighlightColor,
			String otherTextColor, final String otherBackgroundColor, final String otherFontFamily,
			final int otherTextSize, final String otherHighlightColor, final int destructiveButtonIndex,
			String destructiveTextColor, final String destructiveBackgroundColor,
			final String destructiveFontFamily, final int destructiveTextSize, final String destructiveHighlightColor) {

		Display display = parent.getDisplay();

		// Overlay (dim background)
		Shell overlay = new Shell(parent, SWT.NO_TRIM | SWT.APPLICATION_MODAL);
		overlay.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		overlay.setAlpha(120);

		Rectangle bounds = parent.getBounds();
		overlay.setBounds(bounds);
		overlay.open();

		// Sheet
		Shell sheet = new Shell(overlay, SWT.NO_TRIM);
		overlay.setBackgroundMode(SWT.INHERIT_FORCE);

		GridLayout layout = new GridLayout(1, false);
//        layout.marginWidth = 20;
//        layout.marginHeight = 20;
//        layout.verticalSpacing = 12;
		sheet.setLayout(layout);

		if (title != null && !title.isEmpty()) {
			Label lblTitle = new Label(sheet, SWT.CENTER);
			lblTitle.setText(title);
			lblTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			lblTitle.setForeground(display.getSystemColor(SWT.COLOR_BLACK));

			if (titleTextColor != null && !titleTextColor.isEmpty()) {
				Color titleFg = getColor(titleTextColor, parentFragmentId);
				lblTitle.setForeground(titleFg);
			}

			customizeControl(lblTitle, titleTextColor, titleFontFamily, titleBackgroundColor, "", titleTextSize, parentFragmentId);
		}

		List<Button> buttons = new ArrayList<>();
		for (int i = 0; i < actions.length; i++) {
			final int index = i;
			Button btn = new Button(sheet, SWT.PUSH | SWT.FLAT);
			btn.setText(actions[i]);
			btn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			btn.setForeground(display.getSystemColor(SWT.COLOR_BLACK));

			if (i == destructiveIndex) {
				if (destructiveTextColor == null || destructiveTextColor.isEmpty()) {
					destructiveTextColor = "#1E82FF";
				}
				customizeControl(btn, destructiveTextColor, destructiveFontFamily, destructiveBackgroundColor, destructiveHighlightColor, destructiveTextSize, parentFragmentId);
			} else {
				if (otherTextColor == null || otherTextColor.isEmpty()) {
					otherTextColor = "#1E82FF";
				}
				customizeControl(btn, otherTextColor, otherFontFamily, otherBackgroundColor, otherHighlightColor, otherTextSize, parentFragmentId);
			}

			btn.addListener(SWT.Selection, e -> {
				listener.onSelect(index, actions[index]);
				sheet.dispose();
				overlay.dispose();
			});
			buttons.add(btn);
		}

		if (addCancelButtonWithLabel != null && !addCancelButtonWithLabel.isEmpty()) {
			Label spacer = new Label(sheet, SWT.NONE);
			spacer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	
			Button cancel = new Button(sheet, SWT.PUSH);
			cancel.setText(addCancelButtonWithLabel);
			cancel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			cancel.addListener(SWT.Selection, e -> {
				listener.onCancel();
				sheet.dispose();
				overlay.dispose();
			});
			if (cancelTextColor == null || cancelTextColor.isEmpty()) {
				cancelTextColor = "#1E82FF";
			}
			customizeControl(cancel, cancelTextColor, cancelFontFamily, cancelBackgroundColor, cancelHighlightColor, cancelTextSize, parentFragmentId);
		}

		Rectangle parentArea = parent.getBounds();
		Point parentLoc = parent.getLocation();

		// Full width
		int sideMargin = 16;
		int width = parentArea.width - (sideMargin * 2);

		// Compute height using full width
		Point size = sheet.computeSize(width, SWT.DEFAULT);
		sheet.setSize(width, size.y);

		// Slide animation start/end
		int startY = parentLoc.y + parentArea.height;
		int endY = parentLoc.y + parentArea.height - size.y;

		sheet.setLocation(parentLoc.x + sideMargin, startY);
		sheet.open();

		// Slide-up animation
		animate(display, sheet, parentLoc.x + sideMargin, startY - sideMargin, endY - sideMargin);

		// Click outside to dismiss
		overlay.addListener(SWT.MouseDown, e -> {
			listener.onCancel();
			sheet.dispose();
			overlay.dispose();
		});
	}

	private static void customizeControl(Control control, String textColor, String fontFamily, String backgroundColor,
			String highlightColor, int textSize, final String parentFragmentId) {
		if (textColor != null && !textColor.isEmpty()) {
			Color destructiveFg = getColor(textColor, parentFragmentId);
			control.setForeground(destructiveFg);
		}

		
		if (fontFamily != null && !fontFamily.isEmpty()) {
			Font mytitleFont = getFontFamily(control, fontFamily, parentFragmentId);
			control.setFont(mytitleFont);
		}

		if (highlightColor != null && !highlightColor.isEmpty()
				&& backgroundColor != null && !backgroundColor.isEmpty()) {
			Color destructiveBg = getColor(backgroundColor, parentFragmentId);
			Color highlight = getColor(highlightColor, parentFragmentId);
			control.addListener(SWT.MouseDown, e -> control.setBackground(highlight));
			control.addListener(SWT.MouseUp, e -> control.setBackground(destructiveBg));
			control.setBackground(destructiveBg);
		} else if (backgroundColor != null && !backgroundColor.isEmpty()) {
			Color destructiveBg = getColor(backgroundColor, parentFragmentId);
			control.setBackground(destructiveBg);
		}

		final org.eclipse.swt.graphics.Font newFont = getTextSize(control, textSize, parentFragmentId);
		control.setFont(newFont);
	}

	private static org.eclipse.swt.graphics.Font getTextSize(Control control, final int destructiveTextSize,
			final String parentFragmentId) {
		FontData[] fontData = control.getFont().getFontData();
		float fontSize = getTextSize(destructiveTextSize + "sp", parentFragmentId);

		for (int j = 0; j < fontData.length; ++j) {
			fontData[j].setHeight((int) fontSize);
		}

		final org.eclipse.swt.graphics.Font newFont = new org.eclipse.swt.graphics.Font(Display.getDefault(),
				fontData);
		return newFont;
	}

	private static void animate(Display display, Shell shell, int x, int fromY, int toY) {
		int steps = 12;
		int delta = (fromY - toY) / steps;

		for (int i = 0; i < steps; i++) {
			int y = fromY - (delta * i);
			display.timerExec(i * 15, () -> {
				if (!shell.isDisposed()) {
					shell.setLocation(x, y);
				}
			});
		}
		display.timerExec(steps * 15, () -> {
			if (!shell.isDisposed()) {
				shell.setLocation(x, toY);
			}
		});
	}

	private static Font getFontFamily(Control control, String fontName, final String parentFragmentId) {
		Map<String, com.ashera.model.FontDescriptor> fontDescriptors = (Map<String, com.ashera.model.FontDescriptor>) PluginInvoker
				.getConverter("font").convertFrom(fontName, new HashMap<>(), getMyParentFragment(parentFragmentId));
		String weight = "400";
		String fontStyle = "normal";
		com.ashera.model.FontDescriptor fontDescriptor = fontDescriptors.get(fontStyle + "_" + weight);
		final org.eclipse.swt.graphics.Font newFont = new org.eclipse.swt.graphics.Font(Display.getDefault(),
				fontDescriptor.getName(), (int) control.getFont().getFontData()[0].height, fontDescriptor.getStyle());
		return newFont;
	}

	private static Color getColor(String color, final String parentFragmentId) {
		return (Color) PluginInvoker.getConverter("color").convertFrom(color, new HashMap<>(),
				getMyParentFragment(parentFragmentId));
	}
	
	private static float getTextSize(String dimen, final String parentFragmentId) {
		return (float) PluginInvoker.getConverter("dimensionsp").convertFrom(dimen, new HashMap<>(),
				getMyParentFragment(parentFragmentId));
	}

	public static IFragment getMyParentFragment(String fragmentId) {
		return com.ashera.core.FragmentRegistry.getInstance().find(fragmentId);
	}
}
