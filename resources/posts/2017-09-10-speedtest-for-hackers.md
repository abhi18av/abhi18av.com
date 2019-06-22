---
title: "Speedtest for Hackers"
authors: "Alain M. Lafon"
category: "Tooling"
date-published: 2017-09-10
tags: 
- debian
- bash
- linux
- nload
- shell
- unix
uuid: 5a77fd35-30b5-45a2-a2a7-03737598064e

---

If you want to do an ongoing speedtest with graphs for up- and
download, forget about the web-based tools like speedtest.net and
fast.com - those are nice if you are on a mobile phone. These
speedtests as a service will not show you continuous graphs (which
sometime you might want to have if you are tweaking your network).

On such occasions, your *nix shell yields better tooling!

First, fire up a Terminal and start `nload` to view network traffic.
If you are on macOS, you can also use the graphical tool `activity
monitor`.


![nload](/img/speedtest-for-hackers/speedtest_download.png)

Then, in a second Terminal, start a permanent upload or download with
the following commands:


### Upload


`
ssh lafo@dublin.zhaw.ch "cat /dev/urandom" > /dev/null
`

### Download

`
cat /dev/urandom| ssh lafo@dublin.zhaw.ch "cat > /dev/null"
`

Note that depending on your `ssh` configuration, you might need to
disable compression on the client or server side.
