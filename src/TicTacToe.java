/**
 * Dieser Code ist eine einfache Implementierung des Spiels TicTacToe
 */

public class TicTacToe {
    //Diese Variable ist für das Brett
    private int[][] board;

    public int[][] getBoard() {
        return board;
    }

    //Diese Variable ist für den aktuellen Spielen
    private int spielerAmZug;
    //
    private int spielTiefe;
    //
    private int bestMove;

    public TicTacToe() {
        // Am Anfang sind alle Werte des Bretts gleich null (0).
        board = new int[3][3];
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                board[i][j] = 0;
            }
        }

        //Die Variable spielerAmZug ist die Variable für den aktuellen Spielen. 1 ist für X und -1 ist für O
        spielerAmZug = 1;
        spielTiefe   = 0;
        bestMove     = 0;
    }


    //die Funktion spielende prüft ob das Spiel zu Ende ist
    public boolean spielende(){
        return SpielPruefung.spielPruefen(board);
    }

    public String spielErgebnis(){
        return SpielPruefung.ergebnisPruefen(board);
    }

    //Der Spieler ist dran
    public int spielerZug(int x, int y) {
        int werIstDran = 1;

        if (board[x][y]!=0) {
            return 0;
        } else if ((board[x][y]==0)&&(spielerAmZug == werIstDran)) {
            board[x][y]  = werIstDran;

            spielerAmZug = -spielerAmZug;
            spielTiefe++;
            return 1;
        } else{
            System.out.println("Die Daten, die Sie eingegeben haben, sind nicht gültig.");
            System.out.println("Geben Sie zuerst x ein und drücken Enter. Dann geben Sie y ein und drücken Sie wieder Enter: ");
            return -1;
        }
          
    }


    //Der Computer ist dran
    public void computerZug() {

        if (spielerAmZug==-1) {
            System.out.println("Der Computer hat gespielt. Sie sind wieder dran: ");
            minmaxO(board, spielTiefe);
            board[bestMove/10][bestMove%10] = -1;
        } else {
            minmaxX(board, spielTiefe);
            board[bestMove/10][bestMove%10] =  1;
        }
        spielTiefe++;
        spielerAmZug = -spielerAmZug;
    }


    // X ist am Zug, bester Zug wird in bestMove gepseichert
    public int minmaxX(int[][] brett, int tiefe){
        //Zuerst wird geprüft, ob das Spiel noch nicht zu Ende ist
        int spielStand = SpielPruefung.standPruefen(brett);
        if (spielStand != 0) {
            return spielStand;
        }
        if (SpielPruefung.besetzteFelderPruefen(brett)==9) {
            return 0;
        }

        int max = -5;
        int[] zuege = genMoves(brett);

        for (int i=0; i<zuege.length; i++) {
            brett[zuege[i]/10][zuege[i]%10] = 1;    // führe X-Zug aus
            int wert = minmaxO(brett, tiefe+1);
            if (wert > max) {
                max = wert;
                if (tiefe==spielTiefe)
                    bestMove = zuege[i];         // bester Zug wird gespeichert
            }
            brett[zuege[i]/10][zuege[i]%10] = 0;   // nimm X-Zug zurück
        }
        return max;
    }

    // O ist am Zug, bester Zug wird in bestMove gepseichert
    public int minmaxO(int[][] brett, int tiefe){
        // vielleicht ist das Spiel schon fertig?
        int eval = SpielPruefung.standPruefen(brett);
        if (eval != 0)
            return eval;
        if (SpielPruefung.besetzteFelderPruefen(brett)==9)
            return 0;

        int min = 5;
        int[] zuege = genMoves(brett);

        for (int i=0; i<zuege.length; i++) {
            brett[zuege[i]/10][zuege[i]%10] = -1;  // führe O-Zug aus
            int wert = minmaxX(brett, tiefe+1);
            if (wert < min) {
                min = wert;
                if (tiefe==spielTiefe)
                    bestMove = zuege[i];         // bester Zug wird gespeichert
            }
            brett[zuege[i]/10][zuege[i]%10] = 0;   // nimm O-Zug zurück
        }
        return min;
    }


    // liefert die Liste der noch offenen Positionen mit x*10+y
    private int[] genMoves(int[][] b){
        // speichere die Züge
        int[] zuege = new int[9-SpielPruefung.besetzteFelderPruefen(b)];  // wieviele Züge gibt es?
        int anzZuege = 0;
        for (int i=0; i < 3; i++)
            for (int j=0; j < 3; j++) {
                if (b[i][j]==0) {
                    zuege[anzZuege++] = i*10+j;
                }
            }
        return zuege;
    }

}
