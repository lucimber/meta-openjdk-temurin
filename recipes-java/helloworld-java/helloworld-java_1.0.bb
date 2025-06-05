SUMMARY = "Hello World Java Example"
DESCRIPTION = "A simple Hello World Java program for Yocto"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

JDK_VERSION ??= "8"

# javac required at buildtime (define version at local.conf via virtual)
DEPENDS = "openjdk-${JDK_VERSION}-jdk-native"
# JRE required at runtime (select version at local.conf via virtual)
#RDEPENDS:${PN} += "${VIRTUAL-RUNTIME_openjdk-jre}"
RDEPENDS:${PN} += "openjdk-${JDK_VERSION}-jre"

PATH =. "${STAGING_LIBDIR_NATIVE}/jvm/openjdk-${JDK_VERSION}-jdk/bin:"

SRC_URI = "file://HelloWorld.java"
SRC_URI += "file://helloworld-java.sh.in"
S = "${WORKDIR}"

do_compile() {
    javac HelloWorld.java
    sed -e "s,@JAVA_HOME@,${libdir}/jvm/openjdk-${JDK_VERSION}-jre," \
        -e "s,@CLASSPATH@,${libdir}/${BPN}," \
        ${S}/helloworld-java.sh.in > ${B}/helloworld-java
}

do_install() {
    install -D -m 0755 -t ${D}${bindir} ${B}/helloworld-java
    install -D -m 0644 -t ${D}${libdir}/${BPN} ${B}/HelloWorld.class
}

FILES:${PN} += "${libdir}/${BPN}"
