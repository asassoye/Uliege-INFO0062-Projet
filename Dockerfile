FROM openjdk:12
COPY src /usr/soccerball/src
WORKDIR /usr/soccerball
RUN javac -d /usr/soccerball/build/ /usr/soccerball/src/*.java /usr/soccerball/src/piece/*.java /usr/soccerball/src/utils/*.java
CMD ["java", "SoccerBall"]