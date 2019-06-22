---
title: Disable MU4E 'HTML over plain text' heuristic
authors: Alain M. Lafon
category: Emacs
date-published: 2018-10-25
tags: 
- mu4e
uuid: 453dc43b-b160-4c77-9ce7-d0c6a3ec746a
description: >-
  If you're a mu4e user and you're seeing way too many HTML emails,
  consider changing a built in preset.
---

By default, the great Mail User Agent
[MU4E](https://github.com/djcb/mu/) prefers Plain Text mails over
HTML. This configuration can be overridden via
`mu4e-view-prefer-html`, but there's probably few of us who would do
that.

However, you might still see a whole lot of HTML emails. And when you
check if they have a plain text version, they might have one! There's
a reason for that. MU4E has a 'HTML over plain text' heuristic with
this official rationale:

<blockquote>

Ratio between the length of the html and the plain text part below
which mu4e will consider the plain text part to be 'This messages
requires html' text bodies. You can neutralize it (always show the
text version) by using `most-positive-fixnum'.

</blockquote>

This heuristic overwrites the default setting (and configuration) that
Plain text should be preferred over HTML!

In my experience, HTML Emails are _WAY_ longer than only 5x the Plain
text (Doodle, Airbnb, Meetup, etc), so this will yield me a lot of
false positives whereas I have never seen a "This message requires
HTML" body. Since I realized that MU4E has this heuristic, I overrode
it just like the doc string told me to and am an even happier MU4E
user.


```
(setq mu4e-view-html-plaintext-ratio-heuristic most-positive-fixnum)
```

NB, if you want to be able to read HTML emails, that's totally and
100% supported within MU4E! You can render them as:

- Text within a regular Emacs buffer
- PDF within a regular Emacs buffer - complete with styles and all
- Open the email in a browser through a shortcut

For these and other goodies in MU4E, please have a look at my
configuration: https://github.com/munen/emacs.d/#mu4e
