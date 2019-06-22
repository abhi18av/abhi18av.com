---
authors: Phil Hofmann
title: Contextual helpers with zsh hooks
category: tricks
date-published: 2016-12-09
tags: 
- linux
- console
- cli
- terminal
- shell
- zsh
uuid: 9fa5731a-1d91-4cc9-a45b-b479a76a845d
---

Back in the days when I was developing in Ruby most of my waking
hours [rbenv](http://rbenv.org/) was a real life saver. But looking at
what it does, it initially felt awkward how it "wrapped" the `cd`
command. You could argue that the Ruby community are no strangers to
guerrilla patching. But it felt less awkward when I learned that in
my [zsh](http://zsh.org/) it uses hooks instead of guerrilla patching
to achieve the same goal: React on changing the directory.

In the meantime I tried a couple of things with zsh hooks to optimize
work flows and what not, but as it turns out, everything boiled down to
one hook, and one hook only:

```
autoload -U add-zsh-hook

# source .sourceme files
load-local-conf() {
  # check file exists, is regular file and is readable:
  if [[ -f .sourceme && -r .sourceme ]]; then
  	source .sourceme
  fi
}
add-zsh-hook chpwd load-local-conf
```

The basic idea is: Directories naturally act like contexts, projects are
structured in directories, the different concerns within a project
could be structured by directories, and so on. If you store contextual
functions, aliases, variables, or documentation - whatever you need on
the console in the given context - in `.sourceme` files, the hook above
will automatically source the file once you enter that directory and
you will have everything in there available.

Here is a (rather contrived, but never the less pretty clear) example:
Let's say you have two projects that have images in a images
directory. As new images come in you find yourself repeatedly call
ImageMagick's `mogrify` command to reduce the images to a given size
and each of the projects requires a specific image size. In the
project's `.sourceme` you could define an alias

```
alias resize-images='mogrify --geometry 100x images/*'
```

There is your first contextual helper.

Other examples are:

* Running `docker ps` to make sure the required containers are running
  as I start working on a project.
* Running `cat <<EOF` to display some project specific commands that I
  have trouble to remember.
* Setting environment variables with credentials.
