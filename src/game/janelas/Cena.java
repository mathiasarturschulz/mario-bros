package game.janelas;

import game.sprites.Jogador;
import game.sprites.Objeto;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jplay.GameImage;
import jplay.Parallax;
import jplay.Window;
import jplay.Keyboard;

/**
 * Classe da tela principal do jogo
 */
public class Cena implements Runnable {

    private Window JANELA;
    private final Parallax FUNDO;
    public final Jogador MARIO, LUIGI;
    public static List<Objeto> OBJETOS;
    public static boolean RODANDO;

    /**
     * Construtor da classe
     */
    public Cena(Window JANELA) {
    	OBJETOS = new ArrayList<>();
    	RODANDO = true;
    	this.JANELA = JANELA;

    	// adiciona o fundo do jogo
        FUNDO = new Parallax();
        FUNDO.add("imagens/background.png");
        FUNDO.setVelAllLayers(1, 0);

        // adiciona os jogadores
        MARIO = new Jogador(1, "imagens/mario.png", 4, JANELA);
        LUIGI = new Jogador(2, "imagens/luigi.png", 4, JANELA);

        // adiciona as coordenadas iniciais dos jogadores
        MARIO.x = 30;
        MARIO.y = 477;
        LUIGI.x = 0;
        LUIGI.y = 477;
    }

    /**
     * Método responsável por criar uma lista de objetos que são adicionados de forma aleatória na lista
     * As moedas são adicionas de forma aleatória quando um bloco é adicionado,
     * podendo aparecer em cima do bloco, ou em baixo, onde o personagem deve agachar para pegá-la
     * Quando um cano é adicionado, a moeda só pode ser adicionada na parte superior
     */
    private void adicionarObjetos() {
        String moedaIcon = "imagens/moeda.png";
        String canoIcon = "imagens/cano.png";
        String blocoIcon = "imagens/bloco2.png";
        String foguete = "imagens/foguete.png";

        Random r = new Random();
        int tipoObjeto, tipoMoeda, alturaMoeda;
        int distanciaObjetos = 800;

        for (int i = 1; i <= 80; i++) {
            alturaMoeda = 435;
            tipoObjeto = r.nextInt(3);
            if (tipoObjeto == 0) {
                // se é 0 adiciona o objeto cano
                OBJETOS.add(new Objeto(canoIcon, distanciaObjetos * i, 472, "cano"));
                // adiciona uma moeda em cima
                OBJETOS.add(new Objeto(moedaIcon, distanciaObjetos * i, alturaMoeda, "moeda"));
            } else if (tipoObjeto == 1) {
                // se é 1 adiciona o objeto bloco
                OBJETOS.add(new Objeto(blocoIcon, distanciaObjetos * i, 472, "bloco"));
                // adiciona uma moeda em cima, nesse caso a moeda pode estar em cima ou em baixo do objeto
                tipoMoeda = r.nextInt(2);
                if (tipoMoeda == 0) {
                    alturaMoeda = 506;
                }
                OBJETOS.add(new Objeto(moedaIcon, distanciaObjetos * i, alturaMoeda, "moeda"));
            } else {
                // se é 2 adiciona o objeto bloco
                OBJETOS.add(new Objeto(foguete, distanciaObjetos * i, 472, "foguete"));
            }
        }
    }

    /**
     * Método responsável por desenhar os objetos adicionados a lista na tela
     * principal do jogo. Chama também os métodos que verificam a colisão entre
     * os personagens e os objetos dessa lista
     */
    private void desenharObjetos() {
        for (Objeto obj : OBJETOS) {
            obj.draw();
            obj.transitar();
            MARIO.verificarColisao(obj);
            LUIGI.verificarColisao(obj);
        }
    }

    /**
     * Método chamado a partir do start da thread, responsável pelo looping do jogo
     * Atualizando todos os objetos e personagens constantemente
     */
    public void run() {
        // define uma imagem de placar e uma fonte para as pontuações
        GameImage placar = new GameImage("imagens/placar.png");
        Font fonte = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 30);
        // acessa o teclado
        Keyboard teclado = JANELA.getKeyboard();
        // cria uma lista com 80 objetos
        this.adicionarObjetos();

        while (RODANDO) {
            // se pressionou ESC fecha a janela e sai do jogo
            if (teclado.keyDown(Keyboard.ESCAPE_KEY)) {
                JANELA.exit();
        	}

            // apresenta as layers do background
            FUNDO.drawLayers();
            // faz o fundo voltar as coordenadas iniciais
            FUNDO.repeatLayers(800, 600, true);
            // define a movimentação do fundo no eixo X para Esquerda
            FUNDO.moveLayersStandardX(true);

            // apresenta os jogadores e habilita suas movimentações
            MARIO.draw();
            MARIO.mover();
            LUIGI.draw();
            LUIGI.mover();

            // apresenta os objetos na tela
            this.desenharObjetos();

            // apresenta o placar na tela
            placar.x = 300;
            placar.draw();

            // verificações para deixar a pontuação centralizada a imagem do placar
            if (MARIO.getMoedas() < 10) {
                JANELA.drawText(Integer.toString(MARIO.getMoedas()), 344, 75, Color.ORANGE, fonte);
            } else {
                JANELA.drawText(Integer.toString(MARIO.getMoedas()), 338, 75, Color.ORANGE, fonte);
            }
            if (LUIGI.getMoedas() < 10) {
                JANELA.drawText(Integer.toString(LUIGI.getMoedas()), 490, 75, Color.ORANGE, fonte);
            } else {
                JANELA.drawText(Integer.toString(LUIGI.getMoedas()), 484, 75, Color.ORANGE, fonte);
            }

            JANELA.update();
            JANELA.delay(5);
        }
    }
}
