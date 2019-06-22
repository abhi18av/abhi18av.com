---
title: X1 Carbon 6th gen about 50% slower on Linux
authors: Alain M. Lafon, Pascal Huber
category: Debian
date-published: 2018-09-26
tags: 
- x1_carbon
- x1_carbon_6th_gen
- linux
uuid: 5eef3578-c2a2-4179-8984-49f4f0cd9961
description: >-
  X1 Carbon 6th gen about 50% slower on Linux
featured-image: /img/x1_without_cpu_fix.png
---

The X1 Carbon is one of the most popular notebooks for the ambitious
Linux user. With the 6th gen, however, there have been a couple of
caveats. Some other notebooks of the same family (like the T480s) are
affected, as well.

If you're not running the latest BIOS version (1.30), we strongly
recommend upgrading. The most pressing issues under Linux that have
been solved since 1.23 are:

- ACPI S3 (Sleep/Suspend) doesn't work (A workaround exists which
  involves patching the ACPI tables manually)
- When using speakers through the audio jack, there was a pretty
  annoying fizzling sound - unless a certain audio volume was used. So
  specifically for business users who do a lot of conference calls,
  this was pretty hard to bear.
  
You can upgrade your BIOS (without Windows) by following these
instructions:

- https://github.com/fiji-flo/x1carbon2018s3
- https://fwupd.org/lvfs/component/1023/all

There's one big caveat left even in the latest BIOS version, though.
**When you're running a CPU intensive task, your machine will be only
about 50% as fast as it could be!**

Let that sink in for a moment.

The reason is that under Linux the CPU frequency will be throttled
very early - when reaching a temperature of 75 or 80C. The standard
setting in Windows is 97C. Therefore, you might be running only on
half the CPU frequency which you could, for many workloads.

Let me show you a couple screenshots:

**Running sysbench on 8 threads without the fix**

![](/img/2018-09/x1_without_cpu_fix.png)

As you can see, it takes my machine 43s to finish the benchmark.

**Running the same sysbench benchmark _with_ the fix**

![](/img/2018-09/x1_with_cpu_fix.png)

Now, the same calculation takes only 18s - less than half the time
before! These are reproducible numbers. We realize that the benchmarks
would otherwise be rather short. It's the same result when the
benchmarks run substantially longer, too.

If you want to install the workaround yourself, here's the fix which
luckily was already provided by Francesco Palmarini:
https://github.com/erpalma/lenovo-throttling-fix

There's one slight adjustment we made, in order to have a longer
battery life: In `/etc/lenovo.conf`, I set the cpu temp threshold for
battery to 80C instead of 85C.
