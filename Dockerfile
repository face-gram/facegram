# 위의 커맨드는, Docker를 올릴 때 jdk11 버전을 이용해서 올리겠다라고 선언하는 커맨드입니다.
FROM openjdk:11-jdk

# 위의 커맨드는, JAR 파일의 위치를 환경변수의 형태로 선언하는 것입니다.
# 프로젝트를 빌드하게되면 build/libs/xxxx.jar 의 형태로 jar file이 생성이 되어있을텐데요, 그 파일의 위치를 변수로 저장하는 것입니다.
ARG JAR_FILE=./build/libs/facegramBackend-0.0.1-SNAPSHOT.jar

# 위의 커맨드는, 프로젝트의 jar 파일 위치를 참조하여 jar 파일을 가져와서 컨테이너의 루트 디렉토리에 app.jar의 이름으로 복사하는 커맨드입니다.
COPY ${JAR_FILE} app.jar

EXPOSE 8080

# 위의 커맨드는, 도커파일이 도커엔진을 통해서 컨테이너로 올라갈 때, 도커 컨테이너의 시스템 진입점이 어디인지를 선언하는 커맨드입니다.
# 위의 커맨드에서는, java -jar 명령어를 이용해서 컨테이너의 루트에 위치한 app.jar을 실행하라는 뜻의 커맨드입니다.
ENTRYPOINT ["java","-jar","/app.jar"]