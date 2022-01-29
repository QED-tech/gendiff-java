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
	make lint && ./gradlew clean build

run:
	./build/install/app/bin/app ~/projects/java/java-project-lvl2/testdata/file1.json ~/projects/java/java-project-lvl2/testdata/file2.json

re-run: install run