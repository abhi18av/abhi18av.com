---
title: Create Orgmode tasks from Pivotal Tracker issues
authors: Alain M. Lafon
category: orgmode
date-published: 2018-07-15
tags: 
- emacs
- tooling
- productivity
- tracker2pivotal
uuid: 2d8cd5c0-54f1-45c2-b4c4-853f302b8419
description: Fetches issues from Pivotal and creates an Orgmode file for it

---

I personally work a lot with Orgmode, but collaborate on bigger
projects with other people through proprietary tools. Some of them are
very good for collaboration like Pivotal Tracker. So it totally makes
sense to use them in heterogeneous teams! In fact, very often it is me
introducing them into the team.

However, for my workflow (which is based on GTD) it is important to
know that I have all my pending tasks visible in one place. In fact,
that's one of the great features of Orgmode - I have my meeting
minutes, tasks with links to resources like mails, time tracking, etc,
all in one place. Having some tasks scattered in different tools
is dangerous, because:

- It's easy to forget tasks in some tracker
- It's not easy to have an overview of everything that's pending and
  quickly create a ranking of importance
- It creates mental overhead

For this reason, I import tasks from proprietary tools into my local
Orgmode agenda. I don't do anything fancy with it like two-way sync
as this complicates matters a lot. Those external tools are great at
what they do (collaboration [potentially in real time], exchanging
assets, etc) and there is little sense to clone that functionality in
Emacs. There's more important things to do, at least^^

My flow is:

- I have a cronjob running every 15 minutes retrieving tasks from
  external sources
- It creates a file readable in my local agenda
- When this file has changed since the last run (checked with =git=),
  I'll get a desktop notification (through =notify-send=).
  
For importing tasks from Pivotal Tracker, I just open sourced my small
script which you can find here: https://gitlab.com/200ok/tracker2org

It's nothing fancy, at all, but it might save you the time to write it
yourself. Or you might just take the thought away that it might make
sense to have a local copy of all the (potentially distributed) tasks.
