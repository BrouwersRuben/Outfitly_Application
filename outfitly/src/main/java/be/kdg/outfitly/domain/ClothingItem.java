package be.kdg.outfitly.domain;

import java.io.File;

public class ClothingItem extends Entity {
    private enum Material{
        COTTON, WOOL, SILK, SYNTHETIC, LEATHER
    }

    private enum Type{
        JACKET_LIKE, SHIRT_LIKE, T_SHIRT_LIKE, TROUSERS_LIKE, SHOES
    }
    private enum RainProofness{
        BAD, NORMAL, GOOD
    }
    private enum Occasion{
        CASUAL, UNIVERSAL, ELEGANT
    }

    private enum Weather{
        COLD, MILD, WARM, UNIVERSAL
    }

    private String name;
    private Material material;
    private RainProofness rainProofness;
    private Occasion occasion;
    private Weather weather;
//    ??
    private File photo;






}
