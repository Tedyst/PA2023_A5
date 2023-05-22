package ro.tedyst.lab11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tedyst.lab11.models.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
