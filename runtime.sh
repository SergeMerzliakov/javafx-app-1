#!/usr/bin/env bash
#run with JDK 12
rm -rf installBuild/jre
export DEP_LIST=java.base,java.logging,javafx.base,javafx.graphics,javafx.controls,javafx.fxml
$JAVA_HOME/bin/jlink --module-path $JAVA_HOME/jmods:/Library/Java/javafx/12.0.1/jmods --no-header-files --no-man-pages --compress=2 --strip-debug --add-modules $DEP_LIST --output installBuild/jre


 