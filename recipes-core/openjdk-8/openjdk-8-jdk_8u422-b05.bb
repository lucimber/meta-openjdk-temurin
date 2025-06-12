require openjdk-8-target.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 8 offered by Adoptium."
DESCRIPTION = "OpenJDK 8 Java Development Kit for target builds."

API_IMAGE_TYPE = "jdk"
JVM_CHECKSUM:aarch64 = "af98a839ec238106078bd360af9e405dc6665c05ee837178ed13b92193681923"
JVM_CHECKSUM:arm = "5bd0203b2b09b033e3a762299a4975939d7571b433eab8b59340cc966102bef1"
JVM_CHECKSUM:x86-64 = "4c6056f6167fae73ace7c3080b78940be5c87d54f5b08894b3517eed1cbb2c06"
JVM_SRC_CHECKSUM = "ed95a098f1036b1300d6a6470f77b3445c02d2994e4d6ef8ef991183129cda80"

# do_install:append purpose:
# OpenJDK installs libjli.so in two locations:
#   - ${D}/usr/lib/jvm/openjdk-8-jdk/lib/amd64/jli/libjli.so (JDK)
#   - ${D}/usr/lib/jvm/openjdk-8-jdk/jre/lib/amd64/jli/libjli.so (JRE)
#
# This causes a Yocto QA error: "Multiple shlib providers for libjli.so"
# because both copies are treated as independent shared library providers.
#
# To resolve this, we:
#   1. Remove the top-level (JDK) copy of libjli.so
#   2. Replace it with a symlink to the JRE copy
#
# This keeps only one real copy of libjli.so, avoids QA issues,
# and preserves compatibility for any tools expecting it at the JDK path.
do_install:append() {
  # Path to the libjli.so in both locations
  LIBJLI_JRE="${D}/usr/lib/jvm/${PN}/jre/lib/amd64/jli/libjli.so"
  LIBJLI_TOP="${D}/usr/lib/jvm/${PN}/lib/amd64/jli/libjli.so"

  # Remove the duplicate from the top-level lib directory
  if [ -f "$LIBJLI_TOP" ]; then
    echo "Removing duplicate libjli.so from top-level JDK lib path"
    rm -f "$LIBJLI_TOP"
  fi

  # Replace with a symlink to the JRE copy
  if [ -f "$LIBJLI_JRE" ]; then
    echo "Creating symlink for libjli.so pointing to JRE copy"
    ln -sfn "../../../jre/lib/amd64/jli/libjli.so" "${LIBJLI_TOP}"
  fi

  # Sanity log for all .so locations
  echo "Listing all libjli.so instances in image tree:"
  find ${D} -name "libjli.so" || true
}

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
