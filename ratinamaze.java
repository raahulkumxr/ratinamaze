import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class Ratmaze{

        int[][] sol; 
        int[][] maze;
        int n;
        JFrame jf, jf1;
        JTextField jtf, jtf1;
        JPanel[][] jps;
        JPanel jp1,jp2;
        Image mouse;
        JLabel jl, jl1;
        JButton jb,jb1;
        Random random;

        Ratmaze(){

            jb = new JButton("Generate Maze");
            jb.setBounds(110,200,200,30);
            // jl = new JLabel("Rows and Columns: ");
            // jl.setBounds(45,140,200,30);
            // jtf = new JTextField();
            // jtf.setBounds(190,140,200,30);

            jf1 = new JFrame("Rat");
            jf1.setSize(500,500);
            jf1.setLayout(null);
            jf1.add(jb);
            // jf1.add(jl);
            // jf1.add(jtf);
            
            jf1.setVisible(true);
            jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            jb.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    // n = Integer.parseInt(jtf.getText());
                    // maze = new int[n][n];
                    n = 5;
                    sol = new int[n][n];
                    // generatemaze(maze);

                    /* custom maze */
                    int [][] maze = {{1, 1, 0, 1, 0},
                                    {0, 1, 1, 1, 0}, 
                                    {1, 1, 0, 0, 1},
                                    {1, 0, 1, 1, 1},
                                    {1, 1, 1, 0, 1}};
                                    
                    for (int i = 0; i < n; i++){
                        for (int j = 0; j < n; j++){
                            sol[i][j] = 0; 
                        }
                    }
                    
                    jp1 = new JPanel(new GridLayout(n,n));
                    jp2 = new JPanel(null);
                    jb1 = new JButton("Rat in da maze");
                    jb1.setBounds(110,165,200,30);
                    jps = new JPanel[n][n];

                    jf = new JFrame("Rat In da Maze");
                    jf.setSize(500, 850);
                    jf.setLayout(new GridLayout(2,1));
                    jf.add(jp1);
                    jf.add(jp2);
                    jp2.add(jb1);

                    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    jf.setVisible(true);

                    printmaze(sol, maze);
                    jb1.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            if (movement(maze, sol, 0, 0)){
                                for (int i = 0; i < n; i++) {
                                    for (int j = 0; j < n; j++) {
                                        if (sol[i][j] == 1) {
                                            jps[i][j].setBackground(new Color(56,56,56));
                                        }
                                    }
                                }
                            }
                        }
                    });
                    
                }
            });
        }
        /* to create a randomized maze */
        // void generatemaze(int[][] maze){
        //     random = new Random();
        //     int row = 0;
        //     int column = 0; 
        //     for (int i = 0; i < n; i++){
        //         for (int j = 0; j < n; j++) maze[i][j] = 0;
        //     }
        //     maze[row][column] = 1;
        //     generatepassage(maze, row, column);
        // }
        
        // void generatepassage(int[][] maze, int row, int column){
        //     Random random = new Random();
        //     int currentRow = 0;
        //     int currentCol = 0;
        //     int endRow = n-1;
        //     int endCol = n-1;

        //     while (currentRow != endRow || currentCol != endCol) {
        //         maze[currentRow][currentCol] = 1; 
        //         int direction = random.nextInt(4); // 0: up, 1: right, 2: down, 3: left
        //         switch (direction) {
        //             case 0: // Up
        //                 if (currentRow > 0) currentRow--;
        //                 break;
        //             case 1: // Right
        //                 if (currentCol < n - 1) currentCol++;
        //                 break;
        //             case 2: // Down
        //                 if (currentRow < n - 1) currentRow++;
        //                 break;
        //             case 3: // Left
        //                 if (currentCol > 0) currentCol--;
        //                 break;
        //         }
        //     }
        //     maze[endRow][endCol] = 1;
        // }

        boolean canigo(int[][] maze, int x, int y){
            if ((x < n && x >= 0) && (y < n && y >= 0) && (maze[x][y] == 1) && (sol[x][y] == 0)) return true;
            else return false;
        }
        boolean movement(int[][] maze, int[][] sol, int x, int y){
            if (x == n-1 && y == n-1){
                sol[x][y] = 1;
                return true;
            } 
            if (canigo(maze, x, y) == true){
                sol[x][y] = 1;
                if (movement(maze, sol, x+1, y) == true) return true;
                if (movement(maze, sol, x, y+1) == true) return true;
                if (movement(maze, sol, x-1, y) == true) return true;
                if (movement(maze, sol, x, y-1) == true) return true;
                sol[x][y] = 0; 
                return false;
            }
            return false; 
        }
       
        void printmaze(int[][] sol, int[][] maze){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(" " + maze[i][j] + " ");
                    jps[i][j] = new JPanel();
                    jp1.add(jps[i][j]);
                    jps[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                    if (maze[i][j] == 1) jps[i][j].setBackground(new Color(255,255,255));
                    else jps[i][j].setBackground(new Color(128,128,128));
                }
                System.out.println();
            }
        }
                    
                   
                
        public static void main(String args[]){
            new Ratmaze();
        }
}

