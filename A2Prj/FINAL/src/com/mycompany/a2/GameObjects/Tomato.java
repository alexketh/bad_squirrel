package com.mycompany.a2.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Tomato extends Fixed {

	private int nutrition;

    public Tomato(int size, Point location, int newNutrition, int color){
        //public Fixed(int size, Point location, int color) {
    	super(size, location, color);
        this.nutrition = newNutrition;
    }
    public int getNutrition(){
        return nutrition;
    }
    public void setNutrition(){
    	nutrition = 0;
    }

    public String toString() {
		String x = "Tomato:" 	+ " loc=" + this.getLocation().getX() + "," + this.getLocation().getY() 
								+ " color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]" 
								+ " size=" + this.getSize() 
								+ " nutrition=" + nutrition;
		return x ;
	}
}
