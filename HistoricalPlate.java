package sushigame.model;

import comp401sushi.Plate;

public interface HistoricalPlate extends Plate {

	boolean wasSpoiled();
	Customer getConsumer();
}
