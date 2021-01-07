package comp401sushi;

import sushigame.model.Chef;

abstract public class PlateImpl implements Plate {

	private Sushi _contents;
	private double _price;
	private Plate.Color _color;
	private Chef _chef;
		
	public PlateImpl(Chef chef, Sushi s, double price, Plate.Color color) throws PlatePriceException {
		if (s == null) {
			throw new IllegalArgumentException();
		}

		if (chef == null) {
			throw new IllegalArgumentException();
		}
		
		if (s.getCost() > price) {
			throw new PlatePriceException(this, s);
		}
		
		_price = price;
		_color = color;
		_chef = chef;
		_contents = s;
	}

	@Override
	public Sushi getContents() {
		return _contents;
	}

	@Override
	public double getPrice() {
		return _price;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public double getProfit() {
		return getPrice() - _contents.getCost();
	}
	
	@Override
	public Chef getChef() {
		return _chef;
	}
}
