PR = "r1"
FILESEXTRAPATHS_prepend := "${THISDIR}/config:"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Add kernel meta data for atlas sockit
SRC_URI_append += " \
			file://atlas-sockit-base;type=kmeta;destsuffix=atlas-sockit-base \
			"
# Add patches
SRC_URI_append += " \
			file://${KBRANCH}/0001-altera_gpio_get-bit-mask-error-in-gpio-altera.c.patch \
			file://${KBRANCH}/0001-Add-devicetree-for-de0-sockit.patch \
			file://${KBRANCH}/0002-Add-FPGA-devicetree-content-for-de0-sockit.patch \
			"
