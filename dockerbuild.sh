./gradlew clean bootJar
#docker build -t bradbarnhill/barcode-api:latest .
docker-compose build linux
docker-compose push linux
#docker push bradbarnhill/barcode-api