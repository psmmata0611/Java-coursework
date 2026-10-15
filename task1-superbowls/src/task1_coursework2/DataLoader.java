package task1_coursework2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataLoader {

    private ArrayList<SuperBowls> SuperB;

    public DataLoader() {
        SuperB = new ArrayList<>();

        String filePath = System.getProperty("user.dir") + File.separator + "superbowls.txt";
        Scanner input = null;

        try {
            input = new Scanner(new File(filePath));

            while (input.hasNext()) {
                String line = input.nextLine().trim(); // Read and trim the line
                String[] Firstpart = line.split("\\|"); // Split the line by "|"
                
                // Extract the year and date from the first segment
                String[] Secpart = Firstpart[0].split("\\(");
                String date = Secpart[1].replace(")", "").trim();

                // Create a new SuperBowls object with the parsed data
                SuperBowls w = new SuperBowls(
                        Integer.parseInt(Secpart[0].trim()),
                        date,
                        Firstpart[1].trim(),
                        Firstpart[2].trim(),
                        Integer.parseInt(Firstpart[3].trim()),
                        Firstpart[4].trim(),
                        Integer.parseInt(Firstpart[5].trim()),
                        Firstpart[6].trim(),
                        Firstpart[7].trim(),
                        Firstpart[8].trim(),
                        Firstpart[9].trim());
                SuperB.add(w);
            }

        } catch (FileNotFoundException fnf) {
            System.out.println("Cannot locate file");
            System.exit(0);
        }
    }

    public ArrayList<SuperBowls> getSuperB() {
        return SuperB;
    }

}
