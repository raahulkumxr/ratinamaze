import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class Ratmaze{
        int[][] maze; 
        int[][] sol; 
        int n;
        JFrame jf;
        Image mouse;
        Ratmaze(int n, int[][] maze){
            try{
                mouse = ImageIO.read(new File("mouse.jpg"));
            }catch(IOException e){}
            jf = new JFrame("Rat In da Maze");
            jf.setSize(500,500);
            jf.setLayout(new GridLayout(1,1));
            sol = new int[n][n];
            this.n = n;
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    sol[i][j] = 0; 
                }
            }
            this.maze = maze; 
            printmaze(sol, this.maze);
            jf.setVisible(true);
        }
        boolean canigo(int[][] maze, int x, int y){
            if ((x < n && x >= 0) && (y < n && y >= 0) && (maze[x][y] == 1)) return true;
            else return false;
        }
        boolean movement(int[][] maze, int[][] sol, int x, int y){
            if (x == n-1 & y == n-1){
                sol[x][y] = 1;
                return true;
            } 
            if (canigo(maze, x, y) == true){
                sol[x][y] = 1;

                jf.repaint();

                if (movement(maze, sol, x+1, y) == true){
                    return true;
                }
                if (movement(maze, sol, x, y+1) == true){
                    return true;
                }
                
                sol[x][y] = 0; 
                return false;
                
            }
            return false; 
        }
       
        void printmaze(int[][] sol, int[][] maze) {
            if (movement(maze, sol, 0, 0)){
                JPanel panel = new JPanel() {
                @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < n; j++) {
                                if (maze[i][j] == 1) {
                                    g.setColor(new Color(255, 255, 255));
                                } else {
                                    g.setColor(new Color(128, 128, 128));
                                }
                                g.fillRect(j * 100, i * 100, 100, 100);
                                if (sol[i][j] == 1) {
                                    g.drawImage(mouse, j * 50, i * 50, 50, 50, null);
                                }
                            }
                        }
                    }
                };
                panel.setSize(500,500);
                jf.add(panel);
                jf.revalidate();
            }
        }
        public static void main(String args[]){
            int n = 5;
            int[][] maze = {{1, 1, 0, 0, 0},
                            {0, 1, 1, 0, 0},
                            {1, 0, 1, 1, 0},
                            {1, 1, 0, 1, 1},
                            {0, 1, 1, 1, 1}};
            new Ratmaze(n, maze);
        }
}