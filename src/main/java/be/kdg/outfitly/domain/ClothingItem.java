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

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public RainProofness getRainProofness() {
        return rainProofness;
    }

    public Occasion getOccasion() {
        return occasion;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setRainProofness(RainProofness rainProofness) {
        this.rainProofness = rainProofness;
    }

    public void setOccasion(Occasion occasion) {
        this.occasion = occasion;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
