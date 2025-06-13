require openjdk-17.inc
SUMMARY = "Prebuilt OpenJDK JDK for Java 17 offered by Adoptium."
JVM_CHECKSUM:aarch64 = "8257de06bf37f0c8f19f8d542e2ab5a4e17db3ca5f29d041bd0b02ab265db021"
JVM_CHECKSUM:arm = "ce7873ebf40ed0eb1089941ead4d3af79a205b1264f3162860d26ae957572b74"
JVM_CHECKSUM:x86-64 = "9d4dd339bf7e6a9dcba8347661603b74c61ab2a5083ae67bf76da6285da8a778"
JVM_CHECKSUM:riscv64 = "d024c100eba4709970716ddcac757ba5e3122a8ff9c6f539ff8bac5b47f51f3a"

API_IMAGE_TYPE = "jdk"
BBCLASSEXTEND = "native"

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

# 32-bit ARM includes multiple copies of libjvm.so
PRIVATE_LIBS = "libjvm.so"
