package ro.tedyst.lab11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.tedyst.lab11.exceptions.InvalidGameException;
import ro.tedyst.lab11.models.Game;
import ro.tedyst.lab11.models.Player;
import ro.tedyst.lab11.repositories.GameRepository;
import ro.tedyst.lab11.repositories.PlayerRepository;

import java.util.List;

@RestController
public class API {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/games")
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/games/{id}")
    public Game getGame(@PathVariable Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @PostMapping("/games")
    public Game createGame() {
        Game game = new Game();
        gameRepository.save(game);
        return game;
    }

    @GetMapping("/games/{id}/start")
    public Game startGame(@PathVariable Long id) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game == null) {
            return null;
        }
        game.start();
        gameRepository.save(game);
        return game;
    }

    @PostMapping("/player/{playerName}/join/{id}")
    public Game joinGame(@PathVariable String playerName, @PathVariable Long id) throws InvalidGameException {
        Game game = gameRepository.findById(id).orElse(null);
        if (game == null) {
            return null;
        }
        Player player = playerRepository.findByName(playerName);
        if (player == null) {
            player = new Player(playerName);
            playerRepository.save(player);
        }
        GameManager.joinGame(player, game);
        gameRepository.save(game);
        return game;
    }

    @PutMapping("/player/{playerName}")
    public Player changeName(@PathVariable String playerName, @RequestBody String newName) {
        Player player = playerRepository.findByName(playerName);
        if (player == null) {
            return null;
        }
        player.setName(newName);
        playerRepository.save(player);
        return player;
    }

    @DeleteMapping
    public void deletePlayer(@PathVariable String playerName) {
        Player player = playerRepository.findByName(playerName);
        if (player == null) {
            return;
        }
        playerRepository.delete(player);
    }

}
