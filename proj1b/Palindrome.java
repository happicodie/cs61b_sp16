public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word.equals("")) {
            return null;
        }
        Deque<Character> stringDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            stringDeque.addLast(word.charAt(i));
        }
        return stringDeque;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return true;
        }
        int len = word.length();
        for (int i = len - 1; i >= len / 2; i--) {
            if (word.charAt(i) != word.charAt((len - 1) - i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return true;
        }
        int len = word.length();
        for (int i = len - 1; i >= len / 2; i--) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len - 1 - i)) && i != len - 1 - i) {
                return false;
            }
        }
        return true;
    }
}
