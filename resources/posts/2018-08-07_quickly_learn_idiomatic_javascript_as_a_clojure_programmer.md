---
title: Quickly learn idiomatic JavaScript as a Clojure programmer
authors: Alain M. Lafon
category: clojure
date-published: 2018-08-07
tags: 
- clojure
- javascript
- programming
uuid: 34b18432-7f99-4aed-b381-d8d08fcb1012
description: >-
  Learning a new programming language can be hard. However, learning idiomatic
  JavaScript for Clojure devs (or generally devs with solid functional
  programming experience), is not.
---

Learning a new programming language can be hard. However, learning
idiomatic JavaScript is not if you're already a Clojure developer. In
fact, this statement could be extended to say that JavaScript will be
rather easy to pick up for many developers that are knowledgeable in a
functional programming language.

Generally speaking, functional programming can be boiled down to the
idiom that you write your programs through intense usage of a few
versatile data structures and many composable functions. Clojure is
such a language. JavaScript initially was created with
[Scheme](https://www.scheme.com/) and
[Self](http://www.selflanguage.org/) in mind, therefore it supports
both the paradigms of functional programming and object orientation.
Modern JavaScript has lots of syntax on top of a very lean and
arguably beautiful core. However, the additional syntax absolutely
isn't required to write well structured idiomatic JavaScript code. In
fact, it is an ongoing debate on whether or not it generally is a good
idea to blow the language spec up as much as it did in the last years.
The additional syntax can help you where it makes sense, but there's
no need to start learning all that and a functional core take you very
far! Therefore, if you know a LISP, you're already halfway done to
becoming a good JavaScript developer! If you also know a language with
a "standard" class-based hierarchy approach (Ruby, Python, Java, C#,
you name it), you're even closer to writing great JavaScript code!

Clojure and JavaScript share many attributes which you don't have to
re-learn:

- Dynamic Typing
- Employs similar types
- Functional Programming (first class functions, anonymous functions,
  closures)
- Garbage Collection

To quickly transpose your development knowledge, you have to ask the
following questions: 

1. What [data structures](#1-data-structures) should I use?
2. Where do I get my [functional
vocabulary](#2-functional-vocabulary)?
3. How do I [structure my code](#3-code-structure)?
4. [What's different in
JavaScript](#4-what-is-differentspecial-in-javascript)?

## 1 Data Structures

I'll make a stretch and say that Maps and Vectors are the most
important data structures in Clojure (at least to get started).
JavaScript has two data structures which are similar to those. Of
course they are not persisting, so they are not exactly the same.
However the other attributes are very similar.

### `Maps` are `Objects`

https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Objects

### `Vectors` are `Arrays`

https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array

## 2 Functional Vocabulary

- [`map`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/Map), [`reduce`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/Reduce), [`filter`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/Filter) are built into JavaScript

- For a larger vocabulary, import the [Lodash library](https://lodash.com/)


## 3 Code structure

- Small applications can easily be built in one file by just writing
  functions, same as in Clojure:
  https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions
- If in Clojure, you'd like to introduce a new Namespace, go for a
  `module` in JavaScript: https://hacks.mozilla.org/2018/03/es-modules-a-cartoon-deep-dive/
- If you need further abstraction, you can introduce a `class`: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes
- For packaging your application (making it available) and importing
  other libraries, use [NPM](https://www.npmjs.com/)


## 4 What is different/special in JavaScript?

### Concurrency and Event Orientation: 

Many things make JavaScript special, but one of the most interesting
properties is that doing I/O is _never_ blocking! JavaScript is
heavily event oriented. Handling I/O is performed via events and
callbacks/promises.

Example:

```javascript
setTimeout(function() {
  console.log("This text appers in 2s as a log message.")
}, 2000)
```

Read more about the concurrency model and event orientation here: https://developer.mozilla.org/en-US/docs/Web/JavaScript/EventLoop

### Biggest OSS community

There are currently over 700'000 packages on [NPM](https://www.npmjs.com/)
with about 40% year over year growth.

Of course, quantity and quality are not the same. There's lot's of
churn and the 'new hotness' sometimes is quickly replaced by 'the
leading edge'. However, a benefit of writing idiomatic functional code
is that your code will persevere!

### Great documentation

Learn how to program in JavaScript with equally good high-level guides
and in-depth tutorials: https://developer.mozilla.org/en-US/docs/Web/JavaScript
