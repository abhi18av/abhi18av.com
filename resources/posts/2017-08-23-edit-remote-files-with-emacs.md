---
title: "Edit remote files with Emacs"
authors: "Alain M. Lafon"
category: "Emacs"
date-published: 2017-08-23
tags: 
- emacs
- eshell
uuid: 36acb87c-a6fb-4f53-88a3-1e1ae01f6c2d

---

`tramp-mode` is great for editing files remotely, but sometimes having
a shell and Emacs together on the same file can be invaluable.

`eshell` opens up a shell which is like a regular Unix shell, but is
written completely in Elisp, so it's built-in to Emacs and is completely
portable. `eshell` has many interesting properties, but let's focus on
editing files remotely.

When in `eshell`, it is possible to change the working directory into
a remote directory with the same syntax as `tramp-mode`. Yes, no
manual ssh-ing to the remote machine, it's more like a fuse-sshfs
connection, but without fuse and without the manual mounting!

Changing to a remote directory is trivial:

```shell
~ $ cd /ssh:root@your-host.io:
/ssh:root@your-host.io:/root $
```

From there, you can continue to use the shell, but you can also start to
edit any file with `dired`, `C-x C-f` or `find-file`.

Apart from that it feels pretty much like a mount point to which you can
copy files from and to and so forth.

It's magic!
