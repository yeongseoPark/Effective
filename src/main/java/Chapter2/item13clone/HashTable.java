package Chapter2.item13clone;

public class HashTable implements Cloneable {
    private Entry[] buckets;

    private static class Entry { // 일종의 연결리스트
        final Object key;
        Object value;
        Entry next;

        Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /** 재귀 deepCopy는 스택오버플로를 일으킬 수 있다 **/
        Entry deepCopyRecur() {
            return new Entry(key, value, next == null ? null : next.deepCopy());
        }

        /** 따라서 재귀 대신 반복문 사용 **/
        Entry deepCopy() {
            Entry result = new Entry(key, value, next);
            for (Entry p = result; p.next != null; p = p.next)
                p.next = new Entry(p.next.key, p.next.value, p.next.next);
            return result;
        }
    }

    /** 가변 상태를 공유하는 잘못된 메서드 **/
    // @Override
    public  HashTable clone2() {
        try {
            HashTable res = (HashTable) super.clone();
            res.buckets = buckets.clone(); // 복사된 해당 배열은 원본과 같은 연결리스트를 참조한다.
            return res;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override public  HashTable clone() {
        try {
            HashTable res = (HashTable) super.clone();
            res.buckets = new Entry[buckets.length];
            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != null) res.buckets[i] = buckets[i].deepCopy();
            }
            return res;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
