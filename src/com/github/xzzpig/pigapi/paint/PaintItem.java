package com.github.xzzpig.pigapi.paint;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class PaintItem implements IContainer, Paintable, Sizeable {

	private String name;
	private PaintItem parent;
	private Rect size;
	private List<PaintItem> subs;

	public PaintItem(PaintItem parent, String name, Rect size) {
		subs = new ArrayList<>();
		setName(name);
		this.name = name;
		setSize(size);
		setParent(parent);
	}

	public PaintItem addSub(PaintItem item) {
		subs.add(item);
		return this;
	}

	@Override
	public abstract PaintItem clone();

	public final Image getFinalImage(){
		Image image = getSizedImage();
		for (PaintItem subItem : subs) {
			Image image2 = subItem.getFinalImage();
			image.getGraphics().drawImage(image2, subItem.getSize().left, subItem.getSize().top, null);
		}
		return image;
	}

	public final String getName() {
		return name;
	}

	@Override
	public PaintItem getParent() {
		return parent;
	}
	public Rect getSize() {
		return size;
	}
	public Image getSizedImage() {
		return getSizedImage(getSize(), SizeType.ZOOM);
	}

	@Override
	public Image getSizedImage(Rect size, SizeType type) {
		if (type == SizeType.ZOOM) {
			return getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
		}else{
			BufferedImage image = new BufferedImage(getSize().width,getSize().height,BufferedImage.TYPE_INT_ARGB);
			image.getGraphics().drawImage(getImage(), 0, 0, null);
			return image;
		}
	}
	
	@Override
	public PaintItem[] getSubs() {
		return subs.toArray(new PaintItem[0]);
	}

	public PaintItem removeSub(String name) {
		List<PaintItem> removeitems = new ArrayList<>();
		for (PaintItem item : subs) {
			if (item.name.equalsIgnoreCase(name))
				removeitems.add(item);
		}
		subs.removeAll(removeitems);
		return this;
	}

	public final PaintItem setName(String name) {
		this.name = name;
		return this;
	}

	public PaintItem setParent(PaintItem parent){
		if(this.parent!=null){
			this.parent.removeSub(getName());
		}
		parent.addSub(this);
		this.parent = parent;
		return this;
	}
	
	public PaintItem setSize(Rect size) {
		size = new Rect(size);
		return this;
	}
}
