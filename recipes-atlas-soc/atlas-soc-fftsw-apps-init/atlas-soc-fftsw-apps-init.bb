SUMMARY = "Atlas SoC fftsw initialization"
DESCRIPTION = "Copies fftsw to ram disk"
AUTHOR = "Dalon Westergreen <dwesterg@gmail.com>"
SECTION = "atlas-soc"

PR = "r1"

inherit systemd

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://atlas-soc-fftsw-init.service \
          "

do_install() {
	install -d ${D}${base_libdir}/systemd/system
	install -m 0644 ${WORKDIR}/*.service ${D}${base_libdir}/systemd/system


}

FILES_${PN} = "${base_libdir}/systemd/system/atlassoc-x11vnc-init.service \
               ${sysconfdir}/systemd/ \
              "

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "atlas-soc-fftsw-init.service"
