# Projeto de Análise de Grafos (Dígrafos)

Este projeto consiste em uma implementação em Java para o estudo e a representação de **Grafos Direcionados** (também chamados de **Dígrafos**). Através dessa aplicação, o repositório lê as conexões dispostas no arquivo textual e oferece algumas das operações mais conhecidas na Teoria dos Grafos.

**Equipe:**
* Gabriel Barbosa de Souza - 10434547
* Lucas Osório Baldoino - 10434481
* Manuela Macchion Martedi - 10741587

---

## 📚 Teoria e Funcionalidades (O que cada opção faz?)

O sistema apresenta um menu iterativo listando funcionalidades matemáticas e computacionais a respeito do Grafo carregado na memória. Como se trata de um Dígrafo, **todas as arestas têm direção** (uma aresta que vai do vértice A para B *não implica* que podemos voltar de B para A por essa mesma via de acesso). Abaixo está o embasamento rigoroso e a explicação de uso para cada uma delas:

### 1 - Exibir informações gerais
Ao executar essa opção, você obterá uma visão panorâmica das dimensões e estatísticas do seu grafo.
* **Ordem e Tamanho:** Na teoria, a respectiva quantidade de vértices (nodos) de um dígrafo é a sua **Cota (Ordem)**, enquanto a quantidade de conexões direcionadas que os ligam expressa o seu **Tamanho** (Cota de arestas). 
* **Graus Globais:** Para analisar a densidade e o fluxo, faz-se as métricas estatísticas — Mínimo, Máximo e a Média do Grau de Entrada *(arestas chegando aos vértices)* e Grau de Saída *(arestas saindo deles)*. Isso ajuda a responder perguntas como: *\"Os vértices são todos perfeitamente equilibrados em conexões limitadas na mesma faixa de conectividade?\"*

### 2 - Exibir graus de um vértice
Permite selecionar um vértice $V$ qualquer na matriz de escopo e analisa localmente o que de fato acontece em torno dele:
* **Grau de Entrada ($deg^{-}(v)$):** O número de arestas que "apontam" para o nosso vértice (linhas de fora indo para ele).
* **Grau de Saída ($deg^{+}(v)$):** O número de arestas emitidas pelo vértice (saindo dele indo para alheios).

### 3 - Busca em Largura (BFS - Breadth-First Search)
* **Como Funciona:** É como jogar uma pedra num lago e ver as ondulações. A partir de um vértice raiz, ele explora todos os vizinhos de **distância 1** (aqueles diretamente conectados a ele com 1 aresta), logo em seguida avança para os vizinhos de **distância 2** e assim por diante.
* **Teoria Computacional:** Ele adota uma estrutura de **Fila (Queue - FIFO: First-In, First-Out)** internamente. 
* **Caso de Uso:** É o algoritmo fundamental para a descoberta do caminho mais curto num grafo em que as arestas não têm peso distinto (cada aresta vale 1 "salto").

### 4 - Busca em Profundidade (DFS - Depth-First Search)
* **Como Funciona:** A busca é o equivalente a se jogar num labirinto com a mão presa na parede e descer por cada galeria escura "o mais longe e profundo que puder", até atingir um beco (onde não houver vértices adjacentes ainda não visitados). Depois ele faz o retrocesso (*backtracking*) e avalia vias alternativas.
* **Teoria Computacional:** Se aplica uma travessia baseada fortemente no uso de **Pilha (Stack)**, que no programa é solucionada organicamente e de maneira bela com o uso de **Recursividade**. É a base matemática de algoritmos topológicos.

### 5 - Verificar se o dígrafo é fracamente conexo
* **Dígrafo Fortemente vs Fracamente Conexo:**
   * **Fortemente conexo**: Se conseguirmos chegar em todos os nós saindo de todo e qualquer lugar respeitando completamente a "mão das vias únicas".
   * **Fracamente conexo (O que ele checa)**: O que aconteceria se fingíssemos que as arestas são ruas regulares bidirecionais (ignorando a direção imposta pelas flechas originais). Se com isso removido existe sequer algum meio de haver percurso garantindo a ligação contínua de todo o grafo, ele é classificado matematicamente pela teoria de grafos como **"fracamente conexo"**.

### 6 - Verificar alcançabilidade entre dois vértices
Diga ao grafo por onde você gostaria de começar ( $u$ ) e para onde gostaria de ir ( $v$ ) no meio deste emaranhado:
* **Direcionalidade:** A teoria de dígrafos exige que vejamos a possibilidade de trânsito em ambas as polaridades. Logo, o sistema verifica internamente tanto as trajetórias $u \rightarrow v$ (é possível sair de u e cruzar vias para aportar no nó destino v?), e imediatamente após o seu oposto conceitual $v \rightarrow u$. Se você alcança, não significa dizer que o nó destino alcança você de volta.

### 7 - Listar fontes e sumidouros
O programa fará a classificação anatômica de cada parte baseada num papel especial em termos de fluxo:
* **Fonte (Source):** Vértice que possui *apenas saídas* e nenhuma entrada ( $deg^{-}(v) = 0$ ). Ele se comporta apenas como emissor na rede. 
* **Sumidouro (Sink):** Vértice que possui *apenas entradas* e zero saídas ( $deg^{+}(v) = 0$ ). Uma vez que você atingir este nó caminhando com setas, não poderá mais sair, ele funciona como um sorvedouro de dados/objetos.
* **Vértice Isolado:** Grau de entrada e saída nulos; um pedaço "flutuante" sem caminhos atrelados (também listado).  

### 8 - Fecho transitivo de um vértice
* **Teoria:** O Fecho Transitivo (*Transitive Closure*) nada mais é do que expor todo o **conjunto de influências espaciais** resultantes de um nó inicial no cenário subjacente.
* **Ação Didática:** O software lista, partindo do vértice escolhido (ex. Nó 0), **todos os nós que são acessáveis desde este local utilizando qualquer quantidade infinita de conexões intermediárias.** Na teoria original, a matriz de adjacência de fecho se torna "1" caso possuir alcançabilidade.
