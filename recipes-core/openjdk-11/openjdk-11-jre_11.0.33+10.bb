require openjdk-11-target.inc

SUMMARY = "Prebuilt OpenJDK JRE for Java 11 offered by Adoptium."
DESCRIPTION = "OpenJDK 11 Java Runtime Environment for target builds."

API_IMAGE_TYPE = "jre"
JVM_CHECKSUM:aarch64 = "9691319ce9c30abe24abf679409c48467f8476b5c87ff24735efae63036e0e6b"
JVM_CHECKSUM:arm = "654c48e5bee5675eda8478e255d8dd227415b93af9a8072f35f771bd3db11563"
JVM_CHECKSUM:x86-64 = "038086c264806875f3610317acc3455ce8daaf73561349c514efd646bb24a6e9"
JVM_SRC_CHECKSUM = "9611b9f4531dc003b1583ed531a71234e4cadb2dddbd4f79b2de0b339a8d4fa6"

# Multiple, different copies of this library are installed on 32-bit
# ARM. This library (and probably all the other .so's used by the JVM)
# aren't meant for linking against by other recipes, so just tell
# the packaging code to skip the normal shared library processing.
PRIVATE_LIBS = "libjvm.so"

inherit update-alternatives
ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "java keytool"
ALTERNATIVE_LINK_NAME[java] = "${bindir}/java"
ALTERNATIVE_TARGET[java] = "${libdir_jvm}/bin/java"
ALTERNATIVE_LINK_NAME[keytool] = "${bindir}/keytool"
ALTERNATIVE_TARGET[keytool] = "${libdir_jvm}/bin/keytool"

RPROVIDES:${PN} = "java2-runtime"
