---
title: "Coders Tip: Commit and push at at least at the end of the day"
authors: "Alain M. Lafon"
category: "coders tip"
date-published: 2017-04-02
tags: 
- programming
- version control
- git
uuid: 54aed99c-4003-4a6b-aa5f-c56fbdd7fe2a
---

Many programmers commit infrequently and their pushes are even rarer.
There are multiple reasons for that - some programmers have a strong
background in version control systems like SVN or even CVS (yes, both
are still a thing in 2017) where branching and committing is
expensive. Other programmers mention reasons in the range of "I'm not
finished, yet" and won't commit or push for days or weeks.

If you are using a modern VCS like git or mercurial, then committing
and branching is actually very cheap. With those, it is actually best
practice to commit early and often. If you don't want to commit "until
you are done", then your branch will only ever have one commit,
because branches already are the barrier between different new
features (see <a
href="http://nvie.com/posts/a-successful-git-branching-model/"
target="_blank">A successful Git branching model</a>). Instead, commit
as early as you have finished part of a new feature like a test or an
architecture stub.

This will open up a treasure trove of potential good things for future
you and other programmers:

- You can go back in time and undo/re-do parts of what you have worked
  on.
    - This is incredibly useful - the depth of it will be understood
      when practiced regularly.
- When you open a
  [Pull Request](https://help.github.com/articles/about-pull-requests/)
  on your branch for others to review, they can follow along your
  thought process and will gain a deeper understanding for your
  decisions.
- You will have created a backup of your state of mind that you can
    consult at any time without the complexities of holding new and
    old decisions in your head at the same time.
- Your future self will never have to start from an undocumented state
  of _something_ from your hard drive, but have a semantic last point of
  reference.

Also, do _push_ regularly whenever you have made a commit. This will
also yield great potential:

- It happens quite often that other developer will either depend or
  could improve upon your code. But they can't as long as the code is
  not pushed.
    - Don't worry about pushing features that aren't _done_ yet. As
      long as you haven't opened a Pull Request, it's obvious that you
      are still working on it! However, other programmers can take a
      look at it if need be. This reason is pretty much about them.
      Don't make assumptions on when the other team members could make
      use of your code. That's their job. Yours is already hard enough
      without making future predictions about others(;
- In many projects you also will benefit of
  [Continuous Integration (CI)](https://martinfowler.com/articles/continuousIntegration.html)
  running tests for you on a per commit basis if you regularly push.
  Depending on your project, it is not feasible to run the full test
  suite all the time on your machine. CI will help you with that - if
  you let it.
- By pushing, you move all your changes to another machine. With that,
  you have effectively made a backup! This means you will never lose
  work if you make this a habit. This is actually very important,
  because it is only a question of _when_ and not _if_ your machine
  breaks down in the future.

In conclusion, the coders tip: **Make it a habit to commit and push
whatever you have at the end of the day**. Even if you forgot to do it
during the day, by making it a habit to commit and push at the end of
the day, you will get a lot of the benefits.
