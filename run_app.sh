#!/bin/bash

exec java ${JAVA_OPTS:-} -javaagent:opentelemetry-javaagent.jar -jar informatica-plugin.jar