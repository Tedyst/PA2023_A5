package ro.tedyst.lab11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tedyst.lab11.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByName(String name);
}
