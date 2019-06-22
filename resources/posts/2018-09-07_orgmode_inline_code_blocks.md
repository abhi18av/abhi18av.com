---
title: Orgmode inline code blocks
authors: Alain M. Lafon
category: orgmode
date-published: 2018-09-07
tags: 
- emacs
uuid: 41ee0bdc-510a-4bb2-ac9d-efbacd7ef0a4
description: >-
  Working with Orgmode inline code blocks
---

Working with [code
blocks](https://orgmode.org/manual/Structure-of-code-blocks.html#Structure-of-code-blocks)
is one of the great features of Orgmode and is therefore rather well
known.

They canonically look like this:

```
#+NAME: <name>
#+BEGIN_SRC <language> <switches> <header arguments>
  <body>
#+END_SRC
```

This works great when you want to add either a bigger snippet for an
exported document or you want to do actual calculations with data from
the document.

For the latter case, if you don't need a whole lot of room for your
code - for example if you're doing an easy calculation like the VAT on
an invoice, it would be great if you had the ability to add inline
code blocks. And of course, Orgmode provides!

Anywhere in an Orgmode document, inline code snippets can be embedded
like this: `src_elisp{( + 1 2)}`

When you hit `C-c C-c` while point is on this code, it will execute
and append the result. When exporting, the code block is not visible
while the result is.
