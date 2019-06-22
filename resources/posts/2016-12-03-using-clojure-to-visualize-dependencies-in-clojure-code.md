---
title: using clojure to visualize dependencies in clojure code
authors: Phil Hofmann
category: tooling
date-published: 2016-12-02
tags: 
- clojure
- graph
- dependencies
- visualization
uuid: 3f14de9f-273b-4e44-86cd-504b88225b8b
---

TL;DR Use [codegraph](https://gitlab.com/200ok/codegraph) to visualize
dependencies within a Clojure file. Codegraph applied to itself will
for example generate this graph:

![example codegraph graph](/img/codegraph.png)

Here is how it works.

The other day I wished I had a visualization of the dependencies in a
piece of ClojureScript code that over the course of the year has
gotten a bit unwieldy. I did some thinking and some codeing and it
turns out it's quite easy. Here are some of the highlights in code &
images, but mostly code.

Reading a string and have it evaluates in Clojure is done with
`read-string`. Reading a string and have it **not** evaluated is as
easy as wrapping the string in an additional set of square brackets.

```
(read-string (str "[" (slurp path) "]")))
```

This returns the content of the file given by `path` as a Clojure data
structure. In less homoiconic languages you would probably call this
an Abstract Syntax Tree. Homoiconicity for the win!

The data structure is a tree that we want to walk to find dependencies
in the code. Here `clojure.walk/prewalk` comes in handy. We'll pass it
a function which will be called for every node in the tree. I went
with a multimethod since I initially expected that different typo of
nodes would require different action, but it turns out the only thing
we're actually interested in are symbols.

If the symbol is member of a given set, namely `def`, `defn`, `defn-`,
`defmulti`, and `defmethod`, we will pay special attention to the next
symbol, because it will be the dependant of the upcoming dependencies
that we will record. Every subsequent occurrence of a symbol will then
constitute a dependency.

From there the only thing left to do is to limit the dependencies to
those where the _dependee_ is also the dependant of another
dependency; and render the remainder in dot syntax to be visualized by
graphviz.

Check out the code for more details.
