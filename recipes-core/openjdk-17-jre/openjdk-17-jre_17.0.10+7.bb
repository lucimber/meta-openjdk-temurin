SUMMARY = "Prebuilt OpenJDK JRE for Java 17 offered by Adoptium."
HOMEPAGE = "https://adoptium.net"
LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-with-classpath-exception;md5=6133e6794362eff6641708cfcc075b80"

COMPATIBLE_HOST = "(x86_64|arm|aarch64).*-linux"
OVERRIDES = "${TARGET_ARCH}"

DEPENDS = "patchelf-native"

JVM_CHECKSUM:aarch64 = "16080d055da0962fbd6b40f659a98a457cba3efa7ea716d5400cfebe8b935bf0"
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
JVM_CHECKSUM:arm = "0378bdf6769632b182b27ba4e53b17eaefefdbafa3845c15e1bd88a5aeec8442"
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
JVM_CHECKSUM:x86_64 = "620cc0e7338f2722f3ed076ac65c0fafb575981426bac4e1970860e5e2d048f0"
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

SRC_URI = "https://api.adoptium.net/v3/binary/version/${API_RELEASE_NAME}/${API_OS}/${API_ARCH}/${API_IMAGE_TYPE}/${API_JVM_IMPL}/${API_HEAP_SIZE}/${API_VENDOR};name=binary;downloadfilename=${BPN}-${API_ARCH}-${PV}.tar.gz;subdir=${BPN}-${PV}"
SRC_URI[binary.sha256sum] = "${JVM_CHECKSUM}"

# Provide sources of the JVM because of its license.
ADAPTED_PV_SRC = ""
python () {
  import re
  d.setVar('ADAPTED_PV_SRC', d.getVar('PV').replace('+', '_'))
}
SRC_URI:append = " https://github.com/adoptium/temurin17-binaries/releases/download/${API_RELEASE_NAME}/OpenJDK17U-jdk-sources_${ADAPTED_PV_SRC}.tar.gz;name=sources;downloadfilename=${BPN}-sources-${PV}.tar.gz;unpack=false"
SRC_URI[sources.sha256sum] = "111988b009f6856c717a1a55753bd72048adfd637ed8f232aaf6d96ec329a9d4"

S = "${WORKDIR}/${BPN}-${PV}/jdk-17.0.10+7-jre"

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
