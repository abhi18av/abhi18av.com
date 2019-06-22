---
title: New release of our OSS crowdfunding platform swiss-crowdfunder.com 
authors: Alain M. Lafon
category: 200ok
date-published: 2018-10-14
tags: 
- swiss_crowdfunder
- oss
- ruby
- rails
uuid: E93EB457-D23A-43FF-9F27-FB6F60829EF1
description: >-
  Release 1.1 of Swiss Crowdfunder featuring: Translatable user
  generated content
featured-image: https://swiss-crowdfunder.com/android-chrome-512x512.png
---

[Swiss Crowdfunder](https://github.com/200ok-ungleich/swiss-crowdfunder/) is an OSS crowd funding and equity funding platform
which we are building together with [ungleich](https://ungleich.ch/).
We just released version 1.1 which now features translatable user
generated content. This is another unique selling proposition of Swiss
Crowdfunder compared to other platforms - you are now able to market
your campaign in multiple countries targeted through their unique
language!

Here's the gist of what it gives you:

![](/img/2018-10/swiss_crowdfunder_release_1_1.gif)

The project is hosted here: [https://github.com/200ok-ungleich/swiss-crowdfunder/](https://github.com/200ok-ungleich/swiss-crowdfunder/).

Here are the full release notes:


*Added*

- User generated content (Campaigns and Googies) is translated (Using
  Rails i18n and [globalize](https://github.com/globalize/globalize))
    - Switch the language in the back-office (ActiveAdmin) while
      editing a resource and hit save
    - Now all content (pages and user generated content) is translated!
- Introduce Rubocop for linting

*Changed*

- Integration tests run with Firefox headless instead of Chromium
- Upgrade from CircleCI v1 to v2
- Upgrade to Rails 5.1.6 (latest in 5.1.x branch) and remove
- deprecation messages
- Goody validates presence of :title, :description

Enjoy^^
