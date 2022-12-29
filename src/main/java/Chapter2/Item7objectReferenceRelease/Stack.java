package Chapter2.Item7objectReferenceRelease;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private Object[] elements;// 객체 참조를 담는 elements 배열, 자기 메모리를 직접 관리
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size]; // 비활성 영역(줄어든 영역)은 쓰이지 않지만 null처리 하지 않으면 GC가 알 방법이 없다
        elements[size] = null; // 따라서 다쓴 참조를 null처리해서 해제하지 않으면 GC가 Obsolete reference를 계속 가지고 있는다
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
