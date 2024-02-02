
/**
 * @Author : junnny
 * @Date : 2024/2/1 20:42
 * @Description : Implement the ArrayDeque
 */

public class ArrayDeque<T> implements Deque<T>{
    private T[] array;
    private int size;   // 当前双端队列的大小
    private int length; // 数组的长度
    private int first;  // 指向队列的第一个元素
    private int last;   // 指向队列的最后一个元素的下一个位置

    // 构造方法
    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        first = 0; // 初始化时队列为空，first和last都指向同一个位置
        last = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private int minusOne(int index) {
        return (index == 0) ? length - 1 : index - 1;
    }

    private int plusOne(int index, int mod) {
        return (index + 1) % mod;
    }

    private void resize(int newLength) {
        T[] newArray = (T[]) new Object[newLength];
        int ptr1 = first;
        for (int i = 0; i < size; i++) {
            newArray[i] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
        }
        array = newArray;
        first = 0;
        last = size;
        length = newLength;
    }

    private void grow() {
        resize(length * 2);
    }

    private void shrink() {
        if (length > 8) {
            resize(length / 2);
        }
    }

    @Override
    public void addFirst(T item) {
        if (size == length) {
            grow();
        }
        first = minusOne(first);
        array[first] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == length) {
            grow();
        }
        array[last] = item;
        last = plusOne(last, length);
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (length >= 16 && size * 4 <= length) {
            shrink();
        }
        T removedItem = array[first];
        array[first] = null; // 防止内存泄漏
        first = plusOne(first, length);
        size--;
        return removedItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (length >= 16 && size * 4 <= length) {
            shrink();
        }
        last = minusOne(last);
        T removedItem = array[last];
        array[last] = null; // 防止内存泄漏
        size--;
        return removedItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int ptr = first;
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr, length);
        }
        return array[ptr];
    }

    @Override
    public void printDeque() {
        int ptr = first;
        for (int i = 0; i < size; i++) {
            System.out.print(array[ptr] + " ");
            ptr = plusOne(ptr, length);
        }
        System.out.println(); // 为了格式美观，打印完后换行
    }
}
