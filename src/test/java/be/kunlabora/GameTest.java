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
        String placeShipResult = aGame.placeShip(ShipType.CARRIER, 1, 1, Orientation.HORIZONTAL);

        //then
        Assertions.assertThat(placeShipResult).isEqualTo("Ship successfully placed");
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
        String placeShipResult1 = aGame.placeShip(ShipType.CARRIER,1, 4, Orientation.HORIZONTAL);
        String placeShipResult2 = aGame.placeShip(ShipType.DESTROYER,4, 5, Orientation.HORIZONTAL);
        String placeShipResult3 = aGame.placeShip(ShipType.PATROLBOAT,6, 9, Orientation.HORIZONTAL);
        String placeShipResult4 = aGame.placeShip(ShipType.SUBMARINE,7, 1, Orientation.HORIZONTAL);

        //then
        Assertions.assertThat(placeShipResult1).isEqualTo("Ship successfully placed");
        Assertions.assertThat(placeShipResult2).isEqualTo("Ship successfully placed");
        Assertions.assertThat(placeShipResult3).isEqualTo("Ship successfully placed");
        Assertions.assertThat(placeShipResult4).isEqualTo("Ship successfully placed");
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
        aGame.placeShip(ShipType.BATTLESHIP,4, 5, Orientation.HORIZONTAL);

        //then
        Assertions.assertThat(aGame.placeShip(ShipType.CARRIER, 4,1, Orientation.HORIZONTAL))
                .isEqualTo("Ship cannot overlap with an already placed ship! Try again.");
    }

    @Test
    void placeShipVertically() {
        //given
        Game aGame = new Game();

        //when
        String placeShipResult = aGame.placeShip(ShipType.DESTROYER, 1, 1, Orientation.VERTICAL);

        //then
        Assertions.assertThat(placeShipResult).isEqualTo("Ship successfully placed");
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

    @Test
    void placeShipCannotExceedOceanLimits() {
        //given
        Game aGame = new Game();

        //when
        String placeShipResult1 = aGame.placeShip(ShipType.BATTLESHIP, 2,8, Orientation.HORIZONTAL);
        String placeShipResult2 = aGame.placeShip(ShipType.BATTLESHIP, 8,2, Orientation.VERTICAL);

        //then
        Assertions.assertThat(placeShipResult1).isEqualTo("Ship is too long and exceeds row limits");
        Assertions.assertThat(placeShipResult2).isEqualTo("Ship is too long and exceeds column limits");
    }

    @Test
    void placingASecondShipOfTheSameTypeReturnsAWarningMessage() {
        //given
        Game aGame = new Game();

        //when
        aGame.placeShip(ShipType.SUBMARINE, 1, 1, Orientation.HORIZONTAL);
        String placeShipResult = aGame.placeShip(ShipType.SUBMARINE, 3, 3, Orientation.VERTICAL);

        //then
        Assertions.assertThat(placeShipResult)
                .isEqualTo("You already placed a ship of this type. Choose another type.");
    }
}
