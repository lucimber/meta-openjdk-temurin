require openjdk-21-native.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 21 offered by Adoptium."
DESCRIPTION = "OpenJDK 21 Java Development Kit for native builds."

API_IMAGE_TYPE = "jdk"
JVM_BASE_PV = "21.0.13+9"
JVM_CHECKSUM:aarch64 = "e3522e707fc7dbe2a72c30746b1e7068301a0213ecd213d4491f35a14eb55578"
JVM_CHECKSUM:x86-64 = "bb8a93f9ae6e7a693b2b721e85dbeff26ae1a77447985a8d7afcdd9c7cf6222e"
JVM_CHECKSUM:riscv64 = "639d712c96899ec84e95394f90e8b4cc6a0ad0154bbec2fe657f8da00b182013"
JVM_SRC_CHECKSUM = "427e3eaec042f61c93258b5a42c86f3ddbad45a2ba95132dd4a3e3c07414bc8c"
