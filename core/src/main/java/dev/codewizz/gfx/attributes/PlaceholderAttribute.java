package dev.codewizz.gfx.attributes;

import com.badlogic.gdx.graphics.g3d.Attribute;

public class PlaceholderAttribute extends Attribute {

    public static final String Alias = "placeholder";
    public static final long Type = register(Alias);

    public PlaceholderAttribute() {
        super(Type);
    }

    @Override
    public Attribute copy() {
        return new PlaceholderAttribute();
    }

    @Override
    protected boolean equals(Attribute other) {
        return other instanceof PlaceholderAttribute;
    }

    @Override
    public int compareTo(Attribute o) {
        return Math.toIntExact(o.type - type);
    }
}
