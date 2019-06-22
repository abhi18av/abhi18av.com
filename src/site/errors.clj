(ns ok.errors
  (:require [ok.helper :refer [image-tag]]
            [ok.layout :as layout]))

(def ^:private markup-404
  [:main#errors
   [:div#content

    [:div#text
     [:h2 "404"]
     [:h1 "Something went wrong"]
     [:h3 "The page you're trying to access doesn't appear to exist. Looking for our "
      [:a {:href "/blog.html"} " Blog"]
      " or our "
      [:a {:href "/team.html"} " Team"]
      "?"]]
    [:div#image
     (image-tag "/img/404.jpg")]]])

(def ^:private markup-old-ie-warning
  [:body {:id "ie-fallback"}
   [:div
    [:div.logo (image-tag "/img/200ok.svg")]
    [:div.content
     [:h2 "💣 Your browser is out of date 💣"]
     [:p
      "You are using a version of Internet Explorer that is no longer
      supported by Microsoft. It has known "
      [:strong "security flaws"]
      " and will have trouble displaying many modern
      websites (including 200ok.ch)."]
     [:p
      "We encourage you to use Mozilla Firefox, as it is a modern,
      fast and safe browser. Also, it updates itself continuously, so
      you will never run into an issue of having an outdated browser,
      again."]
     [:p.download
      [:a {:href "https://www.mozilla.org/en-US/firefox/new/"}
       (image-tag "/img/ie-fallback/firefox-logo.png")
       [:span "Download it now"]]]]]])

;; public

(defn page-old-ie
  [global-meta]
  (layout/bare global-meta markup-old-ie-warning))

(defn page-404
  [global-meta]
  (layout/main global-meta markup-404 :is-error? true))
