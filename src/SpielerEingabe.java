import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Diese Klasse liest die Eingabe des Spielers und gibt den Wert als int zurück
 */
public class SpielerEingabe {

    //Diese Funktion liest die Werte X oder Y.
    public static  int eingabeLesen(String XorY){
        InputStreamReader playerInputStream  = new InputStreamReader(System.in);
        //spieleInput ist die Variable für die Werte, die vom Spieler eingegeben werden.
        BufferedReader playerInput   = new BufferedReader(playerInputStream);

        int playerInputValue = 0;

        try {
            //Der Spieler darf nur Werte zwischen 1 und 3 verwenden.
            while (playerInputValue < 1 || playerInputValue > 3)
            {
                System.out.println("Geben Sie für " + XorY + " einen Wert zwischen 1 und 3 ein: ");
                //Die Eingabe wird als int gelesen
                playerInputValue = Integer.parseInt(playerInput.readLine());
            }
        } catch(Exception e){
            System.out.println("Es gab ein Problem mit der Eingabe.");
            System.out.println("Sie sind dran. Geben Sie zuerst x ein und drücken Enter. Dann geben Sie y ein und " +
                    "drücken Sie wieder Enter: ");
        }
        //Der int-Wert wird zurückgegeben
        return playerInputValue;
    }
}