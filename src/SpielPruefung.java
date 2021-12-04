/**
 * Diese Klasse kümmert sich um die Prüfung des Spielstandes.
 */
public class SpielPruefung {

    /**
     * Diese Funktion prüft, ob das Spiel schon gewonnen wurde oder nicht
     *
     * @param brett
     * @return
     */
    public static int standPruefen(int[][] brett) {
        // prüfe zeilen und spalten
        int sum1, sum2;
        for (int i = 0; i < 3; i++) {
            //sum1 wird hier für die Spalten verwendet
            sum1 = brett[i][0] + brett[i][1] + brett[i][2];
            //sum1 wird hier für die Zeilen verwendet
            sum2 = brett[0][i] + brett[1][i] + brett[2][i];
            //Wenn die Summe in einer Zeile oder Spalte -3 ist, hat der Computer gewonnen.
            // Der Wert -1 wird zurückgegeben
            if ((sum1 == -3) || (sum2 == -3)) {
                return -1;
            } else if ((sum1 == 3) || (sum2 == 3)) {
                //Wenn die Summe in einer Zeile oder Spalte 3 ist, hat der Spieler gewonnen.
                // Der Wert 1 wird zurückgegeben
                return 1;
            }
        }

        //prüfe die Diagonale links oben nach rechts unten
        sum1 = brett[0][0] + brett[1][1] + brett[2][2];
        //prüfe die Diagonale links unten nach rechts oben
        sum2 = brett[0][2] + brett[1][1] + brett[2][0];

        //Wenn die Summe in der Diagonale -3 ist, hat der Computer gewonnen. Der Wert -1 wird zurückgegeben
        if ((sum1 == -3) || (sum2 == -3)) {
            return -1;
        } else if ((sum1 == 3) || (sum2 == 3)) {
            //Wenn die Summe in der Diagonale 3 ist, hat der Spieler gewonnen. Der Wert 1 wird zurückgegeben
            return 1;
        }
        //Falls das Spiel noch unentschieden ist, wird 0 zurückgegeben
        return 0;
    }


    /**
     * Diese Funktion zählt die Anzahl der bereits besetzten Felder
     */
    public static int besetzteFelderPruefen(int[][] brett) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (brett[i][j] != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Diese Funktion prüft, ob das Brett schon voll ist oder, ob jemand schon gewonnen hat
     */
    public static boolean spielPruefen(int[][] brett) {
        return (standPruefen(brett) != 0) || (besetzteFelderPruefen(brett) == 9);
    }

    /**
     * Diese Funktion prüft wer gewonnen hat oder, ob es unentschieden ist.
     */
    public static String ergebnisPruefen(int[][] brett) {

        int ergebnisWert = standPruefen(brett);

        if (ergebnisWert == 0) {
            return "Spiel endete unentschieden";
        } else if (ergebnisWert == 1) {
            return "Spieler X gewinnt";
        } else {
            return "Spieler O gewinnt";
        }
    }
}
