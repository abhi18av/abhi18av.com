---
title: X1 Carbon CPU frequency update
authors: Alain M. Lafon
category: Debian
date-published: 2018-09-27
tags: 
- x1_carbon
- x1_carbon_6th_gen
- linux
uuid: ebec9d41-f44f-44bd-a002-09d84c6f2b2e
---

This is an update to our post from yesterday [X1 Carbon 6th gen about 50% slower on Linux](/posts/2018-09-26_X1_carbon_6th_gen_about_50_percent_slower_on_Linux.html).

Based on the [Intel
datasheet](https://ark.intel.com/products/122589/Intel-Core-i7-8550U-Processor-8M-Cache-up-to-4-00-GHz-)
on my CPU (i7-8550U), it seems that with the 'fix' enabled, the CPU
potentially draws *a lot more power for longer times than it is
designed to do* (see the TDP numbers in the sheet). This whole clocking
issue seems to be an issue for people that really know what they're
doing - and I feel I don't have all the information to make a
reasonable decision to overrule the defaults. I have the feeling that
the CPU can do 4GHz, but isn't supposed to do it for longer than a
couple of seconds, because then it would get too hot and draw too much
power.

Since I was content with the speed of the machine before the fix and I
don't want to run the *danger of melting my machine*, I'm personally
going to disable the fix. I know that some people have been running
with the 'fix' present on their machines for months, so I'm not saying
that it's the wrong thing to do. I'm just personally going to tread on
the safe side.

Since the 'fix' ran through an `install.sh` script and wasn't
available as a package, uninstalling has to be done by hand. However,
reading the `install.sh` script reveals all the points that need to be done:

```shell
systemctl stop lenovo_fix.service
rm /etc/systemd/system/lenovo_fix.service
rm /etc/lenovo_fix.conf
reboot
```

The above script leaves the three packages installed through pip and
whatever OS packages you installed still on your system. I don't want
to issue a remove script for those, because you might be using them
for something else and I don't want to break your system.
