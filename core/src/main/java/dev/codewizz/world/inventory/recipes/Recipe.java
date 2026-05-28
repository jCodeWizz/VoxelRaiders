package dev.codewizz.world.inventory.recipes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import dev.codewizz.utils.Logger;
import dev.codewizz.world.inventory.Item;
import dev.codewizz.world.inventory.types.ItemType;
import java.util.HashMap;

public class Recipe {

    public static final HashMap<String, Recipe> CHOPPING_REGISTRY = new HashMap<>();

    private String type;
    private float duration;

    private Array<Item> input;
    private Array<Item> output;

    public Recipe(String type, float duration, Array<Item> input, Array<Item> output) {
        this.type = type;
        this.duration = duration;
        this.input = input;
        this.output = output;
    }

    public static void register(String id, Recipe recipe) {
        if (recipe.getType().equalsIgnoreCase("chopping") && recipe != null) {
            CHOPPING_REGISTRY.put(id, recipe);
        }
    }

    public static Recipe fromJson(String file) {
        JsonValue json = new JsonReader().parse(Gdx.files.internal(file));
        String type = json.getString("type");
        float duration = json.getFloat("duration");

        JsonValue inputJson = json.get("input");
        JsonValue outputJson = json.get("output");

        Array<Item> input = new Array<>();
        Array<Item> output = new Array<>();

        for (JsonValue item : inputJson) {
            ItemType itemType = ItemType.find(item.getString("id"));

            if (itemType == null) {
                Logger.error("No ItemType found for " + item.getString("id") + " in input");
                return null;
            }

            input.add(new Item(itemType, item.getInt("size")));
        }

        for (JsonValue item : outputJson) {
            ItemType itemType = ItemType.find(item.getString("id"));

            if (itemType == null) {
                Logger.error("No ItemType found for " + item.getString("id") + " in output");
                return null;
            }

            output.add(new Item(itemType, item.getInt("size")));
        }

        return new Recipe(type, duration, input, output);
    }

    public Array<Item> getOutput() {
        return new Array<>(output);
    }

    public void setOutput(Array<Item> output) {
        this.output = output;
    }

    public Array<Item> getInput() {
        return new Array<>(input);
    }

    public void setInput(Array<Item> input) {
        this.input = input;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
