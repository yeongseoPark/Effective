package Chapter2.Item2.JavaBeans;

public class JavaBeans {
    public static class NutritionFacts {
        // 기본값이 있다면(0), 기본값으로 초기화됨
        private  int servingSize = -1; // 필수 / 기본값 없음
        private  int servings = -1; // 필수
        private  int calories; // 이밑으로 선택
        private  int fat;
        private  int sodium;

        public NutritionFacts() {}; // 매개변수가 없는 생성자

        public void setServingSize(int val) { servingSize = val; }
        public void setServings(int val) { servings = val; }
        public void setCalories(int val) { calories = val; }
        public void setFat(int val) { fat = val; }
        public void setSodium(int val) { sodium = val; }
    }

    public static void main(String[] args) {
        NutritionFacts nt = new NutritionFacts();
        nt.setServingSize(1);
        System.out.println(nt.servings);
    }
}
