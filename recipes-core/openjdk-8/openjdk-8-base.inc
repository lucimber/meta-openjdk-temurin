HOMEPAGE = "https://adoptium.net"
LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-with-classpath-exception;md5=6133e6794362eff6641708cfcc075b80"
COMPATIBLE_HOST = "(x86_64|arm|aarch64).*-linux"
DEPENDS = "alsa-lib freetype libx11 libxext libxi libxrender libxtst"

API_RELEASE_NAME = "jdk${PV}"
API_OS = "linux"
API_ARCH:aarch64 = "aarch64"
API_ARCH:arm = "arm"
API_ARCH:x86-64 = "x64"
API_JVM_IMPL = "hotspot"
API_HEAP_SIZE ?= "normal"
API_VENDOR = "eclipse"

SRC_URI = "https://api.adoptium.net/v3/binary/version/${API_RELEASE_NAME}/${API_OS}/${API_ARCH}/${API_IMAGE_TYPE}/${API_JVM_IMPL}/${API_HEAP_SIZE}/${API_VENDOR};name=binary;downloadfilename=${BPN}-${API_ARCH}-${PV}.tar.gz;subdir=${BPN}-${PV};striplevel=1"
SRC_URI[binary.sha256sum] = "${JVM_CHECKSUM}"

# Provide sources of the JVM because of its license.
ADAPTED_PV_SRC = ""
python () {
  import re
  d.setVar('ADAPTED_PV_SRC', d.getVar('PV').replace('-', ''))
}
SRC_URI:append = " https://github.com/adoptium/temurin8-binaries/releases/download/${API_RELEASE_NAME}/OpenJDK8U-jdk-sources_${ADAPTED_PV_SRC}.tar.gz;name=sources;downloadfilename=${BPN}-sources-${PV}.tar.gz;unpack=false"
SRC_URI[sources.sha256sum] = "${JVM_SRC_CHECKSUM}"
