(ns ok.tags
  (:require [ok.layout :as layout]
            [ok.helper :refer [category-link
                               author-section
                               date-of-post-section
                               word-count-section
                               time-to-read-section
                               subheader-post]]))


(defn render-post
  "Renders a post list."
  [post]
  [:article.blog-post {:itemscope true
                       :itemtype "https://schema.org/BlogPosting"}
   [:h3.headline {:itemprop "headline"}

    [:a.nunito {:href (:permalink post)
                :itemprop "url"}
     (:title post)]]
   (subheader-post post)])


(defn render [{global-meta :meta posts :entries page :entry}]
  (layout/blog global-meta
               posts
               [:main {:id "tags"}
                [:h1.headline (str "#" (:tag page))]
                [:div.content {:id "content"}
                 (for [post posts]
                   (render-post post))]]))
