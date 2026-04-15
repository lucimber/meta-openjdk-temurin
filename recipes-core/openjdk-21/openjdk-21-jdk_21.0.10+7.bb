require openjdk-21-target.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 21 offered by Adoptium."
DESCRIPTION = "OpenJDK 21 Java Development Kit for target builds."

API_IMAGE_TYPE = "jdk"
JVM_CHECKSUM:aarch64 = "357fee29fb0d5c079f6730db98b28942df13a6eed426f6c61cd4ad703ab27b9a"
JVM_CHECKSUM:x86-64 = "ea3b9bd464d6dd253e9a7accf59f7ccd2a36e4aa69640b7251e3370caef896a4"
JVM_CHECKSUM:riscv64 = "a57fd486c3c24ed615eb91ef9421ddd38c720e7398df5a161872fb26ad825936"
JVM_SRC_CHECKSUM = "a286b69953cdb56ab2dc74287e6ebaca8fad7d397a4b5f975b73d23eedeec252"

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
