import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class VaultMap extends Graph{
    String[] inputLine1;
    String[] inputLine2;

    VaultMap(String filename) throws IOException {
        //adjmap = new HashMap<>();
        // Instantiate Scanner and takes in input for how many mazes
        Scanner sc = new Scanner(new File(filename));
        int numMaze = sc.nextInt();
        sc.nextLine(); // scanner refresh

        while (numMaze != 0) {
            int num = 1;
            String[] dimensions = sc.nextLine().split(" ");
            int length = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);
            System.out.println("dimensions in");
            // ties the characters together into a graph
            // until num = height -1
            while (num < height){
                // splits the line and imports the info into a graph
                    String[] inputLine1 = sc.nextLine().split("|");
                    String[] inputLine2 = sc.nextLine().split("|");
                System.out.println(Arrays.toString(inputLine1)); // debug

                for (int i=1; i<length; i++){
                    // left to right
                    addEdge(inputLine1[i-1], inputLine1[i]);
                    // up to down
                    addEdge(inputLine1[i-1], inputLine2[i-1]);
                    addEdge(inputLine1[i], inputLine2[i]);
                }
                num++;
                System.out.println(Arrays.toString(inputLine2)); // debug
            }

            System.out.println("compiled. length : " + length + " height : " + height);
        }
    }
}
