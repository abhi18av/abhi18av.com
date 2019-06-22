---
title: Disable "flat-volume" for PulseAudio
authors: Alain M. Lafon
category: Debian
date-published: 2019-05-20
tags: 
  - linux
uuid: ad5b526c-a788-4802-8f31-c52e1ea4aa1f
description: >-
    Disable the option 'flat-volume' which enables application volume
    changes to change the system volume. The rationale that this
    beavior is dangerous when wearing headphones.

---

If you're a PulseAudio user on Linux, you might have seen this newish
feature: When you change the volume in an application, it changes the
system volume at the same rate.

This feature is called "flat volume" and seems to be targeted to users
who do not understand the concept of a mixer. For example someone
might turn up the volume of an application (i.e. Spotify or Youtube)
all the way up while the system volume is very low or even at zero and
wonder why "it" isn't working.

On a first glance, this might seem reasonable. However, speaking from
personal experience, it's actually physically dangerous. Some
applications take pro-active control over the volume (for example
[Zoom](http://zoom.us/)). If you're wearing headphones and some
application forces the volume to 100%, you might be in for some
ringing ears or even permanently damaged hearing. Another reason is
that you might actually want to run multiple applications on
difference volume settings, because not all sound is mixed at the same
levels - so "100%" for one source might be a lot fewer decibel than
"50%" for a different source.

For me, on Debian, the "flat volume" feature was enabled by default
and has hurt me a couple of times. Fortunately it's very easy to turn
off. You can set all kinds of flags in `~/.pulse/daemon.conf` for
PulseAudio to change it's behavior.

```
flat-volumes=no
```


Then restart PulseAudio with `pulseaudio -k`.

You're good to continue hacking - with earphones on!^^
