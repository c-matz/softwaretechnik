package TicTacToe;

import java.io.*;           //Einbinden der Bibliothek für Eingaben
import java.util.Random;    //Einbinden der Bibliothek für Zufallszahlen
import java.util.Arrays;    //Einbinden der Bibliothek für Arrays

/**
 * Einsendeaufgabe 2 : CCD-Übung  
 * Tic Tac Toe mit einem Durchgang, bei Gleichstand wird wiederholt.
 * 
 * @author Christin Matz <matz@christin.info>
 * @version 0.1 
 * @since 2012-11-02
 */

public class TicTacToe {    
    
    private TicTacToe(){} 
    
    /** 
     * Spieler wählt.
     * @return Auswahl des Spielers aus Stein, Schere und Papier.
     */    
    public String spielerWaehlt(){        

        System.out.println("Wähle: Stein, Schere oder Papier ?");
        
        String auswahl= null;
        
        //Auswahl einlesen
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));        
	
        try {
            auswahl = console.readLine();
	} 
        
        catch (IOException e) {            
            e.printStackTrace();
	}        
        
        //wenn falsche Eingabe, Spieler noch einmal wählen lassen
        if (prüfeSpielerEingabe(auswahl)==false){            
            System.out.print("Deine Eingabe war nicht korrekt, bitte wähle noch einmal: ");  
            spielerWaehlt();
        }    
        
        return auswahl;
    }
    
    /** 
     * Computer wählt.
     * @return Zufallswert aus Stein, Schere und Papier.
     */
    public String computerWaehlt(){      
        
        String[] auswahlmoeglichkeiten= {"Stein","Schere","Papier"}; 
        
        Random random = new Random();
        
        String zufallswert = 
                auswahlmoeglichkeiten[random.nextInt(auswahlmoeglichkeiten.length)];
        
        System.out.print("Computer wählt: "); 
        System.out.println(zufallswert);
        
        return zufallswert;
    }
    
    /**
     * Prüfe ob die Spielereingabe korrekt ist. 
     * Gültig sind "Stein", "Schere" und "Papier", case-insensitiv.
     * @param eingabe Eingabe des Spielers.
     * @return korrekte Eingabe? (true=ja, false=nein)
     */
    public boolean prüfeSpielerEingabe(String eingabe){
        
        eingabe = eingabe.toLowerCase();

        if(eingabe.equals("stein") || eingabe.equals("schere")|| eingabe.equals("papier"))
        {   
            return true;
        }
           
        else{
           return false;
        }
    }
    
    /** 
     * Ermittle den Gewinner, gebe Textmeldung zurück. 
     * @param auswahlSpieler Auswahl des Spielers
     * @param auswahlComputer Auswahl des Computers
     * @return Textmeldung, wer gewinnt?
     */
    public String ermittleGewinner(String auswahlSpieler, String auswahlComputer){
        
        //Auswahlen in Kleinschreibung umwandeln
        auswahlSpieler = auswahlSpieler.toLowerCase();
        auswahlComputer = auswahlComputer.toLowerCase();
        
        String wasSchlaegtWas = null;
        
        if(auswahlSpieler.equals("stein") && auswahlComputer.equals("stein")){
            wasSchlaegtWas = "Gleichstand";
        }
        
        if(auswahlSpieler.equals("stein") && auswahlComputer.equals("schere")){
            wasSchlaegtWas = "Glückwunsch! Du gewinnst: Stein schlägt Schere";
        }
        
        if(auswahlSpieler.equals("stein") && auswahlComputer.equals("papier")){
            wasSchlaegtWas = "Leider Verloren - Computer gewinnt: Papier schlägt Stein";
        }                
                
        if(auswahlSpieler.equals("schere") && auswahlComputer.equals("stein")){
            wasSchlaegtWas = "Leider Verloren - Computer gewinnt: Stein schlägt Schere";
        }
        
        if(auswahlSpieler.equals("schere") && auswahlComputer.equals("schere")){
            wasSchlaegtWas = "Gleichstand";
        }
        
        if(auswahlSpieler.equals("schere") && auswahlComputer.equals("papier")){
            wasSchlaegtWas = "Glückwunsch! Du gewinnst: Schere schlägt Papier";
        }
        
        if(auswahlSpieler.equals("papier") && auswahlComputer.equals("stein")){
            wasSchlaegtWas = "Glückwunsch! Du gewinnst: Papier schlägt Stein";
        }                
                
        if(auswahlSpieler.equals("papier") && auswahlComputer.equals("schere")){
           wasSchlaegtWas = "Leider Verloren - Computer gewinnt: Schere schlägt Papier";
        }
                        
        if(auswahlSpieler.equals("papier") && auswahlComputer.equals("papier")){
            wasSchlaegtWas = "Gleichstand";
        }                
        
        return(wasSchlaegtWas);
    }
    
    /**
     * Fragen ob Spieler weiterspielen möchte, 
     * wenn Eingabe "j" oder "J" weiterspielen, sonst abbrechen.
     */
    public void weiterSpielen(){
        
        System.out.println("Weiterspielen ? (j/n)");
    
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

	try {
            
            //Spiel noch einmal starten
            if(console.readLine().equalsIgnoreCase("j")){
                start();
            }          
            
            //Spiel beenden
            else{
                System.exit(0);
            }
            
	} 
        
        catch (IOException e) {
            e.printStackTrace();
	} 
    }
    
    /**
     * Spiel wird gestartet, bei Gleichstand automatische Wiederholung. 
     */
    public void start(){        
        
        String auswahlSpieler   = spielerWaehlt();        
        String auswahlComputer  = computerWaehlt();
        
        String gewinner         = ermittleGewinner(auswahlSpieler,auswahlComputer);
        
        System.out.println(gewinner); 
        
        //bei Gleichstand Spiel wiederholen
        if(gewinner.equals("Gleichstand")){
            start();
        }
        
        //fragen, ob Spieler weiterspielen möchte
        weiterSpielen();  
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        TicTacToe t  = new TicTacToe();
        t.start();              
    }
}
