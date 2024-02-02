/**
 * @Author : junnny
 * @Date : 2024/2/2 16:47
 * @Description : implements CharacterComparator
 */
public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }
}
