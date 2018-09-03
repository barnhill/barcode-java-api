./gradlew clean bootJar
docker build -t bradbarnhill/barcode-api:latest .
docker push bradbarnhill/barcode-api