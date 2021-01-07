package comp401sushi;

public interface IngredientPortion {

	Ingredient getIngredient();
	double getAmount();
	IngredientPortion combine(IngredientPortion other);
	
	default String getName() {
		return getIngredient().getName();
	}
	
	default double getCalories() {
		return getAmount()*getIngredient().getCaloriesPerOunce();
	}
	
	default double getCost() {
		return getAmount()*getIngredient().getPricePerOunce();
	}
	
	default boolean getIsVegetarian() {
		return getIngredient().getIsVegetarian();
	}
	
	default boolean getIsRice() {
		return getIngredient().getIsRice();
	}
	
	default boolean getIsShellfish() {
		return getIngredient().getIsShellfish();
	}
}
