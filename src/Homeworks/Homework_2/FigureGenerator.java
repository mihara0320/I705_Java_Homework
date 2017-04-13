package Homeworks.Homework_2;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kenzi on 12/04/2017.
 */

public class FigureGenerator {

    final int X = 4;
    final int Y = 4;

    int currentX = X;
    int currentY = Y;
    boolean [][] status = new boolean[7][7];

    public Figure createFigure() {

        Figure figure = new Figure();

        figure.add(new Block(X, Y));
        status[4][4] = true;
//        figure.add(getNextBlock(currentX, currentY));
//        figure.add(getNextBlock(currentX, currentY));
//        figure.add(getNextBlock(currentX, currentY));

        Block[] results = new Block[3];

        for (int i = 0; i < 3; i++) {
            Block result = null;
            while(result == null){
                result = getNextBlock(currentX, currentY);
            }
            results[i] = result;
        }

        for (int i = 0; i < 3; i++) {
            figure.add(results[i]);
        }

        currentX = X;
        currentY = Y;



        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                status[i][j] = false;
            }
        }

        return figure;
    }

    private boolean getRandomBoolean () {
        Random randomno = new Random();
        boolean result = randomno.nextBoolean();
        return result;
    }

    private Block getNextBlock(int x, int y){
        System.out.println("called");
        boolean plus = getRandomBoolean ();
        boolean onX = getRandomBoolean ();

        Block block = null;

        if(plus && onX && status[x+1][y]==false){
            block = new Block(x+1, y);
            currentX = x+1;
            status[x+1][y]=true;
        } else if (!plus && onX && status[x-1][y]==false) {
            block = new Block(x-1, y);
            currentX = x-1;
            status[x-1][y]=true;
        } else if(plus && !onX && status[x][y+1]==false){
            block = new Block(x, y+1);
            currentY = y+1;
            status[x][y+1]=true;
        } else if(!plus && !onX && status[x][y-1]==false){
            block = new Block(x, y-1);
            currentY = y-1;
            status[x][y-1]=true;
        }

        System.out.println(block);
        return block;
    }



}