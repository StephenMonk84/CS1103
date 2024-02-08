import java.util.*;

class Assign1{
    public static void main(String[] args){
        Scanner inp = new Scanner(System.in);
        System.out.println("Please enter text for analysis: ");
        String inpString = inp.nextLine();
        String newInp = "";
        // 1. This checks the text and removes punctuation and new lines
        newInp = checkInput(inpString);
        String newInpL = newInp.toLowerCase();
        char chFreq, chFreqL;
        //2. This first line calculates the total length of the string using builtin String functions
        System.out.println("The total length of the string is: "+ newInp.length() + " characters.");
        //3. This call counts the words
        System.out.println("The number of words are: " + wordCount(newInp));
        //4. The most common character case sensitive.
        System.out.println("The most common character is " + mostCommonChar(newInp)+ ".");
        //5. This line searches for a specific character and is case insensitive
        System.out.println("Please enter a character to search for: ");
        chFreq=inp.nextLine().charAt(0);
        chFreqL = Character.toLowerCase(chFreq);
        System.out.println("The character " + chFreq + " appears " + charFrequency(newInpL, chFreqL) + " times as both upper and lowercase.");
        //6. This searches for a specfic word and is case insensitive.
        System.out.println("Please enter a word to search for: ");
        String inpSearch = inp.nextLine();
        System.out.println("The word " + inpSearch + " appears " + wordFrequency(newInpL, inpSearch) + " times.");
       //7.  This counts the amount of unique words  case insensitive.
        System.out.println("The number of unique words is " + noOfUniqueWords(newInpL));
        inp.close();

    }

    public static String checkInput(String test){
        Scanner inpScan = new Scanner(System.in);
        if (test == null || test ==""){
            System.out.println("Please enter a string with characters. ");
            test = inpScan.nextLine();
        }
        String temp = test.replaceAll("[\n\r]", " ");
        String s = temp.replaceAll("[\\.,]", "");
        return s;
    }

    public static int wordCount(String inp){
        int count = 0;
        count = inp.split(" ").length;
        return count;
    }

    public static int charFrequency(String inp, char ch){
        int count = 0;
        for (int i =0; i < inp.length();i++){
            if(inp.charAt(i) == ch){
                count++;
            }
        }
        return count;

    }
    public static int wordFrequency(String temp, String word){
        int count = 0;

        String wordArr[] = temp.split(" ");

        for(int i=0; i < wordArr.length; i++){
            if(word.equals(wordArr[i])){
                count++;
            }
        }
        return count;
    }

    public static int noOfUniqueWords(String str){
        int count =1;
        String nextWord;
        String words[] = str.split(" ");
        List<String> uniqueWords = new ArrayList<String>(Arrays.asList(words));
        Collections.sort(uniqueWords);
        for(int i = 0; i < uniqueWords.size(); i++){
            if(uniqueWords.size() != 1+i){
                nextWord = uniqueWords.get(1+i);
            }
            else{
              nextWord = uniqueWords.get(i);  
            }
            if(!uniqueWords.get(i).equals(nextWord)){
                count++;
            }
        }
        return count;
    }

    public static String mostCommonChar(String inp){
        String str = "";
        String temp = inp.replaceAll("[\\s]", "");
        String arrayStr[] = temp.split("");
        int count = 0;
        for (int i = 0; i < arrayStr.length; i++) {
            int tempCount = 0;

            for (int j = 0; j < arrayStr.length; j++)
            {
                if (arrayStr[i].equals(arrayStr[j])) {
                    tempCount++;
                }
                if (tempCount > count) {
                    count = tempCount;
                    str = arrayStr[i];
                }
            }
        }
        
        return str;
    }

}