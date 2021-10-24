package be.kdg.outfitly.domain;

import java.io.File;

public class ClothingItem extends Entity {
    public enum Material{
        COTTON, WOOL, SILK, SYNTHETIC, LEATHER, DENIM
    }

    public enum Type{
        JACKET_LIKE, SHIRT_LIKE, T_SHIRT_LIKE, TROUSERS_LIKE, SHOES
    }
    public enum RainProofness{
        BAD, NORMAL, GOOD
    }

    public enum Occasion{
        CASUAL, UNIVERSAL, ELEGANT
    }

    public enum Weather{
        COLD, MILD, WARM, UNIVERSAL
    }

    private String name;
    private Material material;
    private RainProofness rainProofness;
    private Occasion occasion;
    private Weather weather;
//    ??
    private File photo;

    public ClothingItem() {
    }

    //no photo yet
    public ClothingItem(String name, Material material, RainProofness rainProofness, Occasion occasion, Weather weather) {
        this.name = name;
        this.material = material;
        this.rainProofness = rainProofness;
        this.occasion = occasion;
        this.weather = weather;
    }
}
