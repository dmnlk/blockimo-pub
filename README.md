# blockimo

## require
Java8
MySQL 5.7

## config
please edit twitter4j.properties and fill your keys

edit src/main/resources/config/application-*.properties

fill below settings
spring.datasource.url= `your mysql address ex: jdbc:mysql://your-prod-mysql-server.com:3306/blockimo?useUnicode=true&characterEncoding=utf8&verifyServerCertificate=false&useSSL=false&requireSSL=false`
spring.datasource.username= `MySQL user`
spring.datasource.password= `MySQL password`

settings.baseUrl=`your domain ex:blockmo.ml`


## build
./gradlew build
cp build/libs/blockimo-1.0-SNAPSHOT.war ./

## launch for dev

./gradlew bootRun

## launch for prod

java -jar ./blockimo.war --spring.profiles.active=production &

## launch for prod batch
java -jar ./blockimo.war --spring.profiles.active=batch &
