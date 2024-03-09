package Demogame;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.channels.FileChannel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements ActionListener, KeyListener {
    private boolean play=false;
    private int Score=0;

    private int totalBrikes=21;
    private Timer timer;
    private int dealy=8;
    private int ballposX=120;
    private int ballposY=350;
    private int ballXdir=-1;
    private int ballYdir=-2;
    private  int playerX=350;
//    private  int playerY=350;
    private MapGenrator map;
    public GamePlay(int i, int i1){
       addKeyListener(this);
       setFocusable(true);
       setFocusTraversalKeysEnabled(true);
        timer = new Timer(dealy,this);
        timer.start();
        map=new MapGenrator(3,7);
    }
    public void paint(Graphics g){
     g.setColor(Color.BLACK);
     g.fillRect(1,1,692,592);
     g.setColor(Color.yellow);
     g.fillRect(0,0,692,3);
     g.fillRect(0,3,3,592);
     g.fillRect(691,3,3,592);
//     paddle
       g.setColor(Color.green);
        g.fillRect(playerX,540,100,8);
//        brick
        map.draw((Graphics2D) g);
//        ball
        g.setColor(Color.red);
        g.fillOval(ballposX,ballposY,50,50);
//scoreg
        g.setColor(Color.GREEN);
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString("Score :"+Score,550,30);
//gameOver
        if(ballposY>=570){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.GREEN);
            g. setFont(new Font("serif",Font.BOLD,30));
            g.drawString("GameOver!!,Score:"+Score ,200,300);
            g. setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press enter to Resort !!,:" ,230,350);

        }
        if(totalBrikes<=0){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.GREEN);
            g. setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You Won!!,Score:"+Score ,200,300);
            g. setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press enter to Resort !!,:" ,230,350);

        }



    }




    private void moveLeft(){
        play=true;
        playerX-=20;
    }
    private void moveRight(){
        play=true;
        playerX+=20;
    }

    @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode()==KeyEvent .VK_LEFT){
           if(playerX<=0)
           playerX=0;
           else

               moveLeft();
       }
        if(e.getKeyCode()==KeyEvent .VK_RIGHT){

            if(playerX>=600)
                playerX=600;
            else
              moveRight();
        }
        if(e.getKeyCode()==KeyEvent .VK_RIGHT){

            if(!play){
              Score=0;
              totalBrikes=21;
              ballposX=120;
              ballposY=350;
              ballXdir=-1;
              ballYdir=-2;
              playerX=320;
              map=new MapGenrator(3,7);

          }
        }
        try {
            Thread.sleep(23);
        }
        catch(Exception p){
            p.printStackTrace();
        }

        repaint();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if(play){
           if(ballposX<0){
               ballXdir=-ballXdir;
           }
           if(ballposX>=645){
               ballXdir=-ballXdir;
           }
           if(ballposY<0){
               ballYdir=-ballYdir;
           }
           Rectangle ballRect=new Rectangle(ballposX,ballposY,50,50);
           Rectangle paddleRect=new Rectangle(playerX,550,100,8) ;


           if(ballRect.intersects(paddleRect)){
               ballYdir=-ballYdir;
           }
          A: for(int i=0;i<map.map.length;i++){
               for(int j=0;j<map.map[0].length;j++){
                   if(map.map[i][j]>0){
                       int width=map.brickwidth;
                       int height=map.brickheight;
                       int brickXpos=80+j*width;
                       int brickYpos=20+i*height;
                       Rectangle brickRect=new Rectangle(brickXpos,brickYpos,width,height);
                       if(ballRect.intersects(brickRect)){
                           map.SetBrick(0,i,j);
                           totalBrikes--;
                           Score+=5;
                           if(ballposX+19<=brickXpos || ballposX+1>=brickXpos+width){
                               ballXdir=-ballXdir;
                           }
                           else{
                               ballYdir=-ballYdir;
                           }
                           break A;

                       }
                   }
               }
           }
           ballposX+=ballXdir;
           ballposY+=ballYdir;
       }
       repaint();
//
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
