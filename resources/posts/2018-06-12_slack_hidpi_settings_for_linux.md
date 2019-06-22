---
title: Slack HiDPI settings for Linux
authors: Alain M. Lafon
category: debian
date-published: 2018-06-12
tags: 
- slack
- electron
- hidpi
- spotify
uuid: 18836293-284d-4d49-8557-a641a9360089
description: >-
  How to configure Electron apps like Slack and Spotify under Linux to
  look good on HiDPI screens.
---

If you recently upgraded the Slack Desktop App on Debian and now are
wondering what happened to your automatic HiDPI recognition: Don't
worry any further. It's a little peculiar, since this regression
doesn't seem to be well documented on the net. However, the solution
is easy. Slack on the Desktop is an Electron app - therefore it
generally supports the same flags that other Electon Apps (like
Spotify) support and Electron
[has a flag since 2014](https://github.com/electron/electron/issues/615)
`--force-device-scale-factor`. On my Macbook Pro, I'm using a
scale-factor of 1.5.

Here's a little convenience wrapper around the `slack` binary to
always start Slack with this setting. The same can be done for other
Electron apps, as well.

`/usr/local/bin/slack`
```
#!/bin/sh

/usr/bin/slack --force-device-scale-factor=1.5
```

