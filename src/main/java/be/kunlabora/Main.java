package be.kunlabora;

public class Main {
    public static void main(String[] args) {
        Game battleShip = new Game();

        System.out.println(battleShip.render(1));
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

        battleShip.askUserInput();
    }
}
