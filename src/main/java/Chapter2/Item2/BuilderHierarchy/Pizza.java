package Chapter2.Item2.BuilderHierarchy;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

import static Chapter2.Item2.BuilderHierarchy.NyPizza.Size.SMALL;
import static Chapter2.Item2.BuilderHierarchy.Pizza.Topping.*;

/* 다양한 피자 종류를 표현하는 계층구조의 루트 추상 클래스 */
public abstract class Pizza {
    public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> { // Builder<T>와 이를 상속받은 자손만 가능
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public T addToping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self(); // self()가 this를 반환하여 연쇄호출 가능
        }

        abstract Pizza build();

        // 하위클래스는 이 메서드를 오버라이딩하여 "this"를 반환하도록 해야 한다.
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}

class NyPizza extends Pizza {
    public enum Size { SMALL, MEDIUM, LARGE }
    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override public NyPizza build() { 
            return new NyPizza(this);
        }

        @Override protected Builder self() {return this;} // addToping시 self()를 반환하는데, 이 self()가 this를 반환하게 하여 연쇄호출 가능
    }

    private NyPizza(Builder builder) {
        super(builder); // addToping은 여기서 이뤄짐
        size = builder.size;
    }
}

class Calzone extends Pizza {
    private final Boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        private Boolean sauceInside = false;

        public Builder sauceInside() {
            sauceInside = true;
            return this;
        }

        @Override public Calzone build() {
            return new Calzone(this); 
        }

        @Override protected Builder self() {return this;}
    }

    private Calzone(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }

    public static void main(String[] args) {
        NyPizza ny = new NyPizza.Builder(SMALL)
                .addToping(SAUSAGE).addToping(ONION).build();
        Calzone calzone = new Calzone.Builder().addToping(HAM).sauceInside().build();
    }
}
