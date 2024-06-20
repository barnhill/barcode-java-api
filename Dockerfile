FROM amazoncorretto:21-alpine
MAINTAINER bradfordbarnhill@gmail.com

ADD build/libs/barcode-api.jar /

ENV PROFILE=${PROFILE:-nossl}

VOLUME /tmp

EXPOSE 8080

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "-Dspring.profiles.active=${PROFILE}", "/barcode-api.jar"]