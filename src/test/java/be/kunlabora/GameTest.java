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
    void placeShipHorizontally() {
        //given
        Game aGame = new Game();

        //when
//        aGame.placeShip(1, 5);

        //then
        Assertions.assertThat(aGame.placeShip(ShipType.CARRIER, 1, 1, Orientation.HORIZONTAL))
                .isEqualTo("Ship successfully placed");
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
        Assertions.assertThat(aGame.placeShip(ShipType.CARRIER,1, 4, Orientation.HORIZONTAL))
                .isEqualTo("Ship successfully placed");
        Assertions.assertThat(aGame.placeShip(ShipType.DESTROYER,4, 5, Orientation.HORIZONTAL))
                .isEqualTo("Ship successfully placed");
        Assertions.assertThat(aGame.placeShip(ShipType.PATROLBOAT,6, 9, Orientation.HORIZONTAL))
                .isEqualTo("Ship successfully placed");
        Assertions.assertThat(aGame.placeShip(ShipType.SUBMARINE,7, 1, Orientation.HORIZONTAL))
                .isEqualTo("Ship successfully placed");
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
        aGame.placeShip(ShipType.CARRIER,4, 5, Orientation.HORIZONTAL);
//        aGame.placeShip(2, 4);

        //then
        Assertions.assertThat(aGame.placeShip(ShipType.CARRIER, 4,1, Orientation.HORIZONTAL))
                .isEqualTo("Ship cannot overlap with an already placed ship! Try again.");
    }

    @Test
    void placeShipVertically() {
        //given
        Game aGame = new Game();

        //when
//        aGame.placeShipVertically(1, 3);

        //then
        Assertions.assertThat(aGame.placeShip(ShipType.DESTROYER, 1, 1, Orientation.VERTICAL))
                .isEqualTo("Ship successfully placed");
        Assertions.assertThat(aGame.render()).isEqualTo("""
                🚢🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🚢🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🚢🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
                """);
    }

}
