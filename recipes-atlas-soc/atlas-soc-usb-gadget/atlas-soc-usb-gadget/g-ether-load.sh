#!/bin/sh

modprobe g_multi file=/usr/share/atlas-soc-usb-gadget/fat_image.img

sleep 5

/sbin/ifconfig usb0 hw ether 00:07:ed:01:02:03
/sbin/ifconfig usb0 192.168.7.1 netmask 255.255.255.0
/usr/sbin/udhcpd -fS -I 192.168.7.1 /etc/udhcpd.conf
