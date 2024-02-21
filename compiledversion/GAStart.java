
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author oguzh
 */
public class GAStart {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the checkerboard size: ");
        int BOARD_SIZE = scanner.nextInt();
        
        System.out.print("Please enter the population size: ");
        int populationSize = scanner.nextInt();
        
        System.out.print("Please enter the maximum number of generation : ");
        int maxGeneration = scanner.nextInt();
        
            GABoard parent1=null; //populasyondaki en yüksek costlu parent
            GABoard parent2=null; //populasyondaki ikinci en yüksek costlu parent
        
      
        
        
            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(BOARD_SIZE,populationSize,maxGeneration);
            
            
            
            
              int maxUniquePairsForThisBoard=calculateMaxUniquePairs(BOARD_SIZE);
        System.out.println("Maximum unique pairs for this "+BOARD_SIZE+"x"+BOARD_SIZE+ " board: "+ maxUniquePairsForThisBoard);
            
            
            
            
            ///////////////////////////////////// İlk populasyonda örnek olarak herhangi bir tahtayı göstermek için.(Son tahta bu)
    GABoard lastBoard = geneticAlgorithm.population[populationSize-1]; //en son oluşturulan tahta 99. tahta
        System.out.println("To show any board as an example in the initial population(This is last one):");
    lastBoard.printBoard();
    
    
    GABoard tahta = geneticAlgorithm.population[populationSize-1];
    int cost99=tahta.calculateCost();
    
        System.out.println("Its cost:"+cost99);
        ///////////////////////////////////////////
        
        do{
    
           
        int max=0;
        int othermax=0;
        int temp;
        
        
        //POPULASYONDAKI EN İYİ 2 TAHTAYI (INDIVIDUALI) SEÇEN İSLEM ASAGIDA
        for(int i=0;i<populationSize;i++){
            GABoard denenentahta = geneticAlgorithm.population[i];
            temp=denenentahta.calculateCost();
            
            if(temp>max){
                max=temp;
                parent1=denenentahta;
            }
            else if(temp<max){
                if(temp>othermax){
                    othermax=temp;
                    parent2=denenentahta;
                }
            }
        }
        
        
        
        System.out.println("Parent 1 for "+geneticAlgorithm.generationTimer+". generation is below :");
        parent1.printBoard();
        System.out.println("PARENT 1 COST :"+parent1.calculateCost());
        
        System.out.println("Parent 2 for "+geneticAlgorithm.generationTimer+". generation is below :");
        parent2.printBoard();
        System.out.println("PARENT 2 COST :"+parent2.calculateCost());
        
        
        
        if(parent1.calculateCost() == maxUniquePairsForThisBoard){
            //eşitse yeni nesil üretilmeden döngüyü kırıcak
            break;
        }
        
        //EGER İSTEDİGİMİZ SONUCU BULAMADIYSAK YENİ NESİL ÜRETMEK İÇİN
        
        geneticAlgorithm.population=geneticAlgorithm.NesilProducer(geneticAlgorithm.population,parent1,parent2);
        
        //Yeni nesil üretildi geneticAlgorithm içindeki population değerine yazıldı.
    
        
        geneticAlgorithm.generationTimer++;
        
        }while(parent1.calculateCost()!=maxUniquePairsForThisBoard && geneticAlgorithm.generationTimer!=maxGeneration);
        
           
            System.out.println("My result board is below:");
            parent1.printBoard();
            System.out.println("RESULT BOARD COST :"+parent1.calculateCost());
            
            System.out.println("The result was found with "+(geneticAlgorithm.generationTimer)+" generations.");
            
            //GÖRME KODU
            DamaTahtasiGor.Gor(parent1.matrisDon(),"Genetic Algorithm Results Board");
            
            if(parent1.calculateCost()==maxUniquePairsForThisBoard)
                System.out.println("An optimal result has been found, there are no pairs of the same color as each other.\nNumber of different pairs of the result board:"+parent1.calculateCost());
            else{
                int sameColorPairs=maxUniquePairsForThisBoard-parent1.calculateCost();
                System.out.println("An optimal result was not found, "+sameColorPairs+" pairs are the same color as each other.");
            
            }
    }
    
    public static int calculateMaxUniquePairs(int BOARD_SIZE) {
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
