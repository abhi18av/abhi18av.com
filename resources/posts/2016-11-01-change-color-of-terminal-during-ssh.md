---
title: Change the color of the terminal during ssh
authors: Phil Hofmann
category: tricks
date-published: 2016-11-01
tags: 
- linux
- console
- cli
- terminal
- shell
- ssh
- dbus
- roxterm
- zsh
uuid: 23207c54-6893-40bf-9bd1-81d498ea24d7
---

I'm using the command line extensively, with plenty of black terminals
side by side in my tiling window manager I was looking for something
that helps distinguish remote shells (via ssh) from my local shells.

Introducing one of my favorite tricks: Changing the background color
of the terminal while running an ssh session.

    function ssh() {
      dbus-send --session /net/sf/roxterm/Options net.sf.roxterm.Options.SetColourScheme string:$ROXTERM_ID string:Tango
      /usr/bin/ssh $@
      dbus-send --session /net/sf/roxterm/Options net.sf.roxterm.Options.SetColourScheme string:$ROXTERM_ID string:Default
    }

In my setup I'm using zsh and roxterm, but I'm sure it'll work for
other tools as well if you adjust it to yours.
