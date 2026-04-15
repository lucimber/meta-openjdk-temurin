require openjdk-21-target.inc

SUMMARY = "Prebuilt OpenJDK JRE for Java 21 offered by Adoptium."
DESCRIPTION = "OpenJDK 21 Java Runtime Environment for target builds."

API_IMAGE_TYPE = "jre"
JVM_CHECKSUM:aarch64 = "3ca84da7c4f57eee8d7e7f0645dc904a3a06456d32b37a4dd57a5e7527245250"
JVM_CHECKSUM:x86-64 = "991be6ac6725e76109ecbd131d658f992dcbeacba3a8b4b6650302c8012b52fb"
JVM_CHECKSUM:riscv64 = "02cf763836c14bad4d689eb3b4efd691657de753dba07193cd1fb8691c8fe7b8"
JVM_SRC_CHECKSUM = "a286b69953cdb56ab2dc74287e6ebaca8fad7d397a4b5f975b73d23eedeec252"

inherit update-alternatives
ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "java keytool"
ALTERNATIVE_LINK_NAME[java] = "${bindir}/java"
ALTERNATIVE_TARGET[java] = "${libdir_jvm}/bin/java"
ALTERNATIVE_LINK_NAME[keytool] = "${bindir}/keytool"
ALTERNATIVE_TARGET[keytool] = "${libdir_jvm}/bin/keytool"

RPROVIDES:${PN} = "java2-runtime"
