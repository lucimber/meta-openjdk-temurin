SUMMARY = "Prebuilt OpenJDK JRE for Java 17 offered by Adoptium."
HOMEPAGE = "https://adoptium.net"
LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-with-classpath-exception;md5=6133e6794362eff6641708cfcc075b80"

COMPATIBLE_HOST = "(x86_64|arm|aarch64|riscv64).*-linux"
OVERRIDES = "${TARGET_ARCH}"

DEPENDS = "patchelf-native"

JVM_CHECKSUM:aarch64 = "9dfe4c56463690ae67d22268980d8861eb46b907d7914f8f2e6fc7b25778c8ec"
JVM_RDEPENDS:aarch64 = " \
  alsa-lib (>= 0.9) \
  freetype (>= 2.13) \
  glibc (>= 2.17) \
  libx11 (>= 1.7) \
  libxext (>= 1.3) \
  libxi (>= 1.8) \
  libxrender (>= 0.9) \
  libxtst (>= 1.2) \
  zlib (>= 1.2) \
"
JVM_CHECKSUM:arm = "f093094abe0cb2bb5a255d8180810030321073520541f289926c4682eda76136"
JVM_RDEPENDS:arm = " \
  alsa-lib (>= 0.9) \
  freetype (>= 2.13) \
  glibc (>= 2.17) \
  libatomic (>= 1.0) \
  libx11 (>= 1.7) \
  libxext (>= 1.3) \
  libxi (>= 1.8) \
  libxrender (>= 0.9) \
  libxtst (>= 1.2) \
  zlib (>= 1.2) \
"
JVM_CHECKSUM:x86_64 = "0e8088d7a3a7496faba7ac8787db09dc0264c2bc6f568ea8024fd775a783e13c"
JVM_RDEPENDS:x86_64 = " \
  alsa-lib (>= 0.9) \
  freetype (>= 2.13) \
  glibc (>= 2.17) \
  libx11 (>= 1.7) \
  libxext (>= 1.3) \
  libxi (>= 1.8) \
  libxrender (>= 0.9) \
  libxtst (>= 1.2) \
  zlib (>= 1.2) \
"
JVM_CHECKSUM:riscv64 = "2d1ed42918305a1a0754a6e1e9294c7d4d7fda4bff6dba7bc5682037d860dbc9"
JVM_RDEPENDS:riscv64 = " \
  alsa-lib (>= 0.9) \
  freetype (>= 2.13) \
  glibc (>= 2.17) \
  libx11 (>= 1.7) \
  libxext (>= 1.3) \
  libxi (>= 1.8) \
  libxrender (>= 0.9) \
  libxtst (>= 1.2) \
  zlib (>= 1.2) \
"

RDEPENDS:${PN} = "${JVM_RDEPENDS}"

API_RELEASE_NAME = "jdk-${PV}"
API_OS = "linux"
API_ARCH:aarch64 = "aarch64"
API_ARCH:arm = "arm"
API_ARCH:x86_64 = "x64"
API_ARCH:riscv64 = "riscv64"
API_IMAGE_TYPE = "jre"
API_JVM_IMPL = "hotspot"
API_HEAP_SIZE ?= "normal"
API_VENDOR = "eclipse"

SRC_URI = "https://api.adoptium.net/v3/binary/version/${API_RELEASE_NAME}/${API_OS}/${API_ARCH}/${API_IMAGE_TYPE}/${API_JVM_IMPL}/${API_HEAP_SIZE}/${API_VENDOR};name=binary;downloadfilename=${BPN}-${API_ARCH}-${PV}.tar.gz;subdir=${BPN}-${PV};striplevel=1"
SRC_URI[binary.sha256sum] = "${JVM_CHECKSUM}"

# Provide sources of the JVM because of its license.
ADAPTED_PV_SRC = ""
python () {
  import re
  d.setVar('ADAPTED_PV_SRC', d.getVar('PV').replace('+', '_'))
}
SRC_URI:append = " https://github.com/adoptium/temurin17-binaries/releases/download/${API_RELEASE_NAME}/OpenJDK17U-jdk-sources_${ADAPTED_PV_SRC}.tar.gz;name=sources;downloadfilename=${BPN}-sources-${PV}.tar.gz;unpack=false"
SRC_URI[sources.sha256sum] = "bbc5fe161ea3aad2aad855a7954927d18bbdce1d74d14f2922a85ca50a74fec5"

libdir_jre = "${libdir}/jvm/openjdk-17-jre"

# Prevent the packaging task from stripping out
# debugging symbols, since there are none.
INSANE_SKIP:${PN} = "ldflags"
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
  install -d ${D}${libdir_jre}
  cp -R --no-dereference --preserve=mode,links -v ${S}/* ${D}${libdir_jre}

  LDLINUX=$(basename $(ls -1 ${RECIPE_SYSROOT}${base_libdir}/ld-linux* | sort | head -n1))
  if [ -n "$LDLINUX" ]; then
    for i in ${D}${libdir}/jvm/${BPN}/bin/* ; do
      patchelf --set-interpreter ${base_libdir}/$LDLINUX $i
    done
  fi
}

RPROVIDES:${PN} = "java2-runtime"
FILES:${PN} = "${libdir_jre}"

inherit update-alternatives
ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "java keytool"
ALTERNATIVE_LINK_NAME[java] = "${bindir}/java"
ALTERNATIVE_TARGET[java] = "${libdir_jre}/bin/java"
ALTERNATIVE_LINK_NAME[keytool] = "${bindir}/keytool"
ALTERNATIVE_TARGET[keytool] = "${libdir_jre}/bin/keytool"
