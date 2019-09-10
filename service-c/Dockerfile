FROM openjdk:11

COPY target/lib/* /app/lib/
COPY target/service-c-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
