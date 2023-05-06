# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /opt/app
#Crea camino del jar
ARG JAR_FILE=tacocloud/target/tacocloud-0.0.1-SNAPSHOT.jar
# cp tacocloud-0.0.1-SNAPSHOT.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar
# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]

