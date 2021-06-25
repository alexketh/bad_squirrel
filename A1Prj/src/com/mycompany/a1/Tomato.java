package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Tomato extends Fixed {

	private int nutrition;
	private int R;
	private int G;
	private int B;

    public Tomato(int size, Point location, int newNutrition, int R, int G, int B){
        //public Fixed(int size, Point location, int color) {
    	super(size, location, ColorUtil.rgb(R, G, B));
        this.nutrition = newNutrition;
    }
    public int getNutrition(){
        return nutrition;
    }
    public void setNutrition(){
    	nutrition = 0;
    }

    public String toString() {
		String x = "Tomato: " + "loc=" + this.getLocation().getX() + "," + this.getLocation().getY() 
								+ " color=[" + R + "," + G + "," + B + "] size=" + this.getSize() + " nutrition=" + nutrition;
		return x ;
	}
}
