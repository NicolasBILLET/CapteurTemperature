package fr.btsciel.capteurtemperature;

import jssc.SerialPortEvent;
import jssc.SerialPortException;

import java.text.DecimalFormat;

public class CapteurTemperature extends LiaisonSerie{
    private final byte[] CHARS_START = {(byte)0x47,(byte)0x4f,(byte)0x0d};
    private final int STOP = 1;
    private final int PARITY = 0;
    private final int DATA = 8;
    private final byte[] CHARS_START_IEEE = {(byte)0x49,(byte)0x45,(byte)0x45,(byte)0x45,(byte)0x00};
    private final int VITESSE = 9600;
    DecimalFormat df;
    final int LONGUEUR_TRAME = 2;
    FXML_Controller fxml;
    float valeurLue = 0;
    public CapteurTemperature() {
    }
    public void fermerLiaisonSerieCapteur(){
        super.fermerPort();
    }
    public void configureLiaisonSerieCapteur() throws SerialPortException {
        initCom("COM10");
        configurerParametres(VITESSE,DATA,PARITY,STOP);
    }

    public float decodageTrameCapteur(byte[] trame){
        if(trame.length == LONGUEUR_TRAME){
            liretrameCapteurModeNormal();
            valeurLue=conversionTrameCapteur(trame);
        }else{
            liretrameCapteurModeIEEE();
            valeurLue=conversionTrameCapteur(trame);
        }
        return valeurLue;
    }

    public float conversionTrameCapteur(byte[] trame){
        String s;
        int partieEntiere = (int)(trame[0]&0xff);
        int partieDecimale = (int)(trame[1]&0xff);
        s = partieEntiere + "." + partieDecimale;
        return Float.parseFloat(s);
    }

    public void liretrameCapteurModeIEEE (){
        ecrire(CHARS_START_IEEE);
    }

    public void liretrameCapteurModeNormal (){
        ecrire(CHARS_START);
    }

    public float getValeurLue(){
        return this.valeurLue;
    }

    public void serialEvent (SerialPortEvent event){
        int i = event.getEventValue();
        byte[] trame = lireTrame(i);
        decodageTrameCapteur(trame);
    }
}
