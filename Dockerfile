FROM openjdk:8-alpine

RUN apk update && apk --no-cache add bash

RUN mkdir -p /opt/app

ENV PROJECT_HOME /opt/app

COPY target/StarWars-0.0.1-SNAPSHOT.jar $PROJECT_HOME/StarWars-0.0.1-SNAPSHOT.jar

WORKDIR $PROJECT_HOME

CMD ["java", "-jar", "-Dspring.profiles.active=prod" ,"./StarWars-0.0.1-SNAPSHOT.jar"]