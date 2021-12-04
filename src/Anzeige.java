public class Anzeige {


    // das aktuelle Brett wird angezeigt
    public static void brettAnzeigen(int[][] board){

        System.out.println("\n**1*2*3**");

        for (int i=0; i < 3; i++){

            System.out.print((i+1)+" ");

            for (int j=0; j < 3; j++){

                //Wenn das eine Zelle den Werte -1 hat, dann hat dort der Computer gespielt.
                if (board[i][j] == -1) {
                    System.out.print("O ");
                }
                // Für den Spieler hat man den Wert 1
                else if (board[i][j] == 1) {
                    System.out.print("X ");
                } else {
                    System.out.print("- ");
                }
            }

            System.out.println(i + 1);
        }
        System.out.println("**1*2*3**\n");
    }

    public static boolean endeAnzeigen(TicTacToe ticTacToeGame) {
        //Wenn das Spiel zu Ende ist, "Game Over" anzeigen und das Ergebnis anzeigen.
        System.out.println("Game Over!!! ");
        System.out.println(ticTacToeGame.spielErgebnis());
        Anzeige.brettAnzeigen(ticTacToeGame.getBoard());
        return false;
    }


}
