package MPEIProject;

import static java.lang.System.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Contador2 {
	
	/*
	 * counter é o contador, gap é o intervalo a qual o contador incrementa.
	 * Ex: Se a gap for 10, o contador incrementa à décima contagem.
	 * (Disclaimer): O contador não incrementa exatamente pelo valor do intervalo
	 * 	(gap), acontece sim em média, visto que o faz com probabilidade = 1/gap
	 */
	private short counter, gap;
	
	/*
	 * Contador de maior gama (int), com objetivo de contar o valor exato
	 */
	private int actualValue;
	
	/*
	 * 
	 */
	public Contador2() {
		counter = 0;
		gap = 1;
	}

	public void addEvent() {
		actualValue++;
		if(Math.random() < 1.0/gap) {
			if(counter==Short.MAX_VALUE)
				throw new StackOverflowError();
			gap++;
			counter++;
		}
	}
	
	/**
	 * 
	 * @param f
	 * @throws FileNotFoundException
	 * 
	 * Recebe o ficheiro f para executar o contador sobre este, chamando o próximo construtor. 
	 *  
	 */
	public Contador2(File f) throws FileNotFoundException {
		new Contador2(f.getName());
	}
	
	/**
	 * 
	 * @param nameF
	 * @throws FileNotFoundException
	 * 
	 * Recebe uma string contendo o nome do ficheiro f.
	 * O contador estocástico conta o número de linhas de f
	 * 
	 */
	public Contador2(String nameF) throws FileNotFoundException {
		counter = 0;
		gap = 1;

		Scanner scf = new Scanner(
						new File(nameF));

		while(scf.hasNextLine())
			if(scf.nextLine().charAt(0)==' ') {
				if(Math.random()<1.0/gap) {
					if(counter==Short.MAX_VALUE) {
						// Erro ocasional. Acontece quando o contador, mesmo
						//	contando poucas vezes, chega ao limite. Muito pouco provável
						//	 de acontecer
						scf.close();
						throw new StackOverflowError("Erro Ocasional. Volte a executar.");
					}
					gap++;
					counter++;
				}
				actualValue++;
			}
	
		scf.close();
		gap = (short)(gap/2);

	}
	
	/*
	 * Testa o contador comparando o valor estimado com o valor verdadeiro (actualValue)
	 */
	public void testShowCounts() {
		out.println("Com um intervalo médio de "+gap+", o contador incrementou "+counter+" vezes.\n"+
						"\tO valor exato é "+actualValue+", o estimado foi "+
								estimatedValue()+".");
	}
	
	/*
	 * Estima o valor final a partir do intervalo médio
	 *  multiplicado pelas vezes contadas
	 *    (intervaloMedio = gap / 2 e gap = count + 1
	 *     => intervaloMedio = (count + 1) / 2)
	 */
	public int estimatedValue() {
		int ncount = (int)(counter);
		return ncount * (ncount + 1) / 2;
	}
	
}
