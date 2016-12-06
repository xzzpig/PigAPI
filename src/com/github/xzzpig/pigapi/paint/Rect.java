package com.github.xzzpig.pigapi.paint;

public class Rect {
	public int top,left,height,width;
	
	public Rect(){}
	
	public Rect(int top,int left,int height,int width){
		this.top = top;
		this.left = left;
		this.height = height;
		this.width = width;
	}
	public Rect(Rect r){
		top = r.top;
		left = r.left;
		height = r.height;
		width = r.width;
	}
	public int getTop(){
		return top;
	}
	public int getLeft(){
		return left;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public int getBottom(){
		return top+height;
	}
	public int getRight(){
		return left+width;
	}
	
	public Rect setTop(int top){
		this.top = top;
		return this;
	}
	public Rect setLeft(int left){
		this.left = left;
		return this;
	}
	public Rect setWidth(int width){
		this.width = width;
		return this;
	}
	public Rect setHeight(int height){
		this.height = height;
		return this;
	}
	public Rect setBottom(int bottom){
		this.height = bottom-top;
		return this;
	}
	public Rect setRight(int right){
		this.width = right-left;
		return this;
	}
}
