---
title: Make zsh recognize Projects you are working on
authors: Phil Hofmann
category: tricks
date-published: 2018-04-30
tags: 
- linux
- console
- cli
- terminal
- shell
- zsh
uuid: 1b14fdba-ae8b-4936-af07-049edc6e85ec
description: >-
             A snippet for ZSH to recognize project folders you
             changed to, so that when you create new shells (i.e.
             through opening a terminal) it changes to the last used
             project automatically.

---

TL;DR The following snippet added to you `~/.zshrc` will recognize
project folders you changed to, so that when you create new shells
(i.e. through opening a terminal) it changes to the last used project
automatically. Please find the complete snippet at the end of this
post.

Some projects have a lot of processes. While there are tools for
orchestrating the startup of applications that require multiple
processes, sometimes it just more convenient to open terminals for
each of those processes. But having opened multiple terminals, it
would be cumbersome to have to change to the project's directory on
each of those shells. And more generally, it would be nice to have a
shell setup which is aware of the project I'm working on and thus
could automatically change the directory to the current project as I
spawn new terminals.

Let's make a list of what we need to make that happen:

- a persistent storage
    - to store the location of the current project to
    - and to read the location of the current project from
- a way to distinguish project directories from other directories
- a hook that hooks into the event of changing directories
- a way to automatically change the directory of a new shell
- optionally, a way to revert the last recognition of a project

## Storage

A flat file will do for storage. This file storage will be required
throughout the upcoming code. Let's put it in a variable `WD`(for
"working directory").

```sh
WD=~/.wd
```

We can easily save the current working directory:

```sh
pwd > $WD
```

And read it back:

```sh
CURRENT_PROJECT=`cat $WD`
```

But since we're planning to be able to revert to the last detected
project, we'll actually use it as a stack, and thus instead of
overwriting the file we'll append to it.

```sh
pwd >> $WD
```

And when reading from the stack instead of reading the whole file
we'll just read the last line.

```sh
CURRENT_PROJECT=`tail -1 $WD`
```

So reading and writing to the storage is set, let's move on.

## Project or Nonproject Directory

Distinguishing project from nonproject directories is a tricky one and
might depend on the tools you're using. Since I'm using git in almost
all of my projects, I settle with the presence of a `.git` directory
as an indicator for a project directory.

```sh
if [[ -d .git ]]; then
  # ...
fi
```

If you are using other VCSs, you need to change that, obviously. A
good indicator might also be project settings files that are written
by your editor or IDE or dependency/project automation files (like
`Gemfile` for Ruby, `package.json` for JavaScript or `project.clj` for
Clojure).

## Hooking into `cd`

Hooking into changing directories is fairly easy with zsh as it
provides `chpwd` among its so called
[ "Hook Functions" ](http://zsh.sourceforge.net/Doc/Release/Functions.html).
But it is a good practice to use `add-zsh-hook`, which lets you
register multiple functions to a hook.

```sh
autoload -U add-zsh-hook

add-zsh-hook chpwd recognize-project
```

`recognize-project` is a function that we still need to write as of
yet.

Other shells than zsh provide similar functionality. In some cases
like bash you get away by wrapping the builtin `cd` command in a
function, that call the builtin but also runs you own code.

## Automate `cd`

Automatically changing to the last location stored is as easy as calling

```sh
cd `tail -1 $WD`
```

Adding this to you `~/.zshrc` will run it automatically for each new
shell. Just be aware that as long as `~/.wd` is empty or doesn't exist
this will throw an error.

## In Practice

Putting it together:

```sh
#!/usr/bin/zsh

autoload -U add-zsh-hook

WD=~/.wd

recognize-project() {
  if [[ -d .git ]]; then
    pwd >> $WD
  fi
}

add-zsh-hook chpwd recognize-project

cd `tail -1 $WD`
```

Used in practice, this quickly reveals some weaknesses.

### Undo

Sometimes, while working on project A we just want to have one shell in
project B to look something up, but we quickly release that the
location of project B has been stored when opening the next shell and
we would like to have the means of undoing that. In that case we just
need to remove the last line from the storage (pop the stack), read
the location before that and change to it.

```sh
previous-project() {
  sed -i '$ d' $WD
  cd `tail -1 $WD`
}

alias pp=previous-project
```

I like to give functions expressive names, but I don't want to type
these so I aliased `previous-project` here to `pp`.

### Unique

Another weakness is that your our stack will quickly collect multiple
consecutive equal lines. This is of no much use and in fact renders
the just added undo feature useless. So to get rid of duplicate
consecutive lines in our stack we'll use some `sed` magic:

```sh
sed -i '$!N; /^\(.*\)\n\1$/!P; D' $WD
```

This reads: If it's not the last line read the line and see if it is
equal to the next line, if that is not the case print and in any case
delete it. This will effectively remove duplicate consecutive lines
and this keep our stack usable.

## Finally

Ok, let's put everything together! This gives us the complete snippet:

```sh
#!/usr/bin/zsh

autoload -U add-zsh-hook

WD=~/.wd

recognize-project() {
  if [[ -d .git ]]; then
    pwd >> $WD
    # delete consecutive duplicate lines
    sed -i '$!N; /^\(.*\)\n\1$/!P; D' $WD
  fi
}

add-zsh-hook chpwd recognize-project

previous-project() {
  # delete last line
  sed -i '$ d' $WD
  cd `tail -1 $WD`
}

alias pp=previous-project

cd `tail -1 $WD`
```
