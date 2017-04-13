package Homeworks.Homework_2;

/**
 * Created by kenzi on 12/04/2017.
 */

public class FigureGenerator {

    final int X = 8;
    final int Y = 5;

    public Figure createFigure() {

        Figure figure = new Figure();

        if (Math.random() < 0.5) {
            figure.add(new Block(X, Y));
            figure.add(new Block(X -1, Y));
            figure.add(new Block(X + 1, Y));
            figure.add(new Block(X, Y+1));
        }
        else {
            figure.add(new Block(X, Y));
            figure.add(new Block(X, Y+1));
            figure.add(new Block(X, Y+2));
            figure.add(new Block(X + 1, Y+2));
        }

        return figure;
    }

}