require openjdk-8-target.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 8 offered by Adoptium."
DESCRIPTION = "OpenJDK 8 Java Development Kit for target builds."

API_IMAGE_TYPE = "jdk"
JVM_CHECKSUM:aarch64 = "01263df22c4553f63146bbae807e70cd4628dd0d9ec7582f01cb2453f7c69aaa"
JVM_CHECKSUM:arm = "ff18c5421f350fa59b956f763720431c8f861094ec76c06f83a3dfdf502a6bd1"
JVM_CHECKSUM:x86-64 = "fb3bfa869012419b562bb4235d0d83ec4f8c1dda62ae432ba20c6c3a7e43f7e4"
JVM_SRC_CHECKSUM = "1c5749bc1ac6b6bfd9d9ba0e147e7d674ad2dbdb420153482f5c53a317dfeb2d"

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
