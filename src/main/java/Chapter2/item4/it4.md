- 정적 멤버만 담은 유틸리티 클래스는 인스턴스로 만들어 쓰려고 설계한것이 아님 
- 근데 생성자를 명시하지 않으면 컴파일러가 자동으로 기본 생성자 만듬
    - 의도치 않은 인스턴스화 가능성
- 추상클래스화 만으로는 인스턴스화 막을 수 없음
- private 생성자를 만들고, 내부에서 실수로 접근시 에러를 던짐