AndroidReboot
=============

Repository for PoC rebooting of Android from unprivileged apps.

The current PoC just loops through files in /sys/kernel/debug attempting to read
from them. Based on some testing on a hand ful of devices, this alone may cause
a device to reboot.

A very simple way to test on your own device, is to run:

shell@android $ [path-to-busybox] find /sys -type f -exec cat {} \;

Assuming you have busybox somewhere on the device
