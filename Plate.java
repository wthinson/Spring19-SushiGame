package comp401sushi;

import sushigame.model.Chef;

public interface Plate { 
	public enum Color {RED, GREEN, BLUE, GOLD};

	Sushi getContents(); 
	double getPrice(); 
	Plate.Color getColor(); 

	default double getProfit() {
		return getPrice() - getContents().getCost();
	}

	Chef getChef();     
}

