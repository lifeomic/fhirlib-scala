#!/bin/bash

rm -rf docs
mkdir -p docs
cp -Rf target/site/scaladocs/* docs/