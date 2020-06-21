#!/bin/bash
appname="fast-fortress-49960"
heroku deploy:jar target/neuropsi-record-server-0.0.1-SNAPSHOT.jar --app $appname
