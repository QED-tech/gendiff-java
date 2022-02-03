install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app

check-updates:
	./gradlew dependencyUpdates

lint:
	./gradlew checkstyleMain

clean:
	./gradlew clean

build:
	./gradlew clean build

run:
	./build/install/app/bin/app -f plain ./src/test/resources/json/nested/file1.json ./src/test/resources/json/nested/file2.json

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

re-run: install run

.PHONY: build