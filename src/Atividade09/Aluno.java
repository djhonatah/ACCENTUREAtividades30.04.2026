package Atividade09;

public class Aluno {

    private String nome;
    private int[] nota;

    public Aluno(String nome, int[] nota) {
        this.nome = validarNome(nome);
        this.nota = validarNotas(nota);
    }

    // METODOS - VALIDACAO

    private String validarNome(String nome) {
        String resultado = (nome != null && nome.trim().length() >= 3)
                ? nome.trim()
                : null;
        return (resultado != null) ? resultado : lancarErroNome();
    }

    private String lancarErroNome() {
        throw new IllegalArgumentException("ERRO! O nome deve ter no minimo 3 caracteres.");
    }

    private int[] validarNotas(int[] notas) {
        return (notas != null && notas.length == 3)
                ? validarIntervalo(notas)
                : lancarErroNotas();
    }

    private int[] lancarErroNotas() {
        throw new IllegalArgumentException("ERRO! O aluno deve ter exatamente 3 notas.");
    }

    private int[] validarIntervalo(int[] notas) {
        for (int i = 0; i < notas.length; i++) {
            int v = (notas[i] >= 0 && notas[i] <= 100) ? notas[i] : lancarErroNota(i + 1, notas[i]);
        }
        return notas;
    }

    private int lancarErroNota(int pos, int val) {
        throw new IllegalArgumentException("ERRO! A nota " + pos + " (" + val + ") deve estar entre 0 e 100.");
    }

    private static void validarTurma(Aluno[] alunos) {
        if (alunos == null || alunos.length == 0) {
            throw new IllegalArgumentException("ERRO! A turma deve ter pelo menos 1 aluno.");
        }
    }

    // GETTERS E SETTERS

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = validarNome(nome);
    }

    public int[] getNota() {
        return this.nota;
    }

    public void setNota(int[] nota) {
        this.nota = validarNotas(nota);
    }

    public double calcularMedia() {
        int soma = 0;
        for (int n : this.nota) {
            soma += n;
        }
        return soma / 3.0;
    }

    public String getSituacao() {
        double media = calcularMedia();
        return (media >= 70) ? "APROVADO" : (media >= 50) ? "RECUPERACAO" : "REPROVADO";
    }

    // RELATORIOS

    public void relatorioIndividual() {
        System.out.println(this.nome + " | Notas: " + nota[0] + ", " + nota[1] + ", " + nota[2]
                + " | Media: " + String.format("%.2f", calcularMedia()) + " | " + getSituacao());
    }

    public static void estatisticaTurma(Aluno[] alunos) {
        validarTurma(alunos);
        double maior = alunos[0].calcularMedia();
        double menor = alunos[0].calcularMedia();
        double soma = 0;
        for (int i = 0; i < alunos.length; i++) {
            double m = alunos[i].calcularMedia();
            soma += m;
            maior = (m > maior) ? m : maior;
            menor = (m < menor) ? m : menor;
        }

        System.out.println("     ESTATISTICAS DA TURMA");
        System.out.println();
        System.out.println("Maior media: " + String.format("%.2f", maior));
        System.out.println("Menor media: " + String.format("%.2f", menor));
        System.out.println("Media geral da turma: " + String.format("%.2f", soma / alunos.length));
        System.out.println("========================================");
    }

    public static void distribuicaoResultados(Aluno[] alunos) {
        validarTurma(alunos);
        int ap = 0, rec = 0, rep = 0;
        for (int i = 0; i < alunos.length; i++) {
            String s = alunos[i].getSituacao();
            ap += (s.equals("APROVADO")) ? 1 : 0;
            rec += (s.equals("RECUPERACAO")) ? 1 : 0;
            rep += (s.equals("REPROVADO")) ? 1 : 0;
        }

        System.out.println("     DISTRIBUICAO DE RESULTADOS");
        System.out.println();
        System.out.println("Aprovados: " + ap);
        System.out.println("Recuperacao: " + rec);
        System.out.println("Reprovados: " + rep);
        System.out.println("========================================");
    }

    public static void melhoresAlunos(Aluno[] alunos) {
        validarTurma(alunos);
        double maior = alunos[0].calcularMedia();
        for (int i = 1; i < alunos.length; i++) {
            double m = alunos[i].calcularMedia();
            maior = (m > maior) ? m : maior;
        }

        System.out.println("     MELHOR(ES) ALUNO(S)");
        System.out.println();
        System.out.println("Maior media: " + String.format("%.2f", maior));
        System.out.println("Aluno(s):");
        for (int i = 0; i < alunos.length; i++) {
            double m = alunos[i].calcularMedia();
            if (Math.abs(m - maior) < 0.01) {
                System.out.println("  -> " + alunos[i].getNome() + " (media: " + String.format("%.2f", m) + ")");
            }
        }
        System.out.println("========================================");
    }
}
