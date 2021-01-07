package sushigame.model;


import comp401sushi.Plate;

public interface TimedPlate extends Plate {
	int getInceptDate();
	Plate getOriginal();
}
