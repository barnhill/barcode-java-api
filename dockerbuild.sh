./gradlew clean bootJar
docker-compose build linux
docker-compose push linux