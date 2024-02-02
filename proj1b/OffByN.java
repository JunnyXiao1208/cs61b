/**
 * @Author : junnny
 * @Date : 2024/2/2 17:11
 * @Description :
 */
public class OffByN implements CharacterComparator{
    private int n;

    // Construct
    public OffByN(int N){
        this.n = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        return  Math.abs(x - y) == n;
    }
}
