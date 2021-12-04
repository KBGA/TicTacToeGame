/**
 * Diese Klasse ist das Hauptprogramm mit der "main" Funktion.
 */
public class Game {

    /**
     * Diese Methode ist einfach die Methode, die das Programm startet.
     *
     * @param args
     */
    public static void main(String[] args) {

        TicTacToe ticTacToeGame = new TicTacToe();
        //Variable für die Eingabe von X
        int xInput;
        //Variable für die Eingabe von Y
        int yInput;
        //Variable um die While-Schleife zu stoppen, wenn das Spiel zu Ende ist
        boolean whileBedingung = true;

        while (whileBedingung) {
            //Das Brett wird jedes Mal angezeigt. Am Anfang ist das Brett leer.
            Anzeige.brettAnzeigen(ticTacToeGame.getBoard());
            System.out.println("Sie sind dran");

            //Die Werte x und y werden gelesen
            xInput = SpielerEingabe.eingabeLesen("X");
            yInput = SpielerEingabe.eingabeLesen("Y");

            //Hier spielt der Spieler und die Funktion gibt 1 als Wert zurück, wenn alles ohne Probleme geklappt hat
            int playerStep = ticTacToeGame.spielerZug(yInput - 1, xInput - 1);

            //Die Funktion "spielrZug" liefert 1, wenn der Spieler dran ist und -1, wenn der Computer dran ist
            if (playerStep == 1) {
                //Da der Spieler gespielt hat, und es geklappt hat, wird geprüft, ob das Spiel schon zu Ende ist
                if (ticTacToeGame.spielende()) {
                    /*Wenn das Spiel zu Ende ist, "Game Over" und Ergebnis anzeigen. "false" wird zurückgegeben, um die
                    While-Schleife zu beende*/
                    whileBedingung = Anzeige.endeAnzeigen(ticTacToeGame);
                } else {
                    //Wenn der Spiele gespielt hat und alles geklappt hat, dann ist der Computer dran.
                    ticTacToeGame.computerZug();
                    //Nachdem der Computer gespielt hat, wird geprüft, ob das Spiel zu Ende ist.
                    if (ticTacToeGame.spielende()) {
                        /*Wenn das Spiel zu Ende ist, "Game Over" und Ergebnis anzeigen. "false" wird zurückgegeben,
                        um die While-Schleife zu beende*/
                        whileBedingung = Anzeige.endeAnzeigen(ticTacToeGame);
                    }
                }
            }
        }
    }
}
