/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slick.minesweeper;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.gui.TextField;
import java.awt.Font;

public class Play extends BasicGameState {
   //Extend from BasicGameState so we can have the window created
    Font awtFont;
    TrueTypeFont font;
    static String[][] game = new String[31][50]; //You can edit how big the board is.
    static String[][] board = new String[game.length][game[0].length];
    Input input;
    int xpos;
    int ypos;
    static boolean first;
   //A public string that will constantly be updated to show the mouse coordinates
   //We declare a new image and variables for it. We proceed to the init method
   public Play(int state) {
       //1st Method Declared
       //Constructor that accepts the parameters of the game state for mainMenu, so 0;

   }
   public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
       awtFont= new Font("Times New Roman", Font.PLAIN, 60);
       font= new TrueTypeFont(awtFont, false);
       initGame();       
       initBoard();
       first=true;
       //2nd Method Declared
       //Takes a GameContainer and a StateBasedGame object as parameters
       //We declare a new object of the Image clas; the picture we will be using
   }

   public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
       //3rd Method; Render allows for graphics; Graphics ALWAYS has the variable g; Text can be drawn with it
       for(int i=0;i<game.length;i++)
       {
           for(int k=0;k<game[0].length;k++)
           {   
               if(board[i][k].equals("-"))
                    g.drawImage(new Image("res/blank.gif"),k*16,i*16);
           }
       }
       for(int i=0;i<game.length;i++)
       {
           for(int k=0;k<game[0].length;k++)
           {   
               if(!(board[i][k].equals("-")))
               {
               if(board[i][k].equals("0"))
                   g.drawImage(new Image("res/open0.gif"),k*16,i*16);
               else
               if(board[i][k].equals("1"))
                   g.drawImage(new Image("res/open1.gif"),k*16,i*16);
               else
               if(board[i][k].equals("2"))
                   g.drawImage(new Image("res/open2.gif"),k*16,i*16);
               else
               if(board[i][k].equals("3"))
                   g.drawImage(new Image("res/open3.gif"),k*16,i*16);
               else
               if(board[i][k].equals("4"))
                   g.drawImage(new Image("res/open4.gif"),k*16,i*16);
               else
               if(board[i][k].equals("5"))
                   g.drawImage(new Image("res/open5.gif"),k*16,i*16);
               else
               if(board[i][k].equals("6"))
                   g.drawImage(new Image("res/open6.gif"),k*16,i*16);
               else
               if(board[i][k].equals("7"))
                   g.drawImage(new Image("res/open7.gif"),k*16,i*16);
               else
               if(board[i][k].equals("8"))
                   g.drawImage(new Image("res/open8.gif"),k*16,i*16);
               else
               if(board[i][k].equals("B"))
                   g.drawImage(new Image("res/bombdeath.gif"),k*16,i*16);
               else
               if(board[i][k].equals("F"))
                   g.drawImage(new Image("res/bombflagged.gif"),k*16,i*16);
               }
           }           
       }
       if(lose())
           font.drawString(225, 175, "YOU LOSE!", Color.red);
       else
       if(win())
           font.drawString(225, 175, "YOU WIN!", Color.green);
           
      
       

   }

   public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
       //4th Method; Takes 3 parameters; Updates images on screen; Essentially allows for stuff to move around/ have
       //animation on screen
       xpos = Mouse.getX();
       ypos = 496-Mouse.getY();
       input = gc.getInput();
       if(first&&input.isMousePressed(input.MOUSE_LEFT_BUTTON))
       {
            firstChoice(ypos/16,xpos/16);
            bombs(176); //You can edit how many bombs you want there to be.
            scan();  
            first=false;
       }
       else
        if(!lose())
        {
        if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)&&!(board[ypos/16][xpos/16].equals("F")))
            board[ypos/16][xpos/16]=game[ypos/16][xpos/16];
        else
        if(input.isMousePressed(input.MOUSE_RIGHT_BUTTON))
        {
            if(board[ypos/16][xpos/16].equals("-"))
                board[ypos/16][xpos/16]="F";
            else
            if(board[ypos/16][xpos/16].equals("F"))
                board[ypos/16][xpos/16]="-"; 
        }
       reveal();
        }
    }
   public int getID() {
       //5th Method; Method that returns the ID of this state; Since mainMenu has ID 0, it returns 0
       return 3;
   }
   public static void initGame()
    {
        for(int i=0;i<game.length;i++)
        {
            for(int k=0;k<game[0].length;k++)
            {
                game[i][k]="-";
            }
        }
    }
    public static void initBoard()
    {
        for(int i=0;i<game.length;i++)
        {
            for(int k=0;k<game[0].length;k++)
            {
                board[i][k]="-";
            }
        }
    }
    public static void firstChoice(int i, int k)
 {
    board[i][k]="0";
    game[i][k]="0";
    if(i==0&&k==0)
                 {
                   
                         game[i][k+1]="W";   
                      
                         game[i+1][k]="W";   
                   
                         game[i+1][k+1]="W";                            
                 }
                 else //Top Right Corner     
                 if(i==0&&k==game[0].length-1)
                 {
                   
                         game[i][k-1]="W";   
                    
                         game[i+1][k-1]="W";   
                    
                         game[i+1][k]="W";                              
                 }
                 else //Bottom Left Corner
                 if(i==game.length-1&&k==0)
                 {
                   
                         game[i-1][k]="W";  
                    
                         game[i-1][k+1]="W";  
                  
                         game[i][k+1]="W";                               
                 }
                 else //Bottom Right Corner
                 if(i==game.length-1&&k==game[game.length-1].length-1)
                 {
                   
                         game[i-1][k-1]="W";   
                   
                         game[i-1][k]="W";  
                   
                         game[i][k-1]="W";                               
                 }
                 else //Top Row
                 if(i==0)
                 {
                  
                         game[i][k-1]="W";   
                     
                         game[i+1][k-1]="W";   
                    
                         game[i+1][k]="W";   
                  
                         game[i+1][k+1]="W";   
                      
                         game[i][k+1]="W";                               
                 }
                 else //Left Column
                 if(k==0)
                 {
                   
                         game[i-1][k]="W";   
                     
                         game[i-1][k+1]="W";   
                   
                         game[i][k+1]="W";   
                     
                         game[i+1][k+1]="W";   
                  
                         game[i+1][k]="W";   
                      
                 }
                 else //Right Column
                 if(k==game[0].length-1)
                 {
                  
                         game[i-1][k]="W";   
                  
                         game[i-1][k-1]="W";  
                 
                         game[i][k-1]="W";   
                
                         game[i+1][k-1]="W";   
                  
                         game[i+1][k]="W";   
                      
                 }
                 else //Bottom Row
                 if(i==game.length-1)
                 {
                  
                         game[i][k-1]="W";  
                 
                         game[i-1][k-1]="W";   
                  
                         game[i-1][k]="W";  
                 
                         game[i-1][k+1]="W";   
                  
                         game[i][k+1]="W";   
                      
                 }
                 else
                 {
                  
                         game[i-1][k-1]="W";
                  
                         game[i-1][k]="W";
                   
                         game[i-1][k+1]="W";

                         game[i][k-1]="W";

                         game[i][k+1]="W";

                         game[i+1][k-1]="W";

                         game[i+1][k]="W";

                         game[i+1][k+1]="W";
                 }
 }

    public static void bombs(int x)
    {
        int num=0;
        int row=random(0, game.length-1);
        int col=random(0, game[0].length-1);
        while(num<x)
        {
            if(game[row][col].equals("-"))
            {
                game[row][col]="B";
                num++;
            }
            row=random(0, game.length-1);
            col=random(0,game[0].length-1);            
        }
    }
    public static void scan()
    {
        for(int i=0;i<game.length;i++)
        {
            for(int k=0;k<game[0].length;k++)
            {
                if(!game[i][k].equals("B"))
                {
                    game[i][k]=count(i,k)+"";
                }
            }
        }
    }
    public static boolean hasFlag()
    {
        for(int i=0;i<game.length;i++)
        {
            for(int k=0;k<game[i].length;k++)
            {
                if(board[i][k].equals("F"))
                    return true;
            }
        }
        return false;
    }
    public static boolean win()
    {
        for(int i=0;i<game.length;i++)
        {
            for(int k=0;k<game[0].length;k++)
            {
                if(game[i][k].equals("B"))
                    if(!board[i][k].equals("F"))
                        return false;
                if(first)
                    return false;
            }
        }
        return true;
    }
    public static boolean lose()
    {
        for(int i=0;i<game.length;i++)
        {
            for(int k=0;k<game[0].length;k++)
            {
                if(board[i][k].equals("B"))
                    return true;
            }
        }
        return false;
    }
    public static int count(int row, int col)
    {
        int ret=0;
        //Top Left Corner
        if(row==0&&col==0)
        {
            if(game[row][col+1].equals("B"))      
                ret++;
            if(game[row+1][col].equals("B"))      
                ret++;
            if(game[row+1][col+1].equals("B"))      
                ret++;
        }
        else //Top Right Corner        
        if(row==0&&col==game[0].length-1)
        {
            if(game[row][col-1].equals("B"))      
                ret++;
            if(game[row+1][col-1].equals("B"))      
                ret++;
            if(game[row+1][col].equals("B"))      
                ret++;
        }
        else //Bottom Left Corner
        if(row==game.length-1&&col==0)
        {
            if(game[row-1][col].equals("B"))      
                ret++;
            if(game[row-1][col+1].equals("B"))      
                ret++;
            if(game[row][col+1].equals("B"))      
                ret++;
        } 
        else //Bottom Right Corner
        if(row==game.length-1&&col==game[game.length-1].length-1)
        {
            if(game[row-1][col-1].equals("B"))      
                ret++;
            if(game[row-1][col].equals("B"))      
                ret++;
            if(game[row][col-1].equals("B"))      
                ret++;
        }
        else //Top Row
        if(row==0)
        {
            if(game[row][col-1].equals("B"))      
                ret++;
            if(game[row+1][col-1].equals("B"))      
                ret++;
            if(game[row+1][col].equals("B"))      
                ret++;
            if(game[row+1][col+1].equals("B"))      
                ret++;
            if(game[row][col+1].equals("B"))      
                ret++;
        }
        else //Left Column
        if(col==0)
        {
            if(game[row-1][col].equals("B"))      
                ret++;
            if(game[row-1][col+1].equals("B"))      
                ret++;
            if(game[row][col+1].equals("B"))      
                ret++;
            if(game[row+1][col+1].equals("B"))      
                ret++;
            if(game[row+1][col].equals("B"))      
                ret++;
        }
        else //Right Column
        if(col==game[0].length-1)
        {
            if(game[row-1][col].equals("B"))      
                ret++;
            if(game[row-1][col-1].equals("B"))      
                ret++;
            if(game[row][col-1].equals("B"))      
                ret++;
            if(game[row+1][col-1].equals("B"))      
                ret++;
            if(game[row+1][col].equals("B"))      
                ret++;
        }
        else //Bottom Row
        if(row==game.length-1)
        {
            if(game[row][col-1].equals("B"))      
                ret++;
            if(game[row-1][col-1].equals("B"))      
                ret++;
            if(game[row-1][col].equals("B"))      
                ret++;
            if(game[row-1][col+1].equals("B"))      
                ret++;
            if(game[row][col+1].equals("B"))      
                ret++;
        }
        else //Middle
        {
            if(game[row-1][col-1].equals("B"))      
                ret++;
            if(game[row-1][col].equals("B"))      
                ret++;
            if(game[row-1][col+1].equals("B"))      
                ret++;
            if(game[row][col-1].equals("B"))      
                ret++;
            if(game[row][col+1].equals("B"))      
                ret++;
            if(game[row+1][col-1].equals("B"))      
                ret++;
            if(game[row+1][col].equals("B"))      
                ret++;
            if(game[row+1][col+1].equals("B"))      
                ret++;
        }
        return ret;           
    }
    public static void reveal()
    {
        //Revealing Forwards
        for(int i=0;i<game.length;i++)
        {
            for(int k=0;k<game[0].length;k++)
            {                
                if(board[i][k].equals("0"))
                {
                    if(i==0&&k==0)
                    {
                        if(!board[i][k+1].equals("F"))
                            board[i][k+1]=game[i][k+1];      
                        if(!board[i+1][k].equals("F"))    
                            board[i+1][k]=game[i+1][k];      
                        if(!board[i+1][k+1].equals("F"))   
                            board[i+1][k+1]=game[i+1][k+1];                               
                    }
                    else //Top Right Corner        
                    if(i==0&&k==game[0].length-1)
                    {
                        if(!board[i][k-1].equals("F"))
                            board[i][k-1]=game[i][k-1];      
                        if(!board[i+1][k-1].equals("F"))    
                            board[i+1][k-1]=game[i+1][k-1];      
                        if(!board[i+1][k].equals("F"))    
                            board[i+1][k]=game[i+1][k];                                  
                    }
                    else //Bottom Left Corner
                    if(i==game.length-1&&k==0)
                    {
                        if(!board[i-1][k].equals("F"))
                            board[i-1][k]=game[i-1][k];      
                        if(!board[i-1][k+1].equals("F"))    
                            board[i-1][k+1]=game[i-1][k+1];     
                        if(!board[i][k+1].equals("F"))    
                            board[i][k+1]=game[i][k+1];                                  
                    } 
                    else //Bottom Right Corner
                    if(i==game.length-1&&k==game[game.length-1].length-1)
                    {
                        if(!board[i-1][k-1].equals("F"))
                            board[i-1][k-1]=game[i-1][k-1];      
                        if(!board[i-1][k].equals("F"))    
                            board[i-1][k]=game[i-1][k];      
                        if(!board[i][k-1].equals("F"))    
                            board[i][k-1]=game[i][k-1];                                  
                    }
                    else //Top Row
                    if(i==0)
                    {
                        if(!board[i][k-1].equals("F"))
                            board[i][k-1]=game[i][k-1];      
                        if(!board[i+1][k-1].equals("F"))    
                            board[i+1][k-1]=game[i+1][k-1];      
                        if(!board[i+1][k].equals("F"))    
                            board[i+1][k]=game[i+1][k];      
                        if(!board[i+1][k+1].equals("F"))    
                            board[i+1][k+1]=game[i+1][k+1];      
                        if(!board[i][k+1].equals("F"))    
                            board[i][k+1]=game[i][k+1];                                  
                    }
                    else //Left Column
                    if(k==0)
                    {
                        if(!board[i-1][k].equals("F"))
                            board[i-1][k]=game[i-1][k];      
                        if(!board[i-1][k+1].equals("F"))    
                            board[i-1][k+1]=game[i-1][k+1];      
                        if(!board[i][k+1].equals("F"))    
                            board[i][k+1]=game[i][k+1];      
                        if(!board[i+1][k+1].equals("F"))    
                            board[i+1][k+1]=game[i+1][k+1];      
                        if(!board[i+1][k].equals("F"))    
                            board[i+1][k]=game[i+1][k];      
                            
                    }
                    else //Right Column
                    if(k==game[0].length-1)
                    {
                        if(!board[i-1][k].equals("F"))
                            board[i-1][k]=game[i-1][k];      
                        if(!board[i-1][k-1].equals("F"))    
                            board[i-1][k-1]=game[i-1][k-1];     
                        if(!board[i][k-1].equals("F"))    
                            board[i][k-1]=game[i][k-1];      
                        if(!board[i+1][k-1].equals("F"))    
                            board[i+1][k-1]=game[i+1][k-1];      
                        if(!board[i+1][k].equals("F"))    
                            board[i+1][k]=game[i+1][k];      
                            
                    }
                    else //Bottom Row
                    if(i==game.length-1)
                    {
                        if(!board[i][k-1].equals("F"))
                            board[i][k-1]=game[i][k-1];      
                        if(!board[i-1][k-1].equals("F"))    
                            board[i-1][k-1]=game[i-1][k-1];      
                        if(!board[i-1][k].equals("F"))    
                            board[i-1][k]=game[i-1][k];      
                        if(!board[i-1][k+1].equals("F"))    
                            board[i-1][k+1]=game[i-1][k+1];      
                        if(!board[i][k+1].equals("F"))    
                            board[i][k+1]=game[i][k+1];      
                            
                    }
                    else
                    {
                        if(!board[i-1][k-1].equals("F"))
                            board[i-1][k-1]=game[i-1][k-1];
                        if(!board[i-1][k].equals("F"))  
                            board[i-1][k]=game[i-1][k];
                        if(!board[i-1][k+1].equals("F"))  
                            board[i-1][k+1]=game[i-1][k+1];
                        if(!board[i][k-1].equals("F")) 
                            board[i][k-1]=game[i][k-1];
                        if(!board[i][k+1].equals("F"))  
                            board[i][k+1]=game[i][k+1];
                        if(!board[i+1][k-1].equals("F"))  
                            board[i+1][k-1]=game[i+1][k-1];
                        if(!board[i+1][k].equals("F")) 
                            board[i+1][k]=game[i+1][k];
                        if(!board[i+1][k+1].equals("F")) 
                            board[i+1][k+1]=game[i+1][k+1];
                    }
                }
            }
        }
        //Revealing Backwards
        for(int i=game.length-1;i>=0;i--)
        {
            for(int k=game[0].length-1;k>=0;k--)
            {                
                if(board[i][k].equals("0"))
                {
                    if(i==0&&k==0)
                    {
                        if(!board[i][k+1].equals("F"))
                            board[i][k+1]=game[i][k+1];      
                        if(!board[i+1][k].equals("F"))    
                            board[i+1][k]=game[i+1][k];      
                        if(!board[i+1][k+1].equals("F"))   
                            board[i+1][k+1]=game[i+1][k+1];                               
                    }
                    else //Top Right Corner        
                    if(i==0&&k==game[0].length-1)
                    {
                        if(!board[i][k-1].equals("F"))
                            board[i][k-1]=game[i][k-1];      
                        if(!board[i+1][k-1].equals("F"))    
                            board[i+1][k-1]=game[i+1][k-1];      
                        if(!board[i+1][k].equals("F"))    
                            board[i+1][k]=game[i+1][k];                                  
                    }
                    else //Bottom Left Corner
                    if(i==game.length-1&&k==0)
                    {
                        if(!board[i-1][k].equals("F"))
                            board[i-1][k]=game[i-1][k];      
                        if(!board[i-1][k+1].equals("F"))    
                            board[i-1][k+1]=game[i-1][k+1];     
                        if(!board[i][k+1].equals("F"))    
                            board[i][k+1]=game[i][k+1];                                  
                    } 
                    else //Bottom Right Corner
                    if(i==game.length-1&&k==game[game.length-1].length-1)
                    {
                        if(!board[i-1][k-1].equals("F"))
                            board[i-1][k-1]=game[i-1][k-1];      
                        if(!board[i-1][k].equals("F"))    
                            board[i-1][k]=game[i-1][k];      
                        if(!board[i][k-1].equals("F"))    
                            board[i][k-1]=game[i][k-1];                                  
                    }
                    else //Top Row
                    if(i==0)
                    {
                        if(!board[i][k-1].equals("F"))
                            board[i][k-1]=game[i][k-1];      
                        if(!board[i+1][k-1].equals("F"))    
                            board[i+1][k-1]=game[i+1][k-1];      
                        if(!board[i+1][k].equals("F"))    
                            board[i+1][k]=game[i+1][k];      
                        if(!board[i+1][k+1].equals("F"))    
                            board[i+1][k+1]=game[i+1][k+1];      
                        if(!board[i][k+1].equals("F"))    
                            board[i][k+1]=game[i][k+1];                                  
                    }
                    else //Left Column
                    if(k==0)
                    {
                        if(!board[i-1][k].equals("F"))
                            board[i-1][k]=game[i-1][k];      
                        if(!board[i-1][k+1].equals("F"))    
                            board[i-1][k+1]=game[i-1][k+1];      
                        if(!board[i][k+1].equals("F"))    
                            board[i][k+1]=game[i][k+1];      
                        if(!board[i+1][k+1].equals("F"))    
                            board[i+1][k+1]=game[i+1][k+1];      
                        if(!board[i+1][k].equals("F"))    
                            board[i+1][k]=game[i+1][k];      
                            
                    }
                    else //Right Column
                    if(k==game[0].length-1)
                    {
                        if(!board[i-1][k].equals("F"))
                            board[i-1][k]=game[i-1][k];      
                        if(!board[i-1][k-1].equals("F"))    
                            board[i-1][k-1]=game[i-1][k-1];     
                        if(!board[i][k-1].equals("F"))    
                            board[i][k-1]=game[i][k-1];      
                        if(!board[i+1][k-1].equals("F"))    
                            board[i+1][k-1]=game[i+1][k-1];      
                        if(!board[i+1][k].equals("F"))    
                            board[i+1][k]=game[i+1][k];      
                            
                    }
                    else //Bottom Row
                    if(i==game.length-1)
                    {
                        if(!board[i][k-1].equals("F"))
                            board[i][k-1]=game[i][k-1];      
                        if(!board[i-1][k-1].equals("F"))    
                            board[i-1][k-1]=game[i-1][k-1];      
                        if(!board[i-1][k].equals("F"))    
                            board[i-1][k]=game[i-1][k];      
                        if(!board[i-1][k+1].equals("F"))    
                            board[i-1][k+1]=game[i-1][k+1];      
                        if(!board[i][k+1].equals("F"))    
                            board[i][k+1]=game[i][k+1];      
                            
                    }
                    else
                    {
                        if(!board[i-1][k-1].equals("F"))
                            board[i-1][k-1]=game[i-1][k-1];
                        if(!board[i-1][k].equals("F"))  
                            board[i-1][k]=game[i-1][k];
                        if(!board[i-1][k+1].equals("F"))  
                            board[i-1][k+1]=game[i-1][k+1];
                        if(!board[i][k-1].equals("F")) 
                            board[i][k-1]=game[i][k-1];
                        if(!board[i][k+1].equals("F"))  
                            board[i][k+1]=game[i][k+1];
                        if(!board[i+1][k-1].equals("F"))  
                            board[i+1][k-1]=game[i+1][k-1];
                        if(!board[i+1][k].equals("F")) 
                            board[i+1][k]=game[i+1][k];
                        if(!board[i+1][k+1].equals("F")) 
                            board[i+1][k+1]=game[i+1][k+1];
                    }
                }
            }
        }
    }
   public static int random(int min, int max)
   {
       return (int)(Math.random() * ((max - min) + 1)) + min;
   }
}
