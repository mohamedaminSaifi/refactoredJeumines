package mines;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Board$MinesAdapter extends MouseAdapter {
   // $FF: synthetic field
   final Board this$0;

   Board$MinesAdapter(Board var1) {
      this.this$0 = var1;
   }

   public void mousePressed(MouseEvent var1) {
      int var2 = var1.getX();
      int var3 = var1.getY();
      int var4 = var2 / 15;
      int var5 = var3 / 15;
      boolean var6 = false;
      if (!this.this$0.inGame) {
         this.this$0.newGame();
         this.this$0.repaint();
      }

      if (var2 < this.this$0.cols * 15 && var3 < this.this$0.rows * 15) {
         int[] var10000;
         int var10001;
         if (var1.getButton() == 3) {
            if (this.this$0.field[var5 * this.this$0.cols + var4] > 9) {
               var6 = true;
               if (this.this$0.field[var5 * this.this$0.cols + var4] <= 19) {
                  if (this.this$0.mines_left > 0) {
                     var10000 = this.this$0.field;
                     var10001 = var5 * this.this$0.cols + var4;
                     var10000[var10001] += 10;
                     --this.this$0.mines_left;
                     this.this$0.statusbar.setText(Integer.toString(this.this$0.mines_left));
                  } else {
                     this.this$0.statusbar.setText("No marks left");
                  }
               } else {
                  var10000 = this.this$0.field;
                  var10001 = var5 * this.this$0.cols + var4;
                  var10000[var10001] -= 10;
                  ++this.this$0.mines_left;
                  this.this$0.statusbar.setText(Integer.toString(this.this$0.mines_left));
               }
            }
         } else {
            if (this.this$0.field[var5 * this.this$0.cols + var4] > 19) {
               return;
            }

            if (this.this$0.field[var5 * this.this$0.cols + var4] > 9 && this.this$0.field[var5 * this.this$0.cols + var4] < 29) {
               var10000 = this.this$0.field;
               var10001 = var5 * this.this$0.cols + var4;
               var10000[var10001] -= 10;
               var6 = true;
               if (this.this$0.field[var5 * this.this$0.cols + var4] == 9) {
                  this.this$0.inGame = false;
               }

               if (this.this$0.field[var5 * this.this$0.cols + var4] == 0) {
                  this.this$0.find_empty_cells(var5 * this.this$0.cols + var4);
               }
            }
         }

         if (var6) {
            this.this$0.repaint();
         }
      }

   }
}
