package br.com.totvs.analisador.teste;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import br.com.totvs.analisador.model.Analise;
import br.com.totvs.analisador.model.Cliente;
import br.com.totvs.analisador.model.RealizarAnalise;
import br.com.totvs.analisador.model.Reuniao;
import br.com.totvs.analisador.model.Usuario;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("SISTEMA DE ANALISE DE REUNIOES TOTVS");

        System.out.print("\nNome do cliente: ");
        String nome = scanner.nextLine();

        String email;

        while (true) {

            System.out.print("Email: ");
            email = scanner.nextLine();

            if (email.contains("@") && email.contains(".")) {
                break;
            }

            System.out.println("Email inválido!");
        }

        System.out.print("Empresa: ");
        String empresa = scanner.nextLine();

        System.out.print("Produto contratado: ");
        String plano = scanner.nextLine();

        Cliente cliente = new Cliente(1, nome, email, empresa, plano);

        String data;
        
        // CORREÇÃO AQUI: Mudamos de "dd/MM/yyyy" para "dd/MM/uuuu"
        // O 'uuuu' representa o ano de forma compatível com o ResolverStyle.STRICT
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                                                        .withResolverStyle(ResolverStyle.STRICT);

        while (true) {

            System.out.print("Data que ocorreu a reunião (dd/MM/yyyy): ");
            data = scanner.nextLine();

            try {
                // Agora vai aceitar 20/05/2025 normalmente e continuar bloqueando 40/04/2025
                LocalDate.parse(data, formatador);
                break; 
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida ou inexistente! Digite uma data real no formato dd/MM/yyyy");
            }
        }

        System.out.println("Digite a transcrição da reunião:");
        String transcricao = scanner.nextLine();

        Reuniao reuniao = new Reuniao(1, data, transcricao, cliente);

        RealizarAnalise service = new RealizarAnalise();

        Analise analise = service.executarAnalise(reuniao);

        System.out.println("\nRESULTADO DA ANALISE");

        Usuario usuario = cliente;

        System.out.println(usuario.exibirDados());

        System.out.println("Sentimento detectado: " + analise.getSentimento());

        System.out.println("Risco de cancelamento: " + analise.getRisco());

        System.out.println("Chance de renovação: " + analise.getChanceRenovacao() + "%");

        System.out.println("Resumo da reunião: " + analise.getResumo());

        System.out.println("Recomendação final: " + analise.getRecomendacao());

        scanner.close();
    }
}