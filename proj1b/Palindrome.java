/**
 * @Author : junnny
 * @Date : 2024/2/2 15:41
 * @Description :
 */
public class Palindrome {


    public Deque<Character> wordToDeque(String word){
        Deque<Character> a = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            a.addLast(word.charAt(i));
        }
        return a;
    }

    public boolean isPalindrome(String word){
        return isPalindrome(word,0, word.length() - 1);
    }

    private boolean isPalindrome(String word, int start, int last){
        if(word.length() < 2 || last - start < 2){
            return true;
        }
        if(word.charAt(start) != word.charAt(last)){
            return false;
        }
        return isPalindrome(word, start + 1, last - 1);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if(word.length() < 2){
            return true;
        }
        int len = word.length();
        for(int i = 0; i < len / 2; i++){
            if(!cc.equalChars(word.charAt(i), word.charAt((len - i - 1)))){
                return false;
            }
        }
        return true;
    }
}
