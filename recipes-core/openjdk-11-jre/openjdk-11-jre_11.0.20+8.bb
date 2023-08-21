SUMMARY = "Prebuilt OpenJDK JRE for Java 11 offered by Adoptium."
HOMEPAGE = "https://adoptium.net"
LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-with-classpath-exception;md5=6133e6794362eff6641708cfcc075b80"

COMPATIBLE_HOST = "(x86_64|arm|aarch64).*-linux"
OVERRIDES = "${TARGET_ARCH}"

JVM_CHECKSUM:aarch64 = "1fe4b20d808f393422610818711c728331992a4455eeeb061d3d05b45412771d"
JVM_RDEPENDS:aarch64 = " \
  alsa-lib (>= 0.9) \
  freetype (>= 2.11) \
  glibc (>= 2.17) \
  libx11 (>= 1.7) \
  libxext (>= 1.3) \
  libxi (>= 1.8) \
  libxrender (>= 0.9) \
  libxtst (>= 1.2) \
  zlib (>= 1.2) \
"
JVM_CHECKSUM:arm = "cb754b055177381f9f6852b7e5469904a15edddd7f8e136043c28b1e33aee47c"
JVM_RDEPENDS:arm = " \
  alsa-lib (>= 0.9) \
  freetype (>= 2.11) \
  glibc (>= 2.17) \
  libatomic (>= 1.0) \
  libx11 (>= 1.7) \
  libxext (>= 1.3) \
  libxi (>= 1.8) \
  libxrender (>= 0.9) \
  libxtst (>= 1.2) \
  zlib (>= 1.2) \
"
JVM_CHECKSUM:x86_64 = "32dcf760664f93531594b72ce9226e9216567de5705a23c9ff5a77c797948054"
JVM_RDEPENDS:x86_64 = " \
  alsa-lib (>= 0.9) \
  freetype (>= 2.11) \
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
API_IMAGE_TYPE = "jre"
API_JVM_IMPL = "hotspot"
API_HEAP_SIZE ?= "normal"
API_VENDOR = "eclipse"

SRC_URI = "https://api.adoptium.net/v3/binary/version/${API_RELEASE_NAME}/${API_OS}/${API_ARCH}/${API_IMAGE_TYPE}/${API_JVM_IMPL}/${API_HEAP_SIZE}/${API_VENDOR};downloadfilename=${BPN}-${API_ARCH}-${PV}.tar.gz;subdir=${BPN}-${PV};striplevel=1"
SRC_URI[sha256sum] = "${JVM_CHECKSUM}"

libdir_jre = "${libdir}/jvm/openjdk-11-jre"

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
