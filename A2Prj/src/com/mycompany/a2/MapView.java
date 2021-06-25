package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	public MapView() {
		this.setWidth(1000);
		this.setHeight(1000);
		this.getUnselectedStyle().setBorder(Border.createLineBorder(20, ColorUtil.rgb(255,0,0)));
	}
	
	@Override
	public void update(Observable observable, Object data) {
		((GameWorld) observable).map();
	}
}
