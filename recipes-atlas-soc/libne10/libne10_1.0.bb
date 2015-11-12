SUMMARY = "Neon library for Atlas SoC FPGA FFT Benchmarking"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=463ac0a7d64edc2b787c4206635ca2b1"

inherit cmake

SRCREV = "${AUTOREV}"
PR = "r1"
PV = "1.0${PR}+git${SRCPV}"

PACKAGES = "${PN}-staticdev ${PN}"

SRC_URI = " \
	git://github.com/projectNe10/Ne10.git;name=libne10;destsuffix=libne10 \
"
S = "${WORKDIR}/libne10"

do_patch () {
	cd ${S}
	sed -i "s/arm-linux-gnueabihf-/${CC%gcc${CC#*gcc}}/g" GNUlinux_config.cmake
}

EXTRA_OECMAKE += " -DCMAKE_CXX_FLAGS='-mfloat-abi=hard -mfpu=vfp3'"
EXTRA_OECMAKE += " -DCMAKE_C_FLAGS='-mfloat-abi=hard -mfpu=vfp3'"
EXTRA_OECMAKE += " -DCMAKE_TOOLCHAIN_FILE=${S}/GNUlinux_config.cmake"

do_install () {
	install -m 0644  ${B}/modules/*.a ${D}${libdir}
	install -m 0644 ${S}/inc/*.h ${D}${includedir}
}

FILES_${PN}-staticdev += "${libdir}/*.a"
FILES_${PN}-staticdev += "${includedir}/*.h"
FILES_${PN} += "${libdir}/*.a"
FILES_${PN} += "${includedir}/*.h"
