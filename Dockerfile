FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/hangman-api-0.0.1-SNAPSHOT.jar hangman.jar
ENTRYPOINT ["java","-jar","hangman.jar"]