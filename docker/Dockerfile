FROM amazoncorretto:20-alpine3.17
MAINTAINER maxtorm <maxtorm12138@gmail.com>
WORKDIR /
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \&& echo 'Asia/Shanghai' >/etc/timezone
ADD target/ledger.jar /app.jar
ENTRYPOINT ["java", "-Xmx128m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]