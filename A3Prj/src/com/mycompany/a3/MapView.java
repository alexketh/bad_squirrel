package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.GameObjects.GameObject;
import com.mycompany.a3.GameObjects.Nut;
import com.mycompany.a3.GameObjects.Tomato;
import com.mycompany.a3.Interfaces.IDrawable;
import com.mycompany.a3.Interfaces.IIterator;
import com.mycompany.a3.Interfaces.ISelectable;

public class MapView extends Container implements Observer {

	private TextArea text;
	private static int height;
	private static int width;
	private GameWorld gw;
	private boolean select;

	public MapView() {
		this.setWidth(1000);
		this.setHeight(1000);
		this.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.rgb(255,0,0)));
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBgTransparency(255);
	}

	@Override
	public void update(Observable observable, Object data) {
		gw = (GameWorld) observable;
		gw.addObserver(this);
		this.repaint();
		gw.map();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		IIterator itr = gw.getGameObjectCollection().getIterator();
		while(itr.hasNext()) {
			GameObject tempObject = (GameObject) itr.getNext();
			if (tempObject instanceof IDrawable) {
				((IDrawable) tempObject).draw(g, pCmpRelPrnt);
			}
		}
	}
	
	public void pointerPressed(int x, int y) {
		/*
		 * Go through the collection list and then see which one is clicked,
		 * if it is the object that was clicked then setSelected would be true
		 * Then when there is a selected object that is a part of the selectable
		 * collection then where you click next is where the object goes.
		 */
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		
		
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		GameObjectCollection list = gw.getGameObjectCollection();
		
		IIterator itr = list.getIterator();
		while(itr.hasNext()) {
			GameObject go = (GameObject)itr.getNext();
			if(go instanceof Nut || go instanceof Tomato) {
				ISelectable selectableCurrentObj = (ISelectable)go;
				if(selectableCurrentObj.isSelected()) { //found a selected item
					if(gw.getPositionClickStatus() == true) { //to make sure the user presses the position button first then clicking on destination
						int newX = x - getX() - go.getSize() / 2;
						int newY = y - getY() - go.getSize() / 2;
						go.setLocation(new Point(newX,newY));
						selectableCurrentObj.setSelected(false);
					}
					//System.out.println("Hello1, " + cObj);
				} else if (selectableCurrentObj.contains(pPtrRelPrnt, pCmpRelPrnt)) { //sets the selected item to true
					if(select == false) {
						selectableCurrentObj.setSelected(true);
						select = true; //switched to true when user clicks on object
					}
					//System.out.println("Hello2, " + cObj);
				} else { //keeps looking for the selected item
					selectableCurrentObj.setSelected(false);
					//System.out.println("Hello3, " + cObj);
				}
			}
		}
			repaint();
			//System.out.println("I'm here");
			gw.setPositionClickStatus(false);
	}

	public void setSelectBoolean(boolean selected) {
		this.select = selected;
	}
}
