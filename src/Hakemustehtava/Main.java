package Hakemustehtava;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        String vastaus = "";
        while (!vastaus.equals("42")) {
            vastaus = JOptionPane.showInputDialog("Mikä on vastaus suureen kysymykseen elämästä,\n"
                    + "maailmankaikkeudesta ja muusta sellaisesta?");
            if (vastaus == null) {
                System.exit(0);
            } else if (!vastaus.equals("42")) {
                JOptionPane.showMessageDialog(null, "Väärä vastaus kokeile uudestaan.");
            }
        }

        GUI gui = new GUI();
        Ohjain ohjain = new Ohjain(gui);
        gui.rekisteroiOhjain(ohjain);
    }
}
