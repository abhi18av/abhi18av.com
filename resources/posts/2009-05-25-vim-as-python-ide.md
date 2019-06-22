---
title: "VIM as Python IDE"
authors: "Alain M. Lafon"
category: "vim"
date-published: 2009-05-24
tags: 
- coding
- ctags
- exuberant_ctags
- minibuf
- omni_completion
- pep_8
- programming
- python
- python_ide
- taglist
- tasklist
- tutorial
- vi
- vimpdb
- walkthrough
uuid: dbae6bba-fd00-4cbe-94e7-e8df800489c1
---

Update: This is a re-post of an older blog post of mine. Originally it
was posted on my
[personal blog](http://blog.dispatched.ch/2009/05/24/vim-as-python-ide/)
where it has >130 comments and ~ 250'000 views over the last years.
I'm deprecating my personal blog in favor of this 200OK blog.

Finding the perfect IDE for Python isn’t an easy feat. There are a
great many to chose from, but even though some of them offer really
nifty features, I can’t help myself but feel attracted to VIM anyway.
I feel that no IDE accomplishes the task of giving the comfort of
complete power over the code – something is always missing out. This
is why I always come back to using IDLE and VIM. Those two seem to be
best companions when doing some quick and agile hacking – but when it
comes to managing bigger and longer term projects, this combo needs
some tweaking. But when it’s done, VIM will be a powerful IDE for
Python – including code completion(with pydoc display), graphical
debugging, task-management and a project view.

This is where we are going:

![VIM as Python IDE](/img/vim-as-python-ide/vim-as-python-ide.png)

So, these are my thoughts on a VIM setup for coding (Python).

Modern GUI VIM implementations like GVIM or MacVIM give the user the
opportunity to organize their open files in tabs. This might look
convenient, but to me it is rather bad practice, because a second tab
will not be in the in the same buffer scope as the first one which
takes away from future interaction options between the two. Using
MiniBufExplorer, however, gives the user tabs(not only in the GUI, but
also in command line) and leaves the classic buffer interaction
intact.


![VIM MiniBufExplorer](/img/vim-as-python-ide/minibuf.png)

Being able to neatly work on multiple files, the user still misses the
potential his favourite IDE gives him in visualizing classes,
functions and variables. Luckily there are quite a few plugins around
to accomplish this task just as well. My favourite one would be
TagList. TagList uses Exuberant Ctags for actually generating the
tags(note: it really relies on this specific version of ctags –
preinstalled implementations on UNIX systems won’t work).

![VIM Exuberant Ctags TagList](/img/vim-as-python-ide/taglist.png)

A lot of coders have the habit of using TODO or FIXME statements in
their code. Other IDEs often rely on having good third party project
management software, but not VIM. There are great plugins like
Tasklist reminding the programmer of those lines of code. Tasklist
even implements custom lists – to me that’s an incredible productivity
gain.

![VIM Tasklist](/img/vim-as-python-ide/tasklist.png)

In these times, the programmer knows his or her programming language
more or less by interactively finding out what it can do. Therefore
code completion(sometimes also called IntelliSense*ugh*) is a major
feature. I have heard many people saying that this is where VIM fails
– but luckily they are plain wrong(; In V7, VIM introduced omni
completion – given it is configured to recognize Python (if not, this
feature is only a plugin away) Ctrl+x Ctrl+o opens a drop down dialog
like any other IDE – even the whole Pydoc gets to be displayed in a
split window.

![VIM Omnicompletion](/img/vim-as-python-ide/omnicompletion.png)

Probably the most wanted feature(besides code completion) is debugging
graphically. VimPDB is a plugin that lets you do just that(. I
acknowledge it is no complete substitution for a full fledged
graphical debugger, but I honour the thought that having to rely on a
debugger (often), is a hint of bad design.

![VIM Debugger VimPDB](/img/vim-as-python-ide/vimpdb.png)

From the eye-candy to the implementation. Don’t worry, it’s no sorcery.

First of all, make sure you have VIM version >=7.x installed, compiled
with Python support. To check for the second, enter :python print
“hello, world” into VIM. If you see an error message like “E319:
Sorry, the command is not available in this version”, then it’s time
to get a new one. If you’re on a Mac, just install MacVIM(there’s also
a binary for the console in /Applications/MacVim.app/Contents/MacOS/).
If you’re on Windows, GVIM will suffice(for versions != 2.4 search for
the right plugin). If you’re on any other machine, you will probably
know how to compile your very own VIM with Python support.

Second, check if you have a plugin directory. In Unix it would
typically be located in $HOME/.vim/plugin, in Windows in the Program
Files directory. If it doesn’t exist, create it.

In this Blog Post, I'll show you how to manually install these
plugins. Of course, there is other options like using the wonderful
Pathogen of Tim Pope, or using the VIM8 plugin methodology.

Now, let’s start with the MiniBufExplorer.
[Get](https://github.com/fholgado/minibufexpl.vim) it and copy it into
your plugin directory. To start it automatically when needed and be
able to use it with keyboard and mouse commands, append these lines in
your vimrc configuration:

```vimrc
let g:miniBufExplMapWindowNavVim = 1
let g:miniBufExplMapWindowNavArrows = 1
let g:miniBufExplMapCTabSwitchBufs = 1
let g:miniBufExplModSelTarget = 1
```

For a project view, get
[TagList](https://github.com/vim-scripts/taglist.vim) and
[Exuberant Ctags](http://ctags.sourceforge.net/). To install Ctags,
unpack it, go into the directory and do a compile/install via:

```sh
./configure && sudo make install
```

Ctags will then be installed in `/usr/local/bin`. When using a Windows
machine, I recommend [ Cygwin ](http://cygwin.com/) with GCC and Make;
it’ll work just fine. If you don’t want to tamper with your original
ctags installation, you can propagate the location to VIM by appending
the following line to vimrc:

```vimrc
let $Tlist_Ctags_Cmd='/usr/local/bin/ctags'
```

To install TagList, just drop it into VIMs plugin directory. You will
now be able to use the project view by typing the command
`:TlistToggle`.

[ Tasklist ](http://www.vim.org/scripts/script.php?script_id=2607) is
a simple plugin, too. Copying it into the plugin directory will
suffice. I like to have shortcuts and have added

```vimrc
map T :TaskList<CR>
map P :TlistToggle<CR>
```

to vimrc. Pressing `T` will then open the TaskList if there are any
tasks to process. `q` quits the TaskList again.

[ VimPDB ](https://github.com/gotcha/vimpdb) is a plugin, as well.
Install as before and see the readme for documentation.

To enable code(omni) completion, add this line to your vimrc:

```vimrc
autocmd FileType python set omnifunc=pythoncomplete#Complete
```

If it doesn’t work then, you’ll need this
[ plugin ](http://www.vim.org/scripts/script.php?script_id=1542).

My last two recommondations are setting these lines to comply to
[ PEP 8 ](https://www.python.org/dev/peps/pep-0008/)(Pythons’ style
guide) and to have decent eye candy:

```vimrc
set expandtab
set textwidth=79
set tabstop=8
set softtabstop=4
set shiftwidth=4
set autoindent
:syntax on
```

There are certainly a lot more flags to help productivity, but those
will probably be more user specific.

Have fun coding Python while not being bound to a specific IDE, but
having all the benefits of VIM bundled with a few helping hands.
Enjoy, everyone.
