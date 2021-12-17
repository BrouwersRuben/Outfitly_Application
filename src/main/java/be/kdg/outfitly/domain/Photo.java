package be.kdg.outfitly.domain;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Base64;

@Entity
@Table(name = "photos")
public class Photo {

    public static final int MAX_NUMBER_BYTES = 100_000_000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "photo", length = MAX_NUMBER_BYTES)
    private byte[] bytes;

    @Column(name = "photo_type")
    private String photoMIMEType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clothing_item_id")
    private ClothingItem clothingItem;

    protected Photo() {
    }

    public Photo(byte[] bytes, String photoMIMEType){
        this.bytes = bytes;
        this.photoMIMEType = photoMIMEType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getPhotoMIMEType() {
        return photoMIMEType;
    }

    public void setPhotoMIMEType(String photoMIMEType) {
        this.photoMIMEType = photoMIMEType;
    }

    public String getPhotoEncoded() {
        return bytes == null ? "" : "data:" + this.photoMIMEType + ";base64," + Base64.getEncoder().encodeToString(bytes);
    }

}
