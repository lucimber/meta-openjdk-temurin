SUMMARY = "Hello World Java Example"
DESCRIPTION = "A simple Hello World Java program for Yocto"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

JAVA_VERSION ??= "8"

# javac required at buildtime (define version at local.conf via virtual)
DEPENDS = "openjdk-${JAVA_VERSION}-jdk-native"
# JRE required at runtime (select version at local.conf via virtual)
RDEPENDS:${PN} += "openjdk-${JAVA_VERSION}-jre"

SRC_URI = "file://HelloWorld.java"
SRC_URI += "file://helloworld-java.sh.in"
S = "${WORKDIR}"

do_compile() {
    ${STAGING_BINDIR_NATIVE}/javac HelloWorld.java
    sed -e "s,@JAVA_HOME@,${libdir}/jvm/openjdk-${JAVA_VERSION}-jre," \
        -e "s,@CLASSPATH@,${libdir}/${BPN}," \
        ${S}/helloworld-java.sh.in > ${B}/helloworld-java
}

do_install() {
    install -D -m 0755 -t ${D}${bindir} ${B}/helloworld-java
    install -D -m 0644 -t ${D}${libdir}/${BPN} ${B}/HelloWorld.class
}

FILES:${PN} += "${libdir}/${BPN}"
