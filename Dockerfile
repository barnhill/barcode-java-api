FROM arm64v8/openjdk:slim
MAINTAINER bradfordbarnhill@gmail.com

ADD build/libs/barcode-api.jar /

VOLUME /tmp

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/barcode-api.jar"]