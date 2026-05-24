package dev.codewizz.world.inventory.recipes;

import com.badlogic.gdx.utils.Array;

public class RecipeData {

    public String type;
    public float duration;

    public Array<IngredientData> input;
    public Array<IngredientData> output;
}
