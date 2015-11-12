require recipes-images/angstrom/console-image.bb

DEPENDS += "bash perl gcc i2c-tools \
"

IMAGE_INSTALL += " \
	kernel-modules \
	kernel-dev \
	linux-firmware \
	usbutils \
	libusbg \
	iw \
        systemd-analyze \
        vim vim-vimrc \
        procps \
        screen minicom \
        cronie-systemd ntpdate \
        tar \
        packagegroup-sdk-target \
	gcc \
        gdb gdbserver \
        iproute2 \
	lighttpd \
        lighttpd-module-cgi \
	bash perl gcc i2c-tools ethtool \
	iperf net-tools nfs-utils-client \
	portmap tcpdump ltp  \
	atlas-soc-lighttpd-conf \
	atlas-soc-101 \
	atlas-soc-usb-gadget \
	atlas-soc-fftdriver-mod \
	atlas-soc-fftsw-apps \
	atlas-soc-fftsw-apps-init \
	atlas-soc-fftsw-apps-src \
	atlas-soc-gpio-apps \
	atlas-soc-adxl-apps \
	gnuplot \
	cmake \
	gator \
	gator-mod \
"
export IMAGE_BASENAME = "atlas-soc-console-image"

#overload timestamp function in image.bbclass

rootfs_update_timestamp () {
        date -u +%4Y%2m%2d%2H%2M -d "+1 day">${IMAGE_ROOTFS}/etc/timestamp
}

EXPORT_FUNCTIONS rootfs_update_timestamp
