# Makefile
run-dist: # запустить приложение
	C:\java-project-71-main\app\build\install\app\bin\app
make build:
	cd app && ./gradlew build --rerun-tasks
report:
    @echo "Генерация отчета о покрытии тестами..."
	cd app && ./gradlew jacocoTestReport
	
.PHONY: build