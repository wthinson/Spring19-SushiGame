package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import comp401sushi.Plate;
import comp401sushi.PlateImpl;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class PlateViewWidget extends JPanel {

	private JButton button;
	
	private Belt belt;
	private Plate plate;
	private int position;
	
	public PlateViewWidget (Belt belt) {
		this.belt = belt;
		this.plate = null;
		this.position = -1;
		
		button = new JButton();
		button.setMinimumSize(new Dimension(300, 20));
		button.setPreferredSize(new Dimension(300, 20));
		button.setOpaque(true);
		button.setBackground(Color.GRAY);
		button.setBorderPainted(false);
		
	}

	public PlateViewWidget (Belt belt, Plate plate, int position) {
		this.belt = belt;
		this.plate = plate;
		this.position = position;
		
		button = new JButton();
		button.setMinimumSize(new Dimension(300, 20));
		button.setPreferredSize(new Dimension(300, 20));
		button.setOpaque(true);
		button.setBackground(Color.GRAY);
		button.setBorderPainted(false);
	}
	
	public JButton getButton() {
		return button;
	}
	
	public Plate getPlate() {
		return plate;
	}
	
	public Belt getBelt() {
		return belt;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void update(Plate plate, int position) {
		this.plate = plate;
		this.position = position;
		
		
		String s = "";
		
		if(this.plate != null) {
			s = plate.getContents().getName() + " by " + this.plate.getChef().getName() + " | Age: " + belt.getAgeOfPlateAtPosition(this.position);
			
			switch (this.plate.getColor()) {
			case RED:
				button.setBackground(Color.RED); break;
			case GREEN:
				button.setBackground(Color.GREEN); break;
			case BLUE:
				button.setBackground(Color.BLUE); break;
			case GOLD:
				button.setBackground(Color.YELLOW); break;
			}
		} else {
			button.setBackground(Color.GRAY);
		}
		
		button.setText(s);
	}
}
