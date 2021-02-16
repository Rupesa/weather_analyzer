package MPEIProject;

import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;

public class Hashing {
	
	//Número de caracteres de cada Shingle
	private static final int K = 5;
	
	public static long hashOf(String str, int a, int b) {
		return ((a*str.hashCode() + b)%((long)Math.pow(2, 31)-1))%(long)(Math.pow(2, 31)-2);
	}
	
	public static String[] minHash(File[] documents, double limite, double erro) throws IOException {
		LinkedList<String> dist = new LinkedList<String>();
		String[][] shingles = new String[documents.length][];
		int n = numFunctions(erro);
		int[] c = new int[n], b = new int[n];
		generateSeeds(c, b);
		
		for(int i = 0; i<documents.length; i++) {
			shingles[i] = getShingles(documents[i]);
		}
		int[][] a = new int[documents.length][n];
		for(int i = 0; i<documents.length; i++) {
			for(int w = 0; w<n; w++) {
				int m = (int)hashOf(shingles[i][0], c[w], b[w]);
				for(int s = 1; s<shingles[i].length; s++) {
					int hash = (int)hashOf(shingles[i][s], c[w], b[w]);
					if(m > hash) m = hash;
				}
				a[i][w] = m;
			}
		}
		
		for(int i = 0; i<documents.length-1; i++) {
			for(int w = i+1; w<documents.length; w++) {
				double num = 0;
				for(int z = 0; z<n; z++) {
					if(a[i][z] == a[w][z]) {
						num++;
					}
				}
				if(1- num/(double)n <= limite) {
					dist.add(i+" "+w+" "+(1- num/(double)n));
				}
			}
		}	
		
		return dist.toArray(new String[0]);
	}
	
	private static String[] getShingles(File document) throws IOException {
		Scanner scf = new Scanner(document);
		LinkedList<String> lista = new LinkedList<String>();
		
		
		while(scf.hasNext())
			lista.add(scf.next());
		scf.close();
		String[] shingles = new String[lista.size()-K+1];
		for(int i = 0; i<lista.size()-K+1; i++) {
			shingles[i] = lista.get(i);
			for(int w = 0; w<K-1; w++) {
				shingles[i] = shingles[i]+" "+lista.get(i+w+1);
			}
		}
		return shingles;
	}
	
	public static String[] trueDist(File[] documents) throws IOException{
		String[][] shingles = new String[documents.length][];
		LinkedList<String> l = new LinkedList<>();
		for(int i = 0; i<documents.length; i++) {
			shingles[i] = getShingles(documents[i]);
		}
		for(int i = 0; i<documents.length-1; i++) {
			for(int w = i+1; w<documents.length; w++) {
				int inter = 0;
				for(int z = 0; z<shingles[i].length; z++) {
					for(int y = 0; y<shingles[w].length; y++) {
						if(shingles[i][z].equals(shingles[w][y])) {
							inter++; break;
						}
					}
					
				}
			l.add(i+" "+w+" "+(1-(double)inter/(shingles[i].length+shingles[w].length-inter)));	
			}
		}
		
		return l.toArray(new String[0]);
	}
	
	private static void generateSeeds(int[] a, int[] b) {
		for(int i = 0; i<a.length; i++) {
			a[i] = (int)(Math.pow(2, 31)*Math.random());
			b[i] = (int)(Math.pow(2, 31)*Math.random());
		}
	}
	
	private static int numFunctions(double erro) {
		return (int) Math.ceil(Math.pow(1/erro, 2));
	}
	

}
