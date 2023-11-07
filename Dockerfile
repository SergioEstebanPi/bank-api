FROM openjdk:17
EXPOSE 8080
RUN mkdir -p /app/
ADD target/bank-api-0.0.1-SNAPSHOT.jar /target/bank-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/target/bank-api-0.0.1-SNAPSHOT.jar"]