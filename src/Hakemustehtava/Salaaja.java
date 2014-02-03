package Hakemustehtava;

public class Salaaja {
    private StringBuilder salattuTeksti;
    private Ohjain ohjain;
    
    public Salaaja(Ohjain ohjain) {
        this.ohjain = ohjain;
    }

    public void salaa(String salattavaTeksti) {
        salattuTeksti = new StringBuilder();
        salattavaTeksti = salattavaTeksti.toUpperCase(); //muutetaan teksti isoiksi kirjaimiksi
        char[] teksti = salattavaTeksti.toCharArray();
        for (int i = 0; i < teksti.length; i++) {
            if ((int) teksti[i] > 64 && (int) teksti[i] < 91) {     //Tutkitaan onko merkit kirjaimia vai erikoismerkkejä
                if ((int) teksti[i] % 2 == 1) {                     //Jos pariton (A,C,E...) käytetään tapaa 1, muuten tapaa 2
                    salattuTeksti.append(muunnaTapa1(teksti[i]));
                } else {
                    salattuTeksti.append(muunnaTapa2(teksti[i]));
                }
            }
            else salattuTeksti.append(muunnaTapa3(teksti[i]));      //jos merkki ei kirjain, se on erikoismerkki ja käsitellään se tavalla 3
        }
        ohjain.muutaTuloste(salattuTeksti.toString());              //viedään salattu teksti ohjaimen läpi näkymään
    }

    private String muunnaTapa1(char c) {
        int jarjestysNro = (int) c - (int) '@';
        String muunnettu = Integer.toHexString(jarjestysNro * 2);
        if(muunnettu.length()==1)                                   //jos hexanumero vain 1 lisätään eteen 0
            muunnettu="0"+muunnettu;
        return muunnettu;
    }

    private String muunnaTapa2(char c) {
        int nro = 32 - ((int) c - (int) '@');
        String muunnettu = Integer.toHexString(nro * 4 - 1);
        if(muunnettu.length()==1)
            muunnettu="0"+muunnettu;
        return muunnettu;
    }

    private String muunnaTapa3(char c) {
       if((int)c == 32)
           return "00";
       else if((int)c == 33)
           return "09";
       else if((int)c == 44)
           return "01";
       else if((int)c == 46)
           return "05";
       else if((int)c == 63)
           return "13";
       else return Character.toString(c);
    }
}
