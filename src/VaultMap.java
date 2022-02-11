import java.io.File;
import java.io.IOException;
import java.util.*;

public class VaultMap extends Graph{
    String[][] maze;
    int[][] adjMazeRef;
    VaultMap(String filename) throws IOException {
        //adjmap = new HashMap<>();
        // Instantiate Scanner and takes in input for how many mazes
        Scanner sc = new Scanner(new File(filename));
        int numMaze = sc.nextInt();
        sc.nextLine(); // scanner refresh

        while (numMaze != 0) {
            Graph g = new Graph();
            String[] dimensions = sc.nextLine().split(" ");
            int height = Integer.parseInt(dimensions[0]);
            int length = Integer.parseInt(dimensions[1]);
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
                    //System.out.print(adjMazeRef[y][x] + " ");
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
            //System.out.println(g.adjmap.keySet());
            /*for (int i=0; i<length*height; i++){
                System.out.println(adjmap.containsKey(Integer.toString(i)));
            }*/
            // debug - prints out maze
            for (int i=0; i<height; i++){
                System.out.println(Arrays.toString(maze[i]));
            }
            //System.out.println("compiled. length : " + length + " height : " + height + " num : " +numMaze );
            // subtracts 1 maze from the total needed to be compiled
            pathfinding(maze, adjMazeRef);
            numMaze--;
        }
    }

    public void pathfinding(String[][] mazeArray, String[][] adjMazeArray){
        // instantiate objects for pathfinding
        Queue q = new LinkedList<>();
        int[] startPosCords = new int[2];

        // nested for loop to iterate through the entire maze to find the starting point
        for (int y=0; y<mazeArray.length; y++){
            for (int x=0; x<mazeArray[y].length; x++){
                if (Objects.equals(mazeArray[y][x], "S")) {
                    // stores startPos into array that houses the cords
                    startPosCords[0] = y;
                    startPosCords[1] = x;
                }
            }
        }
        int startPos = adjMazeRef[startPosCords[0]][startPosCords[1]];
        System.out.println(startPos);
    }
}
