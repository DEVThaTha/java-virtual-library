
    public class ArvoreLivros {

    private class No {
        Livro livro;
        No esquerda;
        No direita;

        No(Livro livro) {
            this.livro = livro;
            this.esquerda = null;
            this.direita = null;
        }
    }

    private No raiz;

    public void inserir(Livro livro) {
        raiz = inserirRecursivo(raiz, livro);
    }

    private No inserirRecursivo(No atual, Livro livro) {
        if (atual == null) {
            return new No(livro);
        }

        if (livro.titulo.compareToIgnoreCase(atual.livro.titulo) < 0) {
            atual.esquerda = inserirRecursivo(atual.esquerda, livro);
        } else if (livro.titulo.compareToIgnoreCase(atual.livro.titulo) > 0) {
            atual.direita = inserirRecursivo(atual.direita, livro);
        }

        return atual;
    }

    public boolean buscar(String titulo) {
        return buscarRecursivo(raiz, titulo);
    }

    private boolean buscarRecursivo(No atual, String titulo) {
        if (atual == null) {
            return false;
        }

        if (titulo.equalsIgnoreCase(atual.livro.titulo)) {
            return true;
        }

        if (titulo.compareToIgnoreCase(atual.livro.titulo) < 0) {
            return buscarRecursivo(atual.esquerda, titulo);
        } else {
            return buscarRecursivo(atual.direita, titulo);
        }
    }

    public void mostrarEmOrdem() {
        mostrarEmOrdemRecursivo(raiz);
    }

    private void mostrarEmOrdemRecursivo(No atual) {
        if (atual != null) {
            mostrarEmOrdemRecursivo(atual.esquerda);
            atual.livro.exibirInformacoes();
            mostrarEmOrdemRecursivo(atual.direita);
        }
    }
}
