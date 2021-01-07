package sushigame.model;

import comp401sushi.Plate;
import comp401sushi.Sushi;
import comp401sushi.Plate.Color;

public interface Chef {

	String getName();
	void setName(String name);
	
	void makeAndPlacePlate(Plate plate, int position) 
			throws InsufficientBalanceException, BeltFullException, AlreadyPlacedThisRotationException;
		
	HistoricalPlate[] getPlateHistory(int max_history_length);
	HistoricalPlate[] getPlateHistory();
	
	double getBalance();
	double getConsumed();
	double getSpoiled();
	
	boolean alreadyPlacedThisRotation();

}

