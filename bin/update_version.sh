#!/bin/bash

#------------------------------------------------
# 升级 onee-framework 版本，包括：
# 1. 升级 pom.xml 中的版本号
# 2. 替换 README.md 和 docs 中的版本号
#------------------------------------------------

set -o errexit

if [ -n "$1" ];then
    new_version="$1"
    old_version=$(cat ./version.txt)
    echo "$old_version 替换为新版本 $new_version"
else
    # 参数错误，退出
    echo "ERROR: 请指定新版本！"
    exit
fi

if [ -z "$old_version" ]; then
    echo "ERROR: 旧版本不存在，请确认 bin/version.txt 中信息正确"
    exit
fi

cd ..

# 替换所有模块 pom.xml 中的版本
mvn versions:set -DnewVersion=$new_version
mvn versions:commit

# 替换 README.md 中的版本
sed -i "s/${old_version}/${new_version}/g" ./README.md
# 替换 docs/* 中的版本
sed -i "s/${old_version}/${new_version}/g" ./docs/starter-redis.md

# 保留新版本号
echo "$new_version" > ./bin/version.txt

echo "当前版本: $new_version"