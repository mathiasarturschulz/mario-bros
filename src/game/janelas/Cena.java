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
    public static boolean rodando;

    /**
     * Construtor da classe
     */
    public Cena(Window JANELA) {
    	this.JANELA = JANELA;
        rodando = true;

        FUNDO = new Parallax();
        FUNDO.add("imagens/background.png");
        FUNDO.setVelAllLayers(1, 0);

        MARIO = new Jogador(1, "imagens/mario.png", 4, JANELA);
        LUIGI = new Jogador(2, "imagens/luigi.png", 4, JANELA);

        //Define as coordenadas iniciais de cada jogador
        MARIO.x = 30;
        MARIO.y = 240;
        LUIGI.x = 0;
        LUIGI.y = 240;

        OBJETOS = new ArrayList<>();
    }

    /**
     * Método responsável por criar uma lista de objetos que são adicionados de
     * forma aleatória na lista. OBS: Coordenadas X atribuídas a partir de
     * testes, pode melhorar. As moedas são adicionas de forma aleatória quando
     * um bloco é adicionado, podendo aparecer em cima do bloco, ou em baixo,
     * onde o personagem deve agachar para pegá-la. Quando um cano é adicionado,
     * a moeda só pode ser adicionada na parte superior
     */
    private void adicionarObjetos() {
        String moedaIcon = "imagens/moeda.png";
        String canoIcon = "imagens/cano.png";
        String blocoIcon = "imagens/bloco.png";

        Random r = new Random();
        int valor, valor2, moedaY;

        for (int i = 1; i <= 100; i++) {
            moedaY = 185;
            valor = r.nextInt(2);
            if (valor == 0) {
                OBJETOS.add(new Objeto(canoIcon, 800 * i, 230, "cano"));
            } else {
                valor2 = r.nextInt(2);
                OBJETOS.add(new Objeto(blocoIcon, 800 * i, 230, "bloco"));
                if (valor2 == 0) {
                    moedaY = 260;
                }
            }

            OBJETOS.add(new Objeto(moedaIcon, 800 * (i + 3), moedaY, "moeda"));
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
     * Método responsável pelo looping do jogo. Atualiza todos os objetos e
     * personagens constantemente
     */
    public void run() {
        //Define uma imagem de placar, assim como a fonte das pontuações
        GameImage placar = new GameImage("imagens/placar.png");
        Font fonte = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 30);
        // acessa o teclado
        Keyboard teclado = JANELA.getKeyboard();

        this.adicionarObjetos(); //Adiciona os sprites de objetos em uma lista

        while (rodando) {
            // se pressionou ESC fecha a janela e sai do jogo
            if (teclado.keyDown(Keyboard.ESCAPE_KEY)) {
                JANELA.exit();
        	}

            FUNDO.drawLayers(); //Desenha os layers criados, neste caso apenas 1
            FUNDO.repeatLayers(800, 600, true); //Faz o fundo voltar as coordenadas iniciais
            FUNDO.moveLayersStandardX(true); //Define a movimentação no eixo X para Esquerda

            //Desenha os jogadores e habilita a movimentação dos mesmos
            MARIO.draw();
            MARIO.mover();
            LUIGI.draw();
            LUIGI.mover();

            //Desenha os objetos na tela
            this.desenharObjetos();

            //Desenha o placar na parte superior da tela
            placar.x = 300;
            placar.draw();
            //Verificações feitas para deixar a pontuação centralizada a imagem do placar
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
