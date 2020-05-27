#!/bin/bash

# 脚本说明：合并 master 分支到 dev 分支

set -o errexit

git checkout dev
git merge master -m 'Sync master'
git push origin dev