package MPEIProject;

import java.io.FileNotFoundException;

public class TestContador {

	public static void main(String[] args) throws FileNotFoundException {
		
		Contador2 eCounter;
		
		eCounter = new Contador2("src/PRECIP_PT_m09(teste).txt");
		
		eCounter.testShowCounts();
		
		//System.out.println(
		//		new File("src/PRECIP_PT_m09(teste).txt").exists());

	}

}
