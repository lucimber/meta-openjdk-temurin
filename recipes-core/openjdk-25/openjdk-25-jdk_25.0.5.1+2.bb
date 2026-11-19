require openjdk-25-target.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 25 offered by Adoptium."
DESCRIPTION = "OpenJDK 25 Java Development Kit for target builds."

API_IMAGE_TYPE = "jdk"
JVM_BASE_PV = "25.0.5+8"
JVM_CHECKSUM:aarch64 = "bb5b2ded252f21c6090cf780b0a408ad0d83bbb557e702955643557f13231880"
JVM_CHECKSUM:x86-64 = "760f91b22c3efc9bd1b64ff950598231be82928cc807e30691a0e7a2995a62c0"
JVM_CHECKSUM:riscv64 = "21d9ee84f64d845e7de888f86cc2abe3256c75853a0d5abe7fd42d6526374e90"
JVM_SRC_CHECKSUM = "3fb4af3a690f6c0f47462f3f813a8f09dc4a237f0c6ab462a764f15bc15313a7"

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
