package Atividade09;

public class AlunoPrincipal {

    public static void main(String[] args) {

        Aluno maria = new Aluno("Maria", new int[] { 80, 70, 90 });
        Aluno joao = new Aluno("Joao", new int[] { 50, 60, 55 });
        Aluno ana = new Aluno("Ana Paula", new int[] { 30, 40, 35 });
        Aluno pedro = new Aluno("Pedro", new int[] { 90, 85, 95 });
        Aluno lucia = new Aluno("Lucia", new int[] { 80, 70, 90 });

        Aluno[] turma = { maria, joao, ana, pedro, lucia };

        System.out.println("\n--- Relatorio Individual ---");
        for (int i = 0; i < turma.length; i++) {
            turma[i].relatorioIndividual();
        }

        System.out.println();
        Aluno.estatisticaTurma(turma);
        System.out.println();
        Aluno.distribuicaoResultados(turma);
        System.out.println();
        Aluno.melhoresAlunos(turma);

        // TESTES DE EXCECAO
        System.out.println("\n TESTES DE EXCECAO \n");

        // NOME COM MENOS DE 3 CARACTERES
        System.out.println("Teste 1: Nome com menos de 3 caracteres");
        try {
            new Aluno("AB", new int[] { 80, 70, 90 });
            System.out.println("FALHOU");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - " + e.getMessage());
        }

        // NOME NULO
        System.out.println("\nTeste 2: Nome nulo");
        try {
            new Aluno(null, new int[] { 80, 70, 90 });
            System.out.println("FALHOU");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - " + e.getMessage());
        }

        // MENOS DE 3 NOTAS
        System.out.println("\nTeste 3: Menos de 3 notas");
        try {
            new Aluno("Carlos", new int[] { 80, 70 });
            System.out.println("FALHOU");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - " + e.getMessage());
        }

        // NOTA ACIMA DE 100
        System.out.println("\nTeste 4: Nota acima de 100");
        try {
            new Aluno("Carlos", new int[] { 80, 150, 90 });
            System.out.println("FALHOU");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - " + e.getMessage());
        }

        // NOTA NEGATIVA
        System.out.println("\nTeste 5: Nota negativa");
        try {
            new Aluno("Carlos", new int[] { 80, -10, 90 });
            System.out.println("FALHOU");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - " + e.getMessage());
        }

        // ARRAY DE NOTAS NULO
        System.out.println("\nTeste 6: Array de notas nulo");
        try {
            new Aluno("Carlos", null);
            System.out.println("FALHOU");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - " + e.getMessage());
        }

        // TURMA VAZIA PARA ESTATISTICAS
        System.out.println("\nTeste 7: Turma vazia para estatisticas");
        try {
            Aluno.estatisticaTurma(new Aluno[] {});
            System.out.println("FALHOU");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - " + e.getMessage());
        }

        // VERIFICAR SITUACOES
        System.out.println("\nTeste 8: Verificar situacoes");
        Aluno aprovado = new Aluno("Teste1", new int[] { 80, 80, 80 });
        Aluno recuperacao = new Aluno("Teste2", new int[] { 50, 60, 55 });
        Aluno reprovado = new Aluno("Teste3", new int[] { 30, 40, 35 });
        System.out.println("Media 80 -> " + aprovado.getSituacao()
                + " (esperado: APROVADO) "
                + (aprovado.getSituacao().equals("APROVADO") ? "OK" : "FALHOU"));
        System.out.println("Media 55 -> " + recuperacao.getSituacao()
                + " (esperado: RECUPERACAO) "
                + (recuperacao.getSituacao().equals("RECUPERACAO") ? "OK" : "FALHOU"));
        System.out.println("Media 35 -> " + reprovado.getSituacao()
                + " (esperado: REPROVADO) "
                + (reprovado.getSituacao().equals("REPROVADO") ? "OK" : "FALHOU"));

        System.out.println("\n=== TODOS OS TESTES FINALIZADOS ===");
    }
}
