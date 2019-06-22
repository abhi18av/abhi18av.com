---
title: jq - my new favorite tool to work with json on the command line
authors: Phil Hofmann
category: tooling
date-published: 2016-12-02
tags: 
- json
- cli
uuid: 105c9d69-08a0-4f37-9cbb-7d1f8c5513da
---

Frustration with slow and manual processes is probably my number one
motivator to discover new tooling. Having lately worked with Swagger
on a Json API I found myself in need of a tool to work with Json on
the command line.

I tried a couple and just when I was about to make the decision that I
want to try them all to find the best one my search was cut short when
I tried [jq](https://stedolan.github.io/jq/). On the web site it
claims: **jq is like sed for JSON data**. And come to think of it what
would we do without sed?

And yes, it's that good. Here two easy examples:


**Extracting a field**

Let's say you access an API to retrieve an access key. Then that's what
it probably looks like.

```
curl -s -X PUT --header 'Content-Type: application/json' \
  --header 'Accept: application/json' \
  -d '{
    "email": "your@email.address",
    "password": "swaggerrocks"
    }' \
  'https://your.api.endpoint/'
```

This of course will return a whole Json object.

```
{"access":"1234567890987654321", "some":"more", "fields":"you don't care about"}
```

But you might only be interested in the access key. Pipe it through jq.

```
curl -s -X PUT --header 'Content-Type: application/json' \
  --header 'Accept: application/json' \
  -d '{
    "email": "your@email.address",
    "password": "swaggerrocks"
    }' \
  'https://your.api.endpoint/' | jq -r .access
```

This will print

```
1234567890987654321
```

(Which in my setup is then piped into `xclip -i` to copy it to X's
primary clipboard.) The `-r` switch gives you the raw output instead
of surrounding the resulting string with quotes.

jq comes with its own mini language to define "filters" (more like
transformers) to manipulate Json or extract data out of it.

Since I now have a tool at hand to work with Json on the command line
nicely. It suddenly bothers me that I've to pass carefully crafted
literal Json into the curl earlier. Hence in a second example I will
show how to use jq to generate Json.


**Generate Json**

To generate Json we tell jq with the `-n` switch to expect no
input. We'll then use filters to add our data.

```
jq -n '.email="your@email.address"|.password="swaggerrocks"'
```

Using this technique we can refactor our command from earlier to

```
jq -n '.email="your@email.address"|.password="swaggerrocks"' \
  | curl -s -X PUT -d @- \
  --header 'Content-Type: application/json' \
  --header 'Accept: application/json' \
  'https://your.api.endpoint/' \
  | jq -r .access
```

I find some beauty in that.
