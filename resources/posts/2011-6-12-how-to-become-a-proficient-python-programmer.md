---
title: "How to become a proficient Python programmer"
authors: "Alain M. Lafon"
category: "python"
date-published: 2011-06-12
tags: 
- coding
- learning
uuid: 360ccd7a-933d-438a-95f5-c6eddf3cd39c
---

Update: This is a re-post of an older blog post of mine. Originally it
was posted on my
[personal blog](http://blog.dispatched.ch/2011/06/12/how-to-become-a-proficient-python-programmer/)
where it has >30 comments and >20'000 views over the last years.
I'm deprecating my personal blog in favor of this 200OK blog.

Spoiler: This post is primarily gonna be an excerpt of my bookmarks
collection. That’s because more intelligent men than me have already
written great articles on the topic of how to become a great Python
programmer.

I will focus on four primary topics: Functional programming,
performance, testing and code guidelines. When those four aspects
merge in one programmer, he or she will gain greatness no matter what.

### Functional programming

Writing code in an imperative style has become the de facto standard.
Imperative programs consist of statements that describe change of
state. While this might sometimes be a performant way of coding, it
sometimes isn’t (for example for sake of complexity) – also, it
probably is not the most intuitive way when compared with declarative
programming.

If you don’t know what I’m talking about, that’s great. Here are some
starter articles to get your mind running. But beware, it’s a little
like the red pill – once you tasted functional programming, you don’t
want to go back.

* [http://www.amk.ca/python/writing/functional](http://www.amk.ca/python/writing/functional)
* [http://www.secnetix.de/olli/Python/lambda_functions.hawk](http://www.secnetix.de/olli/Python/lambda_functions.hawk)
* [http://docs.python.org/howto/functional.html](http://docs.python.org/howto/functional.html)


### Performance

There’s so much talk going on about how inefficient these ‘scripting
languages’ (Python, Ruby, …) are, that it’s easy to forget that very
often it’s the algorithm chosen by the programmer that leads to
horrible runtime behaviour.

Those articles are a great place to get a feel for the ins and outs of
Python’s runtime behaviour, so you can get your high performing
application writting in a language that is concise and fun to write.
And if your manager asks about Python’s performance, don’t forget to
mention that the second largest search engine in the world is run by
Python – namely Youtube(see
[ Python quotes ](http://www.python.org/about/quotes/)).


* [http://jaynes.colorado.edu/PythonIdioms.html](http://jaynes.colorado.edu/PythonIdioms.html)
* [http://wiki.python.org/moin/PythonSpeed/PerformanceTips](http://wiki.python.org/moin/PythonSpeed/PerformanceTips)

### Testing

Testing is probably one the most misjudged topics in computer science these days. Some programmers really got it and emphasize TDD(test driven development) and it’s successor BDD(behaviour driven development) whereever possible. Others simply don’t feel it yet and think it’s a waste of time. Well, I’m gonna be that guy and tell you: If you haven’t started out on TDD/BDD yet, you have missed out greatly!

It’s not about introducing a technology to replace that release management automaton in your company that mindlessly clicks through the application once in a while, it is about giving you a tool to deeply understand your own problem domain – to really conquer, manipulate and twist it the way you want and need it to be. If you haven’t yet, give it a shot. These articles will give you some impulses:

* [http://www.oreillynet.com/lpt/a/5463](http://www.oreillynet.com/lpt/a/5463)
* [http://www.oreillynet.com/lpt/a/5584](http://www.oreillynet.com/lpt/a/5584)
* [http://wiki.cacr.caltech.edu/danse/index.php/Unit_testing_and_Integration_testing](http://wiki.cacr.caltech.edu/danse/index.php/Unit_testing_and_Integration_testing)
* [http://docs.python.org/library/unittest.html](http://docs.python.org/library/unittest.html)

### Code guidelines

Not all code is created equal. Some can be read and changed by any great programmer out there. But some can only be read and only sometimes changed by the original author – and that maybe only a couple of hours after he or she wrote it. Why is that? Because of missing test coverage (see above) and the lack of proper usage of coding guidelines.

These articles establish an absolute minimum to adhere to. When you follow these, you will write more consise and beautiful code. As a side effect it will be more readable and adaptable by you or anyone else.

* [http://www.python.org/dev/peps/pep-0008/](http://www.python.org/dev/peps/pep-0008/)
* [http://www.fantascienza.net/leonardo/ar/python_best_practices.html](http://www.fantascienza.net/leonardo/ar/python_best_practices.html)

Now go ahead and spread the word. Start with the guy sitting right next to you. Maybe you can go to the next hackathlon or code dojo and start becoming great proficient programmers together!

All the best on your journey.

If you liked this article, please feel free to re-tweet it and let others know.
