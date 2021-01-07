package sushigame.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel game_model;
	private JLabel display;
	private JComboBox<String> dropdown;
	private Comparator<Chef> comparator;
	private Sort sort;
	
	private enum Sort {BALANCE, CONSUMED, SPOILED};
	
	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		comparator = new HighToLowBalanceComparator();
		sort = Sort.BALANCE;
		
		String[] options = {"Sort by Balance", "Sort by Amount Consumed", "Sort by Amount Spoiled"};
		dropdown = new JComboBox<String>(options);
		dropdown.addActionListener(this);
		
		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.CENTER);
		setLayout(new BorderLayout());
		add(display, BorderLayout.CENTER);
		add(dropdown, BorderLayout.NORTH);
		display.setText(makeScoreboardHTML());
	}

	private String makeScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		Chef[] chefs = sort(comparator);
		
		switch (sort) {
		case BALANCE:
			for (Chef c : chefs) {
				sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
			}
			break;
		case CONSUMED:
			for (Chef c : chefs) {
				sb_html += c.getName() + " (" + Math.round(c.getConsumed()*100.0)/100.0 + " oz.) <br>";
			}
			break;
		case SPOILED:
			for (Chef c : chefs) {
				sb_html += c.getName() + " (" + Math.round(c.getSpoiled()*100.0)/100.0 + " oz.) <br>";
			}
			break;
		}

		return sb_html;
	}

	public void refresh() {
		display.setText(makeScoreboardHTML());		
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}
	
	private Chef[] sort(Comparator<Chef> comp) {
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i = 1; i < chefs.length; i++) {
			chefs[i] = opponent_chefs[i - 1];
		}
		
		Arrays.sort(chefs, comp);
		return chefs;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == dropdown) {
			String s = (String) dropdown.getSelectedItem();
			
			if (s.contains("Balance")) {
				comparator = new HighToLowBalanceComparator();
				sort = Sort.BALANCE;
			} else if (s.contains("Consumed")){
				comparator = new HighToLowConsumedComparator();
				sort = Sort.CONSUMED;
			} else if (s.contains("Spoiled")) {
				comparator = new HighToLowSpoiledComparator();
				sort = Sort.SPOILED;
			}
		}
		
		refresh();
	}

	
	
	// Find the bug: (EXAM STUFF, IGNORE) 
//	public static int count_common(int[] a, int[] b) {
//		int common = 0; 
//		for (int i = 0; i < a.length; i++) {
//			int aval = a[i];
//			for (int j = 0; j < b.length; j++) {
//				int bval = b[j];
//				if (bval == aval) {
//					common++;
//				}
//			}
//		}
//		return common;
//	}
	
	
}
