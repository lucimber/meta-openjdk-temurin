require openjdk-25-target.inc

SUMMARY = "Prebuilt OpenJDK JRE for Java 25 offered by Adoptium."
DESCRIPTION = "OpenJDK 25 Java Runtime Environment for target builds."

API_IMAGE_TYPE = "jre"
JVM_BASE_PV = "25.0.5+8"
JVM_CHECKSUM:aarch64 = "963931723891302ebb2687178532c3d793d71e2577cd058061c7efb396140a86"
JVM_CHECKSUM:x86-64 = "2edef9e4f81ec5ee426d023336d4cfbd7b4644e99884e7a73a6a867b70b17acf"
JVM_CHECKSUM:riscv64 = "224c72d235b0b9ae49e642ae75ade8d8ed69ff693b9ae2fa7cef8e0e48760289"
JVM_SRC_CHECKSUM = "3fb4af3a690f6c0f47462f3f813a8f09dc4a237f0c6ab462a764f15bc15313a7"

inherit update-alternatives
ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "java keytool"
ALTERNATIVE_LINK_NAME[java] = "${bindir}/java"
ALTERNATIVE_TARGET[java] = "${libdir_jvm}/bin/java"
ALTERNATIVE_LINK_NAME[keytool] = "${bindir}/keytool"
ALTERNATIVE_TARGET[keytool] = "${libdir_jvm}/bin/keytool"

RPROVIDES:${PN} = "java2-runtime"
