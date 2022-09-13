# BoardStudy <!--[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https://github.com/boardStudy/BoardStudy%2Fgjbae1212%2Fhit-counter)](https://github.com/boardStudy/BoardStudy)-->
> 국비 교육을 마치고 조금 더 많은 경험을 해보고자 게시판을 기반으로 개발 중 - (6월부터 개인 프로젝트로 전환)

# 참여 인원
> <strong>민동현</strong> (코드 리뷰, 데브옵스 역할)
> <br> <strong>이호인, 신다훈</strong> (게시판 개발)

# 목표
+ 꼭 코드 작성 시 이유를 찾자! PR 시 꼭 자신이 작성한 코드의 근거, 이유 작성하기.
+ 항상 협업이라는 것을 기억하자! 다른 개발자들이 자신의 코드를 보고 이해가 가능하게끔 작성하려고 노력하기.
+ 실제 실무 개발은 어떤 것을 더 고려해야할까? 단순 기능 구현에 멈추지 말고 실제 서비스가 된다고 생각하고 찾아보자.
+ Sonarqube로 한번, PR 시 서로 코드 리뷰를 통해 클린 코드 지향하기.
+ CI/CD 구성을 통한 자동화 구현.
+ 현재 모놀리스 아키텍처 구조에서 마이크로서비스 아키텍처로 점진적 전환 중 
+ 현재 [alarm-API](https://github.com/boardStudy/alarm-service) 분리 완료
+ [api-gateway](https://github.com/boardStudy/api-gateway), [account-API](https://github.com/boardStudy/account-api) 작업 진행 중
+ board-API 예정

# Stacks

#### 사용 언어

<div>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <br>
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
  <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
  <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
</div>

#### 개발 환경

<div>
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">
  <img src="https://img.shields.io/badge/maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white">
  <img src="https://img.shields.io/badge/thymeleaf-005F0F.svg?style=for-the-badge&logo=thymeleaf&logoColor=white">
  <img src="https://img.shields.io/badge/redis-DC382D.svg?style=for-the-badge&logo=redis&logoColor=white">

  <img src="https://img.shields.io/badge/IntelliJ IDEA-000000.svg?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white">
  <img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white">
</div>

#### 의사소통

<img src="https://img.shields.io/badge/DISCORD-%237289DA.svg?style=for-the-badge&logo=discord&logoColor=white">

#### CI/CD

![Jenkins](https://img.shields.io/badge/jenkins-%232C5263.svg?style=for-the-badge&logo=jenkins&logoColor=white)
![Nginx](https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white)
![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)
![docker](https://img.shields.io/badge/docker-2496ED.svg?style=for-the-badge&logo=docker&logoColor=white")


## PR 목록 (이호인)

#### 기본 게시판 기능
- 게시판 리스트 
- 상세 페이지
- 조회 수 
- 글 등록 
- 수정, 삭제 
- 페이징 처리 


#### 공부하고 적용해보기
- HandlerMethodArgumentResolver 사용해보기 https://github.com/boardStudy/BoardStudy/pull/2
- DB 서버 분리해서 사용하기 https://github.com/boardStudy/BoardStudy/pull/35
- 파일 첨부 기능 https://github.com/boardStudy/BoardStudy/pull/56
- 새로운 글 new 표시하기 https://github.com/boardStudy/BoardStudy/pull/66
- 로그인 체크 인터셉터 사용하기 https://github.com/boardStudy/BoardStudy/pull/67
- 글 개수 선택 기능 https://github.com/boardStudy/BoardStudy/pull/72
- session redis로 관리하기 https://github.com/boardStudy/BoardStudy/pull/83
- favicon 적용하기 https://github.com/boardStudy/BoardStudy/pull/91
- xss 방어 코드 작성하기 https://github.com/boardStudy/BoardStudy/pull/92
- 에러 페이지 작성하기 https://github.com/boardStudy/BoardStudy/pull/96
- 댓글 구현하기 https://github.com/boardStudy/BoardStudy/pull/102
- Json xss 방어 따로 해주기 https://github.com/boardStudy/BoardStudy/pull/105
- 알람 서비스 API 만들어서 호출해보기 https://github.com/boardStudy/BoardStudy/pull/108
- swagger 사용하기 https://github.com/boardStudy/BoardStudy/pull/111
- AOP 적용해보기 (메소드 실행 시간 측정) https://github.com/boardStudy/BoardStudy/pull/113
- @Async 어노테이션 적용하여 편의성 개선하기 https://github.com/boardStudy/BoardStudy/pull/116
- 이전 글, 다음 글 기능 https://github.com/boardStudy/BoardStudy/pull/121

#### account_api 분리 작업
- 로그인 / 로그아웃 https://github.com/boardStudy/account-api/pull/2
- 회원가입 https://github.com/boardStudy/account-api/pull/5

