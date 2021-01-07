package comp401sushi;

public class YellowtailPortion extends IngredientPortionImpl {

	private static Ingredient YELLOWTAIL = new Yellowtail();
	
	public YellowtailPortion(double amount) {
		super(amount, YELLOWTAIL);
	}

	@Override
	public IngredientPortion combine(IngredientPortion other) {
		if (other == null)  {
			return this;
		}
		
		if (!getIngredient().equals(other.getIngredient())) {
			throw new RuntimeException("Cannot combine ingredient portions of different ingredient types");
		}
		
		return new YellowtailPortion(getAmount()+other.getAmount());
	}
}
