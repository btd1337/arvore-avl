import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numElements;
        int key;
        int aux;
        boolean isBalance;
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Numero de elementos para inserir na árvore:\n");
        numElements = scanner.nextInt();
        
        System.out.println("Chave de busca:\n");
        key = scanner.nextInt();
        
        System.out.printf("Balancear?\n 1- Sim\n 2- Não [Padrão]\n\n");
        
        aux = scanner.nextInt();
        
        switch(aux) {
        	case 1: isBalance = true; break;
        	case 2: isBalance = false; break;
        	default: isBalance = false;
        }
        
        scanner.close();

        Comparator sequentialComparator = new Comparator();
        Comparator randomComparador = new Comparator();

        AvlTree randomTree = new AvlTree();
        AvlTree sequentialTree = new AvlTree();

        Random gerador = new Random(100 * 1000000 * numElements);
        
        for (int i = 0; i < numElements; i++) {
            randomTree.insert(gerador.nextInt(numElements * 10000), isBalance);
            sequentialTree.insert(i, isBalance);
        }

        for (int j = 0; j < key; j++) {
            randomTree.search(j * 2, randomComparador);
            sequentialTree.search(j * 2, sequentialComparator);
        }

        System.out.println("--- Árvore Aleatória ---");
        System.out.println(randomComparador.toString());
        System.out.println(randomTree.higher());
        
        System.out.println("--- Árvore Sequencial ---");
        System.out.println(sequentialComparator.toString());
        System.out.println(sequentialTree.higher());
        }

}
