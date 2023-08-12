# Build stage
FROM gradle:8.2.1-jdk8 AS build
COPY . /home/gradle
RUN gradle build -x test

# Package stage
FROM tomcat:9-jre11
ARG WAR_FILE=*.war
COPY --from=build /home/gradle/build/libs/${WAR_FILE} /usr/local/tomcat/webapps/EmployeesHolder.war
EXPOSE 8080
CMD ["catalina.sh", "run"]