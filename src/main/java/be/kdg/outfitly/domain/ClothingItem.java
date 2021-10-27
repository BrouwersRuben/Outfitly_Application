package be.kdg.outfitly.domain;

import java.io.File;

public class ClothingItem extends Entity {

    public enum Material{
        COTTON("Cotton"), WOOL("Wool"), SILK("Silk"), SYNTHETIC("Synthetic"), LEATHER("Leather"), DENIM("Denim"), OTHER("Other");

        private final String name;

        Material(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum Type{
        JACKET_LIKE("Jackets"), SWEATSHIRT_LIKE("Sweatshirts"), SHIRT_LIKE("Shirts"), T_SHIRT_LIKE("T-Shirts"), TROUSERS_LIKE("Trousers"), SHOES("Shoes");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum RainProofness{
        BAD("Bad"), NORMAL("Normal"), GOOD("Good");

        private final String name;

        RainProofness(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum Occasion{
        CASUAL("Casual"), UNIVERSAL("Universal"), ELEGANT("Elegant");

        private final String name;

        Occasion(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum Weather{
        COLD("Cold"), MILD("Mild"), WARM("Warm"), UNIVERSAL("Universal");

        private final String name;

        Weather(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private String name;
    private Type type;
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

    public ClothingItem(String name, Material material, RainProofness rainProofness, Occasion occasion, Weather weather, Type type) {
        this.name = name;
        this.type = type;
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

    public File getPhoto() {
        return photo;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ClothingItem{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", material=" + material +
                ", rainProofness=" + rainProofness +
                ", occasion=" + occasion +
                ", weather=" + weather +
                ", photo=" + photo +
                '}';
    }
}
