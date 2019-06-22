---
title: "RiotJS and nested custom Tags within Tables"
authors: "Alain M. Lafon"
category: "riotjs"
tags: 
- javascript
- spa
date-published: 2016-10-29
uuid: 0accc98c-020e-4293-9b8e-67a2e143a1c5
---

If you want to use [RiotJS](http://riotjs.com) Custom Tags within a
`<table>`, you might have stumbled upon something that looks like a
bug. If you nest Custom Tags within a `<table>` element, they will not
render within said element, but outside of it. Let me clarify with an
example and how to fix it.

If you have code that nests elements like this:

```html
<todoList>
    <table>
      <tbody>
        <todo each="{ allTodos() }">
        </todo>
      </tbody>
    </table>
</todoList>
```

Where `<todo>` is defined as a totally legit table row, you will find
that the result does not look like you imagined it. If you check your
browsers developer tools, you will see that the browser renders your
`<todo>` elements unexpectedly outside the `<table>` element. It might
look like this:

```html
<todolist>
    <todo></todo>
    <table>
    </table>
</todolist>
```

The reason for this phenomenon is that browsers require known HTML
elements within a `<table>` and `<tbody>` elements. If anything else
is issued to be rendered within, it is just ejected.

No worries, though! RiotJS has a proper way of handling this type of
situation. Standard HTML elements can be used as Riot Tags when you
use the `data-is` attribute. For the `<todoList>` example above, this is
functional code:

```html
<todoList>
    <table>
      <tbody >
        <tr each="{ allTodos() }" data-is="todo">
        </tr>
      </tbody>
    </table>
</todoList>
```

In this example, the `<tr>` tag is a legit HTML tag which the browser
expects to be within a `<table>`. However, since we want to write
modular composable code, we tell RiotJS to actually use the `<todo>`
component to compose the `<tr>`. For completeness, this would be valid
code for a `<todo>` component:

```html
<todo>
  <td>
    <input type="checkbox" checked={ done } />
    { name }
  </td>
</todo>
```

One last thing: A custom tag has to be lower-case only when used with
`data-is`, otherwise Riot will not pick it up. This is likely a bug
and will be fixed in a future version.
