DESCRIPTION = "GNU libatomic"
HOMEPAGE = "https://gcc.gnu.org/"
LICENSE = "GPL-3.0-with-GCC-exception"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-with-GCC-exception;md5=aef5f35c9272f508be848cd99e0151df"

SRC_URI = "git://gcc.gnu.org/gcc.git;tag=releases/gcc-11.3.0;subpath=libatomic;protocol=https"

inherit autotools
