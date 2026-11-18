#!/usr/bin/env sh

# Resolve the directory containing this script
PRG="$0"
while [ -h "$PRG" ]; do
  ls=$(ls -ld "$PRG")
  link=$(expr "$ls" : '.*-> \(.*\)$')
  case "$link" in
    /*) PRG="$link" ;;
    *) PRG=$(dirname "$PRG")/"$link" ;;
  esac
done
DIR=$(cd "$(dirname "$PRG")" && pwd)

if [ -n "$JAVA_HOME" ] && [ -x "$JAVA_HOME/bin/java" ]; then
  JAVA_CMD="$JAVA_HOME/bin/java"
elif [ -n "$JAVA_HOME" ] && [ -x "$JAVA_HOME/bin/java.exe" ]; then
  JAVA_CMD="$JAVA_HOME/bin/java.exe"
else
  JAVA_CMD=java
fi

exec "$JAVA_CMD" -Dorg.gradle.appname=gradlew \
  -classpath "$DIR/gradle/wrapper/gradle-wrapper.jar" \
  org.gradle.wrapper.GradleWrapperMain "$@"
