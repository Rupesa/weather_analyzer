package MPEIProject;

import static java.lang.System.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Aplicacao {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		int selecionador, ano1, ano2, mes1, mes2;
		do {
			System.out.println("Programa para analisar um conjunto de precipitações fornecidadas pela IPMA (Instituto português do Mar e da Atmosfera)");
			mostrarOpcoes();
			selecionador = sc.nextInt();
			switch(selecionador) {
				case 1:
					System.out.print("Introduza o primeiro ano (1951 - 2003): ");
					ano1 = sc.nextInt();
					System.out.print("Introduza o segundo ano (1951 - 2003): ");
					ano2 = sc.nextInt();
					System.out.print("\nIntroduza agora o Distrito: ");
					String distrito = sc.next();
					CompararAnosDistritos(ano1+"", ano2+"", distrito);
					break;
				case 2:
					AparecerProbAnuais();
					break;
				case 3:
					System.out.print("Introduza a estação do ano em que deseja ver as probabilidades: ");
					String estacao = sc.next();
					AparecerProbSazonalmente(estacao);
					break;
				case 4:
					System.out.print("Introduza o ano (1951 - 2003: ");
					ano1 = sc.nextInt();
					System.out.print("Introduza o mes (1 - 12): ");
					mes1 = sc.nextInt();
					System.out.print("Introduza o dia: ");
					int dia = sc.nextInt();
					System.out.print("Introduza o distrito: ");
					String distrito3 = sc.next();
					verPrecipitacao(ano1+"d/"+distrito3+"/"+mes1+"", dia+"");
					break;
				case 5:
					System.out.print("Introduza o primeiro ano (1951 - 2003): ");
					ano1 = sc.nextInt();
					System.out.print("Introduza o primeiro mes (1 - 12): ");
					mes1 = sc.nextInt();
					System.out.print("\nIntroduza o segundo ano (1951 - 2003): ");
					ano2 = sc.nextInt();
					System.out.print("Introduza o segundo mes (1 - 12): ");
					mes2 = sc.nextInt();
					System.out.print("\nIntroduza agora o Distrito da primeira data: ");
					String distrito1 = sc.next();
					System.out.print("\nIntroduza agora o Distrito da segunda data: ");
					String distrito2 = sc.next();
					CompararAnosDistritosMeses(ano1+"d/"+distrito1+"/"+mes1+"", ano2+"d/"+distrito2+"/"+mes2+"");
					break;
			}
		}while(selecionador != 0);
		System.out.println("Programa Terminado -- ");
	}
	
	private static void mostrarOpcoes() {
		System.out.println("1 - Escolher dois anos num mesmo distrito e compará-los");
		System.out.println("2 - Mostrar as probabilidades de intervalos de precipitação (nos 12 meses dos vários anos)");
		System.out.println("3 - Mostrar as probabilidades de intervalos de precipitação (numa Estação em todos os anos)");
		System.out.println("4 - Ver a precipitação numa dada data para um certo distrito");
		System.out.println("5 - Escolher dois meses de um ano, e um distrito quaisquer e compará-los");
	}
	
	//identificadores = vai ser uma String com o ano 
	private static void CompararAnosDistritos(String ano1, String ano2, String distrito) throws IOException{
		File ficheiro1 = new File(ano1+"d/"+distrito+"/ano");
		File ficheiro2 = new File(ano2+"d/"+distrito+"/ano");
		File[] ficheiros = {ficheiro1, ficheiro2};
		String[] dists = Hashing.minHash(ficheiros, 1, 0.1);
		System.out.printf("A distância de Jaccard entre os dois anos é %5.3f\n", Double.parseDouble(dists[0].split(" ")[2]));
	}
	
	@SuppressWarnings("unused")
	private static void AparecerProbAnuais() throws IOException{
		Contador2 counter  = new Contador2(); 
		BloomCounter filter = new BloomCounter(16000000, 1200000);
		File[] meses = new File[12];
		meses = new File[]{new File("1.txt"), new File("2.txt"), new File("3.txt"), new File("4.txt"), new File("5.txt"), new File("6.txt"), new File("7.txt"), new File("8.txt"), new File("9.txt"), new File("10.txt"), new File("11.txt"), new File("12.txt")};
		Scanner scf;
		for(int i = 0; i<meses.length; i++) {
			scf = new Scanner(meses[i]);
			while(scf.hasNextLine()) {
				String linha = scf.nextLine();
				if(linha.length() > 8) {
					Scanner sc = new Scanner(linha);
					sc.next(); sc.next();
					int valor = (int) Double.parseDouble(sc.next());
					filter.insert(valor+"");
					counter.addEvent();
					sc.close();
				}
			}
			scf.close();
		}
		int max = 11;
		System.out.println("Probabilidades dos vários intervalos de precipitação\n");
		System.out.printf("%d  probabilidade: %6.3f\n", 0, (double)filter.howMany(0+"")/counter.estimatedValue());
		while(max < 110) {
			int total = 0;
			for(int i = 0; i<=10; i++) {
				total = total + filter.howMany((max-i)+"");
			}
			System.out.printf("%d -- %d probabilidade: %6.3f\n", max-10, max, (double)total/counter.estimatedValue());
			max = max+10;
		}
		System.out.println();
	}
	
	@SuppressWarnings("unused")
	private static void AparecerProbSazonalmente(String estacao) throws IOException{
		Contador2 counter  = new Contador2(); 
		BloomCounter filter = new BloomCounter(5000000, 2000000);
		File[] meses = new File[3];
		if(estacao.equalsIgnoreCase("Primavera")) {
			meses[0] = new File("3.txt");
			meses[1] = new File("4.txt");
			meses[2] = new File("5.txt");
		}
		else if(estacao.equalsIgnoreCase("Verão")) {
			meses[0] = new File("6.txt");
			meses[1] = new File("7.txt");
			meses[2] = new File("8.txt");
		}
		else if(estacao.equalsIgnoreCase("Outono")) {
			meses[0] = new File("9.txt");
			meses[1] = new File("10.txt");
			meses[2] = new File("11.txt");
		}
		else {
			meses[0] = new File("12.txt");
			meses[1] = new File("1.txt");
			meses[2] = new File("2.txt");
		}
		Scanner scf;
		for(int i = 0; i<3; i++) {
			scf = new Scanner(meses[i]);
			while(scf.hasNextLine()) {
				String linha = scf.nextLine();
				if(linha.length() > 8) {
					Scanner sc = new Scanner(linha);
					sc.next(); sc.next();
					int valor = (int) Double.parseDouble(sc.next());
					filter.insert(valor+"");
					counter.addEvent();
					sc.close();
				}
			}
			scf.close();
		}
		
		
		
		
		
		int max = 11;
		System.out.println("Probabilidades dos vários intervalos de precipitação na Estação "+estacao+"\n");
		System.out.printf("%d -- probabilidade: %6.3f\n", 0, (double)filter.howMany(0+"")/counter.estimatedValue());
		while(max < 110) {
			int total = 0;
			for(int i = 0; i<=10; i++) {
				total = total + filter.howMany((max-i)+"");
			}
			System.out.printf("%d -- %d probabilidade: %6.3f\n", max-10, max, (double)total/counter.estimatedValue());
			max = max+10;
		}
		System.out.println();
	}

	@SuppressWarnings("unused")
	private static void verPrecipitacao(String identificador1, String dia)
												throws FileNotFoundException {
		File f = new File(identificador1);
		Scanner scf = new Scanner(f);
		int line=0;
		String ws[], result = "";
		
		while(scf.hasNextLine()) {
			line++;
			if(line==Integer.valueOf(dia)) {
				ws = scf.nextLine().split(" ");
				result = ws[ws.length-1];
				break;
			}
			scf.nextLine();
		}
		scf.close();
		
		if(result.equals(""))
			out.println("Erro");
		else
			out.println("A precipitação nesse dia é de "+result+" mm");
	}
	
	private static void CompararAnosDistritosMeses(String identificador1, String identificador2) throws IOException {
		File ficheiro1 = new File(identificador1);
		File ficheiro2 = new File(identificador2);
		File[] ficheiros = {ficheiro1, ficheiro2};
		String[] dists = Hashing.minHash(ficheiros, 1, 0.1);
		System.out.printf("A distância de Jaccard entre os dois meses é %5.3f\n", Double.parseDouble(dists[0].split(" ")[2]));
	}
	
	
	

}
