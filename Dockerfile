FROM amazoncorretto:17-alpine
MAINTAINER bradfordbarnhill@gmail.com

ADD build/libs/barcode-api.jar /

#Read environment variables from host
ARG BARCODE_SSL_PASSWORD

#Set environment variables in container
ENV BARCODE_SSL_PASSWORD $BARCODE_SSL_PASSWORD

VOLUME /tmp

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/barcode-api.jar"]