SUMMARY = "Atlas SoC Units to initialize usb gadgets"
DESCRIPTION = "Atlas SoC scripts to start usb gadget for usb mass storage, ethernet, and serial interfaces"
AUTHOR = "Dalon Westergreen <dwesterg@gmail.com>"
SECTION = "atlas-soc"

PR = "r7"

inherit systemd

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PACKAGE_ARCH = "${MACHINE_ARCH}"
SRCREV = "${AUTOREV}"
PV = "1.0${PR}+git${SRCPV}"

SRC_URI = "file://atlassoc-gadget-init.service \
           file://udhcpd.conf \
           file://g-ether-load.sh \
	   git://github.com/dwesterg/atlas-drivers.git \
          "

do_install() {
	install -d ${D}${base_libdir}/systemd/system
	install -m 0644 ${WORKDIR}/*.service ${D}${base_libdir}/systemd/system

	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/*.conf ${D}${sysconfdir}

	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/*.sh ${D}${bindir}

	install -d ${D}${sysconfdir}/systemd/system/getty.target.wants
	( cd ${D}${sysconfdir}/systemd/system/getty.target.wants && ln -s /lib/systemd/system/serial-getty@.service serial-getty@ttyGS0.service )

	install -d ${D}${datadir}/${PN}
	tar -xzvf ${WORKDIR}/git/fat_image.img.tgz -C ${D}${datadir}/${PN}
}

PACKAGES =+ "${PN}-network ${PN}-udhcpd"

ALLOW_EMPTY_${PN} = "1"

FILES_${PN} = "${base_libdir}/systemd/system/atlassoc-gadget-init.service \
               ${sysconfdir}/systemd/ \
               ${datadir}/${PN}/ \
               ${datadir}/${PN}/fat_image.img \
              "

FILES_${PN}-network = "${base_libdir}/systemd/system/network-gadget-init.service \
                       ${bindir}/g-ether-load.sh \
		       ${datadir}/${PN}"

FILES_${PN}-udhcpd = "${sysconfdir}/udhcpd.conf"

RRECOMMENDS_${PN} = "${PN}-network ${PN}-udhcpd"
RREPLACES_${PN} = "${PN}-storage"


NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "atlassoc-gadget-init.service"
