package game.recursos;

import game.janelas.Final;
import game.sprites.Jogador;
import jplay.Window;

public class Pontuacao {

	private Window janela;
    private final int TOTAL;
    private Jogador jogador;
    private int moedas;

    public Pontuacao(Window janela) {
        /**
         * Define a pontuação máxima a ser alcançada os jogadores consigam se manter
         * na partida sem colidir com nenhum objeto
         */
    	this.janela = janela;
        this.TOTAL = 25;
    }

    /**
     * Método responsável por adicionar um novo ponto ao jogador que pegar uma moeda.
     * O valor atual de pontos de cada jogador é mostrado no placar na tela principal
     * do jogo.
     */
    public void adicionarPontos(Jogador jogador) {
        this.jogador = jogador;
        moedas++;
        this.verificarPontos();
    }

    /**
     * Método responsável por verificar a quantidade atual de pontos de cada jogador.
     * Caso um dos jogadores alcance o número máximo de ponto, vai para tela de fim 
     * de jogo, mostrando o respectivo jogador como vencedor.
     */
    private void verificarPontos() {
        if (moedas == TOTAL) {
            new Final(janela, jogador.getID());
        }
    }

    public int getMoedas() {
        return moedas;
    }
}
