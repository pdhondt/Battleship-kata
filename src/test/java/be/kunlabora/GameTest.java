package be.kunlabora;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    void renderAnEmptyOcean() {
        //given
        Game aGame = new Game();

        //when
        String playField = aGame.render();

        //then
        Assertions.assertThat(playField).isEqualTo("""
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                """);
    }

    @Test
    void placeShipsHorizontally() {
        //given
        Game aGame = new Game();

        //when
        aGame.placeShip(1, 5);

        //then
        Assertions.assertThat(aGame.render()).isEqualTo("""
                🚢🚢🚢🚢🚢🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                """);

    }

    @Test
    void placeThreeShipsHorizontally() {
        //given
        Game aGame = new Game();

        //when
        aGame.placeShip(4, 8);
        aGame.placeShip(35, 37);
        aGame.placeShip(59, 63);

        //then
        Assertions.assertThat(aGame.render()).isEqualTo("""
                🌊🌊🌊🚢🚢🚢🚢🚢🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🚢🚢🚢🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🚢🚢
                🚢🚢🚢🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                """);
    }
}
