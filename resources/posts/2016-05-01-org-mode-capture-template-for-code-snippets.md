---
title: "Orgmode capture template for code snippets"
authors: "Alain M. Lafon"
category: "orgmode"
date-published: 2016-05-01
tags: 
- elisp
- emacs
- programming
uuid: 3eeda4a5-a749-417a-b07c-e7ded2407b73
---

Update: This is a re-post of an older blog post of mine. Originally it
was posted on my
[personal blog](http://blog.dispatched.ch/2016/05/01/org-mode-capture-template-for-code-snippets/).
I'm deprecating my personal blog in favor of this 200OK blog.

<script id="asciicast-bwri6pffkusss125a654tomjs" src="https://asciinema.org/a/bwri6pffkusss125a654tomjs.js" async="" type="text/javascript"></script>

You can use templates for different types of capture items, and for
different target locations.

The following code sets up three capture templates – for todos, media
urls and code snippets (lines 4-7).

`%?` sets the exit point for the template, `%^g` prompts for a tag,
`%^{language}` prompts for the language of the snippet and the remainder
is boilerplate to create an org-mode entry (*) and an org-mode snippet
(`#+BEGIN_SRC\n\n#+END_SRC`).

http://orgmode.org/manual/Template-expansion.html#Template-expansion

```elisp
(setq org-capture-templates
       '(("t" "Todo" entry (file+headline (concat org-directory "inbox.org") "Tasks")
          "* TODO %?\n  %U\n  %i\n  %a")
        ("s" "Code Snippet" entry
         (file (concat org-directory "snippets.org"))
         ;; Prompt for tag and language
         "* %?\t%^g\n#+BEGIN_SRC %^{language}\n\n#+END_SRC")
         ("m" "Media" entry
          (file+datetree (concat org-directory "media.org"))
          "* %?\nURL: \nEntered on %U\n")))
```


