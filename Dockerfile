FROM openjdk:11-jre-slim
LABEL version=5.3.1 description="EPAM Report portal. Main API Service" maintainer="Andrei Varabyeu <andrei_varabyeu@epam.com>"
WORKDIR /
ADD build/libs/service-api-5.4.0-SNAPSHOT-exec.jar /service-api-5.4.0-exec.jar
RUN echo 'exec java ${JAVA_OPTS} -jar service-api-5.4.0-exec.jar' > /start.sh && chmod +x /start.sh
ENV JAVA_OPTS="-Xmx1g -XX:+UseG1GC -XX:InitiatingHeapOccupancyPercent=70 -Djava.security.egd=file:/dev/./urandom"
VOLUME ["/tmp"]
EXPOSE 8585
ENTRYPOINT /start.sh
