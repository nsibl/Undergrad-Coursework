/*
    Noah Sibley
    COP 3503
    Project 4
    Due 12/8/23
 */

import javax.swing.JFrame;

public class Project4 {
    public static void main(String[] args) {
        CustomJFrame customJframe = new CustomJFrame();

        customJframe.setTitle("Dietary Survey");

        customJframe.setSize(370, 750);

        customJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        customJframe.setVisible(true);
    }
}