package utils;

import java.io.*;
import java.util.*;

public class IOUtilities {
    private String inputFile;
    private String outputFile;

    public IOUtilities(String inputFile, String outputFile){
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public List<String> readInput(){
        List<String> lines = new ArrayList<>();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(this.inputFile));
            String line;
            while( (line=br.readLine()) != null ){
                lines.add(line);
            
            } 
            br.close();  

        } catch(IOException e){
            System.out.println("Error while reading the file: " + e.getMessage());
        }

        return lines;
    }

    public void writeOutput(List<String> outputLines){
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter(this.outputFile));
            for(String line : outputLines){
                bw.write(line);
            }
            bw.close();
        } catch(IOException e){
            System.out.println("Error while writing to file: " + e.getMessage());
        }
    }

}
