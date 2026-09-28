FROM maven:3.9-eclipse-temurin-17

WORKDIR /app

COPY target/informatica-plugin-*.jar informatica-plugin.jar

RUN curl -fsSL -o opentelemetry-javaagent.jar \
    https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.29.0/opentelemetry-javaagent.jar

COPY run_app.sh .

RUN chmod +x run_app.sh

ENTRYPOINT ["bash", "run_app.sh"]