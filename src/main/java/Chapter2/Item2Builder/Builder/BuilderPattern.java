package Chapter2.Item2Builder.Builder;

public class BuilderPattern {
    public static class NutritionFacts {
        private final int servingSize; // 필수
        private final int servings; // 필수
        private final int calories; // 이밑으로 선택
        private final int fat;
        private final int sodium;
        private final int carbohydrate;

        public static class Builder {
            /* 필수 매개변수 */
            private final int servingSize;
            private final int servings;

            /* 선택 매개변수 : 0기본값으로 초기화 */
            private int calories = 0; // 이밑으로 선택
            private int fat = 0;
            private int sodium = 0;
            private int carbohydrate = 0;

            /* 필수 매개변수만으로 생성자 호출 */
            public Builder(int servingSize, int servings) {
                this.servingSize = servingSize;
                this.servings = servings;
            }
            /* 세터 메서드들로 원하는 선택 매개변수들 설정 */
            public Builder calories(int val) { calories = val; return  this;} // 자기 자신 반환하여 연쇄적 호출 가능
            public Builder fat(int val) { fat = val; return  this;}
            public Builder sodium(int val) { sodium = val; return  this;}
            public Builder carbohydrate(int val) { carbohydrate = val; return  this;}

            /* build 메서드로 객체 얻음 */
            public NutritionFacts build() {
                return new NutritionFacts(this);
            }
        }

        private NutritionFacts(Builder builder) {
            servingSize = builder.servingSize;
            servings = builder.servings;
            calories = builder.calories;
            fat = builder.fat;
            sodium = builder.sodium;
            carbohydrate = builder.carbohydrate;
        }
    }

    public static void main(String[] args) {
        /* 클라이언트 코드 */
        NutritionFacts coca = new NutritionFacts.Builder(240,8)
                .calories(100).sodium(35).carbohydrate(27).fat(3).build();

        System.out.println(coca.fat);
    }

}
