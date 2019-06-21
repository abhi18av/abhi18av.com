---
title: Journey to Clojure - Act 2
layout: post
date: 2016-10-28 20:41
image:
description: Programming and Languages
tag: computation
blog: true
jemoji:
author: Abhinav Sharma
---

So, here's the code I've been talking about - do notice how all the computations are really working their way up to a  single composite function!


```python


# -*- coding: utf-8 -*-
"""
Created on Wed Aug  3 17:59:46 2016

@author: eklavya
"""






## Downloading the TED talks in Kazakh



import os
os.chdir("/Users/eklavya/Projects/TheTalkingApes/")
os.getcwd()






import requests
Ab
from bs4 import BeautifulSoup


#def url_talk(lang_code):
#    return "https://www.ted.com/talks/chieko_asakawa_how_new_technology_helps_blind_people_explore_the_world/transcript?language=" + lang_code




def r(lang_code):
    url_talk = "https://www.ted.com/talks/chieko_asakawa_how_new_technology_helps_blind_people_explore_the_world/transcript?language=" + str(lang_code)
    return requests.get(url_talk)


def soup(lang_code):
    return BeautifulSoup(r(str(lang_code)).content, "lxml")

def talk_text(lang_code):
    return soup(str(lang_code)).find_all('div','talk-article__body talk-transcript__body')[0].get_text()


def talk_text_para(lang_code):
    return soup(str(lang_code)).find_all('p', 'talk-transcript__para')[0].get_text()


def sanitize_newline(a_talk_fragement):
    return str(a_talk_fragement).replace('\n','')

    
file = open('ted_talk_kk.txt','w', encoding='utf-8')
file.write(talk_text('kk'))
file.close()


"""
instead of files, prefer
* html
( dominate library )
OR
* sqlite3
( simple sqlite3 lib)

"""


file = open('ted_talk_en.txt','w', encoding='utf-8')
file.write(talk_text('en'))
file.close()

print(talk_text_para('en'))
print(talk_text_para('kk'))


    
##########################
    
    
```


Honestly, this was something which just blew my mind - this just looked so beautiful and even though it collected all the talks in-memory which wasn't exactly what I was looking for but what a beauty! After this program, I feel the way I look at composing programs quite differently.

