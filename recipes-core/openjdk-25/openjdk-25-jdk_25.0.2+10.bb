require openjdk-25-target.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 25 offered by Adoptium."
DESCRIPTION = "OpenJDK 25 Java Development Kit for target builds."

API_IMAGE_TYPE = "jdk"
JVM_CHECKSUM:aarch64 = "a9d73e711d967dc44896d4f430f73a68fd33590dabc29a7f2fb9f593425b854c"
JVM_CHECKSUM:x86-64 = "987387933b64b9833846dee373b640440d3e1fd48a04804ec01a6dbf718e8ab8"
JVM_CHECKSUM:riscv64 = "168119e4fba350f4e6b3ca92450a2b90a8502b89a235a04415e9adf9f5d3164e"
JVM_SRC_CHECKSUM = "4f2df6cdf3870adb23ca71d162dec9238e71834785c81cbd393579aa18cdd045"

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
