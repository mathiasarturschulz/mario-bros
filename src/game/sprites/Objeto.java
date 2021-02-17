package game.sprites;

import jplay.Sprite;

/**
* Classe que representa a entidade objeto
*/
public class Objeto extends Sprite {

    private final String NOME;
    private long velocidade;
    private int contador;

    public Objeto(String path, int x, int y, String nomeObj) {
        super(path);
        this.x = x;
        this.y = y;

        NOME = nomeObj;
        // velocidade inicial
        velocidade = 3;
        contador = 0; //Define um contador auxiliar para incremento de velocidade
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
    }

    public String getNome() {
        return NOME;
    }
}
