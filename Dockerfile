FROM openjdk:21
COPY  "./target/pruebastyt-1.jar" "app.jar"
EXPOSE 8006
ENTRYPOINT ["java", "-jar", "app.jar"]