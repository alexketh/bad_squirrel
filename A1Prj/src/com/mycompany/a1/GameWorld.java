package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;

public class GameWorld {
	private int lives = 3;
	private int clock = 0;
	private int nutSize = 10;
	private int birdSize = 20;
	private int tomatoSize = 20;
	private int squirrelSize = 40;
	private int squirrelEnergy = 100;
	private int squirrelMaxSpeed = 100;
	private int squirrelEnergyConsumptionRate = 5;
	private int squirrelR = 28;
	private int squirrelG = 6;	//BROWN
	private int squirrelB = 0;
	private int birdR = 255;
	private int birdG = 0;		//RED
	private int birdB = 0;
	private int nutR = 0;
	private int nutG = 0;		//BLUE
	private int nutB = 255;
	private int tomatoR = 0;
	private int tomatoG = 255;	//GREEN
	private int tomatoB = 0;
	private int initSpeed = 1;
	private int initHeading = 0;


	Random r1 = new Random();
	ArrayList<GameObject> gameObject = new ArrayList<>();

	public void init() {
		Point nut1Loc = new Point(r1.nextInt(1000), r1.nextInt(1000));
		//Point nut1Loc = new Point(0,0);

		//public Nut(Point location, int size, int seq,  int R, int G, int B) {
		Nut n1 = new Nut(nut1Loc, nutSize, 1, nutR, nutG, nutB);
		Nut n2 = new Nut(new Point(r1.nextInt(1000), r1.nextInt(1000)), nutSize, 2, nutR, nutG, nutB);
		//Nut n2 = new Nut(new Point(0,2), nutSize, 2, nutR, nutG, nutB);
		Nut n3 = new Nut(new Point(r1.nextInt(1000), r1.nextInt(1000)), nutSize, 3, nutR, nutG, nutB);
		Nut n4 = new Nut(new Point(r1.nextInt(1000), r1.nextInt(1000)), nutSize, 4, nutR, nutG, nutB);

		/*public Squirrel(int steeringDirection, int maxSpeed, int energyLevel, int energyConsumptionRate, int damageLevel,
						int lastNutReached, Point location, int squirrelSize, int R, int G, int B, int initialSpeed, int initialHeading) { */
		Squirrel s1 = new Squirrel(0, squirrelMaxSpeed, squirrelEnergy, squirrelEnergyConsumptionRate, 0, 1, nut1Loc, squirrelSize, squirrelR, squirrelG, squirrelB, initSpeed, initHeading);
		//Squirrel s1 = new Squirrel(0, r1.nextInt(20)+5, squirrelEnergy, squirrelEnergyConsumptionRate, 0, 1, nut1Loc, squirrelSize);

		//public Bird(int speed, int heading, int size, Point location, int R, int G, int B) {
		Bird b1 = new Bird(r1.nextInt(20)+5, r1.nextInt(360), birdSize, new Point(r1.nextInt(1000),r1.nextInt(1000)), birdR, birdG, birdB);
		Bird b2 = new Bird(r1.nextInt(20)+5, r1.nextInt(360), birdSize, new Point(r1.nextInt(1000),r1.nextInt(1000)), birdR, birdG, birdB);

		//public Tomato(int size, Point location, int newNutrition, int R, int G, int B){
		tomatoSize = r1.nextInt(10)+1;
		Tomato t1 = new Tomato(tomatoSize , new Point(r1.nextInt(1000),r1.nextInt(1000)), tomatoSize*2, tomatoR, tomatoG, tomatoB);
		tomatoSize = r1.nextInt(10)+1;
		Tomato t2 = new Tomato(tomatoSize , new Point(r1.nextInt(1000),r1.nextInt(1000)), tomatoSize*2, tomatoR, tomatoG, tomatoB);

		gameObject.add(s1);
		gameObject.add(n1);
		gameObject.add(n2);
		gameObject.add(n3);
		gameObject.add(n4);
		gameObject.add(b1);
		gameObject.add(b2);
		gameObject.add(t1);
		gameObject.add(t2);

	}

	public void accelerate() {
		for (Object obj : gameObject) {
			if (obj instanceof Squirrel) {
				((Squirrel) obj).accelerate();
				break;
			}
		}
	}
	public void brake() {
		for (Object obj : gameObject) {
			if (obj instanceof Squirrel) {
				((Squirrel) obj).brake();
				break;
			}
		}
	}
	public void left() {
		for(Object obj: gameObject) {
			if (obj instanceof Squirrel) {
				((Squirrel) obj).steerLeft();
				break;
			}
		}
	}
	public void right() {
		for(Object obj: gameObject) {
			if (obj instanceof Squirrel) {
				((Squirrel) obj).steerRight();
				break;
			}
		}
	}
	public void collision(char collides) {
		for(Object obj : gameObject){
            if(obj instanceof Squirrel && collides == 'c'){
                ((Squirrel) obj).addDamageLevel(2);
                ((Squirrel) obj).changeColorAndSpeed(((Squirrel) obj).getDamageLevel());
                System.out.println("Squirrel has collided with another Squirrel");
                break;
            }else if(obj instanceof Squirrel && collides == 'g'){
                ((Squirrel) obj).addDamageLevel(1);
                System.out.println("Squirrel has collided with a Bird");
                break;
            }else if(obj instanceof Squirrel && collides == 'e') {
                tomatoCollide();
                break;
            }
        }
	}

	public void tomatoCollide(){
        for(Object obj: gameObject){
            if(obj instanceof Squirrel){
                for (Object obj2 : gameObject){
                    if(obj2 instanceof Tomato) {
                        if (((Tomato) obj2).getNutrition() != 0) {
                            ((Squirrel) obj).setEnergyLevel(((Tomato) obj2).getNutrition() + ((Squirrel) obj).getEnergyLevel());
                            System.out.println("Squirrel has gained " + ((Tomato) obj2).getNutrition() + " energy");
                            ((Tomato) obj2).setNutrition();
                            ((Tomato) obj2).setColor(ColorUtil.WHITE);
                            break;
                        }
                    }
                }
            }
        }
      //public Tomato(int size, Point location, int color, int newNutrition){
        Tomato newT = new Tomato(tomatoSize , new Point(r1.nextInt(1000),r1.nextInt(1000)), tomatoSize*2, tomatoR, tomatoG, tomatoB);
        gameObject.add(newT);
    }
	public void nutReached(int n) {
		for (Object obj : gameObject) {
			if (obj instanceof Squirrel) {
				if (((Squirrel) obj).getLastNutReached() + 1 == n) {
					((Squirrel) obj).setLastNutReached();
					System.out.println("Squirrel has reached Nut " + n);
				}
			}
		}
	}
	public void tick(){
        clock++;
        for(Object obj : gameObject){
            if(obj instanceof Squirrel){
                if(((Squirrel) obj).getEnergyLevel() == 0){
                    System.out.println("\nYou have no more energy");
                    lives--;
                    reset();
                    restart();
                    break;
                }else if(((Squirrel) obj).getDamageLevel() == 10){
                    System.out.println("\nYou have taken too much damage");
                    lives--;
                    reset();
                    restart();
                    break;
                }else {
                	((Squirrel) obj).move();
					((Squirrel) obj).setEnergyLevel(
							((Squirrel) obj).getEnergyLevel() - (((Squirrel) obj).getEnergyConsumptionRate()));
					((Squirrel) obj).setSteeringDirection(0);
                }
			} else if (obj instanceof Movable) {
				/*for (Object obj2 : gameObject) {
					if (obj2 instanceof Squirrel) {
						((Squirrel) obj2).move();
						((Squirrel) obj2).setEnergyLevel(
								((Squirrel) obj2).getEnergyLevel() - (((Squirrel) obj2).getEnergyConsumptionRate()));
						break;
					}
				}*/
				((Movable) obj).move();
			}

		}
    }

	public void reset() {
		for(Object obj: gameObject){
            if(obj instanceof Squirrel){
                ((Squirrel) obj).setDamageLevel(0);
                ((Squirrel) obj).setEnergyLevel(100);
                ((Squirrel) obj).setMaxSpeed(100);
                ((Squirrel) obj).setSpeed(0);
                ((Squirrel) obj).setColor(ColorUtil.rgb(255,0,0));
            }
        }
	}
	public void restart() {
		if(lives == 0) {
			System.out.println("Game Over. Please Try Again");
			new Game();
		}
	}

	public void display() {
		for(Object obj: gameObject) {
            if (obj instanceof Squirrel) {
            	System.out.println("-------------------------\n");
                System.out.println(" Lives: " + lives + "\n Elapsed Time: " + clock +
                					"\n Last Nut Reach: " + ((Squirrel) obj).getLastNutReached() +
                					"\n Energy Level: " + ((Squirrel) obj).getEnergyLevel() +
                					"\n Damage Level: " + ((Squirrel) obj).getDamageLevel() );
                System.out.println("\n-------------------------");
                break;
            }
        }
	}
	public void map() {
		System.out.println();
		for (int i = 0; i < gameObject.size(); i++) {
			System.out.println(gameObject.get(i).toString());
		}
	}
	public void quit() {
		boolean bOk = Dialog.show("Confirm quit", "Are you sure you want to quit?", "y", "n");
        if (bOk){
        	x();
            //Display.getInstance().exitApplication();
        }
	}
	public void x() {
		System.exit(0);
	}
}
