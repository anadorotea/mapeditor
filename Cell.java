package org.mapeditor.cells;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cell {

    protected int row;
    protected int col;
    protected Rectangle rectangle;
    private int cellSize = 20;
    public static int PADDING = 20;
    private boolean painted;

    public Cell(int col, int row){
        this.col = col;
        this.row = row;
        rectangle = new Rectangle(PADDING + col * cellSize, PADDING + row * cellSize, cellSize, cellSize);
        rectangle.draw();
    }

    public void paint(){

        rectangle.fill();

    }

    public void erase(){
        rectangle.delete();
        rectangle.draw();
    }


    public int getCellSize(){
        return cellSize;
    }

    public int getCol(){
        return col;
    }


    public int getRow(){
        return row;
    }


    public boolean isPainted(){
        return painted;
    }

    public void setPainted(boolean painted){
        this.painted = painted;
    }
}


