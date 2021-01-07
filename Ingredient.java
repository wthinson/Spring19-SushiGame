package comp401sushi;

public interface Ingredient {

	String getName();
	double getCaloriesPerDollar();
	int getCaloriesPerOunce();
	double getPricePerOunce();
	boolean getIsVegetarian();
	boolean getIsRice();
	boolean getIsShellfish();

	default boolean equals(Ingredient other) {
		return ((getName().equals(other.getName())) &&
				(Math.abs(getPricePerOunce()-other.getPricePerOunce()) < 0.01) &&
				(getCaloriesPerOunce() == other.getCaloriesPerOunce()) &&
				(getIsVegetarian() == other.getIsVegetarian()) &&
				(getIsRice() == other.getIsRice()) &&
				(getIsShellfish() == other.getIsShellfish()));
	}
}
