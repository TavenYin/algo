#!/bin/sh
# 在 leetcode 的题目目录下执行: ./newjava.sh [类名]，缺省类名为 Solution
set -eu

name=${1:-Solution}
leetcode_dir=$(CDPATH= cd -- "$(dirname "$0")" && pwd)
here=$(pwd)

if [ "$here" = "$leetcode_dir" ]; then
  pkg=leetcode
else
  rel=${here#"$leetcode_dir"/}
  if [ "$rel" = "$here" ]; then
    echo "请在 leetcode 目录或其子目录中执行" >&2
    exit 1
  fi
  pkg=leetcode.$(printf '%s' "$rel" | tr '/' '.')
fi

if [ -e "$name.java" ]; then
  echo "$name.java 已存在" >&2
  exit 1
fi

cat > "$name.java" <<EOF
package $pkg;

public class $name {
}
EOF
