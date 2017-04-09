# blockimo

## require
Java8
MySQL 5.7

please edit twitter4j.properties

## build
./gradlew build
cp build/libs/blockimo-1.0-SNAPSHOT.war ./

## launch for dev

./gradlew bootRun

## launch for prod

java -jar ./blockimo.war --spring.profiles.active=production &

## launch for prod batch
java -jar ./blockimo.war --spring.profiles.active=batch &
