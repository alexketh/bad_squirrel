package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.mycompany.a3.GameObjects.*;
import com.mycompany.a3.Interfaces.*;

public class GameWorld extends Observable{
	private int lives 	= 3;
	private int clock 	= 0;
	private boolean sound;
	private boolean paused;
	private boolean isClicked;

	private final GameObjectCollection list = new GameObjectCollection();
	private final PlayerSquirrel pSquirrel = new PlayerSquirrel(new Point(0,0), 100, ColorUtil.rgb(28,6,0), 1, 0, this);

	private Sound attackSound;
	private Sound eatingSound;
	private Sound nutArrivalSound;
	private BGSound backgroundSound;

	public void init() {
		addNuts();
		addBird();
		addSquirrel();
		addNPCSquirrel();
		addTomato();
		sound = false;
		paused = false;
		isClicked = false;
		this.setChanged();
		this.notifyObservers();

	}

//	GETTERS
	public int getClock() {
		return clock;
	}
	public int getLives() {
		return lives;
	}
	public boolean getSound() {
		return sound;
	}
	public boolean getPaused() {
		return paused;
	}
	public boolean getPositionClickStatus() {
		return isClicked;
	}

//	SETTERS
	public void setClock(int newClock) {
		clock = newClock;
	}
	public void setLives(int newLives) {
		clock = newLives;
	}
	public void setGameStatus(boolean isGamePaused) {
		paused = isGamePaused;
	}
	public void setPositionClickStatus(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public void createSounds() {
		attackSound = new Sound("minecraft_hurt.mp3", "audio/mpx");
		eatingSound = new Sound("minecraft_eating.mp3", "audio/mpx");
		nutArrivalSound = new Sound("success.wav", "audio/mpx");
		backgroundSound = new BGSound("mainmenu.wav");
	}

	public BGSound getBackgroundSound() {
		return backgroundSound;
	}

	public String isSoundOn() {
		if(sound)
			return "ON";
		return "OFF";
	}

	public void toggleSound() {
		this.sound = !(this.sound);
		if (!sound)
			backgroundSound.pause();
		else
			backgroundSound.play();
		this.setChanged();
		this.notifyObservers();
	}

	public void addNewTomato() {
		Random r1 = new Random();
		int tomatoSize = r1.nextInt(50) +100;
		list.add(new Tomato(tomatoSize , randomPoint(), tomatoSize*2, ColorUtil.rgb(255,0,0), this));
	}
	
	public GameObjectCollection getGameObjectCollection() {
		return list;
	}

	public void addSquirrel() {
		IIterator itr = list.getIterator();
		while (itr.hasNext()) {
			GameObject go = (GameObject) itr.getNext();
			if (go instanceof Nut) {
				if ( ((Nut)go).getSequenceNumber() == 1 )
					pSquirrel.setLocation( go.getLocation() );
			}
		}
		list.add(pSquirrel);
	}

	public void addNuts() {
		int numOfNuts = 9;
		for (int i=1; i<=numOfNuts; i++) {
			list.add(new Nut(randomPoint(), 50, i, ColorUtil.GREEN, this));
		}
	}

	public void addBird() {
		Random r1 = new Random();
		list.add(new Bird(r1.nextInt(5)+5, r1.nextInt(360), 100, randomPoint(), ColorUtil.CYAN, this));
		list.add(new Bird(r1.nextInt(5)+5, r1.nextInt(360), 100, randomPoint(), ColorUtil.CYAN, this));
	}

	public void addTomato() {
		Random r1 = new Random();
		int tomatoSize = r1.nextInt(50) +100;
		list.add(new Tomato(tomatoSize , randomPoint(), tomatoSize*2, ColorUtil.rgb(255,0,0), this));
		tomatoSize = r1.nextInt(50) +100;
		list.add(new Tomato(tomatoSize , randomPoint(), tomatoSize*2, ColorUtil.rgb(255,0,0), this));
	}

	public void addNPCSquirrel () {
		/*
		list.add(new NonPlayerSquirrel(new Point(120, 120), 100, ColorUtil.YELLOW, 10, 0, this));
		list.add(new NonPlayerSquirrel(new Point(220, 220), 100, ColorUtil.YELLOW, 10, 0, this));
		list.add(new NonPlayerSquirrel(new Point(1000, 1000), 100, ColorUtil.YELLOW, 10, 0, this));
		 */
		for (int i=0; i<3; i++) {
			NonPlayerSquirrel npc = new NonPlayerSquirrel(randomPoint(), 100, ColorUtil.YELLOW, 2, 0, this);
			if (i==0)
				npc.setStrategy(new AttackStrategy(list,npc));
			else
				npc.setStrategy(new NutStrategy(list,npc));
			list.add(npc);
		}
	}

	public Point randomPoint() {
		Random r = new Random();
		int x = r.nextInt(1300) + 100;
		int y = r.nextInt(900) + 100;
		return new Point(x,y);
	}

//	ORIGINAL METHODS --------------------------------------
	// PRESS 'a'
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

	// PRESS 'b'
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

	// PRESS 'l'
	public void left() {
		IIterator itr = list.getIterator();
		while (itr.hasNext()) {
			GameObject go = (GameObject) itr.getNext();
			if (go instanceof PlayerSquirrel) {
				((PlayerSquirrel) go).steerLeft();
			}
		}
		this.setChanged();
		this.notifyObservers();
	}

	// PRESS 'r'
	public void right() {
		IIterator itr = list.getIterator();
		while (itr.hasNext()) {
			GameObject go = (GameObject) itr.getNext();
			if (go instanceof PlayerSquirrel) {
				((PlayerSquirrel) go).steerRight();
			}
		}
		this.setChanged();
		this.notifyObservers();
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

	// PRESS 't'
	public void tick() {
		clock++;
		Random r1 = new Random();
		IIterator itr = list.getIterator();
		IIterator itr2 = list.getIterator();
		while (itr.hasNext()) {
			GameObject go = (GameObject) itr.getNext();
			if (go instanceof PlayerSquirrel) {
				if ( ((PlayerSquirrel) go).getEnergyLevel() > 0 && ((PlayerSquirrel) go).getDamageLevel() < ((PlayerSquirrel) go).getMaxDamageLevel() ) {
					((PlayerSquirrel) go).move();
					((PlayerSquirrel) go).decreaseEnergyLevel();
					((PlayerSquirrel) go).setSteeringDirection(0);
				} else if ( lives != 0 ) {
					System.out.println("You lost a life");
					lives--;
					quit(3);
					reset();
				} else {
					System.out.println("No more lives");
					quit(2);
				}
			} else if (go instanceof NonPlayerSquirrel) {
				if ( ((NonPlayerSquirrel) go).getDamageLevel() < ((NonPlayerSquirrel) go).getMaxDamageLevel() ) {
					((NonPlayerSquirrel) go).invokeStrategy();
					((NonPlayerSquirrel) go).move();
				}
			} else if (go instanceof Bird) {
				((Bird) go).setHeading( ((Bird) go).getHeading() + r1.nextInt(5) );
				((Bird) go).move();
			}
		}
		checkCollision();
		//if (soundChecked)
			//backgroundSound.play();
		this.setChanged();
		this.notifyObservers();
	}

	public void checkCollision() {
		IIterator itr = list.getIterator();
		while(itr.hasNext()) {
			ICollider go = (ICollider) itr.getNext();
			IIterator itr2 = list.getIterator();
			while(itr2.hasNext()) {
				ICollider go2 = (ICollider) itr2.getNext();
				if (go!=go2) {
					if(go.collidesWith(go2)) {
						if (go instanceof PlayerSquirrel && go2 instanceof Bird) {
							if (getSound())
								System.out.println("Play Bird Sound");
								//attackSound.play();
						}
						else if (go instanceof PlayerSquirrel && go2 instanceof NonPlayerSquirrel) {
							if (getSound())
								System.out.println("Play NPC Sound");
								//attackSound.play();
						}
						else if (go instanceof PlayerSquirrel && go2 instanceof Nut) {
							if (getSound())
								System.out.println("Play Nut Sound");
								//nutArrivalSound.play();
						}
						else if (go instanceof PlayerSquirrel && go2 instanceof Tomato) {
							if (getSound())
								System.out.println("Play Tomato Sound");
								//eatingSound.play();
						}
						go.handleCollision(go2);
					}
				}
			}
		}
	}

	public void changeStrategy() {
		//Random r1 = new Random();
		GameObject go;
		IIterator iter = list.getIterator();
		while (iter.hasNext()) {
			go = (GameObject) iter.getNext();
			if (go instanceof NonPlayerSquirrel) {
				//int strategyChoice = r1.nextInt(2);
				if (((NonPlayerSquirrel) go).getStrategy().equals("Nut Strategy")) {
					((NonPlayerSquirrel) go).setStrategy(new AttackStrategy(list, ((NonPlayerSquirrel) go)));
				} else {
					((NonPlayerSquirrel) go).setStrategy(new NutStrategy(list, ((NonPlayerSquirrel) go)));
				}
			}
		}
		this.setChanged();
		this.notifyObservers();
	}

	public void reset() {
		IIterator itr = list.getIterator();
		IIterator itr2 = list.getIterator();
		while (itr.hasNext()) {
			GameObject go = (GameObject) itr.getNext();
            if(go instanceof PlayerSquirrel){
                ((PlayerSquirrel) go).setDamageLevel(0);
                ((PlayerSquirrel) go).setEnergyLevel(100);
                ((PlayerSquirrel) go).setMaxSpeed(100);
                ((PlayerSquirrel) go).setSpeed(0);
				go.setColor(ColorUtil.rgb(28,6,0));
				while (itr2.hasNext()) {
					int lastNut = ((PlayerSquirrel) go).getLastNutReached();
					GameObject go2 = (GameObject) itr2.getNext();
					if(go2 instanceof Nut) {
						if (lastNut == ((Nut) go2).getSequenceNumber()) {
							go.setLocation( go2.getLocation() );
							//if(soundChecked)
							//life.play();
						}
					}
				}
            }
        }
		this.setChanged();
		this.notifyObservers();
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
	
	public void playSound(int x) {
		switch (x) {
			case 1: attackSound.play(); break;
			case 2: nutArrivalSound.play(); break;
			case 3: eatingSound.play(); break;
		}
	}

	public void quit(int number) {
		Command cOk = new Command("Ok");
		Command cOkLife = new Command("Continue");
		Command answer = new Command("");
		switch(number) {
			case 0:
				answer = Dialog.show("Congratulations!!!",  "You Win with the time is: " + this.getClock()/20, cOk);
				break;
			case 1:
				answer = Dialog.show("You have lost the game!!!!",  "Another squirrel has reached the last nut!", cOk);
				break;
			case 2:
				answer = Dialog.show("You have lost the game!!!!",  "You are out of lives!!!", cOk);
				break;
			case 3:
				Dialog.show("ALERT!!!!",  "You have just lost 1 live !!!", cOkLife);
				break;
			case 4:
				System.exit(0);
		}
		if(answer.equals(cOk)) {
			System.exit(0);
		}
	}
}
