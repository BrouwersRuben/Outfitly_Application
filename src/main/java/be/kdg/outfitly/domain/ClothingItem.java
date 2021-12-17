package be.kdg.outfitly.domain;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "clothes")
public class ClothingItem extends Entity {

    public enum Material {
        COTTON("Cotton"), WOOL("Wool"), SILK("Silk"), SYNTHETIC("Synthetic"), LEATHER("Leather"), DENIM("Denim"), OTHER("Other");

        private final String name;

        Material(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum Type {
        JACKET_LIKE("Jackets"), SWEATSHIRT_LIKE("Sweatshirts"), SHIRT_LIKE("Shirts"), T_SHIRT_LIKE("T-Shirts"), TROUSERS_LIKE("Trousers"), SHOES("Shoes");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum RainProofness {
        BAD("Bad"), NORMAL("Normal"), GOOD("Good");

        private final String name;

        RainProofness(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum Occasion {
        CASUAL("Casual"), UNIVERSAL("Universal"), ELEGANT("Elegant");

        private final String name;

        Occasion(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum Weather {
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

//    @Column(name = "image_url", length = 200)
//    private String photoUrl;

    @Column(name = "photo", length = 10_000)
    private byte[] photo;

    @Column(name = "photo_type")
    private String photoMIMEType;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, /*CascadeType.PERSIST,*/ CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    protected ClothingItem() {
    }

    // TODO: Why are there 2 constructors here?

    public ClothingItem(String name, Material material, RainProofness rainProofness, Occasion occasion, Weather weather, Type type) {
        this.name = name;
        this.type = type;
        this.material = material;
        this.rainProofness = rainProofness;
        this.occasion = occasion;
        this.weather = weather;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public RainProofness getRainProofness() {
        return rainProofness;
    }

    public void setRainProofness(RainProofness rainProofness) {
        this.rainProofness = rainProofness;
    }

    public Occasion getOccasion() {
        return occasion;
    }

    public void setOccasion(Occasion occasion) {
        this.occasion = occasion;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Photo getPhoto(){
        return this.photo;
    }

    public void setPhoto(Photo photo){
        this.photo=photo;
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
//                ", photo=" + photoUrl +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClothingItem that = (ClothingItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
