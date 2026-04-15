require openjdk-11-target.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 11 offered by Adoptium."
DESCRIPTION = "OpenJDK 11 Java Development Kit for target builds."

API_IMAGE_TYPE = "jdk"
JVM_CHECKSUM:aarch64 = "3c8fb6754deced4e08a03524b6af1df4f3df451f1832f3dcd3a6848fd54b8d08"
JVM_CHECKSUM:arm = "1ef020c2215f3169c7610df573581806c58f00a0a1d512fd945a2687cbed1173"
JVM_CHECKSUM:x86-64 = "1911fa4010d59985d4cba9f4295c704ae64d08dfc3c2d5747bbc18655b1e911a"
JVM_SRC_CHECKSUM = "10c2d10ee2ca1f22798435fc416593f2f972e53caf4b5c54682126c67fd07a2b"

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
