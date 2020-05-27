#!/bin/bash

# 脚本说明：打版本 tag 包并将项目发布到中央仓库

set -o errexit

# 获取当前项目版本
version=$(cat ./version.txt)

# 打版本 tag 包
git tag -a "$version" -m "release: $version"
git push origin "$version"

# 发布到中央仓库
cd ..
mvn clean deploy -DskipTests -P release