package Hakemustehtava;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Purkaja {

    private StringBuilder purettuTeksti;
    private Ohjain ohjain;
    private Pattern pattern;
    private int purettuHexa;

    public Purkaja(Ohjain ohjain) {
        this.ohjain = ohjain;
        pattern = Pattern.compile("^[A-Za-z0-9]+$");
    }

    public String pura(String purettavaTeksti) {
        purettuTeksti = new StringBuilder();
        char[] jaettu = purettavaTeksti.toCharArray();
        if(jaettu.length > 1){                                  //Tarkistetaan onko purettavassa salauksessa yli 2 merkkiä, jos ei niin ei voida purkaa ja kirjataan vain merkki
            for (int i = 0; i < jaettu.length - 1; i+=2) {
                String hex = jaettu[i]+""+jaettu[i+1];                  //Otetaan 2 merkkiä kerrallaan käsittelyyn
                if(pattern.matcher(hex).find()){                        //Mikäli ne eivät ole erikoismerkkejä tarkistetaan onko mahdollista purkaa
                    try{
                        purettuHexa = Integer.parseInt(hex,16);         
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,"Virheellinen koodi - ei voida purkaa"); //jos koodia ei voida purkaa (heksaa ei ole olemassa) niin palautetaan tyhjä
                        return "";
                    }
                }
                //Mikäli kaikki on kunnossa aletaan tarkistamaan mitä merkkiä heksa vastaa
                if (hex.equals("01")) {purettuTeksti.append(",");}      //Käydään ensin läpi kaikki sallitut erikoismerkit
                else if (hex.equals("05")) {purettuTeksti.append(".");} 
                else if (hex.equals("09")) {purettuTeksti.append("!");} 
                else if (hex.equals("13")) {purettuTeksti.append("?");} 
                else if (hex.equals("00")) {purettuTeksti.append(" ");}
                else if(pattern.matcher(hex).find() && purettuHexa%2==0){   //tutkitaan ovatko merkit sallittuja ja onko niiden desimaaliarvo parillinen, jos on puretaan tavalla 1
                    purettuTeksti.append(puraTapa1(hex));
                }
                else if(pattern.matcher(hex).find() && purettuHexa%2==1){ //tutkitaan ovatko merkit sallittuja ja onko niiden desimaaliarvo parillinen, jos on puretaan tavalla 2
                    purettuTeksti.append(puraTapa2(hex));
                }
                else purettuTeksti.append(hex);
            }
            if(jaettu.length % 2 == 1)                              //Jos merkkijono on pariton täytyy viimeinen merkki lisätä mukaan ja se ei voi olla salattu
                purettuTeksti.append(jaettu[jaettu.length-1]);
        }
        else {
            purettuTeksti.append(jaettu[0]);
            JOptionPane.showMessageDialog(null, "Purku tarvitsee vähintään 2 merkkiä.");
        }
        return purettuTeksti.toString();  //palautetaan purettu teksti ohjaimen kautta näkymään
    }

    private String puraTapa1(String hex) {
            int arvo = Integer.parseInt(hex, 16);
            arvo = arvo/2+(int)'@';
            return ""+(char)arvo;
    }
    
    private String puraTapa2(String hex){
        int arvo = Integer.parseInt(hex, 16);
        arvo = (int)'@' - (arvo+1)/4 + 32;
        return ""+(char)arvo;
    }
}
