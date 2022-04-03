package bancoDigital;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	static Cliente cliente = new Cliente();
	static Conta cc = null, cp = null;

	public static void main(String[] args) throws IOException, InterruptedException {		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(menuCadastro(1));
		String nome = sc.nextLine();
		
		System.out.println(menuCadastro(2));
		int opcao = sc.nextInt();	
		
		cadastrarConta(nome, opcao);
		
		do {
			System.out.println(menuCentral());
			opcao = sc.nextInt();	
		
			operacoesConta(opcao);
			
			System.out.println("\n Escolha uma opção: ");
			System.out.println("6 - Voltar ao menu ");
			System.out.println("7 - Encerrar ");
			opcao = sc.nextInt();
			
		} while (opcao != 7);
				
		sc.close();
		

		System.out.println("\n ENCERRANDO...");
		
		
		
	}
	
	public static String menuCadastro(int opcao) {
		
		if(opcao == 1) {
			
			return "Cadastre sua conta! É muito rápido, só precisamos do seu nome e tipo de conta."
					+"\nInforme seu nome: \n";
			
			
		} else {
			
			return "\nÓtimo! Agora informe qual tipo de conta deseja cadastrar: "
					+"\n"+"1 - Conta Corrente"
					+"\n"+"2 - Conta Poupança"
					+"\n"+"3 - Conta Corrente e Poupança"
					+"\n"+"4 - Desistir\n";
		}
		
	}
	
	public static String menuCentral() {
		return "\n".repeat(100)
				+"\n"+"-".repeat(52)
				+"\n"+"| "+" ".repeat(13) + " SELECIONE UMA OPÇÃO " + " ".repeat(14)+" |"
				+"\n"+"-".repeat(52)
				+"\n"+"| 1 - Consultar extrato"+" ".repeat(28)+"|"
				+"\n"+"| 2 - Saque"+" ".repeat(40)+"|"
				+"\n"+"| 3 - Transferência entre contas"+" ".repeat(19)+"|"
				+"\n"+"| 4 - Depósito"+" ".repeat(37)+"|"
				+"\n"+"| 5 - Sair"+" ".repeat(41)+"|"
				+"\n"+"-".repeat(52);
	}

	public static String subMenuConta() {
		
		return "1 - Conta Corrente"
			+"\n2 - Conta Poupança \n";
				
	}
	
	public static void cadastrarConta(String nome, int opcao) {
		cliente.setNome(nome);		
		
		switch (opcao) {
		case 1:
			cc = new ContaCorrente(cliente);
			System.out.println("\nCONTA CORRENTE CADASTRADA COM SUCESSO !");
			break;
		case 2:
			cp = new ContaPoupanca(cliente);
			System.out.println("\nCONTA POUPANÇA CADASTRADA COM SUCESSO !");
			break;
		case 3:
			cc = new ContaCorrente(cliente);
			cp = new ContaPoupanca(cliente);
			System.out.println("\nCONTA CORRENTE E CONTA POUPANÇA CADASTRADAS COM SUCESSO!");
			break;
		case 4:
			System.out.println("\n ENCERRANDO... ");
			System.exit(opcao);
			break;			
		}
	}
	
	public static void operacoesConta(int opcao) {
		Scanner sc = new Scanner(System.in);
		double valor = 0;
		
		switch (opcao) {
		case 1:
			System.out.println("\n CONSULTAR EXTRATO DE QUAL CONTA? ");
			System.out.println(subMenuConta());
			opcao = sc.nextInt();
			System.out.println("\n\n");
			if (opcao == 1) {
				cc.imprimirExtrato();
			} else if (opcao == 2) {
				cp.imprimirExtrato();
			} else {
				System.out.println("\n Opção Incorreta! ");
			}			
			break;
		case 2:
			System.out.println("\nEFETUAR SAQUE EM QUAL CONTA? ");
			System.out.println(subMenuConta());
			opcao = sc.nextInt();
			System.out.println("\n\n");
			if (opcao == 1) {
				System.out.println("\nInforme o valor do saque: ");
				valor = sc.nextDouble();
				
				if(valor < 0 ) {
					System.out.println("\n NÃO É PERMITIDO VALOR NEGATIVO");
				} else if (valor > cc.getSaldo()) {
					System.out.println("\n SALDO INSUFICIENTE !");
				} else {
					cc.sacar(valor);
					System.out.println("\nExtrato Atualizado: ");
					cc.imprimirExtrato();					
				}				
				
			} else if (opcao == 2) {
				System.out.println("\nInforme o valor do saque: ");
				valor = sc.nextDouble();
				
				if(valor < 0 ) {
					System.out.println("\n NÃO É PERMITIDO VALOR NEGATIVO");
				} else if (valor > cp.getSaldo()) {
					System.out.println("\n SALDO INSUFICIENTE !");
				} else {
					cp.sacar(valor);
					System.out.println("\nExtrato Atualizado: ");
					cp.imprimirExtrato();					
				}
			} 
			break;
		case 3:
			System.out.println("\nDESEJA TRANSFERIR DE QUAL CONTA? ");
			System.out.println(subMenuConta());
			opcao = sc.nextInt();
			System.out.println("\n\n");
			if (opcao == 1) {
				System.out.println("\nInforme o que deseja trasnferir para Conta Poupança: ");
				valor = sc.nextDouble();
				
				if(valor < 0 ) {
					System.out.println("\n NÃO É PERMITIDO VALOR NEGATIVO");
				} else if (valor > cc.getSaldo()) {
					System.out.println("\n SALDO INSUFICIENTE !");
				} else {
					cc.transferir(valor, cp);
					System.out.println("\nExtrato Atualizado: ");
					cc.imprimirExtrato();
					System.out.println("\n");
					cp.imprimirExtrato();				
				}				
				
			} else if (opcao == 2) {
				System.out.println("\nInforme o que deseja trasnferir para Conta Corrente:  ");
				valor = sc.nextDouble();
				
				if(valor < 0 ) {
					System.out.println("\n NÃO É PERMITIDO VALOR NEGATIVO");
				} else if (valor > cp.getSaldo()) {
					System.out.println("\n SALDO INSUFICIENTE !");
				} else {
					cp.transferir(valor, cc);
					System.out.println("\nExtrato Atualizado: ");
					cp.imprimirExtrato();
					System.out.println("\n");
					cc.imprimirExtrato();
				}
			} 
			break;		
		case 4:
			System.out.println("\nDEPOSITAR EM QUAL CONTA? ");
			System.out.println(subMenuConta());
			opcao = sc.nextInt();
			System.out.println("\n\n");
			if (opcao == 1) {
				System.out.println("\nInforme o valor do depósito: ");
				valor = sc.nextDouble();
				
				if(valor < 0 ) {
					System.out.println("\n NÃO É PERMITIDO VALOR NEGATIVO");
				} else {
					cc.depositar(valor);
					System.out.println("\nExtrato Atualizado: ");
					cc.imprimirExtrato();					
				}				
				
			} else if (opcao == 2) {
				System.out.println("\nInforme o valor do depósito: ");
				valor = sc.nextDouble();
				
				if(valor < 0 ) {
					System.out.println("\n NÃO É PERMITIDO VALOR NEGATIVO");
				} else {
					cp.depositar(valor);
					System.out.println("\nExtrato Atualizado: ");
					cp.imprimirExtrato();					
				}
			} 
			break;	
		case 5:
			System.out.println("\n ENCERRANDO... ");
			System.out.println("\n ENCERRANDO... ");
			System.exit(opcao);
			break;			
		}
	}
	

}
