package com.Gelita;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame obj=new JFrame();
        GamePlayMode gamePlayMode=new GamePlayMode();
        obj.setBounds(10,10,700,600);
        obj.setResizable(false);
        obj.setTitle("Brick Bracker");
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlayMode);
    }
}
