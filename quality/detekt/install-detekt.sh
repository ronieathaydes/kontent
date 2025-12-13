#!/bin/sh

version=1.23.8

echo "Downloading detekt v${version}..."
curl --output quality/detekt/detekt-cli-all.jar -sSLO https://github.com/detekt/detekt/releases/download/v${version}/detekt-cli-${version}-all.jar
curl --output quality/detekt/detekt-formatting.jar -sSLO https://github.com/detekt/detekt/releases/download/v${version}/detekt-formatting-${version}.jar

echo "Done."
