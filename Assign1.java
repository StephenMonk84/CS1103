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
        char chFreq;
        //2. This first line calculates the total length of the string using builtin String functions
        System.out.println("The total length of the string is: "+ newInp.length() + " characters.");
        //3. This call counts the words
        System.out.println("The number of words are: " + wordCount(newInp));
        //4. The most common character case sensitive.
        System.out.println("The most common character is " + mostCommonChar(newInp)+ ".");
        //5. This line searches for a specific character and is case insensitive
        System.out.println("Please enter a character to search for: ");
        String charSearch = inp.nextLine();
        chFreq=checkChar(charSearch);
        System.out.println("The character " + chFreq + " appears " + charFrequency(newInpL, chFreq) + " times as both upper and lowercase.");
        //6. This searches for a specfic word and is case insensitive.
        System.out.println("Please enter a word to search for: ");
        String inpSearch = inp.nextLine();
        String inpValid = checkInput(inpSearch);
        System.out.println("The word " + inpSearch + " appears " + wordFrequency(newInpL, inpValid) + " times.");
       //7.  This counts the amount of unique words  case insensitive.
        System.out.println("The number of unique words is " + noOfUniqueWords(newInpL));
        inp.close();

    }

    public static String checkInput(String test){
        /** This method validates string inputs and prompts the user if the input is empty or null
         * This method also removes any special characters and punctuation like periods and commas
         */
        Scanner inpScan = new Scanner(System.in);
        while(true){
            if (test.equals(null) || test.equals("") || test.equals(" ")){
                System.out.println("Please enter a string with characters. ");
                test = inpScan.nextLine();
            }
            else{
                break;
            }
        }
        String temp = test.replaceAll("[\n\r]", " ");
        String s = temp.replaceAll("[\\.,]", "");
        return s;
    }

    public static int wordCount(String inp){
        /**This method counts the number of words by searching for whitespaces and splitting the string */
        int count = 0;
        count = inp.split(" ").length;
        return count;
    }

    public static int charFrequency(String inp, char ch){
        /** This method counts the frequenct of the selected character this includes whitespace */
        int count = 0;
        for (int i =0; i < inp.length();i++){
            if(inp.charAt(i) == ch){
                count++;
            }
        }
        return count;

    }

    public static int wordFrequency(String temp, String word){
        /**This method counts the number of instances that a word appears this is case insensitive */
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
        /** This method counts the number of unique words in the string, this method uses a List of Strings and sorts them and then counts whenever a new word is encountered */
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
        /**This method counts the most common character by removing whitespace and then counting each letter and then comparing with the next letter */
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

    public static char checkChar(String str){
        /**This method checks for null or empty input then sends back a valid character for searching */
        Scanner inp = new Scanner(System.in);
        char chTemp, ch;
        while(true){
            if(str.isEmpty() || str.equals("") || str.equals(" ")){
                System.out.println("Please enter a character to search for: ");
                str = inp.nextLine();
                chTemp=str.charAt(0);
                ch = Character.toLowerCase(chTemp);
            } else{
                break;
            }
        }
        ch = str.charAt(0);
        return ch;
    }

}