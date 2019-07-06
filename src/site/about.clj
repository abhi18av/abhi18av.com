(ns site.about
  (:require [hiccup.page :refer [html5]]))


(defn render [{global-meta :meta posts :entries}]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
         [:head
          [:title (:site-title global-meta)]
          [:meta {:charset "utf-8"}]
          [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
          [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=no"}]
          [:link {:rel "stylesheet" :type "text/css" :href "style.css"}]]
         [:body
          [:img {:src "./assets/images/profile.jpg", :alt "Profile Image"}]
          [:p " I am not from Krypton, or Stark Tower or even BatCave ;)"]
          [:p " What am I passionate about, you ask? "]
          [:p " I am passionate about Startups "]
          [:p " My favourite hobby, my drive, is Growth-hacking! "]
          [:h2 "Skills"]
          [:h4 " Have programming experience with "]
          [:ul {:class "skill-list"}
           [:li "Python"]
           [:li "Go"]
           [:li "Clojure"]
           [:li "JavaScript and TypeScript"]
           [:li "ClojureScript ( hosted on NodeJS )"]
           [:li "ReactJS"]
           [:li "MySQL - MongoDB - SQLite3 "]]]
         [:footer ""]))
