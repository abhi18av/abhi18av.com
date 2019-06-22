---
authors: Phil Hofmann
title: a prototype for a function generating macro in Clojure
category: clojure
date-published: 2016-12-10
tags: 
- macro
- metaprogramming
- prototype
uuid: e96d3d98-6312-4486-92c0-79008774e840
---

TL;DR I was looking for a prototype of a Clojure macro that fulfills
three requirements: (1) Generates a function. (2) Takes options as
parameters and provides those to the generated function. (3) And
lastly the generated function takes arguments. Spoiler! This is it...

```
(defmacro defsample [name & args]
  (let [options (apply hash-map args)]
    `(defn ~name [input#]
       (prn (str (:text ~options) " " input# ".")))))

(defsample sample :text "Hello")

(sample "world")
```

Keep on reading for a detailed description. In my quest to come up
with the prototype above I started with the most basic version of a
macro that generates a function.

```
;; working, but boring

(defmacro defsample [name]
  `(defn ~name []
     (prn "Hello World.")))

(defsample sample)

(sample)
```

The key elements of this macro being of course the Syntax Quote
<code>&#96;</code> and the Syntax Unquote `~`.

With a little modification the macro will take options and provide
these to the generated function:

```
;; working, but still somewhat boring

(defmacro defsample [name & args]
  (let [options (apply hash-map args)]
    `(defn ~name []
       (prn (:text ~options)))))

(defsample example :text "Hello world.")

(example)
```

Now, if we also want to pass in arguments to the generated function,
the straight forward attempt will not work:

```
;; defunkt!

(defmacro defsample [name]
  `(defn ~name [input]
     (prn input)))

(defsample sample)

(sample "Hello world.")
```

The reason that this does not work is, that in the example above the
Syntax Quote <code>&#96;</code> will fully qualify all symbols within.
But since qualified symbols cannot be used in the params of a `defn`
this will result in a

```
CompilerException java.lang.RuntimeException: Can't use qualified name as parameter
```

So let's replace the Syntax Quote with a regular Quote `'` then...

```
;; also defunkt!

(defmacro defsample [name]
  '(defn ~name [input]
     (prn input)))

(defsample sample)

(sample "Hello world.")
```

But, as you might have guessed, using a regular Quote instead of a
Syntax Quote doesn't help, because the Syntax Unquote will not work
properly and thus result in a

```
CompilerException java.lang.IllegalArgumentException: First argument to defn must be a symbol
```

So instead of using a given symbol, which will be fully qualified by
the Syntax Quote, we will have to use a generated symbol. This is
anyway a good practice since it will prevent accidental naming
collisions. In addition to the function `gen-sym` which will generate
such a generated symbol, there is also a short hand in form of a
reader macro, that let's us mark a symbol as to be replaced with a
generated symbol. It works by appending a `#` to the symbol.

```
;; working and almost there...

(defmacro defsample [name]
  `(defn ~name [input#]
     (prn input#)))

(defsample sample)

(sample "Hello world.")
```

Now, combining this with the macro that took options and we finally
arrive at the prototype that you saw in the first paragraph.
