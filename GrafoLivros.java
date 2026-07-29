import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GrafoLivros {

    private HashMap<Livro, Set<Livro>> grafo;

    public GrafoLivros() {
        grafo = new HashMap<>();
    }

    public void adicionarLivro(Livro livro) {
        grafo.putIfAbsent(livro, new HashSet<>());
    }

    public void adicionarRecomendacao(Livro livro, Livro recomendado) {
        grafo.putIfAbsent(livro, new HashSet<>());
        grafo.putIfAbsent(recomendado, new HashSet<>());
        grafo.get(livro).add(recomendado);
    }

    public HashMap<Livro, Set<Livro>> getGrafo() {
        return grafo;
    }

    public static Map<Livro, Integer> djikstraSimples(HashMap<Livro, Set<Livro>> grafo, Livro origem) {
        Map<Livro, Integer> distancias = new HashMap<>();
        Queue<Livro> fila = new LinkedList<>();

        distancias.put(origem, 0);
        fila.add(origem);

        while (!fila.isEmpty()) {
            Livro atual = fila.poll();
            int distanciaAtual = distancias.get(atual);

            for (Livro vizinho : grafo.getOrDefault(atual, new HashSet<>())) {
                if (!distancias.containsKey(vizinho)) {
                    distancias.put(vizinho, distanciaAtual + 1);
                    fila.add(vizinho);
                }
            }
        }

        return distancias;
    }

    public void mostrarRecomendacoes(Livro livro) {
        System.out.println("Recomendações para quem leu: " + livro.titulo);

        Set<Livro> recomendacoes = grafo.get(livro);

        if (recomendacoes == null || recomendacoes.isEmpty()) {
            System.out.println("Nenhuma recomendação encontrada.");
        } else {
            for (Livro recomendado : recomendacoes) {
                recomendado.exibirInformacoes();
            }
        }
    }

    public void mostrarGrafoCompleto() {
        for (Map.Entry<Livro, Set<Livro>> entrada : grafo.entrySet()) {
            System.out.println("Livro: " + entrada.getKey().titulo);
            System.out.println("Recomendações:");

            for (Livro recomendado : entrada.getValue()) {
                System.out.println("- " + recomendado.titulo);
            }

            System.out.println("--------------------------");
        }
    }
}