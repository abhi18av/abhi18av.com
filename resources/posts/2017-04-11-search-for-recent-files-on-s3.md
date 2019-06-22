---
title: "Search for recently updated files on Amazon S3"
authors: "Alain M. Lafon"
category: "tooling"
date-published: 2017-04-11
tags: 
- aws
- cloud
- s3
uuid: ad9e6d0b-f374-42cb-b9d9-5a6ca247ffae

---

Amazon AWS has a nice
[command line client](http://docs.aws.amazon.com/cli/latest/userguide/cli-chap-welcome.html)
for their public cloud. This client is very well suited for automating
tasks in many IaaS DevOps scenarios. However, it can also be used by a
person. The scenarios to do so might be different than for automation,
though.

A common scenario for looking into S3 is to want to list files ordered
by date and including metadata. On a Unix machine, this would be an `ls -lt`.
If it's a very long list of files, you might want to cap the list - which
again is very easy to achieve, for example with `ls -lt | head -n 10`.

Those two things are easy on a Unix machine, but not so straight
forward on Amazon S3. Amazon does have an `ls` command which will list
all files within `[BUCKET]`:

```sh
aws s3 ls s3://[BUCKET]
```

It is totally feasible to pipe the result of this command into `head
-n X`. It does have serious drawbacks, though, because you might have
_a lot_ of files on S3:

1. You will first request all files over the network which will take a
   while and then remove most of them. Waiting for lots of irrelevant
   data to come over the wire is not efficient use of your time.
1. To list things actually does come with a
   [price tag](https://aws.amazon.com/s3/pricing/) on S3! Meaning that
   if you have lots of data on S3 and you're doing this a lot, then
   you will pay for data that you never use, but discard. This might
   not be a lot of money depending on how many requests you have to
   do, but still.
   
Next to `aws s3`, which only implements the most high level features
such as `cp` and `mv`, there is a more powerful API
[`aws s3api`](http://docs.aws.amazon.com/cli/latest/reference/s3api/index.html#cli-aws-s3api).
With this API you can write queries _and_ limit the amount of objects
returned. Queries can include the `LastModified` timestamp. Limiting
on this timestamp is not the same as ordering by time (as in `ls
-lt`), but it's the closest you will get to this functionality on S3.

```sh
aws s3api list-objects \
--bucket "[BUCKET]" \
--query 'Contents[?LastModified>=`2017-04-10`][].{Key: Key, Size:
Size, LastModified: LastModified}'
```


