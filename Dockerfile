FROM openjdk:12
COPY src /usr/soccerball/src
WORKDIR /usr/soccerball
RUN curl --location --silent https://github.com/gliderlabs/herokuish/releases/download/v0.5.0/herokuish_0.5.0_linux_x86_64.tgz \
		  | tar -xzC /bin
RUN javac -d . src/*.java src/piece/*.java src/utils/*.java
CMD ["java", "SoccerBall"]