package dev.codewizz.world.inventory.types;

public class FuelType extends ItemType {

    private final float fuelDuration;

    public FuelType(String id, String name, float fuelDuration) {
        super(id, name);

        this.fuelDuration = fuelDuration;
    }

    public float getNutritionValue() {
        return fuelDuration;
    }
}
