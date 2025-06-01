require openjdk-11-target.inc

SUMMARY = "Prebuilt OpenJDK JRE for Java 11 offered by Adoptium."
DESCRIPTION = "OpenJDK 11 Java Runtime Environment for target builds."

API_IMAGE_TYPE = "jre"
JVM_CHECKSUM:aarch64 = "1fe97cdaad47d7d108f329c6e4560b46748ef7f2948a1027812ade0bbc2a3597"
JVM_CHECKSUM:arm = "bf893085627c6ec484e63aa1290276b23bcfee547459da6b0432ae9c5c1be22a"
JVM_CHECKSUM:x86-64 = "e0c1938093da3780e4494d366a4e6b75584dde8d46a19acea6691ae11df4cda5"
JVM_SRC_CHECKSUM = "3f8b36b6bd13e6b9bffdb19805b49baf9fa4eb977bbf9564db3a95151777dbed"

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
