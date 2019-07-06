(ns site.post
  (:use [hiccup.core :only (html)]
        [hiccup.page :only (html5)]))


(defn render [{global-meta :meta posts :entries post :entry}]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
         [:head
          [:meta {:charset "utf-8"}]
          [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
          [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, user-scalable=no"}]
          [:title "Blog | Abhinav Sharma"]
          [:meta {:name "description" :content "Full Stack Web Engineer"}]
          [:meta {:name "author" :content "Abhinav Sharma"}]
          [:link {:rel "canonical" :href "https://abhi18av.com/"}]
          [:link {:rel "stylesheet" :type "text/css" :href "style.css"}]]
    [:body
        [:h1 (:title post)]
        [:div (:content post)]]))
