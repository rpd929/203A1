import java.util.*;
import java.io.*; 

public class a1 {
    
    private static int index = 0; 
    private static String words[];
    public static String sampleData[]; 
    private static int palindromeCount = 0;
    private static int sampleWordCount = 0;
    public static int uniqueWordCount = 0;
    public static String dictionaryFileString = "dictionary.txt";
    public static String sampleFileString = "sample.txt";
    public static int lineIndex = 0;
    public static int letterPostions[][];


    

    public static void main(String[] args) { 
    
       
        
       
        words = new String [400000];
        sampleData = new String [1000];
    
       
        File file = new File(dictionaryFileString);  

        long start = System.currentTimeMillis();
        


        try {

            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String newWord;
            while (br.ready()) {
                newWord =  br.readLine();
                push(newWord);
            }
            br.close();
           
            
            for (int counter = 0; counter < index; counter++) {
                String reverseWord = "";
                for(int letter = words[counter].length(); letter > 0; letter--) {
                    reverseWord = reverseWord + words[counter].charAt(letter -1);

                }
                binarySearch(0, index, reverseWord);
            }
           
            //linearSearch();
            long finish = System.currentTimeMillis();
         
            long timeElapsed = finish - start;
            System.out.println("pallindromes: " + palindromeCount);

            System.out.println("Number of words: " + index);
            System.out.println("Time in Seconds: " + Double.valueOf(timeElapsed)  /1000 + " sec");
           

            //Start of step 3
           
           
            file = new File(sampleFileString);  
            //readInFile(file);
            System.out.println("total words: " + sampleWordCount);
            System.out.println("Unique words: " + uniqueWordCount);


            //Start of step 4
            sortArrays();
        



        } catch(IOException e) {

        }
    }

    public static void push(String word) {
        words[index] = word;
        index = index + 1;
    }

    public static String top() {

        return words[index];
        
    }

    public static void readInFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String newLine;
            while (br.ready()) {
                newLine =  br.readLine();
                preprocess(newLine);
                lineIndex++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());

        }

        
    }


    public static void linearSearch() {

        int letterCount = 0;
        String reverseWord = new String();
        String originalWord = new String();

        // For every word in words, set the letterCount = the length of each word
        for ( int x = 0; x < index; x++) {
            letterCount = words[x].length();
            
            //For Every letter in the word, add it in reverse order to the new reverse word
            for (int count = letterCount - 1; count >= 0; count--) {                                       
                reverseWord = reverseWord + words[x].charAt(count);   
                originalWord = words[x];
            }  
            
            //For every reversedWord, if it is equal to a word in the dictionary, increment palindrome count and print the reverseWord found. 
            for (int counter = 0; counter < index; counter++) {
                if(words[counter].equals(reverseWord) && reverseWord.length() > 1) {
                    palindromeCount++;
                    System.out.println(palindromeCount + ". " + originalWord + " : " + reverseWord);
                }

                if(palindromeCount > 4) {
                    return;
                }
           }
                reverseWord = "";
        }

    }

    public static void preprocess(String line) {
        String procWord;
        String[] splitLine = line.split("\\s+");

        for(int x = 0; x < splitLine.length; x++ ) {
           
            procWord = splitLine[x];
           
            for (int count = 0; count < procWord.length(); count++) {
         
                char letter = procWord.charAt(count);
               
                
                if (Character.isLetter(letter)) {
                   
    
                } else {
           

                    procWord = procWord.substring(0, count) + procWord.substring(count + 1);
                   
                }
                }
                procWord = procWord.toLowerCase();
               
                if (procWord.equals("")) { 
                    return;
                } else {
                    uniquePush(procWord);
                    sampleWordCount++;
                }
               
            }
        }


    public static void uniquePush(String word) {

            if( sampleWordCount == 0 ) {
                sampleData[0] = word;
                System.out.println("First Insert");
            } else {
                for (int x = 0; x < uniqueWordCount; x++) {
                    if(sampleData[x].equals(word)) {
                        System.out.println(word +" already been read!");
                        return;
                    } else {
    
                    }    
                }
                System.out.println("adding " + word + " to unique list!");
                sampleData[uniqueWordCount] = word;
                uniqueWordCount++;
            }
            
        }



    public static void binarySearch(int searchStart, int searchEnd, String reverseWord) {
       
        while (searchStart <= searchEnd) { 
           
             int middle = searchStart + (searchEnd - searchStart) / 2; 
             
            // Check if x is present at mid 
            if (middle > searchEnd) {
                System.out.println("exit");
                System.exit(0);
            }
            if (middle == index ) {
                return;
            }
            if ( words[middle].equals(reverseWord)  && middle < index ) {
                //System.out.println("Found!: " + words[middle]);
                palindromeCount++;
                return;
            }
  
            // If reverseWord greater, ignore left half 
            if (words[middle].compareTo(reverseWord) < 0) {
                /*
                System.out.println("IGNORE LEFT! reverseWord: " + reverseWord + " word: " + words[middle]);
                System.out.println("start: "  + searchStart + " middle: " + middle + " end: " + searchEnd );
                */
                searchStart = middle + 1; 
            }
            // If x is smaller, ignore right half 
            else if(words[middle].compareTo(reverseWord) > 0) {
                /*
                System.out.println("IGNORE Right! reverseWord: " + reverseWord + " word: " + words[middle]);
                System.out.println("start: "  + searchStart + " middle: " + middle + " end: " + searchEnd );
               
                */
                searchEnd = middle - 1;

            } else {
                System.out.println(words[middle].compareTo(reverseWord));
                System.exit(0);

            }

        } 
       
   }


    public static void sortArrays() {
        int position = 0;
        letterPostions = new int[26][2];
        
        for (char letter = 'a'; letter <= 'z'; letter++ ) {
            int first = -1;
            int last = -1;
            
            letterLoop:
            for( int counter = 0; counter < index; counter++) {
           
                if(letter == words[counter].charAt(0) && first == -1 ) {
                  
                    first = counter;
                    

                } else if(letter < words[counter].charAt(0)) {
                   
                    last = counter - 2;
                    letterPostions[position][0] = first;
                    letterPostions[position][1] = last;
                    System.out.println(letter + " starts at " + first + " ends at " + last);
                    break letterLoop;
                
                }
            }

            if(letter == 'z') {
                letterPostions[25][1] = index;
            }
            
            position++;
        }
    }

}
