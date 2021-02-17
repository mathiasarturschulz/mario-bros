package game.janelas;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.Window;

/**
 * Classe da tela de fim de jogo
 */
public class Final {

    /**
     * Construtor
     */
    public Final(Window janela, int idVencedor) {
        // para a execução do jogo
        Cena.RODANDO = false;
        Keyboard teclado = janela.getKeyboard();

        GameImage marioWins = new GameImage("imagens/marioWins.jpg");
        marioWins.x = 275;
        marioWins.y = 175;
        GameImage luigiWins = new GameImage("imagens/luigiWins.jpg");
        luigiWins.x = 275;
        luigiWins.y = 175;

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
                new Cena(janela).run();
                isFinal = false;
            }
            // se pressionou ESC fecha a janela e sai do jogo
            if (teclado.keyDown(Keyboard.ESCAPE_KEY)) {
                janela.exit();
        	}
        }
    }
}
