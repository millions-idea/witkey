if exist .\packages (
rd /s /q .\packages
mkdir .\packages
) else (mkdir .\packages)

cd ./install

xcopy /y .\env-config\proback-application-test.properties ..\proback\src\main\resources\application-test.properties
xcopy /y .\env-config\proback-application-active.properties ..\proback\src\main\resources\application.properties
cd ../proback
call mvn clean package -Dmaven.test.skip=true
xcopy /y .\target\proback-0.0.1-SNAPSHOT.jar ..\install\packages