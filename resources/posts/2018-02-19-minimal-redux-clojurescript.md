---
title: Redux in 30 lines of ClojureScript
authors: Josef Erben
category: clojure
date-published: 2018-02-26
tags: 
- clojure
- redux
- clojurescript
- state_management
uuid: 872f6ea12523e358594e523c5c251ab3
---

 [Redux](https://redux.js.org/introduction/core-concepts) is a popular choice
 when it comes to dealing with
 state in larger single page applications. But even smaller applications might
 benefit from a redux architecture.
 I will show you how to implement redux in less than 30 lines of ClojureScript to
 get a **clean application structure** and to **reduce cognitive load**.

## redux.reducer

```clojure
(ns redux.reducer)

(defmulti Action
  (fn [state action]
    (:type action)))

(defmethod Action :default [state {:keys [type] :as action-data}]
  (prn "Action of " type " not defined.")
  state)
```

It is possible to keep the redux implementation so simple because of two features
of Clojure. One of those features we are taking advantage of is *multimethods*,
my favorite form of Clojure's runtime polymorphism.
Throughout our app the only way to change the state of the redux store
is by dispatching actions. You can define action types by setting *:type*.
Use it like this:

```clojure
(r/dispatch! {:type :add-todo :name "brew coffee"})
```

Your reducers **always** have to return state!

## redux.core

```clojure
(ns redux.core
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require
   [cljs.core.async :as a]
   [reagent.core :as r]
   [redux.reducer :refer [Action]]))

(defonce !state (r/atom {}))
(defonce !actions (a/chan))

(defn dispatch! [action]
  (a/put! !actions action))

(go-loop []
  (when-let [a (a/<! !actions)]
    (swap! !state Action a)
    (recur)))
```

The other feature we are using to concisely implement redux are **channels**.
Channels allow us to dispatch actions asynchronously from within other actions,
which takes away at least half of the pain of building single page applications.

The redux store is a `ratom`, which is watched by reagent components and triggers
re-renders.

To query state in a component just require

```clojure
[redux.core :as r]
```

and use

```clojure
@r/!state
```

## Benefits

### Structure

Redux can help you structuring your application. It becomes obvious to have a list of
reducers and a list of components. While this is a sensible way to split state
changing logic from the view in early stages of development, you might later group reducers/components by use cases.

```
.
├── redux
│   ├── core.cljs
│   └── reducer.cljs
└── app
    ├── components
    │   ├── box.cljs
    │   ├── button.cljs
    │   ├── item.cljs
    │   ├── screen.cljs
    │   └── snackbar.cljs
    ├── core.cljs
    ├── reducers
    │   ├── components.cljs
    │   ├── validation.cljs
    │   ├── form.cljs
    │   └── websockets.cljs
    └── utils.cljs
```

### Reduced cognitive load

If you agree with Clojure's way of dealing with complexity through isolation,
you will agree with me on this point.

```clojure
(defmethod Action :blur [s {:keys [id evt]}]
  (r/dispatch! {:type :close-warning})
  (let [val (-> evt .-target .-value)]
    (r/dispatch! {:type :update-comp :id id :data textt val}))
  s)
```

While working on a reducer for an action type, you are looking at a pure
<sup>1</sup> function of action and state. There is neither any other state nor any
affected components elsewhere, you can focus on building and returning the
next state.

In order to implement a redux app, start with the event handler,
create empty reducers and implement reducers one by one.

**1** We are dispatching an action from within the function. This is a semantically observable side effect and strictly speaking the function is not pure. Nevertheless, the side effect is applied in a controlled way to our system and reducers remaing easy to reason about. The argument of reduced cognitive load holds.
