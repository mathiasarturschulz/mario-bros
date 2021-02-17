package game.janelas;

import game.recursos.Audio;
import jplay.Keyboard;
import jplay.Sprite;
import jplay.Window;

public class Menu {

    public static void main(String[] args) {

        Window janela = new Window(800, 350);
        janela.getCompatibleDisplayMode();
        Keyboard teclado = janela.getKeyboard();

        //Carrega uma imagem de fundo do menu
        Sprite fundo = new Sprite("imagens/menu.png", 2);

        //Alterna os frames do menu para dar efeito de gif ao "PRESS START"
        fundo.setTotalDuration(1000);

        //Starta a Thread responsável pelo áudio do jogo
        Thread audio = new Thread(new Audio());
        audio.start();

        Audio.executando = true; //Inicia a música de fundo

        boolean isMenu = true;

        while (isMenu) {
            fundo.draw();
            fundo.update();
            janela.update();

            //Vai para janela principal do jogo ao teclar ENTER
            if (teclado.keyDown(Keyboard.ENTER_KEY)) {
                new Thread(new Cena(janela)).start();
                isMenu = false;
            }
            if (teclado.keyDown(Keyboard.ESCAPE_KEY)) {
                //Fecha a janela e sai do jogo
                janela.exit();
        	}
        }
    }
}
