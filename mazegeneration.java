import java.util.Random;

public class mazegeneration {
        Random random;
        public void generatemaze(int[][] maze, int n) {
            Random random = new Random();
            int hurdleCount = (n * n) / 3; 
    
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    maze[i][j] = 1; 
                }
            }
    
            for (int i = 0; i < hurdleCount; i++) {
                int x = random.nextInt(n);
                int y = random.nextInt(n);
                maze[x][y] = 0; 
            }
    
            maze[0][0] = 1;
            maze[n - 1][n - 1] = 1; 
        }
}

