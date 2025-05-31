require openjdk-8-base.inc

inherit native

libdir_jvm = "${libdir}/jvm/${PN}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install() {
  install -d ${D}${libdir_jvm}
  cp -R --no-dereference --preserve=mode,links -v ${S}/* ${D}${libdir_jvm}

  # symlinking manually since update-alternatives isn't meant for native packages
  install -d ${D}${bindir}
  for i in java javac jar javadoc javap keytool; do
    if [ -f ${D}${libdir_jvm}/bin/$i ]; then
      ln -sf ../lib/jvm/${PN}/bin/$i ${D}${bindir}/$i
    fi
  done
}

FILES:${PN} = "${libdir_jvm}"
