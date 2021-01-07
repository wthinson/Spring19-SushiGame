package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class HighToLowConsumedComparator implements Comparator<Chef> {

	@Override
	public int compare(Chef a, Chef b) {
		// TODO Auto-generated method stub
		return (int) (Math.round(b.getConsumed()*100.0) - 
				Math.round(a.getConsumed()*100));
	}


	
	
}
