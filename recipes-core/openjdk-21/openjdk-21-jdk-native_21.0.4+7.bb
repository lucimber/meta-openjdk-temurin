require openjdk-21.inc
require openjdk-21-jdk.inc
inherit native

do_install() {
  install -d ${D}${libdir_jvm}
  cp -R --no-dereference --preserve=mode,links -v ${S}/* ${D}${libdir_jvm}
}

