package com.Gelita;

import java.awt.*;

public class Target {
    public int targets[][];
    public int brWidth;
    public int brHeight;
    public void SetBrickvalue(int value, int row,int colm)
    {
        targets[row][colm]=value;
    }

    public Target(int row,int colm)
    {
        targets=new int[row][colm];
        for(int i=0;i<targets.length;++i)
        {
            for (int j=0;j<targets[0].length;++j)
            {
                targets[i][j]=1;
            }
        }
        brHeight=150/row;
        brWidth=540/colm;
    }
    public void draw(Graphics2D g)
    {
        for(int i=0;i<targets.length;++i)
        {
            for (int j=0;j<targets[0].length;++j)
            {
                if(targets[i][j]>0)
                {
                    g.setColor(Color.red);
                    g.fillRect(j*brWidth+80,i*brHeight+50,brWidth,brHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*brWidth+80,i*brHeight+50,brWidth,brHeight);
                }
            }
        }
    }
}
