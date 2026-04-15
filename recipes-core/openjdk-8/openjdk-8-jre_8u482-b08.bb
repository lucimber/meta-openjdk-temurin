require openjdk-8-target.inc

SUMMARY = "Prebuilt OpenJDK JRE for Java 8 offered by Adoptium."
DESCRIPTION = "OpenJDK 8 Java Runtime Environment for target builds."

API_IMAGE_TYPE = "jre"
JVM_CHECKSUM:aarch64 = "46496dfa7e58784adf96a3a2bd1ac8be9fda2d8749a9c52bf74fb582aa1449e2"
JVM_CHECKSUM:arm = "29c93e29421b9f77bc95305834f3239313466fd988111b5cf409fd9a2f727cff"
JVM_CHECKSUM:x86-64 = "01672ca52509f4cb1ffa8aed905808fed7b984f3e279cb13d90a6e865ff6199f"
JVM_SRC_CHECKSUM = "5685fa2e9c1dff2e62010f389001f5ec6ab5df14f64c4cd4371f522157cf0025"

inherit update-alternatives
ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "java keytool"
ALTERNATIVE_LINK_NAME[java] = "${bindir}/java"
ALTERNATIVE_TARGET[java] = "${libdir_jvm}/bin/java"
ALTERNATIVE_LINK_NAME[keytool] = "${bindir}/keytool"
ALTERNATIVE_TARGET[keytool] = "${libdir_jvm}/bin/keytool"

RPROVIDES:${PN} = "java2-runtime"
