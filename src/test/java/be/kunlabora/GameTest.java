package be.kunlabora;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    void renderAnEmptyOcean() {
        //given
        Game aGame = new Game();

        //when
        String playField = aGame.render(1, true);

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
        aGame.placeShip(ShipType.CARRIER, 1, 1, Orientation.HORIZONTAL, 1);

        //then
        Assertions.assertThat(aGame.render(1, true)).isEqualTo("""
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
    void placeFourShipsHorizontally() {
        //given
        Game aGame = new Game();

        //when
        aGame.placeShip(ShipType.CARRIER, 1, 4, Orientation.HORIZONTAL, 1);
        aGame.placeShip(ShipType.DESTROYER, 4, 5, Orientation.HORIZONTAL, 1);
        aGame.placeShip(ShipType.PATROLBOAT, 6, 9, Orientation.HORIZONTAL, 1);
        aGame.placeShip(ShipType.SUBMARINE, 7, 1, Orientation.HORIZONTAL, 1);

        //then
        Assertions.assertThat(aGame.render(1, true)).isEqualTo("""
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
        aGame.placeShip(ShipType.BATTLESHIP, 4, 5, Orientation.HORIZONTAL, 1);

        //then
        Assertions.assertThat(
                aGame.placeShip(ShipType.CARRIER, 4, 1, Orientation.HORIZONTAL, 1)
        ).isEqualTo("Warning: Ship cannot overlap with an already placed ship! Try again.");
    }

    @Test
    void placeShipVertically() {
        //given
        Game aGame = new Game();

        //when
        aGame.placeShip(ShipType.DESTROYER, 1, 1, Orientation.VERTICAL, 1);

        //then
        Assertions.assertThat(aGame.render(1, true)).isEqualTo("""
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
    void placeShipCannotExceedOceanLimitsHorizontally() {
        //given
        Game aGame = new Game();

        //when
        String placeShipResult = aGame.placeShip(ShipType.BATTLESHIP, 2, 8, Orientation.HORIZONTAL, 1);

        //then
        Assertions.assertThat(placeShipResult).isEqualTo("Warning: Ship is too long and exceeds ocean limits");
//        Assertions.assertThatThrownBy(() -> {
//                    aGame.placeShip(ShipType.BATTLESHIP, 2, 8, Orientation.HORIZONTAL, 1);
//                })
//                .isInstanceOf(OceanLimitsException.class)
//                .hasMessage("Ship is too long and exceeds ocean limits");
    }

    @Test
    void placeShipCannotExceedOceanLimitsVertically() {
        //given
        Game aGame = new Game();

        //when
        String placeShipResult = aGame.placeShip(ShipType.BATTLESHIP, 8, 2, Orientation.VERTICAL, 1);

        //then
        Assertions.assertThat(placeShipResult).isEqualTo("Warning: Ship is too long and exceeds ocean limits");
    }

    @Test
    void placingASecondShipOfTheSameTypeReturnsAWarningMessage() {
        //given
        Game aGame = new Game();

        //when
        aGame.placeShip(ShipType.SUBMARINE, 1, 1, Orientation.HORIZONTAL, 1);

        //then
        Assertions.assertThat(
                aGame.placeShip(ShipType.SUBMARINE, 3, 3, Orientation.VERTICAL, 1)
        ).isEqualTo("Warning: You already placed a ship of this type. Choose another type.");
    }

    @Test
    void firingAndMissing() {
        //given
        Game aGame = new Game();

        //when
        aGame.placeShip(ShipType.DESTROYER, 1, 1, Orientation.HORIZONTAL, 1);
        String fireResult = aGame.fire(1, 4, 2);
        String fireResult2 = aGame.fire(1, 6, 2);

        //then
        Assertions.assertThat(fireResult).isEqualTo("Miss! Number of misses: 1");
        Assertions.assertThat(fireResult2).isEqualTo("Miss! Number of misses: 2");
    }

    @Test
    void firingAndHitting() {
        //given
        Game aGame = new Game();

        //when
        aGame.placeShip(ShipType.DESTROYER, 1, 1, Orientation.HORIZONTAL, 1);
        String fireResult = aGame.fire(1, 2, 2);

        //then
        Assertions.assertThat(fireResult).isEqualTo("Hit! Number of hits: 1");
        Assertions.assertThat(aGame.render(2, false)).isEqualTo("""
                🌊💥🌊🌊🌊🌊🌊🌊🌊🌊
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
    void firingAndSinkingAShip() {
        //given
        Game aGame = new Game();

        //when
        aGame.placeShip(ShipType.PATROLBOAT, 1, 1, Orientation.HORIZONTAL, 1);
        aGame.fire(1, 1, 2);
        String fireResult = aGame.fire(1, 2, 2);

        //then
        Assertions.assertThat(fireResult).isEqualTo("Ship Destroyed and Sinking!");
        Assertions.assertThat(aGame.render(2, false)).isEqualTo("""
                🏊🏊🌊🌊🌊🌊🌊🌊🌊🌊
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
    void allShipsPlaced() {
        //given
        Game aGame = new Game();

        //when
        aGame.placeShip(ShipType.CARRIER, 1, 1, Orientation.HORIZONTAL, 1);
        aGame.placeShip(ShipType.BATTLESHIP, 2, 1, Orientation.HORIZONTAL, 1);
        aGame.placeShip(ShipType.DESTROYER, 3, 1, Orientation.HORIZONTAL, 1);
        aGame.placeShip(ShipType.SUBMARINE, 4, 1, Orientation.HORIZONTAL, 1);
        aGame.placeShip(ShipType.PATROLBOAT, 5, 1, Orientation.HORIZONTAL, 1);

        //then
        Assertions.assertThat(
                aGame.placeShip(ShipType.SUBMARINE, 6, 1, Orientation.HORIZONTAL, 1)
        ).isEqualTo("Warning: All 5 shiptypes have been placed!");
    }

    @Test
    void checkWinner() {
        //given
        Game aGame = new Game();
        aGame.placeShip(ShipType.CARRIER, 1, 1, Orientation.HORIZONTAL, 1);
        aGame.placeShip(ShipType.BATTLESHIP, 2, 1, Orientation.HORIZONTAL, 1);
        aGame.placeShip(ShipType.DESTROYER, 3, 1, Orientation.HORIZONTAL, 1);
        aGame.placeShip(ShipType.SUBMARINE, 4, 1, Orientation.HORIZONTAL, 1);
        aGame.placeShip(ShipType.PATROLBOAT, 5, 1, Orientation.HORIZONTAL, 1);

        //when
        aGame.fire(1, 1, 2);
        aGame.fire(1, 2, 2);
        aGame.fire(1, 3, 2);
        aGame.fire(1, 4, 2);
        aGame.fire(1, 5, 2);
        aGame.fire(2, 1, 2);
        aGame.fire(2, 2, 2);
        aGame.fire(2, 3, 2);
        aGame.fire(2, 4, 2);
        aGame.fire(3, 1, 2);
        aGame.fire(3, 2, 2);
        aGame.fire(3, 3, 2);
        aGame.fire(4, 1, 2);
        aGame.fire(4, 2, 2);
        aGame.fire(4, 3, 2);
        aGame.fire(5, 1, 2);
        String fireResult = aGame.fire(5, 2, 2);

        //then
        Assertions.assertThat(fireResult).isEqualTo("All ships destroyed, player 2 wins, by 17 hits out of 17 attempts!");
    }
}
