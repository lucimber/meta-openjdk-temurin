SUMMARY = "Prebuilt OpenJDK JRE for Java 8 offered by Adoptium."
HOMEPAGE = "https://adoptium.net"
LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-with-classpath-exception;md5=6133e6794362eff6641708cfcc075b80"

COMPATIBLE_HOST = "(x86_64|arm|aarch64).*-linux"
OVERRIDES = "${TARGET_ARCH}"

JVM_CHECKSUM:aarch64 = "f8e440273c8feb3fcfaca88ba18fec291deae18a548adde8a37cd1db08107b95"
JVM_RDEPENDS:aarch64 = " \
  alsa-lib (>= 0.9) \
  freetype (>= 2.11) \
  glibc (>= 2.17) \
  libgcc (>= 4.2) \
  libx11 (>= 1.7) \
  libxext (>= 1.3) \
  libxi (>= 1.8) \
  libxrender (>= 0.9) \
  libxtst (>= 1.2) \
"
JVM_CHECKSUM:arm = "e58e017012838ae4f0db78293e3246cc09958e6ea9a2393c5947ec003bf736dd"
JVM_RDEPENDS:arm = " \
  alsa-lib (>= 0.9) \
  freetype (>= 2.11) \
  glibc (>= 2.17) \
  libatomic (>= 1.0) \
  libgcc (>= 3.5) \
  libx11 (>= 1.7) \
  libxext (>= 1.3) \
  libxi (>= 1.8) \
  libxrender (>= 0.9) \
  libxtst (>= 1.2) \
"
JVM_CHECKSUM:x86_64 = "b6fdfe32085a884c11b31f66aa67ac62811df7112fb6fb08beea61376a86fbb4"
JVM_RDEPENDS:x86_64 = " \
  alsa-lib (>= 0.9) \
  freetype (>= 2.11) \
  glibc (>= 2.17) \
  libgcc (>= 4.2) \
  libx11 (>= 1.7) \
  libxext (>= 1.3) \
  libxi (>= 1.8) \
  libxrender (>= 0.9) \
  libxtst (>= 1.2) \
"

RDEPENDS:${PN} = "${JVM_RDEPENDS}"

API_RELEASE_NAME = "jdk${PV}"
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

libdir_jre = "${libdir}/jvm/openjdk-8-jre"

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
