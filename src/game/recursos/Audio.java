package game.recursos;

import game.janelas.Cena;
import jplay.Sound;

public class Audio implements Runnable {

    public static boolean pulou, bateu, pegou, menu, executando;

    @Override
    public void run() {
        if (executando) {
            this.tocar("audios/fundo.wav");
        }
        while (true) {
            System.out.println();
            if (pulou) {
                this.tocar("audios/pulo.wav");
                pulou = false;
            }
            if (bateu) {
                this.tocar("audios/batida.wav");
                bateu = false;
            }
            if (pegou) {
                this.tocar("audios/moeda.wav");
                pegou = false;
            }
        }
    }

    /**
     * Método responsável por carregar e tocar o arquivo de áudio
     */
    private void tocar(String path) {
        new Sound(path).play();
    }
}
