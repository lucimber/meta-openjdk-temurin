require openjdk-8-target.inc

SUMMARY = "Prebuilt OpenJDK JRE for Java 8 offered by Adoptium."
DESCRIPTION = "OpenJDK 8 Java Runtime Environment for target builds."

API_IMAGE_TYPE = "jre"
JVM_CHECKSUM:aarch64 = "9b45f49bc6e54cf9170aebe0ee45f48a833222325cb3b2d6d21220eee4c5207b"
JVM_CHECKSUM:arm = "bbe01d0683d3dcdd0bbdcc568d3d9123f719a4fa390208b3fe1ca6e1ef083923"
JVM_CHECKSUM:x86-64 = "57468dc8bfd71dd0578d9717e63a8c13b9efe0a2f2c85c2a13966d1c0f9b1e5f"
JVM_SRC_CHECKSUM = "1c5749bc1ac6b6bfd9d9ba0e147e7d674ad2dbdb420153482f5c53a317dfeb2d"

inherit update-alternatives
ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "java keytool"
ALTERNATIVE_LINK_NAME[java] = "${bindir}/java"
ALTERNATIVE_TARGET[java] = "${libdir_jvm}/bin/java"
ALTERNATIVE_LINK_NAME[keytool] = "${bindir}/keytool"
ALTERNATIVE_TARGET[keytool] = "${libdir_jvm}/bin/keytool"

RPROVIDES:${PN} = "java2-runtime"
