FROM maven:3.6.2-ibmjava-8-alpine

ENV APP_FILE insurance-service-1.0.0-SNAPSHOT.jar
ENV APP_HOME /usr/apps
ENV APP_PORT 8080
ENV APP_ID insurance_service

WORKDIR $APP_HOME

COPY target/$APP_FILE app.jar

EXPOSE $APP_PORT

CMD ["java", "-jar", "app.jar"]