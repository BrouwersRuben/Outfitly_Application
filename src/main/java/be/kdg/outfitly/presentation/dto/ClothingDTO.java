package be.kdg.outfitly.presentation.dto;

public class ClothingDTO {
    int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "ClothingDTO{" + "ID=" + ID + '}';
    }
}
