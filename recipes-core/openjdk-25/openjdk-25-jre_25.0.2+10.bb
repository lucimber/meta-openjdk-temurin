require openjdk-25-target.inc

SUMMARY = "Prebuilt OpenJDK JRE for Java 25 offered by Adoptium."
DESCRIPTION = "OpenJDK 25 Java Runtime Environment for target builds."

API_IMAGE_TYPE = "jre"
JVM_CHECKSUM:aarch64 = "e90ad4a618a0228a2126e7c6abfbc0729e2649d7d72cef45fd640239866eb050"
JVM_CHECKSUM:x86-64 = "d6c89e08f42be94cd55eab20190958a35b993625018a3ac59cb3d16d8445cf98"
JVM_CHECKSUM:riscv64 = "0be0aa0a9578d229c2de2e9e05741d1c0726185a2017f8ce2249989f79dc9562"
JVM_SRC_CHECKSUM = "4f2df6cdf3870adb23ca71d162dec9238e71834785c81cbd393579aa18cdd045"

inherit update-alternatives
ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "java keytool"
ALTERNATIVE_LINK_NAME[java] = "${bindir}/java"
ALTERNATIVE_TARGET[java] = "${libdir_jvm}/bin/java"
ALTERNATIVE_LINK_NAME[keytool] = "${bindir}/keytool"
ALTERNATIVE_TARGET[keytool] = "${libdir_jvm}/bin/keytool"

RPROVIDES:${PN} = "java2-runtime"
