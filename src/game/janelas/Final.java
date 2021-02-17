package game.janelas;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.Window;

/**
 * Classe da tela de fim de jogo
 */
public class Final implements Runnable {

    int idVencedor;
    Window janela;
    Keyboard teclado;
    GameImage marioWins;
    GameImage luigiWins;

    /**
     * Construtor
     */
    public Final(Window janela, int idVencedor) {
        // para a execução do jogo
        Cena.RODANDO = false;
        this.janela = janela;
        this.idVencedor = idVencedor;
        this.teclado = janela.getKeyboard();

        this.marioWins = new GameImage("imagens/marioWins.jpg");
        this.marioWins.x = 275;
        this.marioWins.y = 175;
        this.luigiWins = new GameImage("imagens/luigiWins.jpg");
        this.luigiWins.x = 275;
        this.luigiWins.y = 175;
    }

    /**
     * Método responsável por apresentar a mensagem de qual jogador ganhou a partida
     */
    public void run() {
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
