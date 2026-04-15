require openjdk-21-target.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 21 offered by Adoptium."
DESCRIPTION = "OpenJDK 21 Java Development Kit for target builds."

API_IMAGE_TYPE = "jdk"
JVM_CHECKSUM:aarch64 = "d768eecddd7a515711659e02caef8516b7b7177fa34880a56398fd9822593a79"
JVM_CHECKSUM:x86-64 = "51fb4d03a4429c39d397d3a03a779077159317616550e4e71624c9843083e7b9"
JVM_CHECKSUM:riscv64 = "b04fd7f52d18268a935f1a7144dae802b25db600ec97156ddd46b3100cbd13da"
JVM_SRC_CHECKSUM = "ba38841876aeec064b1f0d723117908b25b3961685b9801942c707e5e9bd47d1"

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
