
import java.util.Scanner;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author oguzh
 */
public class HCStart {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the checkerboard size: ");
        int BOARD_SIZE = scanner.nextInt();
        
        System.out.print("Please enter the maximum number of iterations: ");
        int MAX_ITERATIONS = scanner.nextInt();
        
        HillClimbing hillClimbing = new HillClimbing(BOARD_SIZE);
        
        
        
        int maxUniquePairsForThisBoard=calculateMaxUniquePairs(BOARD_SIZE);
        System.out.println("Maximum unique pairs for this "+BOARD_SIZE+"x"+BOARD_SIZE+ " board: "+ maxUniquePairsForThisBoard);
        
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE]; // Dama tahtası
        
        // Rastgele doldurma
        System.out.println("Randomly generated checkerboard:");
        hillClimbing.fillRandom(board);
        hillClimbing.printBoard(board);
        
        
        //GÖRME KODU
        DamaTahtasiGor.Gor(board,"FIRST Random Checkerboard");

        
        
        // Hill climbing algoritması
        int currentCost = hillClimbing.calculateCost(board);
        int iterations = 0;
        
        if(iterations==0){
                System.out.println("Maximum number of unique pairs of the randomly generated checkerboard:"+ currentCost);
            }
        
        
        while (iterations < MAX_ITERATIONS && currentCost < maxUniquePairsForThisBoard&&!hillClimbing.yerelMax) {
            int[][] newBoard = hillClimbing.getNeighbor(board); // Yeni bir komşu durum al
            int newCost = hillClimbing.calculateCost(newBoard); // Yeni durumun maliyetini hesapla

            if (newCost > currentCost) { // Eğer yeni durum daha iyi ise kabul et
                board = newBoard;
                currentCost = newCost;
            }

            iterations++;
            
            
            
                
                //iterasyonları tek tek görebilmek için.
        System.out.println("CHECKERBOARD of "+iterations+ ". iteration:");
        hillClimbing.printBoard(board);
        System.out.println(iterations+". iteration current cost:"+ currentCost);
                
                
            
        }
       

        // Sonucu yazdır
        System.out.println("Hill Climbing result:");
        hillClimbing.printBoard(board);
        System.out.println("Result was found in "+iterations+ ". iterations.");
        System.out.println("Result current cost:"+ currentCost);
        int sameColorPairs=maxUniquePairsForThisBoard-currentCost;
                System.out.println(sameColorPairs+" pairs are the same color as each other.");
        
        
        //GÖRME KODU
        DamaTahtasiGor.Gor(board,"RESULT Checkerboard");
        
        
        if(hillClimbing.yerelMax){
            System.out.println("We stayed at the local maximum, there is no better neighbor.");
        }
     
       
    }
    
    
    //Verilen tahta için maximum unique pairsi hesapladığım fonksiyon; ulaşınca durdurmak için.(GLOBAL MAX)
    private static int calculateMaxUniquePairs(int BOARD_SIZE) {
    int maxPairs = 0;

    for (int i = 0; i < BOARD_SIZE; i++) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            // Sağ ve altta olan eşleşmeleri hesapla
            if (i + 1 < BOARD_SIZE) {
                maxPairs++;
            }
            if (j + 1 < BOARD_SIZE) {
                maxPairs++;
            }
        }
    }
    return maxPairs;
}
    
    
}
