import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ratinamaze extends mazegeneration{

        int[][] sol; 
        int [][] maze;
        int n;

        JFrame jf, jf1;
        JTextField jtf;
        JPanel[][] jps;
        JPanel jp1,jp2;
        JLabel jl;
        JButton jb,jb1;
        Random random;

        void init(){
            
            jb = new JButton("Generate Maze");
            jb.setBounds(110,200,200,30);
            jl = new JLabel("Rows and Columns: ");
            jl.setBounds(45,140,200,30);
            jtf = new JTextField();
            jtf.setBounds(190,140,200,30);
            

            jf1 = new JFrame("Rat");
            jf1.setSize(500,500);
            jf1.setLayout(null);
            jf1.add(jb);
            jf1.add(jl);
            jf1.add(jtf);

            
            jf1.setVisible(true);
            jf1.setLocationRelativeTo(null);
            jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            jb.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    n = Integer.parseInt(jtf.getText());

                    jp1 = new JPanel(new GridLayout(n,n));
                    jp2 = new JPanel(null);
                    jb1 = new JButton("Rat in da maze");
                    jb1.setBounds(110,165,200,30);

                    jps = new JPanel[n][n];
                    sol = new int[n][n];
                    maze = new int[n][n];

                    for (int i = 0; i < n; i++){
                        for (int j = 0; j < n; j++){
                            sol[i][j] = 0; 
                            maze[i][j] = 0;
                        }
                    }
                    
                    jf = new JFrame("Rat In da Maze");
                    jf.setSize(500, 850);
                    jf.setLayout(new GridLayout(2,1));
                    jf.add(jp1);
                    jf.add(jp2);
                    jp2.add(jb1);

                    generatemaze(maze, n);

                    jf.setVisible(true);
                    jf.setLocationRelativeTo(null);

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
                            else JOptionPane.showMessageDialog(jf, "No paths available for the generated maze!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                    
                }
            });
            
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
        }
        

        public ratinamaze(){
            init(); 
        }
   
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
            new ratinamaze();
        }
}

