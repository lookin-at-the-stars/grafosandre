import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConexaoLeitor {
    public Grafo CriarGrafo(String arq) {
        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
            String l = br.readLine();
            if (l == null) return null;
            
            String[] p = l.split(" ");
            int v = Integer.parseInt(p[0]);
            int a = Integer.parseInt(p[1]);
            Grafo g = new Grafo(v, a);
            
            while ((l = br.readLine()) != null) {
                if (l.trim().isEmpty()) continue;
                p = l.split(" ");
                int i = Integer.parseInt(p[0]);
                int j = Integer.parseInt(p[1]);
                g.setMatriz(i, j, 1);
            }
            return g;
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        ConexaoLeitor leitor = new ConexaoLeitor();
        Grafo g = leitor.CriarGrafo("conexoes.txt");
        if (g != null) {
            g.infoGerais();
        }
    }
}
