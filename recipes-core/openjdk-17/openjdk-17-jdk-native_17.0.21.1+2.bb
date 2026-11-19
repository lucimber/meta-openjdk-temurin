require openjdk-17-native.inc

SUMMARY = "Prebuilt OpenJDK JDK for Java 17 offered by Adoptium."
DESCRIPTION = "OpenJDK 17 Java Development Kit for native builds."

API_IMAGE_TYPE = "jdk"
JVM_BASE_PV = "17.0.21+9"
JVM_CHECKSUM:aarch64 = "3e4eca7ead088f080af1476007decf010a240a1eb4e1e78c54fa32fbcc347084"
JVM_CHECKSUM:arm = "7f7aa599ab9d0a594c70e82c7c67fc97b6eb28750d0843a4db8e6fa41252fbe5"
JVM_CHECKSUM:x86-64 = "0b0ac5eab6db6abaec68c9913bcddb31321e5da411c45073d520d9c125e6f7b4"
JVM_CHECKSUM:riscv64 = "5c74fd8f5440098c8f44e54cad428b3915ffd3f2d1ec7967ac78bc6411c4380b"
JVM_SRC_CHECKSUM = "8f1f079f28e006318c4f79f0736dbc1300cb4a07f8b7e3ff4d8efc3ef914ea73"
