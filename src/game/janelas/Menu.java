package game.janelas;

import game.recursos.Audio;
import jplay.Keyboard;
import jplay.Sprite;
import jplay.Window;

/**
 * Classe main do projeto
 */
public class Menu {

	/**
	 * Método main
	 *
	 * @param args
	 */
    public static void main(String[] args) {

    	// cria a janela do jogo
        Window janela = new Window(800, 600);
        janela.getCompatibleDisplayMode();

        // acessa o teclado
        Keyboard teclado = janela.getKeyboard();

        // carrega a imagem de fundo do menu
        Sprite fundo = new Sprite("imagens/menu.png", 2);
        fundo.y = 115;
        // seta um tempo maximo para exibição de todos os frames
        fundo.setTotalDuration(1000);

        // // inicia a thread responsável pelo áudio do jogo
        // Thread audio = new Thread(new Audio());
        // audio.start();
        // // inicia a música de fundo
        // Audio.executando = true;

        boolean isMenu = true;
        while (isMenu) {
        	// desenha a imagem na tela
            fundo.draw();
            // atualiza o frame do desenho
            fundo.update();
            janela.update();

            // se pressionou ENTER redireciona para a tela principal do jogo
            if (teclado.keyDown(Keyboard.ENTER_KEY)) {
                new Thread(new Cena(janela)).start();
                isMenu = false;
            }
            // se pressionou ESC fecha a janela e sai do jogo
            if (teclado.keyDown(Keyboard.ESCAPE_KEY)) {
                janela.exit();
        	}
        }
    }
}
