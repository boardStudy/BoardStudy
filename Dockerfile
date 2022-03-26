FROM openjdk:15
ARG JAR_FILE=target/board-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} board.jar
ENTRYPOINT ["java","-jar","/board.jar"]
