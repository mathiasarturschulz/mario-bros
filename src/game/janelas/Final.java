package game.janelas;

import game.recursos.Audio;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Window;

public class Final {

    public Final(int idVencedor) {
        Cena.rodando = false; // Para a execução do jogo

        Window janela = new Window(250, 250);
        Keyboard teclado = janela.getKeyboard();

        GameImage marioWins = new GameImage("imagens/marioWins.jpg");
        GameImage luigiWins = new GameImage("imagens/luigiWins.jpg");
        
        boolean isFinal = true;

        while (isFinal) {
            //Define a imagem final do jogo, que contém o respectivo vencedor
            if (idVencedor == 1) {
                marioWins.draw();
            } else {
                luigiWins.draw();
            }
            
            janela.update();
            
            //Reinicia o jogo para obter uma revanche
            if (teclado.keyDown(Keyboard.ENTER_KEY)) {                
                new Cena().run();
                isFinal = false;
            }
        }
    }
}
