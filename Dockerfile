FROM amazoncorretto:20-alpine3.17
MAINTAINER maxtorm <maxtorm12138@gmail.com>
WORKDIR /
RUN apk add --no-cache tzdata
ENV TZ=Asia/Shanghai
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

VOLUME /database
ADD target/ledger.jar /app.jar
ENTRYPOINT ["java", "-Xmx128m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]