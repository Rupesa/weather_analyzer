package MPEIProject;
public class TestBloomCounter {

	public static void main(String[] args) {
		
		BloomCounter bc = new BloomCounter(7, 4);
		
		String[] nomes = new String[] {"andre", "rui", "goncalo"};
		
		// add andre
		bc.insert(nomes[0]);
		System.out.println("Adicionado "+nomes[0]);
		// add rui
		bc.insert(nomes[1]);
		System.out.println("Adicionado "+nomes[1]);
		// add goncalo
		bc.insert(nomes[2]);
		System.out.println("Adicionado "+nomes[2]);
		// add goncalo
		bc.insert(nomes[2]);
		System.out.println("Adicionado "+nomes[2]);
		
		// verifica se estes nomes existem
		System.out.println(nomes[1]+" existe? "+bc.verify(nomes[1]));
		System.out.println(nomes[0]+" existe? "+bc.verify(nomes[0]));
		System.out.println("ricardo"+" existe? "+bc.verify("ricardo"));
		
		// conta o número de vezes que os nomes ocorrem
		//  conta goncalos (2)
		System.out.println("Quantas vezes ocorre \""+nomes[2]+"\""+bc.howMany(nomes[2]));
		// remove 1 goncalo
		bc.remove (nomes[2]);
		System.out.println("Removido \""+nomes[2]+"\"");
		//  conta de novo goncalos (1)
		System.out.println("Quantas vezes ocorre \""+nomes[2]+"\""+bc.howMany(nomes[2]));
		//  conta andre (1)
		System.out.println("Quantas vezes ocorre \""+nomes[0]+"\""+bc.howMany(nomes[0]));
		//  remove andre
		bc.remove (nomes[0]);
		System.out.println("Removido \""+nomes[0]+"\"");
		// conta andre (0)
		System.out.println("Quantas vezes ocorre \""+nomes[0]+"\""+bc.howMany(nomes[0]));
	}

}
