/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class TagFinder {
    public String findProtein(String dna) {
        /*int start = dna.indexOf("atg");
                if (start == -1) {
            return "";
        }*/
        int start = 0;
        while (true) {
            start = dna.indexOf("atg", start);
            if (start == -1) {
                break;
            }
            System.out.println("Start codon at: " + start);
            start = start + 3;
        }
          
        
        
        
        
        int stop = dna.indexOf("tag", start+3);
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "";
        }
    }
    
    public int findStopIndex(String dna, int index) {
        int stop1 = dna.indexOf("tag", index);
        if (stop1 == -1 || (stop1 - index) %3 != 0) {
            stop1 = dna.length();
            //break;
        }
        int stop2 = dna.indexOf("tga", index);
        if (stop2 == -1 || (stop2 - index) %3 != 0) {
            stop2 = dna.length();
            //break;
        }
        int stop3 = dna.indexOf("taa", index);
        if (stop3 == -1 || (stop3 - index) %3 != 0) {
            stop3 = dna.length();
            //break;
        }
        System.out.println("result: " + stop1 + " | " + stop2 + " | " + stop3);
        
        return Math.min(stop1, Math.min(stop2, stop3));
    }
    
    public void testStopIndex() {
        String a = "aatgctgacctgatag";
        int expectedResult = 5;
        int index = 1;
        
        int result = findStopIndex(a, index);
        if (expectedResult == result) {
            System.out.println("Success! Result " + result + " = " + expectedResult);
        }
        else {
            System.out.println("Failure! Result " + result + " <> " + expectedResult);
        }
    }
    
    
    public void testing() {
        String a = "cccatggggtttaaataataataggatgagagagagagagttt";
        String ap = "atggggtttaaataataatag";
        //String a = "atgcctag";
        //String ap = "";
        //String a = "ATGCCCTAG";
        //String ap = "ATGCCCTAG";
        String result = findProtein(a);
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }

    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length() + " characters");
            String result = findProtein(s);
            System.out.println("found " + result);
        }
    }
}
