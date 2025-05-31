require openjdk-8-target.inc

SUMMARY = "Prebuilt OpenJDK JRE for Java 8 offered by Adoptium."
DESCRIPTION = "OpenJDK 8 Java Runtime Environment for target builds."

API_IMAGE_TYPE = "jre"
JVM_CHECKSUM:aarch64 = "8fbefff2c578f73d95118d830347589ddc9aa84510200a5a5001901c2dea4810"
JVM_CHECKSUM:arm = "13bdefdeae6f18bc9c87bba18c853b8b12c5442ce07ff0a3956ce28776d695ff"
JVM_CHECKSUM:x86-64 = "0ac516cc1eadffb4cd3cfc9736a33d58ea6a396bf85729036c973482f7c063d9"
JVM_SRC_CHECKSUM = "ed95a098f1036b1300d6a6470f77b3445c02d2994e4d6ef8ef991183129cda80"

inherit update-alternatives
ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "java keytool"
ALTERNATIVE_LINK_NAME[java] = "${bindir}/java"
ALTERNATIVE_TARGET[java] = "${libdir_jvm}/bin/java"
ALTERNATIVE_LINK_NAME[keytool] = "${bindir}/keytool"
ALTERNATIVE_TARGET[keytool] = "${libdir_jvm}/bin/keytool"

RPROVIDES:${PN} = "java2-runtime"
