(ns ok.page
  "NOTE: audio-book is the only page that uses this ns"
  (:require [ok.layout :as layout]))

(defn- render-page
  "Renders a page as :article"
  [page & {:keys [max]}]
  [:article.page {:itemscope true
                  :itemtype "https://schema.org/WebPage"}
   [:h3 {:itemprop "headline"}
    [:a {:href (:permalink page)
         :itemprop "url"}
     (:name page)]]
   [:div {:itemprop "text"} (:content page)]])

(defn render [{global-meta :meta pages :entries page :entry}]
  (layout/blog global-meta
               pages
               [:main
                [:div#content.row
                 (render-page page)]]))
