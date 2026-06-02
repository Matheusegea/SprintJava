package br.com.totvs.analisador.model;

import java.text.Normalizer;

public class RealizarAnalise {

    // Banco de palavras positivas (Frases longas primeiro)
    private final String[] palavrasPositivas = {
        "satisfeito com o produto", "pretendo renovar", "estou satisfeito",
        "bom custo beneficio", "atendimento excelente", "atendimento bom",
        "suporte excelente", "excelente atendimento", "excelente suporte",
        "otimo atendimento", "otimo suporte", "vale a pena", "valeu a pena",
        "muito bom", "muito boa", "sem problemas", "sem erro", "sem falhas",
        "funcionando", "funcionou", "funciona", "recomendo", "recomendaria",
        "recomendar", "renovacao", "renovar", "continuar", "continuarei",
        "excelente", "maravilhoso", "maravilhosa", "perfeito", "perfeita",
        "sensacional", "fantastico", "incrivel", "melhorou", "evoluiu",
        "satisfeito", "satisfeita", "gostando", "gostei", "adorei", "amei",
        "curtindo", "curti", "eficiente", "pratico", "pratica", "intuitivo",
        "intuitiva", "compensa", "resolveu", "resolvido", "solucionou",
        "ajudou", "rapido", "rapida", "simples", "melhor", "facil", 
        "agil", "feliz", "otimo", "otima", "show", "top", "bom", "boa"
    };

    // Banco de palavras negativas (Frases longas primeiro)
    private final String[] palavrasNegativas = {
        "nao gostei do produto", "nao gostei do sistema", "não gostei do produto", 
        "não gostei do sistema", "cancelar contrato", "quero cancelar", 
        "nao vale a pena", "não vale a pena", "atendimento pessimo", 
        "atendimento ruim", "suporte ruim", "nao funciona", "nao funcionou", 
        "não funciona", "não funcionou", "nao recomendo", "não recomendo", 
        "nao compensa", "não compensa", "perda de tempo", "travando muito", 
        "vive dando erro", "sempre da erro", "nao resolve", "não resolve", 
        "nao ajudou", "não ajudou", "nao presta", "não presta", "quero sair", 
        "vou sair", "cancelamento", "cancelar", "problemas", "problema", 
        "travamento", "travando", "travou", "decepcionado", "decepcionada", 
        "desorganizado", "desorganizada", "inutilizavel", "insuportavel", 
        "dificuldade", "complicado", "complicada", "estressado", "estressada", 
        "frustrado", "frustrada", "irritado", "irritada", "atrasado", 
        "horrivel", "terrivel", "pessimo", "instavel", "demorado", "demorada", 
        "lento", "lenta", "erros", "erro", "falhas", "falha", "bugs", "bug", 
        "nao gostei", "não gostei", "atraso", "fraco", "fraca", "inutil", 
        "odiei", "ruim", "caro", "cara", "muito caro", "lixo"
    };

    public Analise executarAnalise(Reuniao reuniao) {
        Analise analise = new Analise();
        
        // Mantemos o texto original limpo de acentos para o resumo salvar bonitinho
        String textoOriginal = reuniao.getTranscricao();
        
        // Criamos cópias mutáveis do texto para realizar a contagem sem repetições
        String textoTratado = Normalizer.normalize(textoOriginal.toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        // Contamos limpando as palavras encontradas do texto de teste
        int positivos = contarELimparPalavras(textoTratado, palavrasPositivas);
        int negativos = contarELimparPalavras(textoTratado, palavrasNegativas);

        // Define os parâmetros com base na nova inteligência de margem neutra
        analise.setSentimento(definirSentimento(positivos, negativos));
        analise.setChanceRenovacao(calcularChanceRenovacao(positivos, negativos));
        analise.setRisco(definirRisco(positivos, negativos));
        analise.setRecomendacao(gerarRecomendacao(positivos, negativos));
        analise.setResumo(gerarResumoReuniao(textoOriginal));

        return analise;
    }

    public String definirSentimento(int positivos, int negativos) {
        int diferenca = positivos - negativos;

        if (diferenca >= -1 && diferenca <= 1) {
            return "NEUTRO";
        } else if (diferenca > 1) {
            return "POSITIVO";
        } else {
            return "NEGATIVO";
        }
    }

    public int calcularChanceRenovacao(int positivos, int negativos) {
        int diferenca = positivos - negativos;

        if (diferenca >= 3) {
            return 90;
        }
        if (diferenca >= 2) {
            return 75;
        }
        if (diferenca >= -1 && diferenca <= 1) {
            return 50; // Retorno de 50% estável para a faixa neutra
        }
        return 30;
    }

    public String gerarResumoReuniao(String texto) {
        if (texto.length() > 150) {
            return texto.substring(0, 150) + "...";
        }
        return texto;
    }

    // NOVA LÓGICA DE CONTAGEM: Evita o duplo cômputo (ex: "atendimento ruim" e "ruim")
    private int contarELimparPalavras(String texto, String[] bancoPalavras) {
        int contador = 0;
        String textoLocal = texto;

        for (String palavra : bancoPalavras) {
            // Normaliza a palavra do banco também para garantir casamento perfeito de caracteres
            String palavraTratada = Normalizer.normalize(palavra.toLowerCase(), Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "");

            if (textoLocal.contains(palavraTratada)) {
                contador++;
                // Retira a expressão já contada substituindo por um espaço vazio
                textoLocal = textoLocal.replaceFirst(palavraTratada, "");
            }
        }
        return contador;
    }

    public String definirRisco(int positivos, int negatives) {
        int diferenca = positivos - negatives;

        if (diferenca >= -1 && diferenca <= 1) {
            return "MODERADO";
        } else if (diferenca > 1) {
            return "BAIXO";
        } else {
            return "ALTO";
        }
    }

    public String gerarRecomendacao(int positivos, int negatives) {
        int diferenca = positivos - negatives;

        if (diferenca >= -1 && diferenca <= 1) {
            return "Monitorar cliente para entender melhor sua experiência.";
        } else if (diferenca > 1) {
            return "Cliente satisfeito e com potencial de renovação.";
        } else {
            return "Entrar em contato imediatamente com o cliente.";
        }
    }
}