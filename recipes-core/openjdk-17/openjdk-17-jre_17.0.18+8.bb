require openjdk-17-target.inc

SUMMARY = "Prebuilt OpenJDK JRE for Java 17 offered by Adoptium."
DESCRIPTION = "OpenJDK 17 Java Runtime Environment for target builds."

API_IMAGE_TYPE = "jre"
JVM_CHECKSUM:aarch64 = "88727c16610d118c0e739f62e6c99dc1cb5a7b4a93a27054fe2a3aa7150e7b5d"
JVM_CHECKSUM:arm = "437c30e861fb091d4bb2ff66a28b1d09e7ac9167f6163e286cb2968d29864e1b"
JVM_CHECKSUM:x86-64 = "8b418e38cca87945858ae911988401720095eb671357d47437b4adb49c28dcab"
JVM_CHECKSUM:riscv64 = "2c12bc1a94c04702935f61f5d15e4950c1ae3f02936931fcc15affc3d22f5d48"
JVM_SRC_CHECKSUM = "c1a0ace2cd38bc3592c9d4ec2fccd7a1dac0c38e4e92589102fba1a63322b8d9"

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
