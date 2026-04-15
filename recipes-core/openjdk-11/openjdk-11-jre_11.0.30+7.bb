require openjdk-11-target.inc

SUMMARY = "Prebuilt OpenJDK JRE for Java 11 offered by Adoptium."
DESCRIPTION = "OpenJDK 11 Java Runtime Environment for target builds."

API_IMAGE_TYPE = "jre"
JVM_CHECKSUM:aarch64 = "9d6a8d3a33c308bbc7332e4c2e2f9a94fbbc56417863496061ef6defef9c5391"
JVM_CHECKSUM:arm = "8cc849890ecee80b002171f54b6df7d14744b83ba44646f52f5ca927a85599b7"
JVM_CHECKSUM:x86-64 = "d851e43d81ec6ff7f28efe28c42b4787a045e8f59cdcd6434dece98d8342eb8a"
JVM_SRC_CHECKSUM = "10c2d10ee2ca1f22798435fc416593f2f972e53caf4b5c54682126c67fd07a2b"

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
