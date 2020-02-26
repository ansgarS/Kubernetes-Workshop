FROM maven:3.6.2-ibmjava-8-alpine

# Copy Executable Spring Application into Docker Container
ARG SPRING_PORT
ENV APP_FILE patient-service-1.0.0-SNAPSHOT.jar
ENV APP_HOME /usr/apps
ENV APP_PORT $SPRING_PORT

COPY target/$APP_FILE $APP_HOME/$APP_FILE

EXPOSE $APP_PORT

CMD ["exec", "java", "-jar", "$APP_FILE"]