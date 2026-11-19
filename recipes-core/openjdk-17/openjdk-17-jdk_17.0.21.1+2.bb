require openjdk-17-target.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 17 offered by Adoptium."
DESCRIPTION = "OpenJDK 17 Java Development Kit for target builds."

API_IMAGE_TYPE = "jdk"
JVM_BASE_PV = "17.0.21+9"
JVM_CHECKSUM:aarch64 = "3e4eca7ead088f080af1476007decf010a240a1eb4e1e78c54fa32fbcc347084"
JVM_CHECKSUM:arm = "7f7aa599ab9d0a594c70e82c7c67fc97b6eb28750d0843a4db8e6fa41252fbe5"
JVM_CHECKSUM:x86-64 = "0b0ac5eab6db6abaec68c9913bcddb31321e5da411c45073d520d9c125e6f7b4"
JVM_CHECKSUM:riscv64 = "5c74fd8f5440098c8f44e54cad428b3915ffd3f2d1ec7967ac78bc6411c4380b"
JVM_SRC_CHECKSUM = "8f1f079f28e006318c4f79f0736dbc1300cb4a07f8b7e3ff4d8efc3ef914ea73"

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
