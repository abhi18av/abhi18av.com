---
title: "Use Emacs for mass renaming of files (instead of vidir)"
authors: "Alain M. Lafon"
category: "Emacs"
date-published: 2017-09-11
tags: 
- shell
- linux
- emacs
- unix
uuid: c8a75550-6534-42cf-bb3b-d6fcc6319d72

---

You might be familiar with the great
[vidir](https://linux.die.net/man/1/vidir) utility which allows
editing of contents of a directory in a text editor. This is a great
concept, because editors are great at text manipulation - which in
turn yields more efficiency for mass renaming/moving/deletion than
typing commands in a shell or using a GUI. vidir is a great tool,
there's no argument to be had - it's even completely decoupled from
the actual `$EDITOR` used. If you're using Debian, it's contained in
the [moretools](https://packages.debian.org/sid/utils/moreutils)
package (which has other helpful utilities).

However, if you're using Emacs as your editor, there's no need for
vidir. In fact, there's some extra work involved to open a shell,
change to the directory in which you're already working and then use
vidir. Besides, Emacs comes with a built-in mode that already has
the same functionality. The well known
[dired-mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Dired.html#Dired),
has a lesser known option to actually edit the `dired` buffer. This mode
can be toggled with `M-x dired-toggle-read-only` (or `C-x C-q`). Then
you make your edits to the files (rename, move, delete) and commit
them using the familiar `C-c C-c`.

[Here's the official documentation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Wdired.html)
on editing `dired` buffers and here's a short video demonstration of
me using writable `dired` buffers.

[![asciicast](https://asciinema.org/a/vYb4bTBxDFixc1lG3hpAZMGgi.png)](https://asciinema.org/a/vYb4bTBxDFixc1lG3hpAZMGgi)
