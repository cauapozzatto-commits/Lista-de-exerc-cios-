import java.util.Scanner;

class Fila {
    private String[] nomes = new String[3];
    private int inicio;
    private int fim;          // próxima posição livre
    private int quantidade;

    public boolean entrar(String nome) {
        if (quantidade == nomes.length) return false;
        nomes[fim] = nome;
        fim = (fim + 1) % nomes.length;      // volta para 0 no fim do array
        quantidade++;
        return true;
    }

    public String atender() {
        if (estaVazia()) return null;
        String nome = nomes[inicio];
        nomes[inicio] = null;
        inicio = (inicio + 1) % nomes.length;
        quantidade--;
        return nome;
    }

    public String proximo() {
        return estaVazia() ? null : nomes[inicio];
    }

    public int tamanho() { return quantidade; }

    public boolean estaVazia() { return quantidade == 0; }
}

public class Exercicio9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Fila fila = new Fila();

        while (sc.hasNextLine()) {
            String linha = sc.nextLine().trim();
            if (linha.isEmpty()) continue;
            if (linha.equals("FIM")) break;

            String[] partes = linha.split("\\s+", 2);
            String cmd = partes[0];

            switch (cmd) {
                case "ENTRA":
                    if (partes.length < 2) { System.out.println("Informe o nome"); break; }
                    if (fila.entrar(partes[1])) System.out.println(partes[1] + " entrou na fila");
                    else System.out.println("Fila cheia: " + partes[1] + " não entrou");
                    break;
                case "ATENDE":
                    String a = fila.atender();
                    System.out.println(a == null ? "Fila vazia" : "Atendendo: " + a);
                    break;
                case "PROXIMO":
                    String p = fila.proximo();
                    System.out.println(p == null ? "Fila vazia" : "Próximo: " + p);
                    break;
                case "TAMANHO":
                    System.out.println("Pessoas na fila: " + fila.tamanho());
                    break;
                default:
                    System.out.println("Comando desconhecido: " + cmd);
            }
        }
    }
}
