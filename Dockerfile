FROM openjdk:11
ARG JAR_FILE=target/board-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} board.jar
COPY pinpoint-agent-1.8.5.tar.gz pinpoint.tar.gz

RUN tar -zxvf pinpoint.tar.gz
ARG ENVIRONMENT

ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}
RUN mkdir -p /apps/board/${ENVIRONMENT}
#ENV JAVA_OPTS="-javaagent:/pinpoint-agent-1.8.5/pinpoint-bootstrap-1.8.5.jar -Dpinpoint.agentId=board-$SPRING_PROFILES_ACTIVE -Dpinpoint.applicationName=board-$SPRING_PROFILES_ACTIVE"
#ENTRYPOINT ["java","-jar","/board.jar"]
#ENTRYPOINT ["java", "-javaagent:/pinpoint-agent-1.8.5/pinpoint-bootstrap-1.8.5.jar", "-Dpinpoint.agentId=springboot.board" ,"-Dpinpoint.applicationName=board-**$SPRING_PROFILES_ACTIVE**","-jar" ,"/board.jar"]
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /board.jar"]


# java -jar -javaagent:pinpoint-agent-2.2.0/pinpoint-bootstrap-2.2.0.jar -Dpinpoint.agentId=base-api -Dpinpoint.applicationName=API-EXAMPLE -Dpinpoint.config=pinpoint-agent-2.2.0/pinpoint-root.config -Dspring.profiles.active=local /home/bkjeon/app/base-api-0.0.0.jar &
