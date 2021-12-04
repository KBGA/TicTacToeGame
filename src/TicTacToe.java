/**
 * Dieser Code ist eine einfache Implementierung des Spiels TicTacToe
 */
public class TicTacToe {
    /**
     * Diese Variable ist für das Brett
     */
    private int[][] brett;

    /**
     * Diese Funktion bist einfach das Brett zurück
     */
    public int[][] getBoard() {
        return brett;
    }

    /**
     * Diese Variable ist für den aktuellen Spielen
     */
    private int spielerAmZug;
    /**
     * Variable für die Spieltiefe
     */
    private int spielTiefe;
    /**
     * Variable für den besten Zug
     */
    private int bestMove;

    /**
     * Constructor
     */
    public TicTacToe() {
        // Am Anfang sind alle Werte des Bretts gleich null (0).
        brett = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                brett[i][j] = 0;
            }
        }

        //Die Variable spielerAmZug ist die Variable für den aktuellen Spielen. 1 ist für X und -1 ist für O
        spielerAmZug = 1;
        spielTiefe = 0;
        bestMove = 0;
    }


    /**
     * Die Funktion spielende prüft ob das Spiel zu Ende ist
     */
    public boolean spielende() {
        return SpielPruefung.spielPruefen(brett);
    }

    /**
     * Diese Funktion liefert das Spielergebnis.
     */
    public String spielErgebnis() {
        return SpielPruefung.ergebnisPruefen(brett);
    }

    /**
     * Der Spieler ist dran und spielt in der ausgewählten Zelle, wenn Sie noch leer ist.
     */
    public int spielerZug(int x, int y) {
        int werIstDran = 1;

        //Wenn die Zelle schon besetzt ist, wird nicht dort gespielt
        if (brett[x][y] != 0) {
            return 0;
        } else if ((brett[x][y] == 0) && (spielerAmZug == werIstDran)) {
            //Wenn die Zelle noch leer ist (0), und der Spieler dran ist, bekommt die Zellen den Wert 1.
            brett[x][y] = werIstDran;
            //Wenn dieser Werte -1 ist, spielt der Computer
            spielerAmZug = -spielerAmZug;
            //Jedes Mal, wenn gespielt wird, erhöht sich die Spieltiefe
            spielTiefe++;
            return 1;
        } else {
            System.out.println("Die Daten, die Sie eingegeben haben, sind nicht gültig.");
            System.out.println("Geben Sie zuerst x ein und drücken Enter. Dann geben Sie y ein und drücken Sie wieder Enter: ");
            return -1;
        }

    }

    /** Der Computer ist dran */
    public void computerZug() {

        if (spielerAmZug == -1) {
            System.out.println("Der Computer hat gespielt. Sie sind wieder dran: ");
            minmaxO(brett, spielTiefe);
            brett[bestMove / 10][bestMove % 10] = -1;
        } else {
            minmaxX(brett, spielTiefe);
            brett[bestMove / 10][bestMove % 10] = 1;
        }
        spielTiefe++;
        spielerAmZug = -spielerAmZug;
    }

    /** X ist am Zug, bester Zug wird in bestMove gepseichert */
    public int minmaxX(int[][] brett, int tiefe) {
        //Zuerst wird geprüft, ob das Spiel noch nicht zu Ende ist
        int spielStand = SpielPruefung.standPruefen(brett);
        if (spielStand != 0) {
            return spielStand;
        }
        // Wenn alle Felder schon besetzt sind
        if (SpielPruefung.besetzteFelderPruefen(brett) == 9) {
            return 0;
        }

        int max = -5;
        int[] zuege = genMoves(brett);

        for (int i = 0; i < zuege.length; i++) {
            brett[zuege[i] / 10][zuege[i] % 10] = 1;    // führe X-Zug aus
            int wert = minmaxO(brett, tiefe + 1);
            if (wert > max) {
                max = wert;
                if (tiefe == spielTiefe)
                    bestMove = zuege[i];         // bester Zug wird gespeichert
            }
            brett[zuege[i] / 10][zuege[i] % 10] = 0;   // nimm X-Zug zurück
        }
        return max;
    }

    /** O ist am Zug, bester Zug wird in bestMove gepseichert */
    public int minmaxO(int[][] brett, int tiefe) {
        // vielleicht ist das Spiel schon fertig?
        int eval = SpielPruefung.standPruefen(brett);
        if (eval != 0)
            return eval;
        if (SpielPruefung.besetzteFelderPruefen(brett) == 9)
            return 0;

        int min = 5;
        int[] zuege = genMoves(brett);

        for (int i = 0; i < zuege.length; i++) {
            brett[zuege[i] / 10][zuege[i] % 10] = -1;  // führe O-Zug aus
            int wert = minmaxX(brett, tiefe + 1);
            if (wert < min) {
                min = wert;
                if (tiefe == spielTiefe)
                    bestMove = zuege[i];         // bester Zug wird gespeichert
            }
            brett[zuege[i] / 10][zuege[i] % 10] = 0;   // nimm O-Zug zurück
        }
        return min;
    }

    /** liefert die Liste der noch offenen Positionen mit x*10+y */
    private int[] genMoves(int[][] b) {
        // speichere die Züge
        int[] zuege = new int[9 - SpielPruefung.besetzteFelderPruefen(b)];  // wieviele Züge gibt es?
        int anzZuege = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (b[i][j] == 0) {
                    zuege[anzZuege++] = i * 10 + j;
                }
            }
        return zuege;
    }

}
