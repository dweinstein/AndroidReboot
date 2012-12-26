AndroidReboot
=============

Repository for PoC rebooting of Android from unprivileged apps.

The current PoC just loops through files in /sys/kernel/debug attempting to read
from them. Based on some testing on a hand ful of devices, this alone may cause
a device to reboot.
