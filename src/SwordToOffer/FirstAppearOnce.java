package SwordToOffer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tlh on 2017/3/28.
 */
public class FirstAppearOnce {
    char[] book = new char[256];
    List<Character> characters = new LinkedList<>();

    //Insert one char from stringstream
    public void Insert(char ch) {
        book[ch]++;
        characters.add(ch);
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        for (Character ch : characters) {
            if (book[ch] == 1) return ch;
        }
        return '0';
    }
}
