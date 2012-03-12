/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.internal.texteditor;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;

import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationPresentation;

/**
 * The matching brackets annotation
 * 
 * @since 3.8
 */
public class MatchingBracketsAnnotation extends Annotation implements IAnnotationPresentation {

	/** The annotation color. */
	private Color fColor;

	/**
	 * Creates a new MatchingBracketsAnnotation.
	 * 
	 * @param color the annotation color
	 */
	public MatchingBracketsAnnotation(Color color) {
		super("org.eclipse.ui.workbench.texteditor.matchingBrackets", true, EditorMessages.MatchingBracketsAnnotation_brackets); //$NON-NLS-1$
		fColor= color;
	}

	/*
	 * @see org.eclipse.jface.text.source.IAnnotationPresentation#paint(org.eclipse.swt.graphics.GC, org.eclipse.swt.widgets.Canvas, org.eclipse.swt.graphics.Rectangle)
	 */
	public void paint(GC gc, Canvas canvas, Rectangle bounds) {

		Point canvasSize= canvas.getSize();

		int x= 2 * bounds.width / 3;
		int y= bounds.y;
		int w= canvasSize.x;
		int h= bounds.height;

		if (y + h > canvasSize.y)
			h= canvasSize.y - y;

		if (y < 0) {
			h= h + y;
			y= 0;
		}

		if (h <= 0)
			return;

		gc.setBackground(fColor);
		gc.fillRectangle(x, bounds.y, w, bounds.height);
	}

	/*
	 * @see org.eclipse.jface.text.source.IAnnotationPresentation#getLayer()
	 */
	public int getLayer() {
		return IAnnotationPresentation.DEFAULT_LAYER + 1;
	}

	/**
	 * Sets the annotation color.
	 * 
	 * @param color the color
	 */
	public void setColor(Color color) {
		fColor= color;
	}
}