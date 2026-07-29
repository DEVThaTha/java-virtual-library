import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        LinkedList<Livro> biblioteca = new LinkedList<>();
        Queue<String> filaEspera = new LinkedList<>();
        Stack<Livro> historico = new Stack<>();

        Livro livro1 = new Livro("Cleópatra", "Stacy Schiff", 2010);
        Livro livro2 = new Livro("Ada's Algorithm", "James Essinger", 2014);
        Livro livro3 = new Livro("Joana d'Arc", "Helen Castor", 2015);
        Livro livro4 = new Livro("Marie Curie", "Éve Curie", 1937);
        Livro livro5 = new Livro("Rosa Parks", "Douglas Brinkley", 2000);
        Livro livro6 = new Livro("Frida", "Hayden Herrera", 1983);
        Livro livro7 = new Livro("Dandara e os Palmares", "Jarid Arraes", 2021);
        Livro livro8 = new Livro("Elizabeth I", "Margaret George", 2011);
        Livro livro9 = new Livro("O Diário de Anne Frank", "Anne Frank", 1947);
        Livro livro10 = new Livro("Memórias de uma Moça Bem-Comportada", "Simone de Beauvoir", 1958);

        biblioteca.add(livro1);
        biblioteca.add(livro2);
        biblioteca.add(livro3);
        biblioteca.add(livro4);
        biblioteca.add(livro5);
        biblioteca.add(livro6);
        biblioteca.add(livro7);
        biblioteca.add(livro8);
        biblioteca.add(livro9);
        biblioteca.add(livro10);

        filaEspera.add("Thais");
        filaEspera.add("Louize");
        filaEspera.add("Thitha");

        historico.push(livro1);
        historico.push(livro3);
        historico.push(livro5);

        GrafoLivros grafoLivros = new GrafoLivros();

        for (Livro livro : biblioteca) {
            grafoLivros.adicionarLivro(livro);
        }

        grafoLivros.adicionarRecomendacao(livro1, livro2);
        grafoLivros.adicionarRecomendacao(livro1, livro8);

        grafoLivros.adicionarRecomendacao(livro2, livro4);
        grafoLivros.adicionarRecomendacao(livro2, livro10);

        grafoLivros.adicionarRecomendacao(livro3, livro7);
        grafoLivros.adicionarRecomendacao(livro3, livro5);

        grafoLivros.adicionarRecomendacao(livro4, livro2);
        grafoLivros.adicionarRecomendacao(livro4, livro6);

        grafoLivros.adicionarRecomendacao(livro5, livro3);
        grafoLivros.adicionarRecomendacao(livro5, livro7);

        grafoLivros.adicionarRecomendacao(livro6, livro10);
        grafoLivros.adicionarRecomendacao(livro6, livro4);

        grafoLivros.adicionarRecomendacao(livro7, livro5);
        grafoLivros.adicionarRecomendacao(livro7, livro3);

        grafoLivros.adicionarRecomendacao(livro8, livro1);
        grafoLivros.adicionarRecomendacao(livro8, livro4);

        grafoLivros.adicionarRecomendacao(livro9, livro5);
        grafoLivros.adicionarRecomendacao(livro9, livro10);

        grafoLivros.adicionarRecomendacao(livro10, livro6);
        grafoLivros.adicionarRecomendacao(livro10, livro9);

        System.out.println("=== Biblioteca Virtual - Sistema de Recomendação com Djikstra ===");
        System.out.println();

        System.out.println("Primeira pessoa da fila de espera: " + filaEspera.poll());
        System.out.println();

        System.out.println("Último livro visualizado no histórico:");
        historico.peek().exibirInformacoes();

        System.out.println("Sugestões baseadas na última leitura:");
        grafoLivros.mostrarRecomendacoes(historico.peek());

        System.out.println("Grafo completo de recomendações:");
        grafoLivros.mostrarGrafoCompleto();

        System.out.println();
        System.out.println("=== Distâncias usando Djikstra ===");

        Map<Livro, Integer> distancias =
                GrafoLivros.djikstraSimples(grafoLivros.getGrafo(), livro5);

        for (Map.Entry<Livro, Integer> entrada : distancias.entrySet()) {
            System.out.println(
                    entrada.getKey().titulo +
                    " -> Distância: " +
                    entrada.getValue()
            );
        }
    }
}