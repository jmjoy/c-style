#!/bin/sh

version=$1

if [ -z $version ]; then
    echo "Please specify version."
    exit 1
fi

