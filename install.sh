#!/usr/bin/env bash
set -euo pipefail

pip install emoji bs4

if [ ! -d $HOME/.bbsk ]
then
    mkdir $HOME/.bbsk
fi

if [ $(grep "alias bb=" $HOME/.bashrc | wc -l) == 0 ]
then
bash <(curl -s https://raw.githubusercontent.com/babashka/babashka/master/install) $HOME/.bbsk/
echo "alias bb=$HOME/.bbsk/bb" >> $HOME/.bashrc
fi
