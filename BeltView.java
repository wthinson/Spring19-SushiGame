package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import comp401sushi.Plate;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver, ActionListener {

	private Belt belt;
	private PlateViewWidget[] labels;

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);

		setLayout(new GridLayout(belt.getSize(), 1));

		// you just need to display the information of each of the plates somehow. Make the JLabel's as JButtons
		// and assign a separate widget (PlateViewWidget) as a listener to these buttons and this widget
		// should should display given information about the plate. 
		
		labels = new PlateViewWidget[belt.getSize()];
		for (int i = 0; i < belt.getSize(); i++) {
			PlateViewWidget label = new PlateViewWidget(belt, null, i);
			
			label.getButton().addActionListener(this);
			add(label.getButton());
			labels[i] = label;
		}
		
		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}

	private void refresh() {
		for (int i = 0; i < belt.getSize(); i++) {
			Plate p = belt.getPlateAtPosition(i);
			labels[i].update(p, i);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// For each button on the view
		for (int i = 0; i < belt.getSize(); i++) {
			if(e.getSource() == labels[i].getButton()) {
				
				
				if(labels[i].getButton().getText().contains("Ingredients")) {
					labels[i].update(labels[i].getPlate(), labels[i].getPosition());
				} else {
					String s = "Ingredients: ";
					
					for (int j = 0; j < labels[i].getPlate().getContents().getIngredients().length; j++) {
						s += String.format("%.2f", labels[i].getPlate().getContents().getIngredients()[j].getAmount()) + " oz. " + labels[i].getPlate().getContents().getIngredients()[j].getName() + ", ";
					}
					
					labels[i].getButton().setText(s);
				}
			}
		}
		
		
		

	}
}
