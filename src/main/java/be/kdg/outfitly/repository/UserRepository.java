package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
