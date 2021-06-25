package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Interfaces.ICollider;
import com.mycompany.a3.Interfaces.IDrawable;

public class Nut extends Fixed implements IDrawable {

	private int sequenceNumber;
	private GameWorld gw;

	public Nut(Point location, int size, int seq,  int color, GameWorld gw) {
		super(size, location, color, gw);
		this.sequenceNumber = seq;
		this.gw = gw;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		if (otherObject instanceof PlayerSquirrel || otherObject instanceof NonPlayerSquirrel ) {

			if (((Squirrel) otherObject).getLastNutReached() +1 == this.getSequenceNumber() && this.getSequenceNumber() != 9) {
				if (otherObject instanceof PlayerSquirrel) {
					if (((PlayerSquirrel)otherObject).getLastNutReached() +1 == this.getSequenceNumber() ) {
						super.getGW().playSound(2);
					}
				}
				System.out.println("A squirrel has reached the next nut!");
				((Squirrel) otherObject).setLastNutReached(this.getSequenceNumber());
			}
			else if (this.getSequenceNumber() == 9 && otherObject instanceof PlayerSquirrel) {
				if (((PlayerSquirrel)otherObject).getLastNutReached()+1 == 9) {
					System.out.println("Game Over, you win! Total time:" + super.getGW().getClock()/20);
					gw.quit(0);
				}
			}
			else if (this.getSequenceNumber()== 9 && otherObject instanceof NonPlayerSquirrel) {
				if (((NonPlayerSquirrel)otherObject).getLastNutReached()+1 == 9) {
					System.out.println("Game Over, you lose the game!!!!");
					gw.quit(1);
				}
			}
		}
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getColor());
		int stringX = (int)Math.round(this.getLocation().getX()) + (int)pCmpRelPrnt.getX();
		int stringY = (int)Math.round(this.getLocation().getY()) + (int)pCmpRelPrnt.getY();

		int x = (int)this.getLocation().getX() + (int)pCmpRelPrnt.getX();
		int y = (int)this.getLocation().getY() + (int)pCmpRelPrnt.getY();

		int[] xPoints = new int[3];
		int[] yPoints = new int[3];

		xPoints[0] = x;
		yPoints[0] = y +(this.getSize()/2);
		xPoints[1] = x +(this.getSize()/2);
		yPoints[1] = y -(this.getSize()/2);
		xPoints[2] = x -(this.getSize()/2);
		yPoints[2] = y -(this.getSize()/2);

		//g.fillPolygon(xPoints, yPoints, 3);
		
		if (super.isSelected())
			g.drawPolygon(xPoints, yPoints, 3);
		else
			g.fillPolygon(xPoints, yPoints, 3);
		
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + this.sequenceNumber, stringX, stringY);
	}

	public String toString() {
		String x = "Nut:" 	+ " loc=" + this.getLocation().getX() + "," + this.getLocation().getY()
							+ " color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]"
							+ " size=" + this.getSize()
							+ " seqNumber=" + sequenceNumber;
		return x ;
	}
}
