package be.kdg.outfitly.exceptions;

import be.kdg.outfitly.domain.Photo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE, reason = "The picture is too large")
public class ClothingPictureTooLargeException extends RuntimeException {
    private final Photo photo;

    public ClothingPictureTooLargeException(String message, Photo photo) {
        super(message);
        this.photo = photo;
    }

    public Photo getPhoto(){
        return this.photo;
    }
}
