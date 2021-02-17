package game.sprites;

import jplay.Sprite;

/**
* Classe que representa a entidade objeto
*/
public class Objeto extends Sprite {

    private final String NOME;
    private long velocidade;
    private int contador;
    // atributo para aumentar a velocidade do foquete quando estiver perto dos personagens
    boolean ativouTurbo = false;

    /**
     * Construtor
     */
    public Objeto(String path, int x, int y, String nomeObj) {
        super(path);
        this.x = x;
        this.y = y;

        NOME = nomeObj;
        // velocidade inicial
        this.velocidade = 3;
        // contador auxiliar para incremento de velocidade
        contador = 0;
    }

    /**
     * Método responsável por aumentar a velocidade em que os objetos transitam
     * na tela da direita para a esquerda.
     * Foi definido um valor qualquer de "tempo" para incremento de velocidade
     */
    private void aumentarVelocidade() {
        contador++;
        if (contador == 1000) {
            if (velocidade < 10) {
                velocidade++;
            }
            contador = 0;
        }
    }

    /**
     * Método responsável pela movimentação dos objetos no tela, da direita
     * para esquerda com velocidade aumentada ao passar tempo.
     */
    public void transitar() {
        this.x -= velocidade;
        this.aumentarVelocidade();
        // se o objeto é o foguete e está chegando no jogador a velocidade aumenta
        if (this.NOME == "foguete" && this.x < 800 && !this.ativouTurbo) {
            this.velocidade += 2;
            this.ativouTurbo = true;
        }
    }

    /**
     * Retorna o nome do objeto
     * @return
     */
    public String getNome() {
        return NOME;
    }
}
