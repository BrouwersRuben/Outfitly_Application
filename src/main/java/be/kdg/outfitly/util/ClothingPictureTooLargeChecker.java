package be.kdg.outfitly.util;

import be.kdg.outfitly.domain.Photo;
import be.kdg.outfitly.exceptions.ClothingPictureTooLargeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClothingPictureTooLargeChecker {
    //Checks if the clothing picture uploaded by the user is not too large, if so, it will throw an exception
    public static void checkPictureSize(Photo photo){
        if(photo == null) return;
        if (photo.getBytes().length > Photo.MAX_NUMBER_BYTES) throw new ClothingPictureTooLargeException("The picture is too large", photo);
    }
}
