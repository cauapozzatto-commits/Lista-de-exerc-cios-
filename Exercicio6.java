class Cofre {
    private String senha;
    private int tentativasErradas;
    private boolean bloqueado;

    public Cofre(String senha) {
        this.senha = senha;
    }

    public boolean abrir(String tentativa) {
        // Cofre bloqueado recusa tudo, até a senha certa (é o que o teste do enunciado
        // mostra: depois do bloqueio, abrir("dragao42") devolve false).
        if (bloqueado) return false;
        if (senha.equals(tentativa)) {
            tentativasErradas = 0;
            return true;
        }
        tentativasErradas++;
        if (tentativasErradas >= 3) bloqueado = true;
        return false;
    }

    public boolean trocarSenha(String antiga, String nova) {
        if (bloqueado) return false;
        if (!senha.equals(antiga)) return false;
        if (nova == null || nova.length() < 6) return false;
        senha = nova;
        return true;
    }

    public boolean estaBloqueado() { return bloqueado; }
    // Sem getSenha(): a senha nunca sai da classe.
}

public class Exercicio6 {
    public static void main(String[] args) {
        Cofre c = new Cofre("dragao42");
        System.out.println(c.abrir("abc"));                      // false
        System.out.println(c.abrir("dragao42"));                 // true
        System.out.println(c.trocarSenha("dragao42", "123"));    // false
        c.abrir("x");
        c.abrir("y");
        c.abrir("z");
        System.out.println(c.estaBloqueado());                   // true
        System.out.println(c.abrir("dragao42"));                 // false
    }
}
