
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author oguzh
 */

public class GeneticAlgorithm {
    
    private  int BOARD_SIZE = 5; // Dama tahtası boyutu*
    private static final int numColors = 4; // Renk sayısı
    private  int populationSize = 100; // Bir populasyondaki maksimum birey sayısı*
    private  int maxGeneration = 500; //Bu kadar nesil sonra algoritma sonlanacak*
    public int generationTimer=0; //Nesil saymak için
    

    // Renkleri temsil etmek için sayılar
    private static final int[] COLORS = {0,1,2,3};

    
    
    
    public GABoard[] population; //tahtalardan bir popülasyon oluşturdum (tahtalar dizisi içi boş)

    //First Random Population (Genetic Algoritmayı oluşturduğunda otomatik bir şekilde populationSize kadar tahta random generate edilecek)
    public GeneticAlgorithm(int BOARD_SIZE,int populationSize,int maxGeneration) {
        //kullanıcıdan gelen verileri giriyorum
        this. BOARD_SIZE=BOARD_SIZE;
        this.populationSize=populationSize;
        this.maxGeneration=maxGeneration;
        
        population = new GABoard[populationSize];
        for (int i = 0; i < populationSize; i++) {
            GABoard board = new GABoard(BOARD_SIZE);
            board.initializeBoard(numColors);
            population[i] = board;
        }
    }
    
   
    
    
    
   
    
    //şuanki populasyonu alıp en iyi parentlara direkt yeni childlar ürettirecek ve bana yeni populasyonu dönücek
    public GABoard[] NesilProducer(GABoard[] population,GABoard parent1,GABoard parent2){

                
       
        //çiftse
        if(populationSize%2==0)
        for(int i=0;i<populationSize;i+=2){
            GABoard[] childs=CrossingOver(parent1,parent2);
            
            
            //EGER YÜZDE 5 İHTİMALLE MUTASYONA UGRARSA ÇOCUKLARDAN BİRİ
             Random rand = new Random();
        int rastgeleSayi = rand.nextInt(100);
        if (rastgeleSayi < 5) {
            
            GABoard mutasyonluChild=Mutation(childs[0]);
            childs[0]=mutasyonluChild;
        }
        //EGER YÜZDE 5 İHTİMALLE MUTASYONA UGRARSA ÇOCUKLARDAN BİRİ
           
            
            population[i]=childs[0];
            population[i+1]=childs[1];
        }
        
        
        return population;
        
    }
    
    //nesilprodcure tarafından gönderilen çocuğu mutasyonluyacak.
    private GABoard Mutation(GABoard child){
        
        int [][] childMatrixForMutation = child.matrisDon();
        
        Random random = new Random();
        int satir = random.nextInt(BOARD_SIZE); // rastgele satır seç
        int sutun = random.nextInt(BOARD_SIZE); // rastgele sutun seç

        int newColor = random.nextInt(numColors); //0 , 1 , 2 , 3 den rastgele birini seçicek
        childMatrixForMutation[satir][sutun] = newColor;
        
        GABoard mutasyonlananChild = new GABoard(BOARD_SIZE);
    
        mutasyonlananChild.setBoard(childMatrixForMutation);
        
        
        return(mutasyonlananChild);
        
    }
 
    
    //Her çağırıldığında rastgele iki adet child üretir.Farklı şekillerde crossing over işlemi yapar ve çocuk üretmiş olur.
    //Unutma bu CrossingOver dizisinden iki adet çocuk gelecek.(GABoard biçiminde gelicek)
    private GABoard [] CrossingOver(GABoard parent1,GABoard parent2){
   
   int [][] parent1Matrix = parent1.matrisDon();
   int [][] parent2Matrix = parent2.matrisDon();
   
   
   Random random = new Random();
    boolean isHorizontal = random.nextBoolean(); // Rastgele yatay veya dikey kesme(isHorizontal true ise yatayda kesicek i yi kontrol ediceksin.)

    int crossingoverPoint = random.nextInt(BOARD_SIZE-1)+1; //Crossing over için ilk ve son stun ya da satırları seçmemeliyiz crossing over noktası olarak.
   

    int[][] child1Matrix = new int[BOARD_SIZE][BOARD_SIZE];
    int[][] child2Matrix = new int[BOARD_SIZE][BOARD_SIZE];

    for (int i = 0; i < BOARD_SIZE; i++) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            if ((isHorizontal && i < crossingoverPoint) || (!isHorizontal && j < crossingoverPoint)) {
                child1Matrix[i][j] = parent1Matrix[i][j]; // İlk yarıyı parent1'den al
                child2Matrix[i][j] = parent2Matrix[i][j]; // İlk yarıyı parent2'den al
            } else {
                child1Matrix[i][j] = parent2Matrix[i][j]; // İkinci yarıyı parent2'den al
                child2Matrix[i][j] = parent1Matrix[i][j]; // İkinci yarıyı parent1'den al
            }
        }
    }

    GABoard child1 = new GABoard(BOARD_SIZE);
    GABoard child2 = new GABoard(BOARD_SIZE);
    
    child1.setBoard(child1Matrix);
    child2.setBoard(child2Matrix);

   
   

   GABoard[] twoChild = new GABoard[2]; //iki tane çocuk üreticekler
   
   twoChild[0]=child1;
   twoChild[1]=child2;
   
   
   return twoChild;
        
   }
    
    
    
    
    
       // Dama tahtasının maliyetini hesapla
    public int calculateCost(int[][] board) {
        int uniquePairs = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int currentColor = board[i][j];
                if (i + 1 < BOARD_SIZE && board[i + 1][j] != currentColor) { // Aşağıda 
                    uniquePairs++;
                }
                if (j + 1 < BOARD_SIZE && board[i][j + 1] != currentColor) { // Sağda
                    uniquePairs++;
                }
            }
        }
        return uniquePairs;
    }
    
   
    
}