package game.sprites;

import game.recursos.Audio;
import java.awt.event.KeyEvent;
import game.janelas.Final;
import game.recursos.Pontuacao;
import jplay.Keyboard;
import jplay.Sprite;
import jplay.Window;

public class Jogador extends Sprite {

    private final Keyboard TECLADO;
    private final Pontuacao PONTUACAO;
    private final int ID, FREQUENCIA;
    private int contador;

    public Jogador(int id, String path, int frames, Window janela) {
        super(path, frames);

        PONTUACAO = new Pontuacao(janela);

        TECLADO = janela.getKeyboard();
        //Adiciona teclas não padrões do JPlay Keyboard
        TECLADO.addKey(KeyEvent.VK_A, Keyboard.DETECT_EVERY_PRESS);
        TECLADO.addKey(KeyEvent.VK_D, Keyboard.DETECT_EVERY_PRESS);
        TECLADO.addKey(KeyEvent.VK_W, Keyboard.DETECT_EVERY_PRESS);
        TECLADO.addKey(KeyEvent.VK_S, Keyboard.DETECT_EVERY_PRESS);

        ID = id;
        
        /**
         * Define uma frequência e inicializa um contador, utilizados para auxiliar
         * na troca de frames dos personagens ao correr
         */
        FREQUENCIA = 25;
        contador = 0;

        /**
         * Define o chão dos personagens. É necessário quando se utiliza o
         * método jump(), para que o personagem não ultrapasse esse valor quando
         * cair do pulo. É também utilizado no momento em que o personagem se
         * agacha
         */
        this.setFloor(233 + this.height);
        this.setJumpVelocity(4.6); //Define a velocidade/altura do pulo
    }

    /**
     * Método responsável por alterar os frames do jogador, dando o efeito de
     * corrida ao se movimentar. Alterna entre dois frames constantemente: 0-24
     * no frame 0 / 25-49 no frame 1 e reseta no 50.
     */
    public void alterarFrame() {
        if (contador < FREQUENCIA) {
            this.setCurrFrame(0);
        } else {
            this.setCurrFrame(1);
        }
        if (contador == 2 * FREQUENCIA) {
            contador = 0;
        }
        contador++;
    }

    /**
     * Método responsável pela movimentação dos jogadores Ações possíveis: andar
     * para direita, esquerda, pular e agachar. 
     * Troca os frames de pular e agachar 
     * (AJUSTAR: colocar essa troca no método alterarFrame()).
     */
    int aux = 0; //Variavel auxiliar para o audio do pulo
    public void mover() {
        if (ID == 1) {
            //Define os movimentos possíveis para o mario = ID 1            
            //Define as teclas de movimento no eixo X, assim como a velocidade
            this.moveX(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY, 2);
            this.jump(Keyboard.UP_KEY);
            if (TECLADO.keyDown(Keyboard.DOWN_KEY) && !this.isJumping()) {
                this.y += 30;
                this.setCurrFrame(3);
            } else {
                if (this.isJumping()) {
                    this.setCurrFrame(2);
                } else {
                    this.alterarFrame();
                }
            }
        } else {
            //Define os movimentos possíveis para o luigi = ID 2
            this.moveX(KeyEvent.VK_A, KeyEvent.VK_D, 2);
            this.jump(KeyEvent.VK_W);
            if (TECLADO.keyDown(KeyEvent.VK_S) && !this.isJumping()) {
                this.y += 30;
                this.setCurrFrame(3);
            } else {
                if (this.isJumping()) {
                    this.setCurrFrame(2);
                } else {
                    this.alterarFrame();
                }
            }
        }
        
        /**
         * Verificação auxiliar para que o áudio do pulo não seja repetido enquanto
         * o personagem estiver no ar, e sim tocado apenas uma vez
         */
        if (isJumping()) {
            if (aux == 0) {
                Audio.pulou = true;
                aux++;
            }
        } else {
            aux = 0;
        }
    }

    /**
     * Métidi responsável por verificar a colisão entre os personagens e os objetos
     * na tela. Caso o personagem colida com uma moeda, adiciona um ponto a ele.
     * Caso o personagem colida com algum outro objeto, vai para a janela final,
     * passando o ID do adversário como vencedor.
     * obj.setX(-100) é definido para "remover" a moeda da tela assim que um jogador
     * a pegue.
     */
    public void verificarColisao(Objeto obj) {
        if (this.collided(obj)) {
            if (obj.getNome().equals("moeda")) {
                Audio.pegou = true;
                PONTUACAO.adicionarPontos(this);
                obj.setX(-100);
            } else {
                Audio.bateu = true;
                if (ID == 1) {
                    new Final(2);
                } else {
                    new Final(1);
                }
            }
        }
    }

    public int getMoedas() {
        return PONTUACAO.getMoedas();
    }

    public int getID() {
        return ID;
    }
}
