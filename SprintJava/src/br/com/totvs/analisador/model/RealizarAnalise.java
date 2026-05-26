package br.com.totvs.analisador.model;

public class RealizarAnalise {

    public Analise executarAnalise(Reuniao reuniao) {

        Analise analise = new Analise();

        String texto = reuniao.getTranscricao().toLowerCase();

        int positivos = 0;
        int negativos = 0;

        String[] palavrasPositivas = {
                "bom",
                "otimo",
                "excelente",
                "gostando",
                "renovar",
                "satisfeito",
                "rapido",
                "eficiente",
                "perfeito",
                "maravilhoso"
        };

        String[] palavrasNegativas = {
                "cancelar",
                "cancelamento",
                "ruim",
                "pessimo",
                "horrivel",
                "lento",
                "demorado",
                "problema",
                "erro",
                "falha",
                "travando",
                "insuportavel",
                "instavel",
                "bug",
                "bugs",
                "reclamaçao",
                "reclamar",
                "fraco",
                "decepcionado",
                "caro",
                "não gostei",
                "não funciona",
                "dificuldade",
                "inutilizavel",
                "irritado",
                "estressado",
                "suporte ruim",
                "atraso",
                "desorganizado",
                "quero sair",
                "nao gostei"
        };

        for (String palavra : palavrasPositivas) {

            if (texto.contains(palavra)) {
                positivos++;
            }
        }

        for (String palavra : palavrasNegativas) {

            if (texto.contains(palavra)) {
                negativos++;
            }
        }

        if (negativos > positivos) {

            analise.setSentimento("NEGATIVO");

            analise.setChanceRenovacao(20);

            analise.setRisco("ALTO");

            analise.setRecomendacao("Entrar em contato imediatamente com o cliente.");

        } else {

            analise.setSentimento("POSITIVO");

            analise.setChanceRenovacao(85);

            analise.setRisco("BAIXO");

            analise.setRecomendacao("Cliente satisfeito e com potencial de renovação.");
        }

        if (texto.length() > 150) {

            analise.setResumo(texto.substring(0, 150) + "...");

        } else {

            analise.setResumo(texto);
        }

        return analise;
    }
}