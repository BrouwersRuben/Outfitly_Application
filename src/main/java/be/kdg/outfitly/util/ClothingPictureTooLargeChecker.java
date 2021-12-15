package be.kdg.outfitly.util;

import be.kdg.outfitly.exceptions.ClothingPictureTooLargeException;

public class ClothingPictureTooLargeChecker {
    public static void checkPictureSize(byte[] photo, String photoMIMEType, int maxSize){
        if (photo.length >= maxSize){ throw new ClothingPictureTooLargeException("The picture is too large", photo, photoMIMEType);
        }
    }
}
