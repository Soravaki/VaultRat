import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class VaultMap extends Graph{
    VaultMap(String filename) throws IOException {
        //adjmap = new HashMap<>();
        // Instantiate Scanner and takes in input for how many mazes
        Scanner sc = new Scanner(new File(filename));
        int numMaze = sc.nextInt();
        sc.nextLine(); // scanner refresh

        while (numMaze != 0) {
            Graph g = new Graph();
            String[] dimensions = sc.nextLine().split(" ");
            int length = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);
            System.out.println("dimensions in");
            // ties the characters together into a graph
            // until num = height -1

            // creates 2d array that stores the vaultmap and another array to act its location
            String[][] maze = new String[height][length];
            int[][] adjMazeRef = new int[height][length];
            // creates maze
            for (int i=0; i<height; i++){
                maze[i] = sc.nextLine().split("|");
            }
            // creates maze reference for making adjacent list
            int num=0;
            for (int y=0; y<height; y++){
                for (int x=0; x<length; x++){
                    adjMazeRef[y][x] = num;
                    num++;
                }
            }

            // traverses 2d array to make adjList
            for (int y=0; y<height; y++){
                for (int x=0; x<length; x++){
                    System.out.print(adjMazeRef[y][x] + " ");
                    // addEdge to the right if in bounds
                    if (x<length-1 && !(hasVertex( Integer.toString(adjMazeRef[y][x]))))
                        g.addEdge(Integer.toString(adjMazeRef[y][x]), Integer.toString((adjMazeRef[y][x]+1)));

                    // addEdge to the left if in bounds
                    if (x>0 && !(hasVertex( Integer.toString(adjMazeRef[y][x]))))
                        g.addEdge(Integer.toString(adjMazeRef[y][x]), Integer.toString(( adjMazeRef[y][x]-1)));

                    // addEdge above if in bounds
                    if (y>0 && !(hasVertex( Integer.toString(adjMazeRef[y][x]))))
                        g.addEdge(Integer.toString(adjMazeRef[y][x]), Integer.toString((adjMazeRef[y][x]-height)));

                    // addEdge below if in bounds
                    if (y<height-1 && !(hasVertex( Integer.toString(adjMazeRef[y][x]))))
                        g.addEdge(Integer.toString(adjMazeRef[y][x]), Integer.toString((adjMazeRef[y][x]+height)));
                }
            }

            System.out.println(g.adjmap.keySet());
            /*for (int i=0; i<length*height; i++){
                System.out.println(adjmap.containsKey(Integer.toString(i)));
            }*/

            /*// debug - prints out maze
            for (int i=0; i<height; i++){
                System.out.println(Arrays.toString(adjMazeRef[i]));
            }*/

            System.out.println("compiled. length : " + length + " height : " + height);
        }
    }
}
