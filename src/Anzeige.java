/**
 * Diese Klasse kümmert sich um die Anzeigen in der Console
 */
public class Anzeige {
    /**
     * Diese Methode bekommt das Brett als Parameter und zeigt es einfach an.
     *
     * @param brett
     */
    public static void brettAnzeigen(int[][] brett) {

        //Die erste Zeile des Bretts wird angezeigt
        System.out.println("\n**1*2*3**");

        for (int i = 0; i < 3; i++) {
            //Die Zeilen links numerieren und ein Leerzeichen danach hinzufügen
            System.out.print((i + 1) + " ");

            for (int j = 0; j < 3; j++) {
                //Wenn eine Zelle den Werte -1 hat, dann hat dort der Computer gespielt.
                if (brett[i][j] == -1) {
                    System.out.print("O ");
                }
                // Für den Spieler hat man den Wert 1
                else if (brett[i][j] == 1) {
                    System.out.print("X ");
                } else {
                    //Die Zelle ist noch nicht besetzt
                    System.out.print("- ");
                }
            }
            //Die Zeilen rechts numerieren
            System.out.println(i + 1);
        }
        //Die letzte Zeile des Bretts wird angezeigt
        System.out.println("**1*2*3**\n");
    }

    /**
     * Diese Methode zeigt das Ende des Spiels und das Endergebnis.
     *
     * @param ticTacToeGame
     * @return
     */
    public static boolean endeAnzeigen(TicTacToe ticTacToeGame) {
        //Wenn das Spiel zu Ende ist, "Game Over" anzeigen und das Ergebnis anzeigen.
        System.out.println("Game Over!!! ");
        //Anzeigen wer gewonnen hat. Der Spieler oder der Computer
        System.out.println(ticTacToeGame.spielErgebnis());
        //Das Brett mit dem Endstand anzeigen
        Anzeige.brettAnzeigen(ticTacToeGame.getBoard());
        return false;
    }
}
