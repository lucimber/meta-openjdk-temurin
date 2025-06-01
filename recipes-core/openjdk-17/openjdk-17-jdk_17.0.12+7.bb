require openjdk-17-target.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 17 offered by Adoptium."
DESCRIPTION = "OpenJDK 17 Java Development Kit for target builds."

API_IMAGE_TYPE = "jdk"
JVM_CHECKSUM:aarch64 = "8257de06bf37f0c8f19f8d542e2ab5a4e17db3ca5f29d041bd0b02ab265db021"
JVM_CHECKSUM:arm = "ce7873ebf40ed0eb1089941ead4d3af79a205b1264f3162860d26ae957572b74"
JVM_CHECKSUM:x86-64 = "9d4dd339bf7e6a9dcba8347661603b74c61ab2a5083ae67bf76da6285da8a778"
JVM_CHECKSUM:riscv64 = "d024c100eba4709970716ddcac757ba5e3122a8ff9c6f539ff8bac5b47f51f3a"
JVM_SRC_CHECKSUM = "bbc5fe161ea3aad2aad855a7954927d18bbdce1d74d14f2922a85ca50a74fec5"

# Multiple, different copies of this library are installed on 32-bit
# ARM. This library (and probably all the other .so's used by the JVM)
# aren't meant for linking against by other recipes, so just tell
# the packaging code to skip the normal shared library processing.
PRIVATE_LIBS = "libjvm.so"

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
