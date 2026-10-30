import java.util.ArrayList;

class Item {
    private final String nome;
    private final double peso;

    public Item(String nome, double peso) {
        this.nome = nome;
        this.peso = peso;
    }

    public String getNome() { return nome; }

    public double getPeso() { return peso; }
}

class Inventario {
    private static final int MAX_ITENS = 5;
    private static final double MAX_PESO = 20.0;

    private ArrayList<Item> itens = new ArrayList<>();

    public boolean adicionar(Item i) {
        if (i == null || i.getPeso() < 0) return false;
        if (itens.size() >= MAX_ITENS) return false;
        if (pesoTotal() + i.getPeso() > MAX_PESO) return false;
        itens.add(i);
        return true;
    }

    public boolean remover(String nome) {
        for (int k = 0; k < itens.size(); k++) {
            if (itens.get(k).getNome().equals(nome)) {
                itens.remove(k);
                return true;
            }
        }
        return false;
    }

    public double pesoTotal() {
        double total = 0;
        for (Item i : itens) total += i.getPeso();
        return total;
    }

    public void listar() {
        for (Item i : itens) {
            System.out.println("- " + i.getNome() + " (" + i.getPeso() + " kg)");
        }
    }

    // Não existe getItens() de propósito: devolver a lista interna daria a quem
    // está fora uma referência para o mesmo ArrayList. Com ela, o main poderia
    // fazer getItens().add(...) e ultrapassar 5 itens ou 20 kg, quebrando a
    // invariante sem passar pelas regras de adicionar().
}

public class Exercicio5 {
    public static void main(String[] args) {
        Inventario inv = new Inventario();
        inv.adicionar(new Item("Espada", 8));
        inv.adicionar(new Item("Escudo", 7));
        inv.adicionar(new Item("Poção", 1));
        System.out.println(inv.adicionar(new Item("Armadura", 10))); // false
        System.out.println(inv.pesoTotal());                         // 16.0
        inv.listar();
    }
}
