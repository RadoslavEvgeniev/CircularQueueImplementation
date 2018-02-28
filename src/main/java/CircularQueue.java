import java.util.Arrays;

public class CircularQueue<E> {

    private static final int DEFAULT_CAPACITY = 4;

    private int size;
    private int capacity;
    private E[] arr;
    private int startIndex;
    private int endIndex;

    @SuppressWarnings("unchecked")
    public CircularQueue() {
        this.capacity = DEFAULT_CAPACITY;
        this.startIndex = 0;
        this.endIndex = 0;
        this.arr = (E[]) new Object[this.capacity];
    }

    @SuppressWarnings("unchecked")
    public CircularQueue(int initialCapacity) {
        this.capacity = initialCapacity;
        this.arr = (E[]) new Object[this.capacity];
    }

    public int size() {
        return this.size;
    }

    private  void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if (this.size >= this.capacity) {
            this.increaseSize();
        }
        this.arr[this.endIndex] = element;
        this.endIndex = (this.endIndex + 1) % this.capacity;
        size++;
    }

    public E dequeue() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }
        E result = this.arr[this.startIndex];
        this.startIndex = (this.startIndex + 1) % this.capacity;
        this.size--;
        return result;
    }

    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] newArr = (E[]) new Object[this.size];
        for (int i = startIndex; i < endIndex; i++) {
            newArr[i] = this.arr[startIndex];
        }
        return newArr;
    }

    @SuppressWarnings("unchecked")
    private void increaseSize() {
        E[] newArr = (E[]) new Object[this.capacity * 2];
        copyArr(newArr);
        this.arr = newArr;
        this.startIndex = 0;
        this.endIndex = this.size;
        this.capacity *= 2;
    }

    private void copyArr(E[] newArr) {
        int sourceIndex = this.startIndex;
        for (int i = 0; i < this.size; i++) {
            newArr[i] = this.arr[sourceIndex];
            sourceIndex = (sourceIndex + 1) % this.size;
        }
    }
}
