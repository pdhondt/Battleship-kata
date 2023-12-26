package be.kunlabora;

import java.util.Scanner;

public class Main {

    //TODO 1: make the game playable for 2 players
    public static void main(String[] args) {
        Game battleShip = new Game();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Rendering the playfield:");
        System.out.println(battleShip.render(1, true));
        System.out.println("""
                Welcome to BattleShip.  You can place 5 ships on the playfield, which is
                a 10x10 ocean.  Each ship can only be placed once.  The different ships are, together
                with their length:
                * CARRIER(5)
                * BATTLESHIP(4)
                * DESTROYER(3)
                * SUBMARINE(3)
                * PATROLBOAT(2)
                You place a ship by passing the type, the starting coordinate (e.g. 3, 4), which
                indicates the row and the column where you want to place the ship, and the
                orientation (HORIZONTAL or VERTICAL)
                """);
        while (battleShip.getShipsCurrentPlayer(1).size() < 5) {
            battleShip.askUserInputAndPlaceShips();
        }
        System.out.println("All ships successfully placed!");


        System.out.println("Start firing your missiles by entering coordinates!");
        while (!battleShip.checkWinner(battleShip.getShipsCurrentPlayer(1))) {
            System.out.println("Enter x coordinate: ");
            int x = scanner.nextInt();
            System.out.println("Enter y coordinate: ");
            int y = scanner.nextInt();
            System.out.println(battleShip.fire(x, y, 2));
            System.out.println(battleShip.render(2, false));
        }
    }
}
