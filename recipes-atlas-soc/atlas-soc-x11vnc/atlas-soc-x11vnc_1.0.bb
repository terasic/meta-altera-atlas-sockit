SUMMARY = "Atlas SoC X11VNC startus"
DESCRIPTION = "Atlas SoC scripts to start x11vnc"
AUTHOR = "Dalon Westergreen <dwesterg@gmail.com>"
SECTION = "atlas-soc"

PR = "r9"

inherit systemd

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://atlassoc-x11vnc-init.service \
           file://atlassoc-x11vnc-init.sh \
          "

do_install() {
	install -d ${D}${base_libdir}/systemd/system
	install -m 0644 ${WORKDIR}/*.service ${D}${base_libdir}/systemd/system

	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/*.sh ${D}${bindir}

}

FILES_${PN} = "${base_libdir}/systemd/system/atlassoc-x11vnc-init.service \
               ${sysconfdir}/systemd/ \
               ${bindir}/atlassoc-x11vnc-init.sh \
              "

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "atlassoc-x11vnc-init.service"
