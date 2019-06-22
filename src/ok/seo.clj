(ns ok.seo
  (:require [ok.helper :as helper]))

(defn meta-description [meta-data]
  (:description meta-data))

(defn image-url [post-meta]
  (if-let [featured-image-url (:featured-image post-meta)]
    (helper/fully-qualifying-url featured-image-url)
    (helper/logo-url)))

(defn meta-description-tag
  "If the `meta-data` includes a `:description`, add the relevant <meta>
  tag"
  [meta-data]
  (if (meta-description meta-data)
    [:meta {:name "description"
            :content (meta-description meta-data)}]))

(defn title [global-meta post-meta]
  (if-let [post-title (:title post-meta)]
    (str post-title " - 200ok")
    (:site-title global-meta)))

(defn opengraph-meta [global-meta post-meta]
  [[:meta {:property "og:title" :content (title global-meta post-meta)}]
   [:meta {:property "og:type" :content "article"}]
   [:meta {:property "og:description" :content (meta-description post-meta)}]
   [:meta {:property "og:url" :content (helper/fully-qualifying-url (:permalink post-meta))}]
   [:meta {:property "og:image" :content (image-url post-meta)}]])


(defn twitter-meta [global-meta post-meta]
  [;; "summary" defines the card type: https://developer.twitter.com/en/docs/tweets/optimize-with-cards/overview/summary.html
   [:meta {:name "twitter:card" :content "summary"}]
   [:meta {:name "twitter:site" :content "@twohundredok"}]
   [:meta {:name "twitter:title" :content (title global-meta post-meta)}]
   [:meta {:name "twitter:image" :content (image-url post-meta)}]
   [:meta {:name "twitter:description" :content (meta-description post-meta)}]])
