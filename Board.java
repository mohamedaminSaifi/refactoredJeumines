package mines;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel {
   private static final long serialVersionUID = 6195235521361212179L;
   private final int NUM_IMAGES = 13;
   private final int CELL_SIZE = 15;
   private final int COVER_FOR_CELL = 10;
   private final int MARK_FOR_CELL = 10;
   private final int EMPTY_CELL = 0;
   private final int MINE_CELL = 9;
   private final int COVERED_MINE_CELL = 19;
   private final int MARKED_MINE_CELL = 29;
   private final int DRAW_MINE = 9;
   private final int DRAW_COVER = 10;
   private final int DRAW_MARK = 11;
   private final int DRAW_WRONG_MARK = 12;
   private int[] field;
   private boolean inGame;
   private int mines_left;
   private Image[] img;
   private int mines = 40;
   private int rows = 16;
   private int cols = 16;
   private int all_cells;
   private JLabel statusbar;

   public Board(JLabel var1) {
      this.statusbar = var1;
      this.img = new Image[13];

      for(int var2 = 0; var2 < 13; ++var2) {
         this.img[var2] = (new ImageIcon(this.getClass().getClassLoader().getResource(var2 + ".gif"))).getImage();
      }

      this.setDoubleBuffered(true);
      this.addMouseListener(new mines.Board.MinesAdapter(this));
      this.newGame();
   }

   public void newGame() {
      boolean var3 = false;
      boolean var4 = false;
      boolean var5 = false;
      Random var1 = new Random();
      this.inGame = true;
      this.mines_left = this.mines;
      this.all_cells = this.rows * this.cols;
      this.field = new int[this.all_cells];

      int var6;
      for(var6 = 0; var6 < this.all_cells; ++var6) {
         this.field[var6] = 10;
      }

      this.statusbar.setText(Integer.toString(this.mines_left));
      var6 = 0;

      while(var6 < this.mines) {
         int var7 = (int)((double)this.all_cells * var1.nextDouble());
         if (var7 < this.all_cells && this.field[var7] != 19) {
            int var2 = var7 % this.cols;
            this.field[var7] = 19;
            ++var6;
            int var10002;
            int var8;
            if (var2 > 0) {
               var8 = var7 - 1 - this.cols;
               if (var8 >= 0 && this.field[var8] != 19) {
                  var10002 = this.field[var8]++;
               }

               var8 = var7 - 1;
               if (var8 >= 0 && this.field[var8] != 19) {
                  var10002 = this.field[var8]++;
               }

               var8 = var7 + this.cols - 1;
               if (var8 < this.all_cells && this.field[var8] != 19) {
                  var10002 = this.field[var8]++;
               }
            }

            var8 = var7 - this.cols;
            if (var8 >= 0 && this.field[var8] != 19) {
               var10002 = this.field[var8]++;
            }

            var8 = var7 + this.cols;
            if (var8 < this.all_cells && this.field[var8] != 19) {
               var10002 = this.field[var8]++;
            }

            if (var2 < this.cols - 1) {
               var8 = var7 - this.cols + 1;
               if (var8 >= 0 && this.field[var8] != 19) {
                  var10002 = this.field[var8]++;
               }

               var8 = var7 + this.cols + 1;
               if (var8 < this.all_cells && this.field[var8] != 19) {
                  var10002 = this.field[var8]++;
               }

               var8 = var7 + 1;
               if (var8 < this.all_cells && this.field[var8] != 19) {
                  var10002 = this.field[var8]++;
               }
            }
         }
      }

   }

   public void find_empty_cells(int var1) {
      int var2 = var1 % this.cols;
      int[] var10000;
      int var3;
      if (var2 > 0) {
         var3 = var1 - this.cols - 1;
         if (var3 >= 0 && this.field[var3] > 9) {
            var10000 = this.field;
            var10000[var3] -= 10;
            if (this.field[var3] == 0) {
               this.find_empty_cells(var3);
            }
         }

         var3 = var1 - 1;
         if (var3 >= 0 && this.field[var3] > 9) {
            var10000 = this.field;
            var10000[var3] -= 10;
            if (this.field[var3] == 0) {
               this.find_empty_cells(var3);
            }
         }

         var3 = var1 + this.cols - 1;
         if (var3 < this.all_cells && this.field[var3] > 9) {
            var10000 = this.field;
            var10000[var3] -= 10;
            if (this.field[var3] == 0) {
               this.find_empty_cells(var3);
            }
         }
      }

      var3 = var1 - this.cols;
      if (var3 >= 0 && this.field[var3] > 9) {
         var10000 = this.field;
         var10000[var3] -= 10;
         if (this.field[var3] == 0) {
            this.find_empty_cells(var3);
         }
      }

      var3 = var1 + this.cols;
      if (var3 < this.all_cells && this.field[var3] > 9) {
         var10000 = this.field;
         var10000[var3] -= 10;
         if (this.field[var3] == 0) {
            this.find_empty_cells(var3);
         }
      }

      if (var2 < this.cols - 1) {
         var3 = var1 - this.cols + 1;
         if (var3 >= 0 && this.field[var3] > 9) {
            var10000 = this.field;
            var10000[var3] -= 10;
            if (this.field[var3] == 0) {
               this.find_empty_cells(var3);
            }
         }

         var3 = var1 + this.cols + 1;
         if (var3 < this.all_cells && this.field[var3] > 9) {
            var10000 = this.field;
            var10000[var3] -= 10;
            if (this.field[var3] == 0) {
               this.find_empty_cells(var3);
            }
         }

         var3 = var1 + 1;
         if (var3 < this.all_cells && this.field[var3] > 9) {
            var10000 = this.field;
            var10000[var3] -= 10;
            if (this.field[var3] == 0) {
               this.find_empty_cells(var3);
            }
         }
      }

   }

   public void paint(Graphics var1) {
      boolean var2 = false;
      int var3 = 0;

      for(int var4 = 0; var4 < this.rows; ++var4) {
         for(int var5 = 0; var5 < this.cols; ++var5) {
            int var6 = this.field[var4 * this.cols + var5];
            if (this.inGame && var6 == 9) {
               this.inGame = false;
            }

            if (!this.inGame) {
               if (var6 == 19) {
                  var6 = 9;
               } else if (var6 == 29) {
                  var6 = 11;
               } else if (var6 > 19) {
                  var6 = 12;
               } else if (var6 > 9) {
                  var6 = 10;
               }
            } else if (var6 > 19) {
               var6 = 11;
            } else if (var6 > 9) {
               var6 = 10;
               ++var3;
            }

            var1.drawImage(this.img[var6], var5 * 15, var4 * 15, this);
         }
      }

      if (var3 == 0 && this.inGame) {
         this.inGame = false;
         this.statusbar.setText("Game won");
      } else if (!this.inGame) {
         this.statusbar.setText("Game lost");
      }

   }
}
