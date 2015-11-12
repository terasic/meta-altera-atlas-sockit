DESCRIPTION = "Atlas SoC web content"
AUTHOR = "Dalon Westergreen <dwesterg@gmail.com>"
SECTION = "atlas-soc"

LICENSE = "MIT & LGPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a8f924340e3ce1ff14c0f9f53b9a6da4"

PR = "r4"
SRCREV = "${AUTOREV}"
PV = "1.0${PR}+git${SRCPV}"

SRC_URI = "git://github.com/dwesterg/atlas-soc-webcontent.git"
S = "${WORKDIR}/git"

inherit allarch

do_install() {
	install -d ${D}${datadir}/${PN}
	cp -a ${S}/* ${D}${datadir}/${PN}
}

FILES_${PN} += "${datadir}/${PN}"
