import java.util.*;

class Assign1{
    public static void main(String[] args){
        Scanner inp = new Scanner(System.in);
        System.out.println("Please enter text for analysis: ");
        String newInp = inp.nextLine();
        char chFreq, chFreqL;
        //This first line calculates the total length of the string using builtin String functions
        System.out.println("The total length of the string is: "+ newInp.length() + " characters.");
        System.out.println("The number of words are: " + wordCount(newInp));
        System.out.println("Please enter a character to search for: ");
        chFreq=inp.nextLine().charAt(0);
        chFreqL = Character.toLowerCase(chFreq);
        System.out.println("The character " + chFreq + " appears " + charFrequency(newInp, chFreqL) + " times as both upper and lowercase.");
        //checkInput(inp);
        System.out.println("Please enter a word to search for: ");
        String inpSearch = inp.nextLine();
        System.out.println("The word " + inpSearch + " appears " + wordFrequency(newInp, inpSearch) + " times.");
        System.out.println("The number of unique words is " + noOfUniqueWords(newInp));
        inp.close();

    }

    public static void checkInput(Object test){
        if(test instanceof String){
            System.out.println("Its a string");
        }
        else{
            System.out.println("Please input something else");
        }
    }

    public static int wordCount(String inp){
        int count = 0;
        count = inp.split(" ").length;
        return count;
    }

    public static int charFrequency(String inp, char ch){
        int count = 0;
        String inpL = inp.toLowerCase();
        for (int i =0; i < inpL.length();i++){
            if(inpL.charAt(i) == ch){
                count++;
            }
        }
        return count;

    }
    public static int wordFrequency(String inp, String word){
        int count = 0;
        String wordArr[] = inp.split(" ");

        for(int i=0; i < wordArr.length; i++){
            if(word.equals(wordArr[i])){
                count++;
            }
        }
        return count;
    }

    public static int noOfUniqueWords(String inp){
        String inpSplit[] = inp.split(" ");
        int count = 0;
        boolean[] boolArr = new boolean[inpSplit.length];

        for(int i=0; i < inpSplit.length;i++){
            if(!boolArr[i]){
                count++;
                for(int j = i+1; j < inpSplit.length;j++){
                    if(inpSplit[j].compareTo(inpSplit[i])==0){
                        boolArr[j] = true;
                        count--;
                    }
                }
                
            }
        }
        return count;
    }

}