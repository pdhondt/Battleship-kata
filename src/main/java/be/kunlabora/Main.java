package be.kunlabora;

import java.util.Scanner;

public class Main {

    //TODO 1: make the game playable for 2 players
    //TODO 2: make the same player fire a second time if the first attempt is a hit
    //TODO 3: improve handling of incorrect input (e.g. coordinates not within ocean limits)
    //TODO 4: avoid program crashes when a coordinate input is not a number
    public static void main(String[] args) {
        Game battleShip = new Game();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Rendering the playfield:");
        System.out.println(battleShip.render(1, true));
        battleShip.askPlayerName(1);
        System.out.println("""
                Welcome to BattleShip.  You can place 5 ships on the playfield, which is
                a 10x10 ocean.  Each ship can only be placed once.  The different ships are, together
                with their length:
                * CARRIER(5)
                * BATTLESHIP(4)
                * DESTROYER(3)
                * SUBMARINE(3)
                * PATROLBOAT(2)
                You place a ship by passing the first letter of the ship type, the starting coordinate (e.g. 3, 4), which
                indicates the row and the column where you want to place the ship, and the
                orientation (H for HORIZONTAL or V for VERTICAL)
                """);
        while (battleShip.getCurrentPlayer(1).getFleet().getFleetSize() < 5) {
            battleShip.askUserInputAndPlaceShips();
        }
        System.out.println("All ships successfully placed!");

        battleShip.askPlayerName(2);
        System.out.println(battleShip.getCurrentPlayer(2).getName() + ", start firing your missiles by entering coordinates!");
        while (!battleShip.getCurrentPlayer(1).getFleet().checkWinner()) {
            System.out.println("Enter x coordinate: ");
            int x = scanner.nextInt();
            System.out.println("Enter y coordinate: ");
            int y = scanner.nextInt();
            System.out.println(battleShip.fire(x, y, 2));
            System.out.println(battleShip.render(2, false));
        }
    }
}
