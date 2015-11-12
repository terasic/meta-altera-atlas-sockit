SUMMARY = "Example application for Atlas SoC FPGA FFT Benchmarking"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://fftsw_apps/COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"


SRCREV = "${AUTOREV}"
PR = "r7"
PV = "1.0${PR}+git${SRCPV}"

SRC_URI = " \
	git://github.com/dwesterg/atlas-soc-fftsw-apps.git;name=fftsw_apps;destsuffix=fftsw_apps \
"
S = "${WORKDIR}"

do_configure () {
	cd ${S}/fftsw_apps
	rm -rf Ne10-master
	bash clean_all.sh
}

do_compile () {
	cd ${S}/fftsw_apps
	bash build_ne10.sh
	bash build_all.sh
}

do_install () {
	
	cd ${S}/fftsw_apps

	install -d ${D}/examples/fft/bin
	bash archive_for_target.sh
	cp -a fft.tgz ${D}/examples/fft/bin/
	cp -a README_TARGET.TXT ${D}/examples/fft/bin/
	cp -a setup_target_fft_env.sh ${D}/examples/fft/bin/

	install -d ${D}/examples/fft/src
	bash archive_src_for_target.sh
	cp -a fft_src.tgz ${D}/examples/fft
	tar xf fft_src.tgz -C ${D}/examples/fft/src

	install -d ${D}/examples/fft/sandbox
	bash archive_src_for_sandbox.sh
	cp -a fft_sandbox.tgz ${D}/examples/fft
	tar xf fft_sandbox.tgz -C ${D}/examples/fft/sandbox
}

FILES_${PN} += "examples/fft/bin/"

PACKAGES =+ "${PN}-src"
RDEPENDS_${PN}-src += "gnuplot"
FILES_${PN}-src += "examples/fft/fft_src.tgz"
FILES_${PN}-src += "examples/fft/fft_sandbox.tgz"
FILES_${PN}-src += "examples/fft/src/"
FILES_${PN}-src += "examples/fft/sandbox/"

