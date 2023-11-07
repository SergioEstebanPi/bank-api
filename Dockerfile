FROM maven:3.8.3-openjdk-17 AS maven
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package

FROM openjdk:17
ARG JAR_FILE=bank-api-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/
ENTRYPOINT ["java", "-jar", "bank-api-0.0.1-SNAPSHOT.jar"]
