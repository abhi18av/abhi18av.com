(ns ok.index
  "NOTE: this ns still has too much stuff in it"
  (:require [clojure.string :as s]
            [ok.helper :refer [logo-url
                               author-section
                               date-of-post-section
                               word-count-section
                               time-to-read-section
                               subheader-post
                               category-link]]
            [ok.layout :as layout]))

;; Returns the publish date or a fallback if no :date-published is
;; provided. This, however, is always a lie, because the
;; last-modified-time is whenever the page has been built.
(defn- date-of-post
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

(defn- ok-metadata
  []
  [:div {:itemscope true
         :itemprop "publisher"
         :itemtype "https://schema.org/Organization"}
   [:span
    [:meta {:itemprop "name"
            :content "200ok GmbH"}]]
   [:span {:itemscope true
           :itemprop "logo"
           :itemtype "https://schema.org/ImageObject"
           }
    [:meta {:itemprop "url"
            :content "http://200ok.ch/img/logo.png"}]]
   [:span
    [:meta {:itemprop "legalName"
            :content "200ok GmbH"}]]])


(defn- category-section
  [post]
  (if (:category post)
    [:li.category

     [:a {:href (category-link (:category post)) }
      (:category post)]]))

(defn- separated-tags
  "Get tag(s). If there are none, return '200ok'."
  [post]
  (or (:tags post)
      ["200ok"]))

;; TODO: Refactor this to yield a unique keyword list
;; TODO: Find out how to yield the 'category' as focus keyword
(defn- tags
  "Renders tags and category into the footer of a post"
  [post]
  [:div.tags
   [:img.tag-icon {:src "/img/tag.svg"}]
   [:ul {:itemprop "keywords"}
    (category-section post)
    (for [tag (separated-tags post)]
      [:li.tag
       [:a {:href (str "/tags/" tag ".html")}
        (str "#" tag)]])]])

(defn full-post [post]
  [:div
   [:div.article-body {:itemprop "articleBody"} (:content post)]
   (tags post)])

(defn- preview-post
  "Returns the first 100 words of a post wrapped in a :section.
   Optionally with a link to 'Read more'"
  [post]
  [:section
   [:div.article-section {:itemprop "articleSection"}
    (as-> (:content post) %
      (s/split % #"<pre>")
      (first %)
      (s/split % #" ")
      (take 100 %)
      (s/join " " %))
    "..."
    (tags post)
    [:p
     [:a.read-more {:href (:permalink post)
                    :itemprop "url"}
      "Read more..."]]]])

(defn- image-meta-data
  "Add image meta data for the 200ok logo."
  [post]
  [:span {:itemscope true
          :itemprop "image"
          :itemtype "https://schema.org/ImageObject"}

   [:meta {:itemprop "height"
           :content "190"}]
   [:meta {:itemprop "width"
           :content "349"}]
   [:meta {:itemprop "url"
           :content (logo-url)}]])

(defn render-post
  "Renders a post as :article"
  [post & {:keys [max]}]
  [:article.blog-post {:itemscope true
                       :itemtype "https://schema.org/BlogPosting"}
   [:h3.headline {:itemprop "headline"}
    [:a.nunito {:href (:permalink post)
                :itemprop "url"}
     (:title post)]]
   (subheader-post post)
   (image-meta-data post)
   (let [content (:content post)
         words (:word-count post)]
     (if (or (nil? max) (> max words))
       (full-post post)
       (preview-post post)))])

(defn- categories
  "Add categories to sidebar"
  [global-meta]
  [:div.sidebar
   [:div
    [:h5.categories "Categories"]
    [:ul
     (let [sorted (->> (:categories global-meta)
                       (map :category)
                       (map s/lower-case)
                       frequencies
                       (sort-by second)
                       reverse)]
       (map (fn [[category occurrences]]
              [:li [:a {:href (category-link category)}
                    (s/capitalize category)
                    [:span.badge occurrences]]])
            sorted))]]])

(defn- pagination
  [page]
  ;; TODO: Implement `:pages-count` within the `pagination` task of
  ;; Perun.
  (let [pages-count (if (:last-page page)
                      (Integer/parseInt (first (re-seq #"\d" (:last-page page))))
                      ;; There seems to be a bug in Perun. Sometimes
                      ;; `:last-page` isn't set. For all actually
                      ;; rendered pages is is, though.
                      0)]
    (if (> pages-count 0)
      [:div#pagination
       (if-not (= (:page page) 1)
         [:a.pager {:href (:first-page page)}
          "<<"])
       (if (:prev-page page)
         [:a.pager {:href (:prev-page page)}
          "<"])
       [:span#current-page.pager (str (:page page)
                                      " / "
                                      pages-count)]
       (if (:next-page page)
         [:a.pager {:href (:next-page page)}
          ">"])
       (if (not (= (:page page) pages-count))
         [:a.pager {:href (:last-page page)}
          ">>"])])))

(defn render [{global-meta :meta posts :entries page :entry}]
  (layout/blog global-meta
               posts
               [:main
                [:div.content {:id "content"}
                 (for [post posts]
                   (render-post post :max 100))
                 (pagination page)]
                (categories global-meta)]))
