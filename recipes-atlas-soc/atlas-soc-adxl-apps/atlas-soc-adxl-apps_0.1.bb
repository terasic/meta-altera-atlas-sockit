SUMMARY = "Example applications for Atlas SoC accelerometer"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

PR = "r5"

SRC_URI = "\
	file://adxl_calibrate.sh \
	file://build_pov_demo.sh \
	file://build_tap_detect.sh \
	file://build_watch_adxl.sh \
	file://COPYING \
	file://pov_demo.c \
	file://README_SANDBOX.txt \
	file://README_SRC.txt \
	file://tap_detect.c \
	file://watch_adxl.c \
	file://find_adxl_dt.src \
"

ADXL_BIN_FILES  = "pov_demo"

ADXL_SRC_FILES  = "build_pov_demo.sh"
ADXL_SRC_FILES += "COPYING"
ADXL_SRC_FILES += "pov_demo.c"
ADXL_SRC_FILES += "README_SRC.txt"

ADXL_SANDBOX_FILES  = "adxl_calibrate.sh"
ADXL_SANDBOX_FILES += "build_tap_detect.sh"
ADXL_SANDBOX_FILES += "build_watch_adxl.sh"
ADXL_SANDBOX_FILES += "COPYING"
ADXL_SANDBOX_FILES += "README_SANDBOX.txt"
ADXL_SANDBOX_FILES += "tap_detect.c"
ADXL_SANDBOX_FILES += "watch_adxl.c"
ADXL_SANDBOX_FILES += "find_adxl_dt.src"

S = "${WORKDIR}"

do_configure () {

	rm -f pov_demo
}
do_compile () {

	${CC} \
		-march=armv7-a \
		-mfloat-abi=hard \
		-mfpu=vfp3 \
		-mthumb-interwork \
		-mthumb \
		-O2 \
		-g \
		-feliminate-unused-debug-types  \
		-std=gnu99 \
		-W \
		-Wall \
		-Werror \
		-Wc++-compat \
		-Wwrite-strings \
		-Wstrict-prototypes \
		-pedantic \
		-o "pov_demo" \
		"pov_demo.c"
}

do_install () {
	
	cd ${S}

	install -d ${D}/examples/adxl/bin
	cp -a ${ADXL_BIN_FILES} ${D}/examples/adxl/bin

	install -d ${D}/examples/adxl/src
	cp -a ${ADXL_SRC_FILES} ${D}/examples/adxl/src

	install -d ${D}/examples/adxl/sandbox
	cp -a ${ADXL_SANDBOX_FILES} ${D}/examples/adxl/sandbox

	rm -f adxl_src.tgz
	tar czf adxl_src.tgz -C ${D}/examples/adxl/src ${ADXL_SRC_FILES}
	cp -a adxl_src.tgz ${D}/examples/adxl

	rm -f adxl_sandbox.tgz
	tar czf adxl_sandbox.tgz -C ${D}/examples/adxl/sandbox ${ADXL_SANDBOX_FILES}
	cp -a adxl_sandbox.tgz ${D}/examples/adxl
}

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
PACKAGES = "${PN}"
FILES_${PN} += "examples/adxl/"

