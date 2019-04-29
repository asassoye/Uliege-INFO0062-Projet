FROM maven:3-jdk-12
WORKDIR /usr/soccerball
RUN mvn clean package
CMD ["java", "SoccerBall"]