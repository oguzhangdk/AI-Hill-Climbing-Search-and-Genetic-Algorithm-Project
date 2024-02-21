/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author oguzh
 */
import java.util.Arrays;
import java.util.Random;
import java.util.*;

public class HillClimbing {
    public static boolean yerelMax=false;//daha iyi komşu yoksa yerelMax true olacak
    private int BOARD_SIZE = 8; // Dama tahtası boyutu*
    private static final int NUM_COLORS = 4; // Renk sayısı

    // Renkleri temsil etmek için sayılar
    private static final int[] COLORS = {0,1,2,3};

    
    public HillClimbing(int BOARD_SIZE) {
        //kullanıcıdan gelen veriyi giriyorum
        this.BOARD_SIZE=BOARD_SIZE;
    }
    
    
    
    
    // Dama tahtasını rastgele doldur
public void fillRandom(int[][] board) {
    Random random = new Random();
    for (int i = 0; i < BOARD_SIZE; i++) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            board[i][j] = random.nextInt(NUM_COLORS); // Renkler 0'dan 3'e kadar
        }
    }
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
    
    
    
    

    // Dama tahtasını yazdır
    public void printBoard(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    
    public int[][] getNeighbor(int[][] board) {
    int[][] newBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
    int bestUniquePairs = calculateCost(board);

    // En iyi komşu durumu saklayacak değişkenler
    int[][] bestNeighbor = new int[BOARD_SIZE][BOARD_SIZE];

    // Tüm hücreleri ve renklerini değerlendir
    for (int i = 0; i < BOARD_SIZE; i++) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            // Orijinal renk
            int originalColor = newBoard[i][j];

            // Tüm renkleri deneyerek komşu durumları oluştur
            for (int color = 0; color < NUM_COLORS; color++) {
                if (color != originalColor) {
                    newBoard[i][j] = color; // Renk değiştir

                    // Yeni durumun uniquePairs sayısını hesapla
                    int newUniquePairs = calculateCost(newBoard);

                    // Eğer yeni durum daha iyiyse güncelle
                    if (newUniquePairs > bestUniquePairs) {
                        bestUniquePairs = newUniquePairs;
                        
                        
                        // En iyi durumu kopyala
                        for (int x = 0; x < BOARD_SIZE; x++) {
                            System.arraycopy(newBoard[x], 0, bestNeighbor[x], 0, BOARD_SIZE);
                        }
                    }
                }
            }

            // Orijinal rengi geri al
            newBoard[i][j] = originalColor;
        }
    }

    // En iyi komşu durumu döndür
    
    if (bestUniquePairs > calculateCost(board)) {
    return bestNeighbor;
} else {
        
    yerelMax=true;
    return board;
}
    
}
}
    
