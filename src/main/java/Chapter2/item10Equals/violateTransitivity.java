package Chapter2.item10Equals;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

class Point {
    final int x;
    final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;
        Point p = (Point)o;
        return p.x == x && p.y == y;
    }
}

class ColorPoint extends Point {
    enum Color {
        RED,
        BLUE
    }

    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    /* 대칭성 위배 */
    // @Override
    public boolean equalss(Object o) { // Point와 ColorPoint를 비교하려 한다면
        if (!(o instanceof ColorPoint))
            return false; // ColorPoint의 equalss 는 일반 Point를 비교하려 하면 클래스 종류가 다르기 때문에 false 반환
        return super.equals(o) && ((ColorPoint) o).color == color;
        // Point(super)의 equals 는 색상을 무시해서 true 여도
    }

    /* 추이성 위배 */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;

        // o가 일반 Point인 상황. 색깔을 무시하고 비교
        if (!(o instanceof ColorPoint)) {
            return o.equals(this);
        }

        // o가 PColorPoint면 색깔까지 비교
        return super.equals(o) && ((ColorPoint) o).color == color;
    }

    /* 리스코프 치환 원칙 위배 */
//    @Override
//    public boolean equals(Object o) {
//        if (o == null || o.getClass() != getClass()) // 같은 구현 클래스의 객체와 비교할 때에만 true를 반환
//            return false;
//        Point p = (Point) o;
//        return p.x == x && p.y == y;
//    }

    public static void main(String[] args) {
        ColorPoint cp1 = new ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1,2);
        ColorPoint cp2 = new ColorPoint(1, 2, Color.BLUE);

        System.out.println(cp1.equals(p2)); // true
        System.out.println(p2.equals(cp2)); // true
        System.out.println(cp1.equals(cp2)); // false면 안되는데..
    }
}

class CounterPoint extends Point {
    private static final Set<Point> unitCircle = Set.of(
            new Point(1,0) , new Point(0,1),
            new Point(-1, 0), new Point(0, -1));

    private static final AtomicInteger counter = new AtomicInteger();

    public CounterPoint(int x, int y) {
        super(x, y);
        counter.incrementAndGet();
    }
    public static int numberCreated() {
        return counter.get();
    }
}
