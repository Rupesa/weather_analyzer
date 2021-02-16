package MPEIProject;

import java.io.*;

public class TestHashing {
	
	public static void main(String[] args) throws IOException {
		File ficheiro1 = new File("sample.txt");
		File ficheiro2 = new File("sample2.txt");
		File ficheiro3 = new File("sample3.txt");
		File[] ficheiros = {ficheiro2, ficheiro3};
		
		int m = 0;
		String[] dist_s;
		double dist, s = 0;
		for(int i = 0; i<1000; i++) {
			dist_s = Hashing.minHash(ficheiros, 1, 0.1);
			dist = Double.parseDouble(dist_s[0].split(" ")[2]);
			if(m < 10) System.out.printf("%2dª Medição: %6.4f\n", m+1, dist);
			else if(m == 10) System.out.println("...");
			m++;
			s = s+dist;
		}
		System.out.println("Aproximação por minHash: "+s/1000);
		
		
		dist_s = Hashing.trueDist(ficheiros);
		System.out.println("Distância Real: "+dist_s[0].split(" ")[2]);
		
		ficheiros = new File[] {ficheiro1, ficheiro2};
		s = 0;
		m = 0;
		for(int i = 0; i<1000; i++) {
			dist_s = Hashing.minHash(ficheiros, 1, 0.1);
			dist = Double.parseDouble(dist_s[0].split(" ")[2]);
			if(m < 10) System.out.printf("%2dª Medição: %6.4f\n", m+1, dist);
			else if(m == 10) System.out.println("...");
			m++;
			s = s+dist;
		}
		System.out.println("Aproximação por minHash: "+s/1000);
		dist_s = Hashing.trueDist(ficheiros);
		System.out.println("Distância Real: "+dist_s[0].split(" ")[2]);
	}
	
	
	

}
