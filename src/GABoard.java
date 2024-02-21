
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author oguzh
 */
public class GABoard{
    
    
    private int[][] board;
    private int BOARD_SIZE;

    public GABoard(int size) {
        this.BOARD_SIZE = size;
        this.board = new int[size][size];
    }

    // Tahta üzerinde rastgele renkleri yerleştirme
    public void initializeBoard(int numColors) {
        Random random = new Random();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = random.nextInt(numColors);
            }
        }
    }
    
    
     // Tahtadaki durumu yazdırma
    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    
     // Dama tahtasının maliyetini hesapla
    public int calculateCost() {
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
    
    
    
    //matrisin kendisini alıyorum
    public int[][] matrisDon(){
        return(this.board);
    }
    
    public void setBoard(int[][] board){
        //size zaten constructorda belirlenecek
        this.board=board;
    }
    
    
    
}
