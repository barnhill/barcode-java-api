FROM tomcat:alpine
MAINTAINER bradfordbarnhill@gmail.com

ADD build/libs/barcode-api.war /usr/local/tomcat/webapps/

VOLUME /tmp

EXPOSE 8080

CMD ["catalina.sh", "run"]