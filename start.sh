#!/usr/bin/env bash

cd node-sonos-http-api
npm start &
cd ..
cd prayer-time-service
./gradlew build
java -jar build/libs/prayer-time-service-0.0.1-SNAPSHOT.jar &