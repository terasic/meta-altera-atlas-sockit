SUMMARY = "DS-5 Gator Module"
DESCRIPTION = "Target-side kernel module for ARM Streamline Performance Analyzer."

LICENSE = "GPL-2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

#SRCREV = "f0ddf6b40e42579f2275974620399fb3406d0c18"
#PV = "5.21.1+git${SRCPV}"

SRCREV = "7ca6004c0b05138c49b9b21e0045487f55a60ab6"
PV = "5.22+git${SRCPV}"

SRC_URI = "git://git.linaro.org/arm/ds5/gator.git;protocol=http;branch=linaro \
	   file://Makefile"

inherit module

S = "${WORKDIR}/git/driver"

do_configure_append() {
	cp ${WORKDIR}/Makefile ${S}
}
