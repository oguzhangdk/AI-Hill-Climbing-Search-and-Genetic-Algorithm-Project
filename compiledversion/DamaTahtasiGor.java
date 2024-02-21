/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author oguzh
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DamaTahtasiGor {

    public static void Gor(int [] [] board,String frameBaslik){
        
        int n = board.length; // Dama tahtası boyutu

        JFrame frame = new JFrame(frameBaslik);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(n, n));


    for (int i = 0; i < n * n; i++) {
        int row = i / n;
        int col = i % n;

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        Color color;
        String labelText;

        switch (board[row][col]) {
            case 0:
                color = Color.CYAN;
                labelText = "0";
                break;
            case 1:
                color = Color.ORANGE;
                labelText = "1";
                break;
            case 2:
                color = Color.RED;
                labelText = "2";
                break;
            case 3:
                color = Color.BLUE;
                labelText = "3";
                break;
            default:
                color = Color.WHITE;
                labelText = "ERROR";
                break;
        }

        panel.setBackground(color);
        JLabel label = new JLabel(labelText, SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        frame.add(panel);
    }

        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

