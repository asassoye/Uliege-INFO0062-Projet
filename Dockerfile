FROM maven:latest
COPY . /usr/soccerball/
WORKDIR /usr/soccerball
RUN mvn compile
CMD ["java", "-cp", "build/classes/", "soccerball.SoccerBall"]