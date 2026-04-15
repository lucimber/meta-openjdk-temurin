require openjdk-17-target.inc

SUMMARY = "Prebuilt OpenJDK JRE for Java 17 offered by Adoptium."
DESCRIPTION = "OpenJDK 17 Java Runtime Environment for target builds."

API_IMAGE_TYPE = "jre"
JVM_CHECKSUM:aarch64 = "9dfe4c56463690ae67d22268980d8861eb46b907d7914f8f2e6fc7b25778c8ec"
JVM_CHECKSUM:arm = "f093094abe0cb2bb5a255d8180810030321073520541f289926c4682eda76136"
JVM_CHECKSUM:x86-64 = "0e8088d7a3a7496faba7ac8787db09dc0264c2bc6f568ea8024fd775a783e13c"
JVM_CHECKSUM:riscv64 = "2d1ed42918305a1a0754a6e1e9294c7d4d7fda4bff6dba7bc5682037d860dbc9"
JVM_SRC_CHECKSUM = "bbc5fe161ea3aad2aad855a7954927d18bbdce1d74d14f2922a85ca50a74fec5"

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
