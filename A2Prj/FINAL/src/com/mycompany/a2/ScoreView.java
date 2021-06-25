package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
	private Label time, lives, lastNut, energy, damage, sound;
	private GameWorld gw;

	public ScoreView(GameWorld w) {
		gw = w;
		this.setLayout(new FlowLayout(Component.CENTER));
		time = new Label("Time: " + gw.getClock());
		lives = new Label("Lives Left: " + gw.getLives());
		lastNut = new Label("Last Nut Reached: " + gw.getLastNut());
		energy = new Label("Energy Level: " + gw.getSquirrelEnergy());
		damage = new Label("Damage Level: " + gw.getSquirrelDamage());
		sound = new Label("Sound: " + gw.getSound());
		this.add(time);
		this.add(lives);
		this.add(energy);
		this.add(damage);
		this.add(sound);
	}

	@Override
	public void update(Observable observable, Object data) {
		time.setText("Time: " + gw.getClock() + "    ");
		lives.setText("Lives Left: " + gw.getLives() + "    ");
		lastNut.setText("Last Nut Reached: " + gw.getLastNut() + "    ");
		energy.setText("Energy Level: " + gw.getSquirrelEnergy() + "    ");
		damage.setText("Damage Level: " + gw.getSquirrelDamage()+ "    ");
		sound.setText("Sound: " + gw.getSound());

	}
}	