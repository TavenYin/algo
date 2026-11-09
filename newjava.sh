#!/bin/sh
# 在仓库根目录执行: ./newjava.sh <目录> [类名]，缺省类名为 Solution
set -eu

if [ $# -lt 1 ]; then
  echo "用法: $0 <目录> [类名]" >&2
  exit 1
fi

dir=$1
name=${2:-Solution}
repo_root=$(CDPATH= cd -- "$(dirname "$0")" && pwd)
leetcode_dir=$repo_root/leetcode
here=$(CDPATH= cd -- "$dir" && pwd)

case $here in
  "$leetcode_dir")
    pkg=leetcode
    ;;
  "$leetcode_dir"/*)
    rel=${here#"$leetcode_dir"/}
    pkg=leetcode.$(printf '%s' "$rel" | tr '/' '.')
    ;;
  *)
    echo "目录必须在 leetcode 目录下" >&2
    exit 1
    ;;
esac

if [ -e "$here/$name.java" ]; then
  echo "$name.java 已存在" >&2
  exit 1
fi

cat > "$here/$name.java" <<EOF
package $pkg;

public class $name {
}
EOF
