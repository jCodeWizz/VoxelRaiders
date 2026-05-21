package dev.codewizz.world.inventory.types;

public class FoodType extends ItemType {

    private final int nutritionValue;

    public FoodType(String id, String name, int nutritionValue) {
        super(id, name);

        this.nutritionValue = nutritionValue;
    }

    public int getNutritionValue() {
        return nutritionValue;
    }
}
