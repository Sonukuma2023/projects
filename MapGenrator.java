package Demogame;

import java.awt.*;

import static java.awt.Color.black;


public class MapGenrator {
    public int map [][];
    public int brickwidth;
    public int brickheight;
    public MapGenrator(int row,int col) {
        map = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = 1;
            }
        }
        brickwidth = 540 / col;
        brickheight = 150 / row;
    }
        public void SetBrick(int value ,int r,int c){
            map[r][c]=value;

        }
        public void draw(Graphics2D g){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]>0){
                    g.setColor(Color.white);
                    g.fillRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight);
                    g.setColor(Color.BLACK);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight);


                }
            }
        }
        }


}
