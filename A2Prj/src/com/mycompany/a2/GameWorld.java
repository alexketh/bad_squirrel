package com.mycompany.a2;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.mycompany.a2.GameObjects.*;
import com.mycompany.a2.Interfaces.*;

public class GameWorld extends Observable{
	private int lives 	= 3;
	private int clock 	= 0;
	
	private boolean soundChecked = false;
	//private int mapHeight = 1000;
	//private int mapWidth 	= 1000;
	private GameObjectCollection list = new GameObjectCollection();
	private PlayerSquirrel pSquirrel = new PlayerSquirrel(new Point(0,0), 40, ColorUtil.MAGENTA, 1, 0);
	
	public void init() {
		
		addSquirrel();
		addNPCSquirrel();
		addNuts();
		addBird();
		addTomato();
		this.setChanged();
		this.notifyObservers();

	}
	
	public void changeStrategy() {
		Random r1 = new Random();
		GameObject go;
		IIterator iter = list.getIterator();
		while(iter.hasNext()) {
			go = (GameObject) iter.getNext();
			if (go instanceof NonPlayerSquirrel) {
				NonPlayerSquirrel npc = (NonPlayerSquirrel) go;
				if ( ((NonPlayerSquirrel) go).getLastNutReached() != 9 ) {
					int temp = ((NonPlayerSquirrel) go).getLastNutReached();
					temp++;
					((NonPlayerSquirrel) go).setLastNutReached(temp);
					int strategyChoice = r1.nextInt(2);
					if (strategyChoice == 0) {
						npc.setStrategy(new AttackStrategy(list, npc));
					}
					else {
						npc.setStrategy(new NutStrategy(list, npc));
					}
				}
				else if ( ((NonPlayerSquirrel) go ).getLastNutReached() == 9 ) {
					System.out.println("Game Over, you lost to an NPC!");
					System.exit(0);
				}
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public int getClock() {
		return clock;
	}
	public int getLives() {
		return lives;
	}
	public int getLastNut() {
		GameObject go;
		IIterator itr = list.getIterator();
		PlayerSquirrel s1 = null;
		while (itr.hasNext()) {
			go = (GameObject) itr.getNext();
			if (go instanceof PlayerSquirrel) {
				return ((PlayerSquirrel) go).getLastNutReached();
			}
		}
		return 0;
		//return s1.getEnergyLevel();
	}
	public int getSquirrelEnergy() {
		GameObject go;
		IIterator itr = list.getIterator();
		PlayerSquirrel s1 = null;
		while (itr.hasNext()) {
			go = (GameObject) itr.getNext();
			if (go instanceof PlayerSquirrel) {
				return ((PlayerSquirrel) go).getEnergyLevel();
			}
		}
		return 0;
		//return s1.getEnergyLevel();
	}
	public int getSquirrelDamage() {
		GameObject go;
		IIterator itr = list.getIterator();
		PlayerSquirrel s1 = null;
		while (itr.hasNext()) {
			go = (GameObject) itr.getNext();
			if (go instanceof PlayerSquirrel) {
				return ((PlayerSquirrel) go).getDamageLevel();
			}
		}
		return 0;
		//return s1.getDamageLevel();
	}
	public String getSound() {
		if (soundChecked) {
			return "ON";
		} else {
			return "OFF";
		}
	}
	
	public void addSquirrel() {
		list.add(pSquirrel);
	}
	
	public void addNuts() {
		list.add(new Nut(new Point(0,0), 		10, 1, ColorUtil.BLUE));
		list.add(new Nut(new Point(200,200), 	10, 2, ColorUtil.BLUE));
		list.add(new Nut(new Point(300,300), 	10, 3, ColorUtil.BLUE));
		list.add(new Nut(new Point(400,400), 	10, 4, ColorUtil.BLUE));
	}
	
	public void addBird() {
		Random r1 = new Random();
		list.add(new Bird(r1.nextInt(25)+5, r1.nextInt(360), 20, randomPoint(), ColorUtil.GRAY));
		list.add(new Bird(r1.nextInt(25)+5, r1.nextInt(360), 20, randomPoint(), ColorUtil.GRAY));
	}
	
	public void addTomato() {
		Random r1 = new Random();
		int tomatoSize = r1.nextInt(15) +5;
		list.add(new Tomato(tomatoSize , randomPoint(), tomatoSize*2, ColorUtil.CYAN));
		list.add(new Tomato(tomatoSize , randomPoint(), tomatoSize*2, ColorUtil.CYAN));
	}
	
	public void addNPCSquirrel () {
		list.add(new NonPlayerSquirrel(new Point(120,120), 40, ColorUtil.WHITE, 1, 0));
		list.add(new NonPlayerSquirrel(new Point(220,220), 40, ColorUtil.WHITE, 1, 0));
		list.add(new NonPlayerSquirrel(new Point(420,420), 40, ColorUtil.WHITE, 1, 0));
	}
	
	public void toggleSound() {
		this.soundChecked = !(this.soundChecked);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void npcCollision()// This method shows that two robots have collided
	{
		collision('c');
		this.setChanged();
		this.notifyObservers();
		System.out.println("NPC Squirrel has been collided");		
	}

	public Point randomPoint() {
		Random r = new Random();
		int x = r.nextInt(1000) + 1;
		int y = r.nextInt(1000) + 1;
		return new Point(x,y);
	}
	
	public void accelerate() {
		GameObject go;
		IIterator itr = list.getIterator();
		while (itr.hasNext()) {
			go = (GameObject) itr.getNext();
			if (go instanceof PlayerSquirrel) {
				((PlayerSquirrel) go).accelerate();
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void brake() {
		GameObject go;
		IIterator itr = list.getIterator();
		while (itr.hasNext()) {
			go = (GameObject) itr.getNext();
			if (go instanceof PlayerSquirrel) {
				((PlayerSquirrel) go).brake();
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void left() {
		GameObject go;
		IIterator itr = list.getIterator();
		while (itr.hasNext()) {
			go = (GameObject) itr.getNext();
			if (go instanceof PlayerSquirrel) {
				((PlayerSquirrel) go).steerLeft();
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void right() {
		GameObject go;
		IIterator itr = list.getIterator();
		while (itr.hasNext()) {
			go = (GameObject) itr.getNext();
			if (go instanceof PlayerSquirrel) {
				((PlayerSquirrel) go).steerRight();
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void collision(char collides) {
		GameObject go;
		IIterator itr = list.getIterator();
		while (itr.hasNext()) {
			go = (GameObject) itr.getNext();
			if(go instanceof PlayerSquirrel && collides == 'c'){ // Player Collide w NPC
                ((PlayerSquirrel) go).addDamageLevel(2);
                ((PlayerSquirrel) go).changeColorAndSpeed(((PlayerSquirrel) go).getDamageLevel());
                System.out.println("Squirrel has collided with another Squirrel");
                break;
            }else if(go instanceof PlayerSquirrel && collides == 'g'){ // Bird Collide
                ((PlayerSquirrel) go).addDamageLevel(1);
                System.out.println("Squirrel has collided with a Bird");
                break;
            }else if(go instanceof PlayerSquirrel && collides == 'e') { // Tomato Collide
                tomatoCollide();
                break;
            }
		}
		this.setChanged();
		this.notifyObservers();
	}

	public void tomatoCollide(){
		Random r1 = new Random();
		int tomatoSize = r1.nextInt(15) +5;
		GameObject go;
		IIterator itr = list.getIterator();
		PlayerSquirrel s1 = null;
		ArrayList<Tomato> tomatoes = new ArrayList<Tomato>();
		while (itr.hasNext()) {
			go = (GameObject) itr.getNext();
			if(go instanceof PlayerSquirrel){
				s1 = (PlayerSquirrel) go;
			}
			if(go instanceof Tomato) {
				tomatoes.add((Tomato) go);
			}
		}
		for(Tomato t: tomatoes) {
			if(s1.getLocation().getX() == t.getLocation().getX() && s1.getLocation().getY() == t.getLocation().getY()) {
				if (t.getNutrition() != 0) {
					s1.setEnergyLevel( t.getNutrition() + s1.getEnergyLevel() );
					System.out.println("Squirrel has gained " + t.getNutrition() + " energy");
					t.setNutrition();
					t.setColor(ColorUtil.WHITE);
					list.add(new Tomato(tomatoSize , randomPoint(), tomatoSize*2, ColorUtil.CYAN));
					break;
				}
			} else {
				System.out.println("You have not reached a tomato yet.");
			}
		}
        this.setChanged();
		this.notifyObservers();
    }
	
	public void nutReached(int x) {
		GameObject go;
		IIterator itr = list.getIterator();
		ArrayList<Nut> nuts = new ArrayList<Nut>();
		PlayerSquirrel s1 = null;
		itr = list.getIterator();
		while (itr.hasNext()) {
			go = (GameObject) itr.getNext();
			if (go instanceof Nut) {
				nuts.add((Nut) go);
			}
			if (go instanceof PlayerSquirrel) {
				s1 = (PlayerSquirrel) go;
			}
		}
		for (Nut n : nuts) {
			if (n.getSequenceNumber() == x) {
				s1.setLocation(new Point(n.getLocation().getX(), n.getLocation().getY()));
			}
		}
		if (s1.getLastNutReached() == x - 1) {
			s1.setLastNutReached();
			Dialog.show("Reached next nut!", null, "continue", null);
			if (s1.getLastNutReached() == 9) {
				Dialog.show("Victory!", "All nuts reached!", "exit", null);
				quit();
			}
		} else {
			Dialog.show("Wrong nut!", "Must go in sequence!", "continue", null);
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public void tick(){
        clock++;
        GameObject go;
		IIterator itr = list.getIterator();
		while (itr.hasNext()) {
			go = (GameObject) itr.getNext();
			if(go instanceof PlayerSquirrel){
                if(((PlayerSquirrel) go).getEnergyLevel() == 0){
                    System.out.println("\nYou have no more energy");
                    lives--;
                    reset();
                    restart();
                    break;
                }else if(((PlayerSquirrel) go).getDamageLevel() == 10){
                    System.out.println("\nYou have taken too much damage");
                    lives--;
                    reset();
                    restart();
                    break;
                }else {
                	((PlayerSquirrel) go).move();
					((PlayerSquirrel) go).setEnergyLevel(
							((PlayerSquirrel) go).getEnergyLevel() - (((PlayerSquirrel) go).getEnergyConsumptionRate()));
					((PlayerSquirrel) go).setSteeringDirection(0);
                }
			} else if (go instanceof Movable) {
				((Movable) go).move();
			}
		}
        this.setChanged();
		this.notifyObservers();
    }

	public void reset() {
		GameObject go;
		IIterator itr = list.getIterator();
		while (itr.hasNext()) {
			go = (GameObject) itr.getNext();
            if(go instanceof PlayerSquirrel){
                ((PlayerSquirrel) go).setDamageLevel(0);
                ((PlayerSquirrel) go).setEnergyLevel(100);
                ((PlayerSquirrel) go).setMaxSpeed(100);
                ((PlayerSquirrel) go).setSpeed(0);
                ((PlayerSquirrel) go).setColor(ColorUtil.MAGENTA);
            }
        }
		this.setChanged();
		this.notifyObservers();
	}
	
	public void restart() {
		if(lives == 0) {
			Dialog.show("Game Over", "No more lives", "Exit", null);
			quit();
		}
	}

	public void display() {
		GameObject go;
		IIterator itr = list.getIterator();
		PlayerSquirrel s1 = null;
		while (itr.hasNext()) {
			go = (GameObject) itr.getNext();
            if(go instanceof PlayerSquirrel){
            	s1 = (PlayerSquirrel) go;
            }
        }

		System.out.println("-------------------------\n");
		System.out.println(" Lives: " + lives + 
				"\n Elapsed Time: " + clock +
				"\n Last Nut Reach: " + s1.getLastNutReached() +
				"\n Energy Level: " + s1.getEnergyLevel() +
				"\n Damage Level: " + s1.getDamageLevel() );
		System.out.println("\n-------------------------");

	}
	
	public void map() {
		GameObject go;
		IIterator itr = list.getIterator();
		System.out.println("\n----------------------MAP----------------------");
		while (itr.hasNext()) {
			go = (GameObject) itr.getNext();
			System.out.println(go);
        }
	}
	
	public void quit() {
		System.exit(0);
	}
}