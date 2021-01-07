package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.Popup;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import comp401sushi.AvocadoPortion;
import comp401sushi.Crab;
import comp401sushi.CrabPortion;
import comp401sushi.EelPortion;
import comp401sushi.Ingredient;
import comp401sushi.IngredientPortion;
import comp401sushi.IngredientPortionImpl;
import comp401sushi.Nigiri;
import comp401sushi.Plate;
import comp401sushi.RedPlate;
import comp401sushi.Roll;
import comp401sushi.Sashimi;
import comp401sushi.SeaweedPortion;
import comp401sushi.ShrimpPortion;
import comp401sushi.Sushi;
import comp401sushi.TunaPortion;
import comp401sushi.YellowtailPortion;

public class PlayerChefView extends JPanel implements ActionListener, ChangeListener {

	private List<ChefViewListener> listeners;
	private Sushi kmp_roll;
	private Sushi ingredients_sashimi;
	private Sushi ingredients_nigiri;
	private int belt_size;

	private JSlider position_slider;
	private JSpinner crab_spinner;
	private JSpinner eel_spinner;
	private JSpinner shrimp_spinner;
	private JSpinner tuna_spinner;
	private JSpinner yellowtail_spinner;
	private JLabel crab_label;
	private JLabel eel_label;
	private JLabel shrimp_label;
	private JLabel tuna_label;
	private JLabel yellowtail_label;
	private ButtonGroup sushi_buttons;
	private ButtonGroup color_buttons;
	private JSlider cost_slider;
	private JButton set;
	private ArrayList<IngredientPortion> ingPortionArray;

	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();

		//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		setLayout(new GridLayout(0, 2));

		sushi_buttons = new ButtonGroup();
		JRadioButton sashimi_button = new JRadioButton("Make sashimi", false);
		JRadioButton nigiri_button = new JRadioButton("Make nigiri", false);
		JRadioButton roll_button = new JRadioButton("Make a roll", false);
		sashimi_button.setActionCommand("sashimi");
		nigiri_button.setActionCommand("nigiri");
		roll_button.setActionCommand("roll");
		sushi_buttons.add(sashimi_button);
		sushi_buttons.add(nigiri_button);
		sushi_buttons.add(roll_button);
		sashimi_button.addActionListener(this);
		nigiri_button.addActionListener(this);
		roll_button.addActionListener(this);
		add(sashimi_button);
		add(nigiri_button);
		add(roll_button);

		//JSpinner position_spinner = new JSpinner();

		position_slider = new JSlider(0, belt_size, 0);
		position_slider.setMinorTickSpacing(1);
		position_slider.setMajorTickSpacing(5);
		position_slider.setSnapToTicks(true);
		position_slider.addChangeListener(this);
		position_slider.setPaintTicks(true);
		position_slider.setPaintLabels(true);
		add(position_slider);

		color_buttons = new ButtonGroup();
		JRadioButton red_button = new JRadioButton("Red Plate", false);
		JRadioButton blue_button = new JRadioButton("Blue Plate", false);
		JRadioButton green_button = new JRadioButton("Green Plate", false);
		JRadioButton gold_button = new JRadioButton("Gold Plate", false);
		red_button.setActionCommand("red");
		blue_button.setActionCommand("blue");
		green_button.setActionCommand("green");
		gold_button.setActionCommand("gold");
		color_buttons.add(red_button);
		color_buttons.add(blue_button);
		color_buttons.add(green_button);
		color_buttons.add(gold_button);
		red_button.addActionListener(this);
		blue_button.addActionListener(this);
		green_button.addActionListener(this);
		gold_button.addActionListener(this);
		add(red_button);
		add(blue_button);
		add(green_button);
		add(gold_button);

		JPanel ingredient_panel = new JPanel();
		ingredient_panel.setLayout(new BorderLayout());

		crab_label = new JLabel("Crab: ");
		eel_label = new JLabel("Eel: ");
		shrimp_label = new JLabel("Shrimp: ");
		tuna_label = new JLabel("Tuna: ");
		yellowtail_label = new JLabel("Yellowtail: ");

		add(crab_label);

		crab_spinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1.5, .1));
		JComponent crab_editor = crab_spinner.getEditor();
		crab_editor.setPreferredSize(new Dimension(75,50));
		crab_spinner.addChangeListener(this);
		crab_spinner.setVisible(true);
		add(crab_spinner);

		add(eel_label);

		eel_spinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1.5, .1));
		JComponent eel_editor = eel_spinner.getEditor();
		eel_editor.setPreferredSize(new Dimension(75,50));
		eel_spinner.addChangeListener(this);
		eel_spinner.setVisible(true);
		add(eel_spinner);

		add(shrimp_label);

		shrimp_spinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1.5, .1));
		JComponent shrimp_editor = shrimp_spinner.getEditor();
		shrimp_editor.setPreferredSize(new Dimension(75,50));
		shrimp_spinner.addChangeListener(this);
		shrimp_spinner.setVisible(true);
		add(shrimp_spinner);

		add(tuna_label);

		tuna_spinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1.5, .1));
		JComponent tuna_editor = tuna_spinner.getEditor();
		tuna_editor.setPreferredSize(new Dimension(75,50));
		tuna_spinner.addChangeListener(this);
		tuna_spinner.setVisible(true);
		add(tuna_spinner);

		add(yellowtail_label);

		yellowtail_spinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1.5, .1));
		JComponent yellowtail_editor = yellowtail_spinner.getEditor();
		yellowtail_editor.setPreferredSize(new Dimension(75,50));
		yellowtail_spinner.addChangeListener(this);
		yellowtail_spinner.setVisible(true);
		add(yellowtail_spinner);


		//		addIng = new JButton("Add Another Ing");
		//		addIng.addActionListener(this);
		//		addIng.setActionCommand("adding");
		//		ingredient_panel.add(addIng, BorderLayout.EAST);
		//		addIng.setVisible(true);

		set = new JButton("Place On Belt");
		set.addActionListener(this);
		set.setActionCommand("set");
		set.setVisible(true);
		add(set);


		cost_slider = new JSlider(5, 10, 5);
		cost_slider.setMajorTickSpacing(1);
		cost_slider.setSnapToTicks(true);
		cost_slider.addChangeListener(this);
		cost_slider.setPaintTicks(true);
		cost_slider.setPaintLabels(true);
		add(cost_slider);

		ingPortionArray = new ArrayList<IngredientPortion>();

		//		kmp_roll = new Roll("KMP Roll", new IngredientPortion[] {new EelPortion(1.0), new AvocadoPortion(0.5), 
		//				new SeaweedPortion(0.2)});
		//		crab_sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
		//		eel_nigiri = new Nigiri(Nigiri.NigiriType.EEL);
	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "set":

			switch (sushi_buttons.getSelection().getActionCommand()) {
			case "sashimi": 

				switch (color_buttons.getSelection().getActionCommand()) {
				case "red":

					if ((double) crab_spinner.getValue() > 0.0) {
						makeRedPlateRequest(new Sashimi(Sashimi.SashimiType.CRAB), position_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeRedPlateRequest(new Sashimi(Sashimi.SashimiType.EEL), position_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeRedPlateRequest(new Sashimi(Sashimi.SashimiType.SHRIMP), position_slider.getValue());
					} else if ((double) shrimp_spinner.getValue() > 0.0) {
						makeRedPlateRequest(new Sashimi(Sashimi.SashimiType.TUNA), position_slider.getValue());
					} else if ((double) yellowtail_spinner.getValue() > 0.0) {
						makeRedPlateRequest(new Sashimi(Sashimi.SashimiType.YELLOWTAIL), position_slider.getValue());
					}

					break;

				case "blue":

					if ((double) crab_spinner.getValue() > 0.0) {
						makeBluePlateRequest(new Sashimi(Sashimi.SashimiType.CRAB), position_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeBluePlateRequest(new Sashimi(Sashimi.SashimiType.EEL), position_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeBluePlateRequest(new Sashimi(Sashimi.SashimiType.SHRIMP), position_slider.getValue());
					} else if ((double) shrimp_spinner.getValue() > 0.0) {
						makeBluePlateRequest(new Sashimi(Sashimi.SashimiType.TUNA), position_slider.getValue());
					} else if ((double) yellowtail_spinner.getValue() > 0.0) {
						makeBluePlateRequest(new Sashimi(Sashimi.SashimiType.YELLOWTAIL), position_slider.getValue());
					}
					break;

				case "green":

					if ((double) crab_spinner.getValue() > 0.0) {
						makeGreenPlateRequest(new Sashimi(Sashimi.SashimiType.CRAB), position_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeGreenPlateRequest(new Sashimi(Sashimi.SashimiType.EEL), position_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeGreenPlateRequest(new Sashimi(Sashimi.SashimiType.SHRIMP), position_slider.getValue());
					} else if ((double) shrimp_spinner.getValue() > 0.0) {
						makeGreenPlateRequest(new Sashimi(Sashimi.SashimiType.TUNA), position_slider.getValue());
					} else if ((double) yellowtail_spinner.getValue() > 0.0) {
						makeGreenPlateRequest(new Sashimi(Sashimi.SashimiType.YELLOWTAIL), position_slider.getValue());
					}
					break;

				case "gold":

					if ((double) crab_spinner.getValue() > 0.0) {
						makeGoldPlateRequest(new Sashimi(Sashimi.SashimiType.CRAB), position_slider.getValue(), cost_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeGoldPlateRequest(new Sashimi(Sashimi.SashimiType.EEL), position_slider.getValue(), cost_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeGoldPlateRequest(new Sashimi(Sashimi.SashimiType.SHRIMP), position_slider.getValue(), cost_slider.getValue());
					} else if ((double) shrimp_spinner.getValue() > 0.0) {
						makeGoldPlateRequest(new Sashimi(Sashimi.SashimiType.TUNA), position_slider.getValue(), cost_slider.getValue());
					} else if ((double) yellowtail_spinner.getValue() > 0.0) {
						makeGoldPlateRequest(new Sashimi(Sashimi.SashimiType.YELLOWTAIL), position_slider.getValue(), cost_slider.getValue());
					}
					break;
				}
				break;

			case "nigiri": 

				switch (color_buttons.getSelection().getActionCommand()) {
				case "red":

					if ((double) crab_spinner.getValue() > 0.0) {
						makeRedPlateRequest(new Nigiri(Nigiri.NigiriType.CRAB), position_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeRedPlateRequest(new Nigiri(Nigiri.NigiriType.EEL), position_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeRedPlateRequest(new Nigiri(Nigiri.NigiriType.SHRIMP), position_slider.getValue());
					} else if ((double) shrimp_spinner.getValue() > 0.0) {
						makeRedPlateRequest(new Nigiri(Nigiri.NigiriType.TUNA), position_slider.getValue());
					} else if ((double) yellowtail_spinner.getValue() > 0.0) {
						makeRedPlateRequest(new Nigiri(Nigiri.NigiriType.YELLOWTAIL), position_slider.getValue());
					}
					//					System.out.println("red nigiri plate");
					//					makeRedPlateRequest();
					break;

				case "blue":

					if ((double) crab_spinner.getValue() > 0.0) {
						makeBluePlateRequest(new Nigiri(Nigiri.NigiriType.CRAB), position_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeBluePlateRequest(new Nigiri(Nigiri.NigiriType.EEL), position_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeBluePlateRequest(new Nigiri(Nigiri.NigiriType.SHRIMP), position_slider.getValue());
					} else if ((double) shrimp_spinner.getValue() > 0.0) {
						makeBluePlateRequest(new Nigiri(Nigiri.NigiriType.TUNA), position_slider.getValue());
					} else if ((double) yellowtail_spinner.getValue() > 0.0) {
						makeBluePlateRequest(new Nigiri(Nigiri.NigiriType.YELLOWTAIL), position_slider.getValue());
					}
					break;

				case "green":

					if ((double) crab_spinner.getValue() > 0.0) {
						makeGreenPlateRequest(new Nigiri(Nigiri.NigiriType.CRAB), position_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeGreenPlateRequest(new Nigiri(Nigiri.NigiriType.EEL), position_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeGreenPlateRequest(new Nigiri(Nigiri.NigiriType.SHRIMP), position_slider.getValue());
					} else if ((double) shrimp_spinner.getValue() > 0.0) {
						makeGreenPlateRequest(new Nigiri(Nigiri.NigiriType.TUNA), position_slider.getValue());
					} else if ((double) yellowtail_spinner.getValue() > 0.0) {
						makeGreenPlateRequest(new Nigiri(Nigiri.NigiriType.YELLOWTAIL), position_slider.getValue());
					}
					break;

				case "gold":

					if ((double) crab_spinner.getValue() > 0.0) {
						makeGoldPlateRequest(new Nigiri(Nigiri.NigiriType.CRAB), position_slider.getValue(), cost_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeGoldPlateRequest(new Nigiri(Nigiri.NigiriType.EEL), position_slider.getValue(), cost_slider.getValue());
					} else if ((double) eel_spinner.getValue() > 0.0) {
						makeGoldPlateRequest(new Nigiri(Nigiri.NigiriType.SHRIMP), position_slider.getValue(), cost_slider.getValue());
					} else if ((double) shrimp_spinner.getValue() > 0.0) {
						makeGoldPlateRequest(new Nigiri(Nigiri.NigiriType.TUNA), position_slider.getValue(), cost_slider.getValue());
					} else if ((double) yellowtail_spinner.getValue() > 0.0) {
						makeGoldPlateRequest(new Nigiri(Nigiri.NigiriType.YELLOWTAIL), position_slider.getValue(), cost_slider.getValue());
					}
					break;
				}
				break;

			case "roll":

				switch (color_buttons.getSelection().getActionCommand()) {
				case "red":
					if ((double) crab_spinner.getValue() > 0.0) {
						ingPortionArray.add(new CrabPortion((double)crab_spinner.getValue()));
//						System.out.println("red crab roll");
					}
					if ((double) eel_spinner.getValue() > 0.0) {
						ingPortionArray.add(new EelPortion((double)eel_spinner.getValue()));					
					}
					if ((double) shrimp_spinner.getValue() > 0.0) {
						ingPortionArray.add(new ShrimpPortion((double)shrimp_spinner.getValue()));					
					}
					if ((double) tuna_spinner.getValue() > 0.0) {
						ingPortionArray.add(new TunaPortion((double)tuna_spinner.getValue()));					
					}
					if ((double) yellowtail_spinner.getValue() > 0.0) {
						ingPortionArray.add(new YellowtailPortion((double)yellowtail_spinner.getValue()));					
					}

					IngredientPortion[] ingRedArray = ingPortionArray.toArray(new IngredientPortion[ingPortionArray.size()]);

					makeRedPlateRequest(new Roll("User Roll", ingRedArray), position_slider.getValue());

					break;

				case "blue":
					if ((double) crab_spinner.getValue() > 0.0) {
						ingPortionArray.add(new CrabPortion((double)crab_spinner.getValue()));
					}
					if ((double) eel_spinner.getValue() > 0.0) {
						ingPortionArray.add(new EelPortion((double)eel_spinner.getValue()));					
					}
					if ((double) shrimp_spinner.getValue() > 0.0) {
						ingPortionArray.add(new ShrimpPortion((double)shrimp_spinner.getValue()));					
					}
					if ((double) tuna_spinner.getValue() > 0.0) {
						ingPortionArray.add(new TunaPortion((double)tuna_spinner.getValue()));					
					}
					if ((double) yellowtail_spinner.getValue() > 0.0) {
						ingPortionArray.add(new YellowtailPortion((double)yellowtail_spinner.getValue()));					
					}

					IngredientPortion[] ingBlueArray = ingPortionArray.toArray(new IngredientPortion[ingPortionArray.size()]);

					makeBluePlateRequest(new Roll("User Roll", ingBlueArray), position_slider.getValue());
					break;

				case "green":
					if ((double) crab_spinner.getValue() > 0.0) {
						ingPortionArray.add(new CrabPortion((double)crab_spinner.getValue()));
					}
					if ((double) eel_spinner.getValue() > 0.0) {
						ingPortionArray.add(new EelPortion((double)eel_spinner.getValue()));					
					}
					if ((double) shrimp_spinner.getValue() > 0.0) {
						ingPortionArray.add(new ShrimpPortion((double)shrimp_spinner.getValue()));					
					}
					if ((double) tuna_spinner.getValue() > 0.0) {
						ingPortionArray.add(new TunaPortion((double)tuna_spinner.getValue()));					
					}
					if ((double) yellowtail_spinner.getValue() > 0.0) {
						ingPortionArray.add(new YellowtailPortion((double)yellowtail_spinner.getValue()));					
					}

					IngredientPortion[] ingGreenArray = ingPortionArray.toArray(new IngredientPortion[ingPortionArray.size()]);

					makeGreenPlateRequest(new Roll("User Roll", ingGreenArray), position_slider.getValue());
					break;

				case "gold":
					if ((double) crab_spinner.getValue() > 0.0) {
						ingPortionArray.add(new CrabPortion((double)crab_spinner.getValue()));
						
					}
					if ((double) eel_spinner.getValue() > 0.0) {
						ingPortionArray.add(new EelPortion((double)eel_spinner.getValue()));					
					}
					if ((double) shrimp_spinner.getValue() > 0.0) {
						ingPortionArray.add(new ShrimpPortion((double)shrimp_spinner.getValue()));					
					}
					if ((double) tuna_spinner.getValue() > 0.0) {
						ingPortionArray.add(new TunaPortion((double)tuna_spinner.getValue()));					
					}
					if ((double) yellowtail_spinner.getValue() > 0.0) {
						ingPortionArray.add(new YellowtailPortion((double)yellowtail_spinner.getValue()));					
					}

					IngredientPortion[] ingGoldArray = ingPortionArray.toArray(new IngredientPortion[ingPortionArray.size()]);
					makeGoldPlateRequest(new Roll("User Roll", ingGoldArray), position_slider.getValue(), cost_slider.getValue());
					
					break;
				}
				break;
			}
			break;
		}


	}


	@Override
	public void stateChanged(ChangeEvent e) {



	}
}
