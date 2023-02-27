./gradlew clean bootJar
docker-compose build linux-amd64
docker-compose push linux-amd64

docker-compose build linux-arm64
docker-compose push linux-arm64