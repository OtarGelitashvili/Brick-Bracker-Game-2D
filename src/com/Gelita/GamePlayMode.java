package com.Gelita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlayMode extends JPanel implements ActionListener, KeyListener {

   private Boolean play=false;
   private int score=0;
   private int totalBricks=21;
   private Timer timer;
   private int delay=8;

   private int playerX=310;

    private Target target;
    private int ballX=348;
    private int ballY=534;
    private int ballXdir=-1;
    private int ballYdir=-2;

    public GamePlayMode()
    {
        target=new Target(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
    }
    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);

        target.draw((Graphics2D)g);
           g.setColor(Color.yellow);


           g.setColor(Color.WHITE);
           g.setFont(new Font("serif",Font.BOLD,15));
           g.drawString(""+score,20,20);

        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);

        g.setColor(Color.white);
        g.fillOval(ballX,ballY,15,15);

            if(totalBricks<=0)
            {
                play=false;
                ballYdir=0;
                ballXdir=0;
                g.setColor(Color.white);
                g.setFont(new Font("serif",Font.BOLD, 20));
                g.drawString("You Won",200,300 );

                g.setFont(new Font("serif",Font.BOLD,20));
                g.drawString("Press Enter to Restart",250,350);
            }
        if(ballY>570)
        {
            play=false;
            ballYdir=0;
            ballXdir=0;
            g.setColor(Color.white);
            g.setFont(new Font("serif",Font.BOLD, 20));
            g.drawString("Game over :(" ,200,300 );

            g.setFont(new Font("serif",Font.BOLD, 20));
            g.drawString("Score:"+score ,225,325 );

            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Restart",250,350);
        }
        g.dispose();
    }
    public  void moveRight()
    {
        play=true;
        playerX+=20;

    }
    public void moveLeft()
    {
        play=true;
        playerX-=20;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        timer.start();
        if(play)
        {
            if(new Rectangle(ballX,ballY,15,15).intersects(new Rectangle(playerX,550,100,8)))
            {
                ballYdir=-ballYdir;
            }
           A: for(int i=0;i<target.targets.length;++i)
            {
                for(int j=0;j<target.targets[0].length;++j)
                {
                    if(target.targets[i][j]>0)
                    {
                        int brickX=j*target.brWidth+80;
                        int brickY=i*target.brHeight+50;
                        int brickHeight=target.brHeight;
                        int brickWidth=target.brWidth;

                        Rectangle rectangle=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballReact=new Rectangle(ballX,ballY,15,15);
                        Rectangle brickReact=rectangle;

                        if(ballReact.intersects(brickReact))
                        {
                            target.SetBrickvalue(0,i,j);
                            totalBricks--;
                            score+=5;

                        if(ballX+19<=brickReact.x || ballX+1>=brickReact.x+brickReact.width)
                        {
                            ballXdir=-ballXdir;
                        }
                        else
                        {
                            ballYdir=-ballYdir;
                        }
                        break A;
                    }
                    }
                 }
            }
            ballX+=ballXdir;
            ballY+=ballYdir;
            if(ballX>670)
            {
                ballXdir=-ballXdir;
            }
            if(ballX<0)
            {
                ballXdir=-ballXdir;
            }
            if(ballY<0)
            {
                ballYdir=-ballYdir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if(keyEvent.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            if(playerX>=600){
                playerX=600;
            }
            else
            {
                    moveRight();
            }
        }
        if(keyEvent.getKeyCode()==KeyEvent.VK_LEFT)
        {
            if(playerX<10)
            {
                playerX=10;
            }
            else
            {
                moveLeft();
            }
        }
        if(keyEvent.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(!play)
            {
                play=true;
                ballX=348;
                ballY=534;
                ballXdir=-1;
                ballYdir=-2;
                playerX=310;
                score=0;
                totalBricks=21;
                target=new Target(3,7);
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
