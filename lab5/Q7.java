// Q.7. Input a paragraph from any book, and write the java program to count the
//      occurrence of any given word in the paragraph.
package lab5;

import java.util.Scanner;

public class Q7 {
    static int countOccurrence(String para, String word) {
        String[] words = para.toLowerCase().split("\\W+");

        int count = 0;
        for (String w : words) {
            if (w.equals(word)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a paragraph from any book: ");
        String paragraph = sc.nextLine();

        System.out.println("Enter a word whose occurrence is to be calculated: ");
        String word;
        word = sc.next();

        System.out.println("Frequency of " + word + " = " + countOccurrence(paragraph, word.toLowerCase()));
        sc.close();
    }
}
