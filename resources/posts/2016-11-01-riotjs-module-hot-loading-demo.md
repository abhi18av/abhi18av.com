---
title: "RiotJS Module hot-loading Demo"
authors: "Alain M. Lafon"
category: "riotjs"
tags: 
- javascript
- spa
- module
- webpack
date-published: 2016-11-01
uuid: 9330e642-4fea-4315-9291-15074bf6630e
---

Module Hot Loading enables a super efficient workflow without the pain
of manual reloading of your web application. What do I mean by that?
Well, the usual web developer cycle is:

1. Write code
2. Save code
3. Switch to Browser
4. Hit Reload
5. See what happens
6. Switch back to Editor

Half those steps (3, 4 and 6) are manual and very repetitive if you
want to have an incremental development experience. They can be
automated completely.

There are some frameworks that support Module hot-loading by now.
ClojureScript and Elm probably were the first languages to support
that paradigm, but it's possible in JavaScript as well, by now.

This is a demo showing you how to incrementally build a super simple
RiotJS application using module hot-loading. I never had to go back to
the browser and hit 'reload'. To be able to do this, I used the [RiotJS-Loader](https://github.com/esnunes/riotjs-loader).

For the code of this demo, please see [this repository](https://github.com/munen/riotjs-module-hot-loading-demo).

<iframe width="560" height="315" src="https://www.youtube.com/embed/Y9cGM71izVs" frameborder="0" allowfullscreen></iframe>
