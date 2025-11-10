require openjdk-21-base.inc

DEPENDS += "patchelf-native"

# Prevent the packaging task from stripping out
# debugging symbols, since there are none.
INSANE_SKIP:${PN}:append = " ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# Package unversioned libraries
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

# Ignore QA Issue: non -dev/-dbg/nativesdk- package
INSANE_SKIP:${PN}:append = " dev-so"

# Ingore QA Issue: /usr/lib/jvm/openjdk-8-jre/* uses 32-bit api 'time'
INSANE_SKIP:${PN}:append = " 32bit-time"

libdir_jvm = "${libdir}/jvm/${BPN}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install() {
  install -d ${D}${libdir_jvm}
  cp -R --no-dereference --preserve=mode,links -v ${S}/* ${D}${libdir_jvm}

  LDLINUX=$(basename $(ls -1 ${RECIPE_SYSROOT}${base_libdir}/ld-linux* | sort | head -n1))
  if [ -n "$LDLINUX" ]; then
    find ${D}${libdir_jvm} -type f -executable -exec patchelf --set-interpreter ${base_libdir}/$LDLINUX {} \;
  fi
}

FILES:${PN} = "${libdir_jvm}"
