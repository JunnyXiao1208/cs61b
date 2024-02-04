package synthesizer;

import org.junit.Test;

/**
 * @Author : junnny
 * @Date : 2024/2/4 22:07
 * @Description :
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }


}
