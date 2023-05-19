FROM openjdk:11-jdk

ARG JAR_FILE=./target/project-0.0.1-SNAPSHOT.jar

# JAR 파일 메인 디렉토리에 복사
COPY ${JAR_FILE} travelPlanner.jar

# 컨테이너 시작시 실행할 스크립트 혹은 명령 정의
ENTRYPOINT ["java", "-jar", "/travelPlanner.jar"]
