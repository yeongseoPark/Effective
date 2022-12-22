package Chapter2.Item2.TelescopingConstructor;

public class TelescopingConstructor {
    /* 점층적 생성자 */
    public static class NutritionFacts {
        private final int servingSize; // 필수
        private final int servings; // 필수
        private final int calories; // 이밑으로 선택
        private final int fat;
        private final int sodium;

        public NutritionFacts(int servingSize, int servings) {
            this(servingSize, servings, 0);
        }

        public NutritionFacts(int servingSize, int servings, int calories) {
            this(servingSize, servings, calories, 0);
        }

        public NutritionFacts(int servingSize, int servings, int calories, int fat) {
            this(servingSize, servings, calories, fat, 0);
        }

        public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
            this.servingSize = servingSize;
            this.servings = servings;
            this.calories = calories;
            this.fat = fat;
            this.sodium = sodium;
        }
    }

    public static void main(String[] args) {
        NutritionFacts nt = new NutritionFacts(1,2);
        System.out.println(nt.servingSize); // 1
        System.out.println(nt.sodium); // 0
        System.out.println(nt.fat); // 0
    }
}
