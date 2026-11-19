require openjdk-21-target.inc

SUMMARY = "Prebuilt OpenJDK JRE for Java 21 offered by Adoptium."
DESCRIPTION = "OpenJDK 21 Java Runtime Environment for target builds."

API_IMAGE_TYPE = "jre"
JVM_BASE_PV = "21.0.13+9"
JVM_CHECKSUM:aarch64 = "b1734db7ade49c3f6acc6ae06233b8c2dd90e3e2f00f67000ae1c972aaa64d8c"
JVM_CHECKSUM:x86-64 = "18f44fab9065217f3ba61d0eef8a7b5f3c3713aa7293001b0d4ac10ede3850f0"
JVM_CHECKSUM:riscv64 = "40c66736338abeb8b633d50036e5f83e3a3f55654723f31589fa09c081eec118"
JVM_SRC_CHECKSUM = "427e3eaec042f61c93258b5a42c86f3ddbad45a2ba95132dd4a3e3c07414bc8c"

inherit update-alternatives
ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "java keytool"
ALTERNATIVE_LINK_NAME[java] = "${bindir}/java"
ALTERNATIVE_TARGET[java] = "${libdir_jvm}/bin/java"
ALTERNATIVE_LINK_NAME[keytool] = "${bindir}/keytool"
ALTERNATIVE_TARGET[keytool] = "${libdir_jvm}/bin/keytool"

RPROVIDES:${PN} = "java2-runtime"
