package com.github.xzzpig.pigapi.paint;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class PaintView extends JPanel {

	private List<PaintItem> paints = new ArrayList<>();

	/**
	 * 
	 */
	private static final long serialVersionUID = -7776371277251636699L;

	public PaintView() {
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		paints.forEach((PaintItem paint) -> {
			Rect size = paint.getSize();
			arg0.drawImage(paint.getFinalImage(), size.getLeft(), size.getTop(), null);
		});
	}

	public List<PaintItem> getPaints() {
		return paints;
	}

	public void addPaintItem(PaintItem paintItem) {
		paints.add(paintItem);
	}
}
