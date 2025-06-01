require openjdk-11-target.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 11 offered by Adoptium."
DESCRIPTION = "OpenJDK 11 Java Development Kit for target builds."

API_IMAGE_TYPE = "jdk"
JVM_CHECKSUM:aarch64 = "04e21301fedc76841fb03929ac6cacfbbda30b5693acfd515a8f34d4a0cdeb28"
JVM_CHECKSUM:arm = "9d14a076d1440161ab4c9736644e8e9f4719eb8e9f44c03470640960c3cd5e00"
JVM_CHECKSUM:x86-64 = "0e71a01563a5c7b9988a168b0c4ce720a6dff966b3c27bb29d1ded461ff71d0e"
JVM_SRC_CHECKSUM = "3f8b36b6bd13e6b9bffdb19805b49baf9fa4eb977bbf9564db3a95151777dbed"

# Multiple, different copies of this library are installed on 32-bit
# ARM. This library (and probably all the other .so's used by the JVM)
# aren't meant for linking against by other recipes, so just tell
# the packaging code to skip the normal shared library processing.
PRIVATE_LIBS = "libjvm.so"

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
