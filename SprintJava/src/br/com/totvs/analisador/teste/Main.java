package br.com.totvs.analisador.teste;

import java.util.Scanner;

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

        Cliente cliente = new Cliente(1,nome,email,empresa,plano);

        String data;

        while (true) {

            System.out.print("Data que ocorreu a reunião (dd/MM/yyyy): ");
            data = scanner.nextLine();

            if (data.matches("\\d{2}/\\d{2}/\\d{4}")) {
                break;
            }

            System.out.println("Data inválida! Digite no formato dd/MM/yyyy");
        }

        System.out.println("Digite a transcrição da reunião:");
        String transcricao = scanner.nextLine();

        Reuniao reuniao = new Reuniao(1,data,transcricao,cliente);

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