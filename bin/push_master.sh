#!/bin/bash

# 脚本说明：合并 dev 分支到 master 分支

set -o errexit

git checkout master
git merge dev -m 'Prepare release'
git push origin master