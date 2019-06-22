#!/bin/sh

if [ -d /root/.m2/repository/perun/perun/0.4.3-SNAPSHOT ]; then
  exit
fi

git clone https://github.com/twohundredok/perun.git
cd perun
boot build
