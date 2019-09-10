FROM openjdk:8

COPY target/lib/* /app/lib/
COPY target/service-c-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 9080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev", "-jar", "/app/app.jar"]
