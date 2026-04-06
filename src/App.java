//Gabriel Barbosa de Souza - 10434547
//Lucas Osório Baldoino - 10434481
//Manuela Macchion Martedi - 10741587

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        ConexaoLeitor leitor = new ConexaoLeitor();
        Grafo g = leitor.CriarGrafo("conexoes.txt");

        if (g == null) {
            System.err.println("Erro ao carregar o grafo.");
            return;
        }

        Scanner teclado = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n========= MENU DO GRAFO =========");
            System.out.println("1 - Exibir informações gerais");
            System.out.println("2 - Exibir graus de um vértice");
            System.out.println("3 - Busca em largura (BFS) a partir de um vértice dado");
            System.out.println("4 - Busca em profundidade (DFS) a partir de um vértice dado");
            System.out.println("5 - Verificar se o dígrafo é fracamente conexo");
            System.out.println("6 - Verificar alcançabilidade entre dois vértices (BFS)");
            System.out.println("7 - Listar fontes e sumidouros");
            System.out.println("8 - Fecho transitivo de um vértice");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            if (!teclado.hasNextInt()) {
                System.out.println("Por favor, digite um número válido.");
                teclado.next();
                continue;
            }
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    g.infoGerais();
                    break;
                case 2:
                    System.out.print("Digite o índice do vértice (0 a " + (g.getNumV() - 1) + "): ");
                    if (teclado.hasNextInt()) {
                        g.bolinhasaladas(teclado.nextInt());
                    } else {
                        System.out.println("Vértice inválido.");
                        teclado.next();
                    }
                    break;
                case 3:
                    System.out.print("Digite o vértice de início (0 a " + (g.getNumV() - 1) + "): ");
                    if (teclado.hasNextInt()) {
                        g.bfs(teclado.nextInt());
                    } else {
                        System.out.println("Vértice inválido.");
                        teclado.next();
                    }
                    break;
                case 4:
                    System.out.print("Digite o vértice de início (0 a " + (g.getNumV() - 1) + "): ");
                    if (teclado.hasNextInt()) {
                        g.dfs(teclado.nextInt());
                    } else {
                        System.out.println("Vértice inválido.");
                        teclado.next();
                    }
                    break;
                case 5:
                    if (g.isFracamenteConexo()) {
                        System.out.println("O dígrafo é fracamente conexo.");
                    } else {
                        System.out.println("O dígrafo NÃO é fracamente conexo.");
                    }
                    break;
                case 6:
                    System.out.print("Digite o vértice de origem (u) (0 a " + (g.getNumV() - 1) + "): ");
                    if (teclado.hasNextInt()) {
                        int u = teclado.nextInt();
                        System.out.print("Digite o vértice de destino (v) (0 a " + (g.getNumV() - 1) + "): ");
                        if (teclado.hasNextInt()) {
                            int v = teclado.nextInt();

                            System.out.println("VERIFICANDO ALCANÇABILIDADE:");
                            // Sentido u -> v
                            if (g.isAlcancavel(u, v)) {
                                System.out.println("O vértice " + v + " é ALCANÇÁVEL a partir de " + u + " (u -> v).");
                            } else {
                                System.out.println("O vértice " + v + " NÃO é alcançável a partir de " + u + " (u -> v).");
                            }

                            // Sentido v -> u
                            if (g.isAlcancavel(v, u)) {
                                System.out.println("O vértice " + u + " é ALCANÇÁVEL a partir de " + v + " (v -> u).");
                            } else {
                                System.out.println("O vértice " + u + " NÃO é alcançável a partir de " + v + " (v -> u).");
                            }
                        } else {
                            System.out.println("Vértice de destino inválido.");
                            teclado.next();
                        }
                    } else {
                        System.out.println("Vértice de origem inválido.");
                        teclado.next();
                    }
                    break;
                case 7:
                    g.listarFontesESumidouros();
                    break;
                case 8:
                    System.out.print("Digite o vértice de origem (0 a " + (g.getNumV() - 1) + "): ");
                    if (teclado.hasNextInt()) {
                        g.fechoTransitivo(teclado.nextInt());
                    } else {
                        System.out.println("Vértice inválido.");
                        teclado.next();
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        teclado.close();
    }
}
