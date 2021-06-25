package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Interfaces.ICollider;
import com.mycompany.a3.Interfaces.IDrawable;

public class Tomato extends Fixed {

	private int nutrition;

    public Tomato(int size, Point location, int newNutrition, int color, GameWorld gw){
        //public Fixed(int size, Point location, int color) {
    	super(size, location, color, gw);
        this.nutrition = newNutrition;
    }
    public int getNutrition(){
        return nutrition;
    }
    public void setNutrition(int x){
    	nutrition = x;
    }

    public void draw(Graphics g, Point pCmpRelPrnt) {
        g.setColor(super.getColor());
        int stringX = (int)(this.getLocation().getX()) + (int)pCmpRelPrnt.getX();
        int stringY = (int)(this.getLocation().getY()) + (int)pCmpRelPrnt.getY();
        int x = (int)this.getLocation().getX() + (int)pCmpRelPrnt.getX() ;
        int y = (int)this.getLocation().getY() + (int)pCmpRelPrnt.getY();
        int drawX = x - this.getSize()/3;
        int drawY = y - this.getSize()/3;
       // g.fillArc(x - this.getSize()/3, y - this.getSize()/3, this.getSize(),this.getSize(), 0, 360);
        
        if (isSelected()) {
            g.setColor(super.getColor());
            g.drawArc(drawX, drawY, this.getSize(),this.getSize(), 0, 360);
            g.setColor(ColorUtil.WHITE);
            g.fillArc(drawX, drawY, this.getSize(),this.getSize(), 0, 360);
        } else {
            g.setColor(super.getColor());
            g.fillArc(drawX, drawY, this.getSize(),this.getSize(), 0, 360);
        }
        g.setColor(ColorUtil.BLACK);
        g.drawString("" + this.getNutrition(), stringX, stringY);
        //g.drawString("" + getNutrition(), (int)(pCmpRelPrnt.getX() + this.getLocation().getX() ),  (int)(pCmpRelPrnt.getY() + this.getLocation().getY()  ));
    }

    @Override
    public void handleCollision(ICollider otherObject) {
        if (otherObject instanceof Squirrel)  {
            if( nutrition !=0 ) {
            	if (otherObject instanceof PlayerSquirrel) {
            		super.getGW().playSound(3);
            		((Squirrel)otherObject).setEnergyLevel( nutrition + ((Squirrel)otherObject).getEnergyLevel() );
                    this.setNutrition(0);
                    this.setColor(ColorUtil.rgb(255,240,240));
                    super.getGW().addNewTomato();
            	} else {
	                ((Squirrel)otherObject).setEnergyLevel( nutrition + ((Squirrel)otherObject).getEnergyLevel() );
	                this.setNutrition(0);
	                this.setColor(ColorUtil.rgb(255,240,240));
	                super.getGW().addNewTomato();
            	}
            }
        }
    }

    public String toString() {
		String x = "Tomato:" 	+ " loc=" + this.getLocation().getX() + "," + this.getLocation().getY()
								+ " color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]"
								+ " size=" + this.getSize()
								+ " nutrition=" + nutrition;
		return x ;
	}
}
