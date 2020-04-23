package org.mapeditor;


import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.mapeditor.cells.Cell;
import org.mapeditor.cells.Cursor;

import java.io.*;

public class Grid implements KeyboardHandler {

    private Cursor cursor;
    private int cols;
    private int rows;
    private Cell[][] cells;
    private FileWriter file;
    private BufferedWriter bWriter;
    private FileReader fileReader;
    private BufferedReader bReader;



    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        cells = new Cell[rows][cols];
        gridInit();
        cursor = new Cursor();
        initKeyboard();

    }

    public void gridInit() {

        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                cells[col][row] = new Cell(col, row);
            }
        }

    }


    private void initKeyboard() {
        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent moveUp = new KeyboardEvent();
        moveUp.setKey(KeyboardEvent.KEY_UP);
        moveUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveUp);

        KeyboardEvent moveDown = new KeyboardEvent();
        moveDown.setKey(KeyboardEvent.KEY_DOWN);
        moveDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveDown);

        KeyboardEvent moveLeft = new KeyboardEvent();
        moveLeft.setKey(KeyboardEvent.KEY_LEFT);
        moveLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveLeft);

        KeyboardEvent moveRight = new KeyboardEvent();
        moveRight.setKey(KeyboardEvent.KEY_RIGHT);
        moveRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveRight);

        KeyboardEvent paintCell = new KeyboardEvent();
        paintCell.setKey(KeyboardEvent.KEY_SPACE);
        paintCell.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(paintCell);

        KeyboardEvent save = new KeyboardEvent();
        save.setKey(KeyboardEvent.KEY_S);
        save.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(save);

        KeyboardEvent write = new KeyboardEvent();
        write.setKey(KeyboardEvent.KEY_W);
        write.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(write);

    }


    public Cell getCell(int col, int row) {
        Cell a = cells[col][row];
        return a;

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_UP) {
            cursor.movesUp();
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) {
            cursor.moveDown(rows);
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) {
            cursor.moveLeft();
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
            cursor.moveRight(cols);
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {

            Cell cell = getCell(cursor.getCol(), cursor.getRow());

            if (!cell.isPainted()) {
                cell.setPainted(true);
                cell.paint();
                return;
            }

            cell.setPainted(false);
            cell.erase();
        }

         if(keyboardEvent.getKey() == KeyboardEvent.KEY_W){
             try { System.out.println("tecla w");
                 read();

             } catch (IOException e) {
                 e.printStackTrace();
             }

         }



        if(keyboardEvent.getKey() == KeyboardEvent.KEY_S){
            try{
                write();
            } catch (IOException e){
                e.getMessage();
            }
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


    public void write() throws IOException {
      try {
          file = new FileWriter("resources/cenas.txt");
          bWriter = new BufferedWriter(file);

          for (int col = 0; col < cols; col++) {
              for (int row = 0; row < rows; row++) {
                  if (cells[col][row].isPainted()) {
                      bWriter.write("0\n");
                      continue;
                  }
                  bWriter.write("1\n");
              }
          }

            bWriter.close();

         } catch (IOException e){
          e.getMessage();
         }
    }




    public void read() throws IOException{
        String line = "";
        String result = "";
        try {
            fileReader = new FileReader("resources/cenas.txt");
            bReader = new BufferedReader(fileReader);


            for (int col = 0; col < cols; col++) {
                for (int row = 0; row < rows; row++) {
                    result = bReader.readLine();
                    System.out.println(result);
                    if(result.equals("0")){

                        cells[col][row].setPainted(true);
                        cells[col][row].paint();
                    }

                    cells[col][row].setPainted(false);
                }

             }

            bReader.close();

         } catch (IOException e) {

            e.printStackTrace();

         }
    }
}

