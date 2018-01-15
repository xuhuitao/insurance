FROM openjdk:8-jdk-alpine
RUN echo "Asia/Shanghai" > /etc/timezone
VOLUME /tmp
VOLUME /data/logs/insurance
ADD insurance-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
