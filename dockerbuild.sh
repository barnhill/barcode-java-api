./gradlew clean bootJar
docker-compose build barcode-server
docker-compose push barcode-server