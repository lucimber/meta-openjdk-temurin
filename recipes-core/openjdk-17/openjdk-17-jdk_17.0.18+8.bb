require openjdk-17-target.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 17 offered by Adoptium."
DESCRIPTION = "OpenJDK 17 Java Development Kit for target builds."

API_IMAGE_TYPE = "jdk"
JVM_CHECKSUM:aarch64 = "592a6702b3a07a0e0b82cb38aaab149bfce1b0c24d6b57ddb410bd9009333095"
JVM_CHECKSUM:arm = "21050b8325b62cb3fca4f871aadbddc04c67e21f3ab57236439aa951cbcb17ae"
JVM_CHECKSUM:x86-64 = "0c94cbb54325c40dcf026143eb621562017db5525727f2d9131a11250f72c450"
JVM_CHECKSUM:riscv64 = "485f49ec3f7048b466c3b8e5b543932c8aae845a1ba95ebb30fc527019371ed4"
JVM_SRC_CHECKSUM = "c1a0ace2cd38bc3592c9d4ec2fccd7a1dac0c38e4e92589102fba1a63322b8d9"

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
