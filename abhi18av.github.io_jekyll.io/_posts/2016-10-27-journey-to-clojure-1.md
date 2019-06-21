---
title: Journey to Clojure - Act 1
layout: post
date: 2016-10-27 16:41
image:
description: Programming and Languages
tag: computation
blog: true
jemoji:
author: Abhinav Sharma
---

This post is sort of a sum-up of my attempts for the last 4-5 months, basically my post-University life - as far as my choice of programming languages is concerned.
Over the last year in University, while I was involved with a StartUp I *combed* through GitHub searching for resources which will be useful to me. I've already been comfortable using Python for a while, I used it a couple major projects. I knew exactly which library I needed to to a particular task ( I love Anaconda ;P ), which language construct I need to express a problem and was really happy with it all i.e. until I met Julia! I've know Julia since it's 0.3.3 version and it's especially the homoiconicity part of the language that gravitated me towards itself but later on I realized it's ability to really play nice with C that it's able to have fully inter-operatibility with other languages - this essentially means that it can play nice with even Java, there already was a nice package for Python called PyCall.jl.  Then there's Rust, which has such a wonderful community around itself. Post-University I started exploring these languages.

All the while knowing perfectly that being a Computer Science graduate, I basically didn't understanding computing the way I really should and, trust me you can search all you want about "learning Computability" you'll search will *always* end up with a few books and a couple languages.

Let's start with the books,

- [Structure and Interpretation of Computer Programs (SICP)](https://mitpress.mit.edu/sicp/full-text/book/book.html)
- [How to Design Programs](http://www.htdp.org/)
- [Introduction to Algorithms](https://mitpress.mit.edu/books/introduction-algorithms) - this is really popular but I still couldn't work out the magic proofs or the complexity analysis at all.
- Foundational books which revolve around the works of [Turing](https://en.wikipedia.org/wiki/Alan_Turing), [Church](https://en.wikipedia.org/wiki/Alonzo_Church) and [Godel](https://en.wikipedia.org/wiki/Kurt_G%C3%B6del).
- [The Art of Computer Programming](http://www-cs-faculty.stanford.edu/~uno/taocp.html) ( which almost everyone has started and already given up on! ) - it's a Hydra and you need persistence and grit to go through that one.

The languages which come up are
- C/C++/Java
- Python
- Haskell
- LISP

Now, the real story begins. For a long time I was really confused regarding the way I'm supposed to approach programming and even though I could use a library, code something around an Idea - all I've ever been is someone who's comfortable writing small scripts. Never, anything more than 200 lines at all. And by some tough luck, I never could separate the algorithmic analysis from the implementation language. In the Uni, C++ is what we become neauseated with and that's where my problem truly lived. The simple way an idea was introduced in the books had two more layers upon it 
- The algorithmic analysis
- The implementation language 

Funny enough, any new attempt often ended up with me mustering enough courage to actually go through the analysis and then to start coding the algorithm in C++ - pff, never worked out! That's not to say that it was the same with everyone, maybe people were more comfortable with C++ or just more proficient abstract thinkers but for me, the *Huge* journey from a basic idea to actual code was more like "Shire to Mordor " than appreciation of programming as a "Tool for Thought". Let alone trying out new things by myself.

Python was such a relief! Blessed is Guido, for saving so many man-hours ;)

The romantic ideas about Computing, a "Tool for Thought" and that desire to really understand how to solve problems works, how computing really works has always been a huge part of my conscience ever since I skimmed SICP and my University projects which always were around Functional Languages and Programming, in fact all this time I've gotten along fine in Python without using Classes at all - it was all Procedural and Functional programming for me through and through. But I was bitten by this bug, to find beauty in programs and computing - I explored Haskell but sadly, I am far from having the ability to work my way through dry manuals and a lot of interesting libraries in Haskell, I feel, don't make the journey of an amateur any easier. Trust me, I'm done with dry texts after College. Btw, if you decide to get into Haskell "Learn you a Haskell for Great Good " is the way to go, insanely awesome;)

The other, time-tested option for a deeper knowledge in Computer Science is that LISP. This is where I felt at home, LISP is for Computer Science what Prime Numbers are for Mathematics - mysterious, simple and beautiful. But here again, I faced a dilemma which Lisp should I go with? SBCL, Scheme, Racket or the modern Clojure? I've tried them all, again at a very script-oriented level but I just couldn't get a hold of the environment or being able to use the language in real life. I overlooked Clojure(!) for a long time since it's association with Java and I couldn't make myself read anymore of that C like syntax or anything even remotely associated with it.

So, for a long time this constant juggling between Python - Julia - Haskell - Rust was such a confusion in my life. Of course, for any everyday task I'd have Python as my goto solution. I've done a little Web-Scraping in it, Scientific Computing ( Data Science), Visualization and stuff but one day I was trying my level best to make this program to scrape the transcript of a TED-talk from the web and in course of trying out various ways of doing it - I started with a purely procedural implementation and ended up with a purely functional implementation, in search of simplicity! Trust me that's the turning point in my story, It won't be an exaggaration to say I felt relief - for the first time I've actually witnessed that Functional Enlightenmnet everyone keeps talking about.

Stay tune, I'll post the code in the next post :)
