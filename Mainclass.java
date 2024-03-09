package Demogame;

import javax.swing.*;
import java.awt.*;

public class Mainclass extends JFrame {
    public Mainclass(){
        setTitle("Brick Breaker");
        setSize(700,600);
         setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);



         setResizable(false);
        GamePlay gameplay=new GamePlay(700,600);
        add(gameplay);
        setBackground(Color.BLACK);
    }
    public static void main(String[] args) {

        new Mainclass();

}}
