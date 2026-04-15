require openjdk-8-target.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 8 offered by Adoptium."
DESCRIPTION = "OpenJDK 8 Java Development Kit for target builds."

API_IMAGE_TYPE = "jdk"
JVM_CHECKSUM:aarch64 = "ada72fbf191fb287b4c1e54be372b64c40c27c2ffbfa01f880c92af11f4e7c94"
JVM_CHECKSUM:arm = "1d0d16394e2fe637f9eb8e73e63ea6fe9ceee98337c0527aa058cee777ad638a"
JVM_CHECKSUM:x86-64 = "e74becad56b4cc01f1556a671e578d3788789f5257f9499f6fbed84e63a55ecf"
JVM_SRC_CHECKSUM = "5685fa2e9c1dff2e62010f389001f5ec6ab5df14f64c4cd4371f522157cf0025"

# Multiple, different copies of this library are installed. This
# library (and probably all the other .so's used by the JVM)
# aren't meant for linking against by other recipes, so just tell
# the packaging code to skip the normal shared library processing.
PRIVATE_LIBS = "libjli.so"

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
