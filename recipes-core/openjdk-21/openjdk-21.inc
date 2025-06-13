HOMEPAGE = "https://adoptium.net"
LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-with-classpath-exception;md5=6133e6794362eff6641708cfcc075b80"
COMPATIBLE_HOST = "(x86_64|aarch64|riscv64).*-linux"
NATIVEDEPENDS = "patchelf-native"
NATIVEDEPENDS:class-natve = ""
DEPENDS = "alsa-lib freetype libx11 libxext libxi libxrender libxtst zlib ${NATIVEDEPENDS}"

API_RELEASE_NAME = "jdk-${PV}"
API_OS = "linux"
API_ARCH:aarch64 = "aarch64"
API_ARCH:x86-64 = "x64"
API_ARCH:riscv64 = "riscv64"
API_JVM_IMPL = "hotspot"
API_HEAP_SIZE ?= "normal"
API_VENDOR = "eclipse"

ADAPTED_PV_SRC = "${@d.getVar('PV').replace('-','')}"
SRC_URI = "https://api.adoptium.net/v3/binary/version/${API_RELEASE_NAME}/${API_OS}/${API_ARCH}/${API_IMAGE_TYPE}/${API_JVM_IMPL}/${API_HEAP_SIZE}/${API_VENDOR};name=binary;downloadfilename=${BPN}-${API_ARCH}-${PV}.tar.gz;subdir=${BPN}-${PV};striplevel=1 \
           https://github.com/adoptium/temurin21-binaries/releases/download/${API_RELEASE_NAME}/OpenJDK21U-jdk-sources_${ADAPTED_PV_SRC}.tar.gz;name=sources;downloadfilename=${BPN}-sources-${PV}.tar.gz;unpack=false"
SRC_URI[binary.sha256sum] = "${JVM_CHECKSUM}"
SRC_URI[sources.sha256sum] = "ba38841876aeec064b1f0d723117908b25b3961685b9801942c707e5e9bd47d1"

libdir_jvm = "${libdir}/jvm/${BPN}"

# Prevent the packaging task from stripping out
# debugging symbols, since there are none.
INSANE_SKIP:${PN}:append = " ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# Package unversioned libraries
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

# Ignore QA Issue: non -dev/-dbg/nativesdk- package
INSANE_SKIP:${PN}:append = " dev-so"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install() {
  install -d ${D}${libdir_jvm}
  cp -R --no-dereference --preserve=mode,links -v ${S}/* ${D}${libdir_jvm}
  fix_interpreter
  create_jdk_symlinks
}
fix_interpreter() {
  LDLINUX=$(basename $(ls -1 ${RECIPE_SYSROOT}${base_libdir}/ld-linux* | sort | head -n1))
  if [ -n "$LDLINUX" ]; then
    for i in ${D}${libdir_jvm}/bin/* ; do
      patchelf --set-interpreter ${base_libdir}/$LDLINUX $i
    done
  fi
}
fix_interpreter:class-native() {
  :
}
create_jdk_symlinks() {
  :
}
create_jdk_symlinks:class-native() {
  # symlinking manually since update-alternatives isn't meant for native packages
  install -d ${D}${bindir}
  for i in jar java javac keytool; do
    if [ -x ${D}${libdir_jvm}/bin/$i ]; then
      ln -sf ${@oe.path.relative("${bindir}", "${libdir_jvm}/bin")}/$i ${D}${bindir}/$i
    fi
  done
}

RPROVIDES:${PN} = "java2-runtime"
FILES:${PN} = "${libdir_jvm} ${bindir}"
