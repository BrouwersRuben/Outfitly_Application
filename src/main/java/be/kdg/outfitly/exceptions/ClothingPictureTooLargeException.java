package be.kdg.outfitly.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Base64;

@ResponseStatus(value= HttpStatus.INSUFFICIENT_STORAGE, reason="The picture is too large" )
public class ClothingPictureTooLargeException extends RuntimeException{
    private byte[] photo;
    private String photoMIMEType;

    public ClothingPictureTooLargeException(String message, byte[] photo, String photoMIMEType) {
        super(message);
        this.photo = photo;
        this.photoMIMEType = photoMIMEType;
    }

    public String getPhotoEncoded() {
        return photo == null ? "" : "data:" + this.photoMIMEType + ";base64," + Base64.getEncoder().encodeToString(photo);
    }
}
