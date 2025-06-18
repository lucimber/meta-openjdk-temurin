# Using the meta-openjdk-temurin Layer in Yocto/OE

This guide explains how to use the `meta-openjdk-temurin` layer in a Yocto/OpenEmbedded build environment to integrate Java support based on Eclipse Temurin. Special focus is put on how to integrate your Java application into the image, depending on your development workflow.

Repository: [https://github.com/lucimber/meta-openjdk-temurin](https://github.com/lucimber/meta-openjdk-temurin)


## Prerequisites

* A working Yocto/OE environment (e.g. poky)
* Basic understanding of BitBake recipes and layers
* Access to the `meta-openjdk-temurin` layer repository


## Choosing the Right Yocto Version

The `meta-openjdk-temurin` layer follows the Yocto release naming scheme for its branches. Always ensure that the layer branch matches your Yocto release:

Example for Yocto Kirkstone:

```bash
git clone -b kirkstone https://github.com/lucimber/meta-openjdk-temurin.git
```


## Available JVM Recipes Overview

The `meta-openjdk-temurin` layer provides recipes named like `openjdk-X-Y`:

| Recipe Name            | Purpose                               |
| ---------------------- | ------------------------------------- |
| `openjdk-X-jre`        | Target Runtime Environment (JRE)      |
| `openjdk-X-jdk`        | Full Development Kit (JDK) for target |
| `openjdk-X-jdk-native` | Native JDK for build host             |

> The JDK may also be installed on target systems when `javac` or compiler-as-a-service use cases are needed.


## Layer Directory Structure Recommendation

```text
meta-myjava/
├── conf/layer.conf
├── recipes-java/myapp/myapp.bb
├── recipes-java/myapp/files/
│   ├── myapp.jar (optional for pre-built)
│   └── myapp.service
└── README.md
```


## Temurin-Specific Build Caveats

* Temurin builds are large; ensure disk space and build time.
* Host build tools (e.g., GCC, autoconf) may require certain versions.
* JDK cross-building is advanced and typically not required.
* Pay attention to native vs. target conflicts.


## Building OpenJDK (Temurin)

You do not need to build OpenJDK manually. The `meta-openjdk-temurin` layer provides all required recipes. Yocto automatically downloads the correct JDK/JRE based on your declared dependencies (`DEPENDS`, `RDEPENDS`) and builds required dependencies.


## Integrating Your Java Application

There are two primary integration approaches:

### Approach 1: Pre-built JARs (Enterprise Workflow) — Recommended and Simpler

**Simpler** — recommended for most scenarios.

Applications are built outside Yocto using standard CI pipelines (e.g., Maven, Gradle, Ant). The generated JAR files are architecture-independent and can be built on any host system.

#### Pre-built JAR Recipe Template

```bitbake
DESCRIPTION = "My Java Application"
LICENSE = "CLOSED"
SRC_URI = "file://myapp.jar"

S = "${WORKDIR}"

inherit systemd

SYSTEMD_SERVICE:${PN} = "myapp.service"

do_install() {
    install -d ${D}${datadir}/myapp
    install -m 0644 ${S}/myapp.jar ${D}${datadir}/myapp/
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/myapp.service ${D}${systemd_unitdir}/system/
}

FILES:${PN} += "${datadir}/myapp ${systemd_unitdir}/system/myapp.service"
RDEPENDS:${PN} += "openjdk-17-jre"
```

#### systemd service file (`myapp.service`)

```ini
[Unit]
Description=My Java Application
After=network.target

[Service]
ExecStart=/usr/bin/java -jar /usr/share/myapp/myapp.jar
Restart=always

[Install]
WantedBy=multi-user.target
```

### Approach 2: Cross-Compiling Java (Open Source Workflow)

**More complex** — use only if full integration into Yocto build is required.

In this approach, the Java build (e.g. Maven, Gradle, Ant, or even plain javac) happens inside Yocto.

#### Build System Integration

* Ensure `openjdk-17-jdk-native` is built.
* Avoid downloading dependencies during build (offline repos recommended).


#### A. Cross-compiling with javac

```bitbake
DESCRIPTION = "My Java App built with javac"
LICENSE = "Apache-2.0"
SRC_URI = "git://github.com/example/myapp.git;branch=main"

DEPENDS = "openjdk-17-jdk-native"
S = "${WORKDIR}/git"

inherit systemd
SYSTEMD_SERVICE:${PN} = "myapp.service"

do_compile() {
    mkdir -p ${S}/out
    javac -d ${S}/out $(find ${S}/src -name "*.java")
}

do_install() {
    install -d ${D}${datadir}/myapp
    cp -r ${S}/out/* ${D}${datadir}/myapp/
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/myapp.service ${D}${systemd_unitdir}/system/
}

FILES:${PN} += "${datadir}/myapp ${systemd_unitdir}/system/myapp.service"
RDEPENDS:${PN} += "openjdk-17-jre"
```


#### B. Cross-compiling with Ant

```bitbake
DESCRIPTION = "My Java App built with Ant"
LICENSE = "Apache-2.0"
SRC_URI = "git://github.com/example/myapp.git;branch=main"

DEPENDS = "openjdk-17-jdk-native ant-native"
S = "${WORKDIR}/git"

inherit systemd
SYSTEMD_SERVICE:${PN} = "myapp.service"

do_compile() {
    cd ${S}
    ant compile
}

do_install() {
    install -d ${D}${datadir}/myapp
    cp -r ${S}/build/* ${D}${datadir}/myapp/
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/myapp.service ${D}${systemd_unitdir}/system/
}

FILES:${PN} += "${datadir}/myapp ${systemd_unitdir}/system/myapp.service"
RDEPENDS:${PN} += "openjdk-17-jre"
```


#### C. Cross-compiling with Maven or Gradle

```bitbake
DESCRIPTION = "My Java App built with Maven"
LICENSE = "Apache-2.0"
SRC_URI = "git://github.com/example/myapp.git;branch=main"

DEPENDS = "openjdk-17-jdk-native maven-native"
S = "${WORKDIR}/git"

inherit systemd
SYSTEMD_SERVICE:${PN} = "myapp.service"

do_compile() {
    cd ${S}
    mvn package -Dmaven.repo.local=${S}/.m2repo -o
}

do_install() {
    install -d ${D}${datadir}/myapp
    cp ${S}/target/myapp.jar ${D}${datadir}/myapp/
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/myapp.service ${D}${systemd_unitdir}/system/
}

FILES:${PN} += "${datadir}/myapp ${systemd_unitdir}/system/myapp.service"
RDEPENDS:${PN} += "openjdk-17-jre"
```

> Gradle integration follows the same pattern; ensure all downloads happen prior or inside Yocto’s workspace.


#### systemd service file (reused)

```ini
[Unit]
Description=My Java Application
After=network.target

[Service]
ExecStart=/usr/bin/java -cp /usr/share/myapp my.main.ClassName
Restart=always

[Install]
WantedBy=multi-user.target
```


## Best Practices for Java in Yocto

* Avoid bundling external dependencies inside fat JARs (use Yocto-native packaging when possible).
* Always pin Git revisions (`SRCREV`) for reproducibility.
* Use dependency caching for Maven/Gradle inside builds.
* Clearly separate native vs target dependencies.
* Fully verify licenses of bundled Java libraries.
* Use `inherit systemd` for proper service integration.


## Common Pitfalls

* Classpath resolution errors.
* Architecture mismatches with non-portable native libraries.
* Excessive memory usage (tune JVM memory limits for embedded targets).
* Missing timezone or locale data.
* Mixing native vs target Java tools accidentally.


## Debugging Tips

* Use `-verbose:class` and `-verbose:gc` JVM flags.
* Avoid stripping debug symbols (`INHIBIT_PACKAGE_STRIP = "1"`).
* Use `strace`, `lsof`, and `journalctl -u myapp.service`.
* Enable JDWP remote debugging via `-agentlib:jdwp`.
* Verify correct `JAVA_HOME` setup.


## Runtime Considerations

* Additional native dependencies must be handled via Yocto.
* Use systemd services for automatic application startup.


## Conclusion

The `meta-openjdk-temurin` layer provides flexible integration paths for Java within Yocto/OE. Whenever possible, pre-built JARs remain the most robust, reproducible, and enterprise-friendly solution. Fully integrated cross-compilation is also supported for advanced or open source use cases.
