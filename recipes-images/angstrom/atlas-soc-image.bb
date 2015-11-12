require atlas-soc-console-image.bb

IMAGE_INSTALL += " \
	angstrom-gdm-xfce-hack \
	packagegroup-xfce-base \
	xterm \
	angstrom-x11vnc-xinit \
	x11vnc \
	xserver-xorg-xvfb \
	xkbcomp \
        packagegroup-core-x11-xserver \
        xserver-nodm-init \
        xserver-common \
        gimp abiword gedit midori epiphany \
        imagemagick \
	atlas-soc-x11vnc \
"

export IMAGE_BASENAME = "atlas-soc-image"

