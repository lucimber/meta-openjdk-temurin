require openjdk-8-target.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 8 offered by Adoptium."
DESCRIPTION = "OpenJDK 8 Java Development Kit for target builds."

API_IMAGE_TYPE = "jdk"
JVM_CHECKSUM:aarch64 = "af98a839ec238106078bd360af9e405dc6665c05ee837178ed13b92193681923"
JVM_CHECKSUM:arm = "5bd0203b2b09b033e3a762299a4975939d7571b433eab8b59340cc966102bef1"
JVM_CHECKSUM:x86-64 = "4c6056f6167fae73ace7c3080b78940be5c87d54f5b08894b3517eed1cbb2c06"
JVM_SRC_CHECKSUM = "ed95a098f1036b1300d6a6470f77b3445c02d2994e4d6ef8ef991183129cda80"

# Multiple, different copies of this library are installed. This
# library (and probably all the other .so's used by the JVM)
# aren't meant for linking against by other recipes, so just tell
# the packaging code to skip the normal shared library processing.
PRIVATE_LIBS = "libjli.so"

inherit update-alternatives
ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "jar java javac keytool"
ALTERNATIVE_LINK_NAME[jar] = "${bindir}/jar"
ALTERNATIVE_TARGET[jar] = "${libdir_jvm}/bin/jar"
ALTERNATIVE_LINK_NAME[java] = "${bindir}/java"
ALTERNATIVE_TARGET[java] = "${libdir_jvm}/bin/java"
ALTERNATIVE_LINK_NAME[javac] = "${bindir}/javac"
ALTERNATIVE_TARGET[javac] = "${libdir_jvm}/bin/javac"
ALTERNATIVE_LINK_NAME[keytool] = "${bindir}/keytool"
ALTERNATIVE_TARGET[keytool] = "${libdir_jvm}/bin/keytool"
