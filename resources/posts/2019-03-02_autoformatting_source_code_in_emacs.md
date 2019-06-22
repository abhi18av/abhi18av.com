---
title: Autoformatting source code in Emacs
authors: Alain M. Lafon
category: Emacs
date-published: 2019-03-02
tags: 
- programming
- javascript
- elisp
- eslint
- prettier
uuid: d6511129-46d6-4f2f-a5bc-e076b78811d6
description: >-
    Autoformatting source code with prettier, eslint and Emacs.
---

Recently at [ClojureD](http://clojured.de/),
[Josef](https://www.erben.io/) introduced me to a tool which quickly
rose to fame (in the JavaScript world):
[prettier](https://prettier.io/). It is an opinionated code formatter
for various languages - this paradigm probably got popular ever since
Go started to ship such a formatter in the core tooling. I knew the
paradigm and prettier itself from blog posts, but didn't really try to
use it so far.

Generally speaking I liked the idea of having my code automatically
formatted in Emacs whilst adhering to configured linters such as
[eslint](https://eslint.org/). However, from the docs it was much too
complicated to use. More importantly, though, the documentation asks
to configure it through files which are usually checked in (like
`package.json` or `.eslintrc.json`). For new or our own projects, this
is fine. However, for customer projects, I don't want to impose new
tools. Additionally, it is a whole lot of repetitive work to set up
such tooling for every single project which kind of defeats it's
ulterior motive. In these days, who doesn't work on a couple dozen
code bases in parallel?(;

Therefore, I did what every self-respecting engineer would do in this
scenario: I wrote a little Elisp wrapper. This wrapper implements an
interactive function `autoformat` which is a thin wrapper around
command-line based code autoformatters which it utilizes through a
strategy pattern. At this moment, the tools `prettier` and
`prettier-eslint-cli` are implemented. With those, autoformatting a
wide variety of languages/formats like JS, CSS,Sass,HTML, JSON and
many more is possible. To add a new language/framework, just add a new
strategy function which yields the a command-line tool that adheres to
this workflow: Reads source code from `stdin`, formats it and passes
it to `stdout`.

You can find the documented code in my Emacs repository:
https://github.com/munen/emacs.d/#auto-formatting

If you like it, feel free to fork the repository, make pull requests
or just give it a star^^

Happy hacking!

![Autoformat demo](/img/2019/03/demo-ok-autoformat.gif)
