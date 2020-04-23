package org.mapeditor.cells;

import org.academiadecodigo.simplegraphics.graphics.Color;



public class Cursor extends Cell {

    //private Rectangle rectangle1;
    //private int col;
    //private int row;


    public Cursor() {
        super(0, 0);
        //rectangle1 = new Rectangle(PADDING + getCellSize()*col,PADDING+getCellSize()*row, getCellSize(),getCellSize());
        rectangle.setColor(Color.GREEN);
        rectangle.fill();

    }


    public void movesUp() {
        if(getRow()> 0) {
            row--;
            rectangle.translate(0, -getCellSize());
        }
    }

    public void moveDown(int rows) {
            if(getRow() < (rows-1)){
            row++;
            rectangle.translate(0, getCellSize());
        }
    }


    public void moveLeft() {
        if(getCol() > 0) {
            col--;
            rectangle.translate(-getCellSize(), 0);
        }
    }

    public void moveRight(int cols) {
            if(getCol() < (cols-1)) {
                col++;
                rectangle.translate(getCellSize(), 0);
            }

    }



}

