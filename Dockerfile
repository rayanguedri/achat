FROM openjdk:11
EXPOSE 9091
COPY target/achat-1.4.jar achat-1.4.jar
ENTRYPOINT ["java", "-jar", "/achat-1.0.jar"]
