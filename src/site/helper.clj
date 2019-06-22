(ns ok.helper
  (:require [clojure.string :as s]))

(defn stylesheet-link-tag [href]
  [:link {:rel "stylesheet" :href href}])

(defn image-tag [src]
  [:img {:src src}])

(defn category-link [category-name]
  (str "/category/" (clojure.string/lower-case category-name) ".html"))

(defn logo-url []
  "https://200ok.ch/img/logo.png")

(defn fully-qualifying-url
  "Given a `resource-path`, it appends the canonical URL for the domain"
  [resource-path]
  (if resource-path
    (if (re-find #"^http" resource-path)
      resource-path
      (str "https://200ok.ch" resource-path))))

;; Returns the publish date or a fallback if no :date-published is
;; provided. This, however, is always a lie, because the
;; last-modified-time is whenever the page has been built.
(defn date-of-post
  "Returns the last-updated-at date of a post"
  [post]
  (if (:date-published post)
    (.format (java.text.SimpleDateFormat. "yyyy-MM-dd")
             (:date-published post))
    (.format (java.text.SimpleDateFormat. "yyyy-MM-dd")
             (java.util.Date.
              (.lastModified
               (clojure.java.io/file
                (str "resources/" (:path post))))))))

(defn- authors
  "Get author(s). If there are none, return '200ok'."
  [post]
  (s/split (or (:authors post) "200ok") #"\s*,\s*"))

(defn author-section
  [post]
  (for [author (authors post)]
    [:section.author {:itemscope true
                      :itemprop "author"
                      :itemtype "https://schema.org/Person"}
     [:span {:itemprop "name"}
      author]]))

(defn date-of-post-section
  [post]
  [:time {:itemprop "datePublished"}
   (date-of-post post)])

(defn word-count-section
  [post]
  [:span {:itemprop "wordCount"}
   (:word-count post)])

(defn time-to-read-section
  [post]
  [:span {:itemprop "timeRequired"}
   (:ttr post)])

(defn subheader-post
  "Returns metadata of a post inside a :div.subheader"
  [post]
  [:div.subheader
   [:p.post-meta
    (date-of-post-section post)
    " - "
    (word-count-section post)
    " words"
    " - "
    (time-to-read-section post)
    " min read"]
   [:div.byline
    [:img.author-icon {:src "/img/author.svg"}]
    (author-section post)]])
