#!/bin/sh
# Black-box smoke test: extract a built openjdk-* target ipk and run
# "java -version" from it under a target-arch Debian container (qemu-user
# for non-x86-64 machines, which must be registered beforehand).
#
# usage: smoke-java-version.sh <deploy-ipk-dir> <recipe> <machine>
# CONTAINER_ENGINE=podman selects podman instead of docker (local runs).
set -eu

deploy=$1 recipe=$2 machine=$3

# Absolute path: we cd into a temp dir before extracting.
ipk=$(find "$(realpath "$deploy")" -name "${recipe}_*.ipk" \
        ! -name '*-dbg_*' ! -name '*-dev_*' ! -name '*-doc_*' | head -n1)
[ -n "$ipk" ] || { echo "error: no ipk for $recipe below $deploy" >&2; exit 1; }
echo "ipk: $ipk"

case "$machine" in
  qemux86-64)  plat=linux/amd64 ;;
  qemuarm64)   plat=linux/arm64 ;;
  qemuarm)     plat=linux/arm/v7 ;;
  qemuriscv64) plat=linux/riscv64 ;;
  *) echo "error: unknown machine $machine" >&2; exit 1 ;;
esac

work=$(mktemp -d)
trap 'rm -rf "$work"' EXIT
mkdir "$work/rootfs"
( cd "$work" && ar x "$ipk" && tar -C rootfs -xf data.tar.* )

# The JVM's ELF interpreter is patched to ${base_libdir}/ld-linux-*
# (/lib or /usr/lib depending on usrmerge). Debian keeps the real loader
# under a multiarch path, so provide compat links in both places.
"${CONTAINER_ENGINE:-docker}" run --rm --platform "$plat" -v "$work/rootfs:/rootfs:ro,z" \
  debian:trixie-slim sh -euc '
    for real in /lib/*/ld-linux* /lib64/ld-linux* /usr/lib/*/ld-linux*; do
      [ -f "$real" ] || continue
      base=$(basename "$real")
      [ -e "/usr/lib/$base" ] || ln -s "$real" "/usr/lib/$base"
      [ -e "/lib/$base" ]     || ln -s "$real" "/lib/$base"
    done
    java=$(find /rootfs/usr/lib/jvm -path "*/bin/java" -type f | head -n1)
    [ -n "$java" ] || { echo "error: no bin/java in ipk" >&2; exit 1; }
    "$java" -version
  '
