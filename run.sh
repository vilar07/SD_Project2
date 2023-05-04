#!/bin/bash

if [ $# -ne 1 ]; then
  echo "Usage: $0 <port>"
  exit 1
fi

# Open first terminal and run program 1
xterm -hold -e "java serverSide/main/HeistToTheMuseum $1" &
sleep 5
# Open second terminal and run program 2
xterm -hold -e "java clientSide/main/HeistToTheMuseum $1" &

