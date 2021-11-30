package be.kdg.outfitly.domain;

import javax.persistence.*;
import java.io.File;

@javax.persistence.Entity
@Table(name = "clothes")
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "clothing_name", nullable = false, length = 50)
    private String name;

    @Column(name = "clothing_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "clothing_material", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private Material material;

    @Column(name = "rainproofness", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private RainProofness rainProofness;

    @Column(name = "clothing_occasion", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private Occasion occasion;

    @Column(name = "clothing_weather_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private Weather weather;

    @Column(name = "image_url", length = 200)
    private String photoUrl;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    protected ClothingItem() {
    }

    // TODO: Why are there 2 constructors here?
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

    public String getPhoto() {
        return photoUrl;
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
                ", photo=" + photoUrl +
                '}';
    }
}
