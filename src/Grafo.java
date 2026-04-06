//Gabriel Barbosa de Souza - 10434547
//Lucas Osório Baldoino - 10434481
//Manuela Macchion Martedi - 10741587

import java.util.Arrays;

public class Grafo {
    private int numV;
    private int numA;
    private int[][] matriz;
    private int[] gE;
    private int[] gS;

    public Grafo(int numV, int numA) {
        this.numV = numV;
        this.numA = numA;
        this.matriz = new int[numV][numV];
        this.gE = new int[numV];
        this.gS = new int[numV];
    }

    public int getNumV() {
        return numV;
    }

    public int getNumA() {
        return numA;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int i, int j, int valor) {
        matriz[i][j] = valor;
        if (valor == 1) {
            gE[j]++;
            gS[i]++;
        } else {
            gE[j]--;
            gS[i]--;
        }
    }

    public void infoGerais() {
        System.out.println("Cota de vértices: " + numV);
        System.out.println("Cota de arestas: " + numA);

        int minE = Arrays.stream(gE).min().orElse(0);
        int maxE = Arrays.stream(gE).max().orElse(0);
        double medE = Arrays.stream(gE).average().orElse(0.0);

        System.out.println("Entrada - min, max, media: " + minE + ", " + maxE + ", " + medE);

        int minS = Arrays.stream(gS).min().orElse(0);
        int maxS = Arrays.stream(gS).max().orElse(0);
        double medS = Arrays.stream(gS).average().orElse(0.0);

        System.out.println("Saida - min, max, media: " + minS + ", " + maxS + ", " + medS);
    }

    public void bolinhasaladas(int v) {
        if (v < 0 || v >= numV) {
            System.err.println("Erro: Vértice fora da faixa (0 a " + (numV - 1) + ").");
            return;
        }
        System.out.println("Grau de entrada (v=" + v + "): " + gE[v]);
        System.out.println("Grau de saida (v=" + v + "): " + gS[v]);
    }

    public void bfs(int vInicio) {
        if (vInicio < 0 || vInicio >= numV) {
            System.err.println("Erro: Vértice de início fora da faixa.");
            return;
        }

        boolean[] visitados = new boolean[numV];
        java.util.Queue<Integer> fila = new java.util.LinkedList<>();

        visitados[vInicio] = true;
        fila.add(vInicio);

        System.out.println("Caminho BFS partindo de " + vInicio + ":");
        while (!fila.isEmpty()) {
            int v = fila.poll();
            System.out.print(v + " ");

            for (int j = 0; j < numV; j++) {
                if (matriz[v][j] == 1 && !visitados[j]) {
                    visitados[j] = true;
                    fila.add(j);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int vInicio) {
        if (vInicio < 0 || vInicio >= numV) {
            System.err.println("Erro: Vértice de início fora da faixa.");
            return;
        }

        boolean[] visitados = new boolean[numV];
        System.out.println("Caminho DFS partindo de " + vInicio + ":");
        dfsRecursivo(vInicio, visitados);
        System.out.println();
    }

    private void dfsRecursivo(int v, boolean[] visitados) {
        visitados[v] = true;
        System.out.print(v + " ");

        for (int j = 0; j < numV; j++) {
            if (matriz[v][j] == 1 && !visitados[j]) {
                dfsRecursivo(j, visitados);
            }
        }
    }

    public boolean isFracamenteConexo() {
        if (numV == 0) return true;

        boolean[] visitados = new boolean[numV];
        java.util.Queue<Integer> fila = new java.util.LinkedList<>();

        visitados[0] = true;
        fila.add(0);

        int visitadosCount = 0;

        while (!fila.isEmpty()) {
            int v = fila.poll();
            visitadosCount++;

            for (int j = 0; j < numV; j++) {
                if (!visitados[j] && (matriz[v][j] == 1 || matriz[j][v] == 1)) {
                    visitados[j] = true;
                    fila.add(j);
                }
            }
        }

        return visitadosCount == numV;
    }

    public boolean isAlcancavel(int de, int para) {
        if (de < 0 || de >= numV || para < 0 || para >= numV) {
            return false;
        }

        if (de == para) return true;

        boolean[] visitados = new boolean[numV];
        java.util.Queue<Integer> fila = new java.util.LinkedList<>();

        visitados[de] = true;
        fila.add(de);

        while (!fila.isEmpty()) {
            int v = fila.poll();

            for (int j = 0; j < numV; j++) {
                if (matriz[v][j] == 1) {
                    if (j == para) return true;
                    if (!visitados[j]) {
                        visitados[j] = true;
                        fila.add(j);
                    }
                }
            }
        }

        return false;
    }

    public void listarFontesESumidouros() {
        System.out.println("Status de cada vértice (Fonte/Sumidouro):");
        for (int i = 0; i < numV; i++) {
            String status = "Comum";
            if (gE[i] == 0 && gS[i] > 0) {
                status = "Fonte";
            } else if (gS[i] == 0 && gE[i] > 0) {
                status = "Sumidouro";
            } else if (gE[i] == 0 && gS[i] == 0) {
                status = "Vértice Isolado (Fonte e Sumidouro)";
            }
            System.out.println("Vértice " + i + ": " + status + " (gE=" + gE[i] + ", gS=" + gS[i] + ")");
        }
    }

    public void fechoTransitivo(int vInicio) {
        if (vInicio < 0 || vInicio >= numV) {
            System.err.println("Erro: Vértice fora da faixa.");
            return;
        }

        boolean[] visitados = new boolean[numV];
        java.util.Queue<Integer> fila = new java.util.LinkedList<>();

        visitados[vInicio] = true;
        fila.add(vInicio);

        System.out.println("Fecho transitivo de " + vInicio + ":");
        while (!fila.isEmpty()) {
            int v = fila.poll();
            // Para o fecho transitivo, apenas listamos os que são alcançáveis.
            // O vértice inicial já está marcado como visitado.

            for (int j = 0; j < numV; j++) {
                if (matriz[v][j] == 1 && !visitados[j]) {
                    visitados[j] = true;
                    fila.add(j);
                }
            }
        }

        boolean primeiro = true;
        for (int i = 0; i < numV; i++) {
            if (visitados[i]) {
                if (!primeiro) System.out.print(", ");
                System.out.print(i);
                primeiro = false;
            }
        }
        System.out.println();
    }
}
