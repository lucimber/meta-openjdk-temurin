SUMMARY = "Prebuilt OpenJDK JRE for Java 11 offered by Adoptium."
HOMEPAGE = "https://adoptium.net"
LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-with-classpath-exception;md5=6133e6794362eff6641708cfcc075b80"

COMPATIBLE_HOST = "(x86_64|arm|aarch64).*-linux"
OVERRIDES = "${TARGET_ARCH}"

DEPENDS = "patchelf-native"

JVM_CHECKSUM:aarch64 = "1fe97cdaad47d7d108f329c6e4560b46748ef7f2948a1027812ade0bbc2a3597"
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
JVM_CHECKSUM:arm = "bf893085627c6ec484e63aa1290276b23bcfee547459da6b0432ae9c5c1be22a"
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
# Prevents error on ARM 32: Multiple shlib providers for libjvm.so
EXCLUDE_FROM_SHLIBS:arm = "1"
JVM_CHECKSUM:x86-64 = "e0c1938093da3780e4494d366a4e6b75584dde8d46a19acea6691ae11df4cda5"
JVM_RDEPENDS:x86-64 = " \
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
SRC_URI:append = " https://github.com/adoptium/temurin11-binaries/releases/download/${API_RELEASE_NAME}/OpenJDK11U-jdk-sources_${ADAPTED_PV_SRC}.tar.gz;name=sources;downloadfilename=${BPN}-sources-${PV}.tar.gz;unpack=false"
SRC_URI[sources.sha256sum] = "3f8b36b6bd13e6b9bffdb19805b49baf9fa4eb977bbf9564db3a95151777dbed"

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
