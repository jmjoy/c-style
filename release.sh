#!/bin/sh

version=$1

if [ -z $version ]; then
    echo "Please specify version."
    exit 1
fi

branch=`git rev-parse --abbrev-ref HEAD`
if [ $branch != "release" ]; then
    echo "Not in release branch."
    exit 1
fi

git checkout master && \
    git merge --no-ff -m "Release $version." release && \
    git push && \
    git tag $version && \
    git push --tags && \
    lein deploy clojars
