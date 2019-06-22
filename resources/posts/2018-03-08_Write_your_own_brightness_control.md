---
title: Write your own brightness control
authors: Alain M. Lafon
category: Debian
date-published: 2018-03-08
tags: 
- linux
- tooling
uuid: 00d91bdb-3f5a-424e-950f-05ab060491bc
---

Generally speaking, Linux tooling support is pretty great these days.
I'm running Debian Stable on a Macbook Pro (MBP) and couldn't be
happier. Most things just work as expected.

Apart from a few tiny things here and there. One of them is the
ability to adjust brightness. The standard way for many window managers
(WM) is to defer that functionality to `xbacklight`. However, this
doesn't work on the MBP.

```
munen@debzen:~% xbacklight
No outputs have backlight property
```

`xbacklight` looks for some settings in `/sys/class/backlight`, but
it's not configurable under which sub-directory it looks. And for some
reason, the drivers for the MBP puts the settings in different
directory.

While it would be proper to fix this upstream, I decided on working on
this while on the train and being offline - so I took a quicker route
and re-implemented the basic functionality in Ruby. To be able to get
this done in a couple of minutes is a testament to the transparency
and ease of use of a Linux system.

## Steps to build your own brightness control

### Agenda

1. Write a script to adjust brightness control
2. Bind the script to your regular brightness keys on the keyboard
3. Have some visual feedback on brightness

### 1. Write a script

Requirements:

- It should know about the upper bound of your hardware
    - Otherwise you could damage your hardware
- It should accept parameters from the command line
    - Then it can be called from a WM shortcut

`/usr/local/bin/brightness`:
```ruby
#!/usr/bin/env ruby
# coding: utf-8

# Get maximum and current brightness from `/sys` which is provided by
# the kernel
@max_brightness = `cat /sys/class/backlight/gmux_backlight/max_brightness`.to_i
@brightness = `cat /sys/class/backlight/gmux_backlight/brightness`.to_i

def brighter
  @brightness = (@brightness * 1.1).to_i

  # Failsafe
  @brightness = @max_brightness if(@brightness > @max_brightness)

  # Start with a little light
  @brightness = 50 if (@brightness < 50)
end

def darker
  @brightness = (@brightness * 0.9).to_i
  @brightness = 0 if (@brightness < 40)
end

# Note: This needs passwordless sudo privileges
def set_brightness
  `echo #{@brightness} | sudo tee /sys/class/backlight/gmux_backlight/brightness`
  puts @brightness
end

def get_status
  puts "💡 #{(100 * @brightness / 1023.0).to_i}%"
end

case ARGV[0]
when "darker"
  darker
  set_brightness
when "brighter"
  brighter
  set_brightness
when "status"
  get_status
end
```

If `xbacklight` doesn't work for you and the above script doesn't work
as well, then your driver might just use yet another folder to expose
the brightness controls. Check out if there's any folder in
`/sys/class/backlight` - if so, that's probably the one and you can
change the above script accordingly.

This script should reside in `/usr/local/bin` and have executable
permission.

The parameters are:

 - `brighter`: Increases brightness by 10%
 - `darker`: Decreases brightness by 10%
 - `status`: Prints the brightness status in percent

### 2. Bind the script to shortcuts

First, find the keycodes to your brightness keys on the keyboard.
Start the `xev` command in a terminal, hit the brightness keys and
look for the keycodes in the verbose output.

Secondly, register the `brightness` script as a shortcut. For example,
this is the configuration for the i3 window manager:

`~/.i3/config`:
```
bindcode 232 exec "brightness darker"
bindcode 233 exec "brightness brighter"
```

Restart your window manager - or reload it with your config file. For
i3, the latter is bound to `Mod+Shift+R`. Then test the key bindings.

### 3. Visual feedback

Many people use onscreen displays for this. There are many existing
tools that you could employ. I want this information in the status
bar - so I augmented the `i3status` status bar.

To include new information in `i3status`, you have to write a wrapper
script around `i3status`:

`/usr/local/bin/my_i3status.sh`:
```
#!/bin/sh
# shell script to prepend i3status with brightness info

i3status | while :
do
        read line
        echo "`brightness status` | $line" || exit 1
done
```

To use it, change the `status_command` in `.i3/config`.

```
bar {
        status_command my_i3status.sh
}
```

Reload your i3 config once again and enjoy the happy light light
bulb^^

![](/img/2018-03/i3_status.png)
