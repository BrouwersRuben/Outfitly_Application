package be.kdg.outfitly.util;

import be.kdg.outfitly.exceptions.ClothingPictureTooLargeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClothingPictureTooLargeChecker {
    private static Logger logger = LoggerFactory.getLogger(ClothingPictureTooLargeChecker.class);
    public static void checkPictureSize(byte[] photo, String photoMIMEType, int maxSize){
        logger.debug("Going through here!!");
        if (photo.length > maxSize) throw new ClothingPictureTooLargeException("The picture is too large", photo, photoMIMEType);
    }
}
