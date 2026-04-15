require openjdk-21-target.inc

SUMMARY = "Prebuilt OpenJDK JRE for Java 21 offered by Adoptium."
DESCRIPTION = "OpenJDK 21 Java Runtime Environment for target builds."

API_IMAGE_TYPE = "jre"
JVM_CHECKSUM:aarch64 = "58845ce4275f3ec74fba075597c8216bb201773da036c4703be8b7b7b457355d"
JVM_CHECKSUM:x86-64 = "d3affbb011ca6c722948f6345d15eba09bded33f9947d4d67e09723e2518c12a"
JVM_CHECKSUM:riscv64 = "bf814344429f53d11f8aace14d326e2580ea6e66dd81b109c79160bd41735237"
JVM_SRC_CHECKSUM = "ba38841876aeec064b1f0d723117908b25b3961685b9801942c707e5e9bd47d1"

inherit update-alternatives
ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "java keytool"
ALTERNATIVE_LINK_NAME[java] = "${bindir}/java"
ALTERNATIVE_TARGET[java] = "${libdir_jvm}/bin/java"
ALTERNATIVE_LINK_NAME[keytool] = "${bindir}/keytool"
ALTERNATIVE_TARGET[keytool] = "${libdir_jvm}/bin/keytool"

RPROVIDES:${PN} = "java2-runtime"
