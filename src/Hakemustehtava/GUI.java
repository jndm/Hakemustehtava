package Hakemustehtava;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame{
    
    private Ohjain ohjain;
    private JButton puraNappi, salaaNappi;
    private JTextArea tulosteTeksti, syoteTeksti;
    private JPanel syotePaneeli, tulosPaneeli, nappiPaneeli;
    private JScrollPane syoteKelaus, tulosKelaus;
    private JLabel syoteEsittely, tulosEsittely;
    
    public GUI(){
        alustaKomponentit();
    }
    
    public void rekisteroiOhjain(Ohjain ohjain) {
        this.ohjain = ohjain;
    }

    private void alustaKomponentit() {
        
        //Syötteen GUI asetukset
        syoteEsittely = new JLabel("Kirjoita alle salattava/purettava teksti:");
        syoteTeksti = new JTextArea("Tähän salattava tai purettava teksti. Salattaessa älä syötä numeroita tai salausta ei suoriteta");
        syoteTeksti.setLineWrap(true);
        syoteTeksti.setWrapStyleWord(true);
        syoteKelaus = new JScrollPane(syoteTeksti);
        syoteKelaus.setPreferredSize(new Dimension(500,100));
        syoteKelaus.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        syotePaneeli = new JPanel();
        syotePaneeli.setPreferredSize(new Dimension(500,150));
        syotePaneeli.setBorder(new EmptyBorder(5,0,0,0));
        syotePaneeli.add(syoteEsittely, BorderLayout.NORTH);
        syotePaneeli.add(syoteKelaus);
        
        //Tulosteen GUI asetukset
        tulosEsittely = new JLabel("Purettu/salattu teksti:");
        tulosteTeksti = new JTextArea("Tähän ilmestyy purettu/salattu teksti");
        tulosteTeksti.setLineWrap(true);
        tulosteTeksti.setWrapStyleWord(true);
        tulosteTeksti.setEditable(false);
        tulosKelaus = new JScrollPane(tulosteTeksti);
        tulosKelaus.setPreferredSize(new Dimension(500,100));
        tulosKelaus.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tulosPaneeli = new JPanel();
        tulosPaneeli.setPreferredSize(new Dimension(500,150));
        tulosPaneeli.setBorder(new EmptyBorder(5,0,5,0));
        tulosPaneeli.add(tulosEsittely, BorderLayout.NORTH);
        tulosPaneeli.add(tulosKelaus);
        
        //Luodaan napit ja annetaan niille actionlistenerit    
        puraNappi = new JButton("Pura");
        salaaNappi = new JButton("Salaa");
        
        puraNappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!syoteTeksti.getText().equals(""))     //ei pureta tyhjää kenttää
                    ohjain.pura(syoteTeksti.getText());
                else JOptionPane.showMessageDialog(null, "Syöte tyhjä.\nPurkua ei suoriteta.");
             }
        });
        
        salaaNappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!syoteTeksti.getText().equals("") && !syoteTeksti.getText().matches(".*\\d.*"))   //ei salata tyhjää kenttää eikä numeroita sisältäviä tekstejä
                    ohjain.salaa(syoteTeksti.getText());
                else JOptionPane.showMessageDialog(null, "Syötteessä numeroita tai syöte tyhjä.\nSalausta ei suoriteta.");
             }
        });
        
        //Button paneelin ja nappien asetukset
        salaaNappi.setPreferredSize(new Dimension(100,40));
        puraNappi.setPreferredSize(new Dimension(100,40));
        nappiPaneeli = new JPanel();
        nappiPaneeli.setBorder(new EmptyBorder(10,0,5,0));
        nappiPaneeli.setPreferredSize(new Dimension(500,80));
        nappiPaneeli.add(salaaNappi);
        nappiPaneeli.add(puraNappi);
        
        this.setTitle("Herlokki Solmusen salauslaite");
        this.setPreferredSize(new Dimension(600, 400));
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(syotePaneeli, BorderLayout.NORTH);
        this.add(tulosPaneeli, BorderLayout.CENTER);
        this.add(nappiPaneeli, BorderLayout.SOUTH);
        
        //Haetaan näytön resoluutio, jotta voidaan käynnistää ikkuna aina samassa paikassa riippumatta koneesta
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-(this.getPreferredSize().width/2), dim.height/5);
        
        pack();
        this.setVisible(true);
    }
    
    public void muutaTuloste(String s){
        tulosteTeksti.setText(s);
    }
}
