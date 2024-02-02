/**
 * @Author : junnny
 * @Date : 2024/2/2 15:30
 * @Description : contains all of the methods that appear in both ArrayDeque and LinkedListDeque
 */
public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    boolean isEmpty();
    int size();
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
}
