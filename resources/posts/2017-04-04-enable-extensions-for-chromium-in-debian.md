---
title: "Enable extensions for Chromium in Debian"
authors: "Alain M. Lafon"
category: "debian"
date-published: 2017-04-04
tags: 
- debian
- chromium
uuid: 35536858-81dd-467f-90e5-4c9f657569ea
---

If you happen to upgrade your Debian Jessie to Stretch (currently
Debian Testing), one of the things you might want to patch is the
default behavior of Chromium having extensions disabled by default.
There's no way to fix that in the UI, it has to be done by setting a
global configuration flag.

There's also a reason why extensions are disabled by default.
Apparently Chromium started downloading binary extensions that don't
show in the extensions list which had access to the Google Voice API
which sounds kinda scary. Well, maybe it is, maybe it isn't.

However, if you decide you can't live without extensions in Chromium,
this is how you fix it:

Set this global config flag in `/etc/environment`:

    export CHROMIUM_FLAGS='--enable-remote-extensions'
    
More background information on why this is the new default:

 * https://bugs.debian.org/cgi-bin/bugreport.cgi?bug=786909
 * https://news.ycombinator.com/item?id=9724409
