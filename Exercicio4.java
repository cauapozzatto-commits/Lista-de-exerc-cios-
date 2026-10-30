class Pilha {
    private int[] dados;
    private int topo;   // quantidade de elementos; próximo índice livre

    public Pilha(int capacidade) {
        dados = new int[Math.max(1, capacidade)];
        topo = 0;
    }

    public boolean empilhar(int x) {
        if (estaCheia()) return false;
        dados[topo++] = x;
        return true;
    }

    public int desempilhar() {
        if (estaVazia()) {
            System.out.println("Aviso: pilha vazia!");
            return -1;
        }
        return dados[--topo];
    }

    public int espiar() {
        if (estaVazia()) {
            System.out.println("Aviso: pilha vazia!");
            return -1;
        }
        return dados[topo - 1];
    }

    public boolean estaVazia() { return topo == 0; }

    public boolean estaCheia() { return topo == dados.length; }

    public int tamanho() { return topo; }
}

// Extra: versão com char para verificar parênteses balanceados
class PilhaChar {
    private char[] dados;
    private int topo;

    public PilhaChar(int capacidade) {
        dados = new char[Math.max(1, capacidade)];
    }

    public boolean empilhar(char c) {
        if (topo == dados.length) return false;
        dados[topo++] = c;
        return true;
    }

    public char desempilhar() { return dados[--topo]; }

    public boolean estaVazia() { return topo == 0; }
}

public class Exercicio4 {
    static boolean balanceada(String expr) {
        PilhaChar p = new PilhaChar(expr.length() + 1);
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(') {
                p.empilhar(c);
            } else if (c == ')') {
                if (p.estaVazia()) return false;
                p.desempilhar();
            }
        }
        return p.estaVazia();
    }

    public static void main(String[] args) {
        Pilha p = new Pilha(3);
        p.empilhar(10);
        p.empilhar(20);
        p.empilhar(30);
        System.out.println(p.empilhar(40));      // false
        System.out.println(p.desempilhar());     // 30
        System.out.println(p.espiar());          // 20
        System.out.println(p.tamanho());         // 2

        System.out.println(balanceada("(a+b)*(c-d))")); // false
        System.out.println(balanceada("(a+b)*(c-d)"));  // true
    }
}
