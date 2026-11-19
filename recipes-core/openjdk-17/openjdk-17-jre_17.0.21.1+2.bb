require openjdk-17-target.inc

SUMMARY = "Prebuilt OpenJDK JRE for Java 17 offered by Adoptium."
DESCRIPTION = "OpenJDK 17 Java Runtime Environment for target builds."

API_IMAGE_TYPE = "jre"
JVM_BASE_PV = "17.0.21+9"
JVM_CHECKSUM:aarch64 = "1114f09d6174f54270c3117410b5e4022b43ebc0b43b751af08b927c55ed7bb5"
JVM_CHECKSUM:arm = "a1f61c0a9a76af31e6954f93ba329650ce16e556d0508227a50c6203ff012357"
JVM_CHECKSUM:x86-64 = "5a092a0bc21de4c3d855480edd7945be9cfe157b38cdb84dac280511893bb009"
JVM_CHECKSUM:riscv64 = "106afdb1779c2187312717f76d116b6920b96bf8acf8c4b9055655cd7d01415a"
JVM_SRC_CHECKSUM = "8f1f079f28e006318c4f79f0736dbc1300cb4a07f8b7e3ff4d8efc3ef914ea73"

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
