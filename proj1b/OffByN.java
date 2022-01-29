public class OffByN implements CharacterComparator {
    private int N;
    public OffByN(int N) {
        this.N = N;
    }
    public boolean equalChars(char x, char y) {
        if (!Character.isAlphabetic(x) || !Character.isAlphabetic(y)) {
            return false;
        }
        return Math.abs(x - y) == N;
    }
}
