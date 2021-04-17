#!/usr/bin/env bash
set -euo pipefail

pip install emoji bs4

if [ ! -d $HOME/.bbsk ]
then
    mkdir $HOME/.bbsk
fi

bash <(curl -s https://raw.githubusercontent.com/babashka/babashka/master/install) $HOME/.bbsk/
