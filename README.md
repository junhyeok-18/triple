# 트리플여행자 클럽 마일리지 서비스

- 트리플 사용자들이 장소에 리뷰를 작성할 때 포인트를 부여하고, 전체/개인에 대한 포인트 부여 히스토리와 개인별 누적 포인트를 관리하는 API
- Language : Java 17.0.2
- Framework : SpringBoot 2.6.6
- Build Tool : Gradle 7.4.2

# 실행 방법

1. 개발 환경에 맞게 로컬 실행 환경을 구축합니다.
2. org.example.Application을 실행합니다.
3. [사용자](http://localhost:8080/admin/user) 와 [장소](http://localhost:8080/admin/place) 를 추가 해줍니다.
4. [메인](http://localhost:8080/) 에서 리뷰를 작성할 사용자를 선택합니다.
5. [장소선택](http://localhost:8080/place) 에서 리뷰를 작성할 장소를 선택합니다.
6. 보유 포인트는 [메인](http://localhost:8080/) 에서 확인이 가능하며, 포인트 이력은 보유 포인트를 선택할 시에 확인이 가능합니다.