(ns site.core
  (:require [hiccup.page :as hp]))

(defn page [data]
  (hp/html5
    [:div {:style "max-width: 900px; margin: 40px auto;"}
     [:a {:href "/"} "Home"]
     [:div
     [:a {:href "/about.html"} "About"]]
      (-> data :entry :content)]))
