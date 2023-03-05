./gradlew clean bootJar

docker buildx build --push --platform linux/arm64/v8,linux/amd64 --tag bradbarnhill/barcode-api:latest .