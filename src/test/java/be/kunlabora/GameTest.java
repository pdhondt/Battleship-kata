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
//        aGame.placeShip(1, 5);

        //then
        Assertions.assertThat(aGame.placeShip(1, 5)).isEqualTo("Ship successfully placed");
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
//        aGame.placeShip(4, 8);
//        aGame.placeShip(35, 37);
//        aGame.placeShip(59, 63);

        //then
        Assertions.assertThat(aGame.placeShip(4, 8)).isEqualTo("Ship successfully placed");
        Assertions.assertThat(aGame.placeShip(35, 37)).isEqualTo("Ship successfully placed");
        Assertions.assertThat(aGame.placeShip(59, 63)).isEqualTo("Ship successfully placed");
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

    @Test
    void aShipCannotOverlapAnotherShip() {
        //given
        Game aGame = new Game();

        //when
        aGame.placeShip(4, 8);
//        aGame.placeShip(2, 4);

        //then
        Assertions.assertThat(aGame.placeShip(2,4))
                .isEqualTo("Ship cannot overlap with an already placed ship! Try again.");
    }
}
