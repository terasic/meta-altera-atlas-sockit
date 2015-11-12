SUMMARY = "Example Driver for Atlas SoC FPGA based FFT"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

inherit module

PR = "r0"
PV = "0.1"

SRC_URI = "file://Makefile \
           file://COPYING \
           file://fft_driver.c \
           file://fft_driver.h \
           file://my_altera_msgdma_csr_regs.h \
           file://my_altera_msgdma_descriptor_regs.h \
          "

S = "${WORKDIR}"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
