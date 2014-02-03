package Hakemustehtava;

import javax.swing.SwingUtilities;

public class Ohjain {

    private GUI gui;
    private Purkaja purkaja;
    private Salaaja salaaja;

    public Ohjain(GUI gui) {
        this.gui = gui;
        purkaja = new Purkaja(this);
        salaaja = new Salaaja(this);
    }

    public void pura(String purettavaTeksti) {
        String purettuTeksti = purkaja.pura(purettavaTeksti);
        muutaTuloste(purettuTeksti);
    }

    public void salaa(String salattavaTeksti) {
        salaaja.salaa(salattavaTeksti);
    }
    
    public void muutaTuloste(final String s){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui.muutaTuloste(s);
            }
        });
    }
}
