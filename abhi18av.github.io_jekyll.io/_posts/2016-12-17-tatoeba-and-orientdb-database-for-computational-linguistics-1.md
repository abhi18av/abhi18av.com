---
title: 'Tatoeba and OrientDB, Database for Computational Linguistics - 1 '
layout: post
date: 2016-12-17 20:10
image:
description: Building a Linguist's Lab
tag:
    - Computation
    - IILC
    - Masters
blog: true
jemoji:
author: Abhinav Sharma
---
It's been a couple months since I've started to focus exclusively on languages, not from an application oriented approach but from a research oriented perspective. And then slowly, I've carved out a path that seems the most sensible to me as a whole. Of course there're always many (many!) trade-offs involved in making such decisions - what doesn't?

I've been working exclusively with OrientDB as a database, hoping to build around this database a comfortable environment to carry on my linguistics experiments. I've found that "my language is the best" has been nothing but limiting. Btw, when I say language - I do mean both, the human and the programming ones.

Though, I've never really been a huge fan of "one-human-language" and that has slowly proved itself true, even for programming languages. I was exclusively a Pythonista and only later did I realize that even though LISP is how my mind really loves programming, there's no point sticking to one language. Rather than falling in love with the programming language - fall in love with the whole art of problem solving. Languages come and go!

I haven't had much experience with Databases so far and much like my first programming language C++ ( gasp!), the little exposure I had regarding MySQL database in University curicullum left me nauseated. It's only with Neo4J that I'd start to actually believe that "Hey, databases are cool!" - especially the NoSQL ones. Now that I've wisened up a bit, I realize that it'd be a mistake on my part to try to solve every Database problem with just one kind of Database. Sure, I have my preferences but that's all it is - preferences.

Knowing full well the trade-offs I make regarding the Tech. Stack of my project I'd much rather stick with something I actually enjoy working with. This turned out to be

- OrientDB
- Linguistics
- JVM languages
- Python ( Anaconda flavor)
- True Recursion ( via dotNET's F#)

Eventually, I'd like to have a comfortable *lab* to test out various theories and hypothesis that I come across in Computational Linguistics papers. Though the priority would be to continue working with the University Project i.e. **Language, Space and Mind - Paul Chilton** and try to take the theory outlined to it's logical conclusion. 

**Can a language be visualized?**

**Is there a Geometry in linguistic meaning i.e. semantics?**

I'm working towards something like a  single ruby script that sets up everything like -
a) Downloading the latest csv from the Tatoeba database
b) Set up the pre-requisite JVM, Python and dotNET languages
c) Set up the Database

Once the environment is set-up correctly, Clojure based scripting environment which takes care of the fluid thought like nature of code and exploration, designed in a way that it's easy to *pick the right tool for the algorithmic problem* be it Haskell, Anaconda, F# or Ruby.

```clojure
(analyze sentence-analyzer-function (:sentence-id :lang-tag))

```

For the **analyzer** function, I'd be able to rely on the best tool for the job, be it the True Recursion offered by F# ( from dotNET world) or Laziness and Purity for Mathematical modeling of the domain, massive parallelism or just plain old playing with the data. And, of course, we mustn't forget the Golden Rule of science "Stand on the shoulders of Giants to see farther than anyone" i.e. build on the work already done by people, perhaps in the form of the fantastic libraries.

I do believe that something like a Virtual Machine Image and a Docker would be important as I absolutely wish to make the entire thing well-documented and easily-reproducible. 

P.S. Of course, **all my work would always be open source.**
