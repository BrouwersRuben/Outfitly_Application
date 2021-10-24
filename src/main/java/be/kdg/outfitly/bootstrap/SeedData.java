package be.kdg.outfitly.bootstrap;

import be.kdg.outfitly.domain.ClothingItem;
import be.kdg.outfitly.domain.User;
import be.kdg.outfitly.repository.EntityRepository;
import be.kdg.outfitly.repository.ListRepository;
import be.kdg.outfitly.repository.MainUserListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

public class SeedData implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(SeedData.class);
    private EntityRepository entityRepository;
    private ListRepository listRepository;
    private MainUserListRepository mainUserListRepository;
    private ClothingItem clothingItem;

    @Override
    public void run(String... args) throws Exception {
        //User user1 = new User("testUser1@gmail.com","test123","John", List.of(new ClothingItem("Jacket", clothingItem.setMaterial(ClothingItem.Material.LEATHER))));
    }
}
