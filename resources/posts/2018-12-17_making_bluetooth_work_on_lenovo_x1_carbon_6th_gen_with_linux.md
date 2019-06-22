---
title: Making Bluetooth work on Lenovo X1 Carbon 6th gen with Linux
authors: Alain M. Lafon
category: Debian
date-published: 2018-12-17
tags: 
- linux
- bluetooth
uuid: 3f14aceb-84de-415d-8fc7-f9ef44415e76
description: >-
  If your bluetooth adapter doesn't work for your brand new Lenovo X1
  Carbon 6th Gen under Linux, read this article.
---

The Lenovo X1 Carbon 6th gen is quite a nice laptop overall. It used
to have some quirks under Linux, with regards to ACPI sleep, CPU
frequency and such. They have all been taken care of by BIOS upgrades,
already, so just upgrade if you any issues there.

There was one thing that I never got properly to work: Bluetooth. The
new 6th gen uses a different chipset than the older models which I saw
working just fine on my colleagues machines. It turns out, that the
new chipset also did work fine under old Kernels (I tested 4.9) while
on newer ones (I tested 4.16 and 4.18) it's just behaving weirdly.
Sometimes it might find devices, sometimes it might pair, sometimes it
might work for a couple of seconds, but mostly it will not work.

After some time researching the issue, because I didn't want to run a
2 year old kernel if I didn't have to, I found this discussion on
Launchpad: https://bugs.launchpad.net/ubuntu/+source/linux-firmware/+bug/1729389

Turns out, the Intel developers know about the problem and it's
already fixed in some downstreams of the kernel. If it isn't working
for your Distro of choice, you can just change one setting in the
kernel module. Add this one line to your
`/etc/modprobe.d/iwlwifi.conf`:

```
options iwlwifi bt_coex_active=0
```

Create the file if it isn't already there (it wasn't for me on Debian
Testing). Reboot and check if the `bt_coex` option is disabled: 

```
cat /sys/module/iwlwifi/parameters/bt_coex_active
N
```

If you're curious what the flag is about, you can read a good
explanation on [Superuser.com](https://superuser.com/questions/924559/wifi-connection-troubles-solved-why-does-my-fix-work#924560).

You're good to go now. Enjoy all the BT goodness of Audio, input
devices and so forth.
