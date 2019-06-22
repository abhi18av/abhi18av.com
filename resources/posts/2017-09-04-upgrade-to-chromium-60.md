---
title: "Upgrade to Chromium 60 on Debian"
authors: "Alain M. Lafon"
category: "debian"
date-published: 2017-09-04
tags: 
- debian
- chromium
uuid: 1521b412-93f7-4c85-94ed-0da0dde984bc

---

After upgrading to Chromium 60 on current Debian Testing you might
find yourself with a huge UI if you are on a HiDPI device. The current
Chromium version has a bug (see
[Debian tracker](https://bugs.debian.org/cgi-bin/bugreport.cgi?bug=871542)).
The proposed solution is to run Chromium with a manual scale factor
setting. On a 15" MBP Retina, using `chromium --force-device-scale-factor=1.5`
yields the same UI size that I was used to before the upgrade.

Looking at the Chromium upstream bug tracker, this issue is solved
since end of May. It'll find it's way to Debian, eventually. In the
meantime the workaround works just as well.

The exact package version where this behaviour has been observed is: 60.0.3112.78-1
