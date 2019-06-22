---
title: Teaming up sourceme and tmux
authors: Alain M. Lafon
category: tooling
date-published: 2018-09-22
tags: 
- 200ok
uuid: a04d36eb-0649-46a4-ac29-88d2494855f5
description: >-
  Combine sourceme and tmux to automate complicated dev setups.
---

Combining [sourceme](/posts/contextual-helpers-with-zsh-hooks.html)
with [tmux](https://github.com/tmux/tmux) is a small workflow
improvement which I like to use when running 'complicated' dev setups.
By 'complicated" I mean setups where you need multiple terminals to
start multiple things. 

Let me give you an arbitrary example to give you a sense of what kinds
of situations I'm talking about: You often need to run some sorts of
Docker containers (for example for the databases) and then some
services which you actually develop - let's say a server and a
front-end. In such a scenario, there's lots of redundant typing to be
done every time you switch to this project:

1. Start a first terminal
1. Stop your system database
1. Run docker-compose
1. Start a second terminal
1. Run the back-end server
1. Start a third terminal
1. Run the front-end server

Like I said, this is completely arbitrary - there's gazillions other
complicated setups out there.

This manual mess can be solved by adding a custom function around tmux
into your `.sourceme` file:

```sh
startup () {
  tmux new-session -d 'sudo service postgresql stop; docker-compose up'
  tmux split-window -v 'npm run watch'
  tmux split-window -h 'cd ~/src/200ok/some-service/front-end; npm run start'
  tmux attach-session -d 
}

echo "Supported commands: \n"
echo " - startup: Starts tmux, docker-compose, back-end and front-end"
```

So now I only have to start `startup` and I get three terminals in
one, all already running the right services.
