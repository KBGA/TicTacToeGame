public class SpielPruefung {

    public static int standPruefen(int[][] brett) {
        // prüfe zeilen und spalten
        int sum = 0, sum2 = 0;
        for (int i = 0; i < 3; i++) {
            sum = brett[i][0] + brett[i][1] + brett[i][2];
            sum2 = brett[0][i] + brett[1][i] + brett[2][i];
            if ((sum == -3) || (sum2 == -3)){
                return -1;
            }
            else if ((sum == 3) || (sum2 == 3)){
                return 1;
            }
        }

        // prüfe die Diagonale links oben nach rechts unten
        sum = brett[0][0] + brett[1][1] + brett[2][2];
        // prüfe die Diagonale links unten nach rechts oben
        sum2 = brett[0][2] + brett[1][1] + brett[2][0];

        if ((sum == -3) || (sum2 == -3)){
            return -1;
        }
        else if ((sum == 3) || (sum2 == 3)){
            return 1;
        }

        // ansonsten ist es (noch) unentschieden
        return 0;
    }


    // zählt die Anzahl der bereits besetzten Felder
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


    public static boolean spielPruefen(int[][] brett) {
        if ((standPruefen(brett) != 0) || (besetzteFelderPruefen(brett) == 9)) {
            return true;
        } else {
            return false;
        }
    }

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
