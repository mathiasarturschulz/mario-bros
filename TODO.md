# Lista de melhorias para o game:

- [x] Analisar e remover o erro: Resolution is not compatible with this display (erro_resolution.png)

Este erro já aparece antes de iniciar o jogo e continua aparecendo constantemente na tela (Não sei se é um erro apenas na minha máquina)

Pesquisando achei um método da classe Windows para resolver esse problema, chamado:
JANELA.getCompatibleDisplayMode();

Obs: Se setar a resolução como menor que 800x600 continuará gerando o erro

- [x] Atualizar as telas do game (erro_multiplas_telas.png)

Quando o jogo está rodando e inicia uma nova tela não remove a antiga
Por exemplo: Mario/Luigi wins é uma nova tela que fica aberta em segundo plano e não é excluida/substituida

O jogo possui 3 telas:
Main.java  -> Tela de início do jogo
Cena.java  -> Tela do jogo
Final.java -> Tela de apresentação do ganhador
Todas essas telas quando utilizadas eram abertas em uma nova aba

- [x] Analisar e implementar um botão para fechar o jogo
Ao pressionar ESC fecha o jogo

- [x] Atualizar a resolução do jogo
A nova resolução é 800x600, dessa forma foi necessário atualizar as imagens de fundo, entretanto a imagem de fundo na tela principal deve ter a resolução que foi setada na classe Window, o que tornou uma etapa trabalhosa pois foi necessário desenvolver uma imagem 800x600
Com a nova resolução também foi necessário atualizar as localizações dos persongens e objetos na tela

- [x] Corrigido bug de aparecer dois objetos juntos
Pelo o que eu entendi aparece dois objetos de uma vez porque ocorreu um bug
Todos os objetos são gerados antes de iniciar o jogo, como eram gerados 100 objetos a distância dos últimos objetos estava quebrando a biblioteca e setando eles em uma distância não definida pelo usuário e as vezes sem validação de colisão
Um exemplo disso é quando tem um bloco e depois o cano, se passar abaixado entre os dois não resulta erro

- [x] Adicionar um novo objeto, que é o foguete
Ele é um pouco mais rápido que outros objetos

- [x] Analisar a utilização das threads e encontrar possíveis melhorias

- [x] Melhorar a documentação do projeto

- [x] Atualizado o audio de fundo e corrigido um erro de que não apresentava o som de pulo
