package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class Game extends Form {
	private GameWorld gw;

	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
	}

	@SuppressWarnings("rawtypes")
	private void play() {
		Label myLabel = new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();
		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				if (sCommand.length() != 0)
					switch (sCommand.charAt(0)) {
					case 'a':	gw.accelerate();			break;
					case 'b':	gw.brake();					break;
					case 'l': 	gw.left();					break;
					case 'r':	gw.right(); 				break;
					case 'c': 	gw.collision('c');			break;
					case '1':	gw.nutReached(1);			break;
					case '2':	gw.nutReached(2);			break;
					case '3':	gw.nutReached(3);			break;
					case '4':	gw.nutReached(4);			break;
					case '5':	gw.nutReached(5);			break;
					case '6':	gw.nutReached(6);			break;
					case '7':	gw.nutReached(7);			break;
					case '8':	gw.nutReached(8);			break;
					case '9':	gw.nutReached(9);			break;
					case 'e':	gw.collision('e');			break;
					case 'g':	gw.collision('g'); 			break;
					case 't':	gw.tick(); 					break;
					case 'd':	gw.display();				break;
					case 'm':	gw.map();					break;
					case 'x':	gw.quit();					break;
					default: System.out.println("No such command");
					break;
					// add code to handle rest of the commands
					} // switch
			} // actionPerformed
		} // new ActionListener()
		); // addActionListener
	} // play
}
