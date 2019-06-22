(ns ok.post
  (:require [ok.layout :as layout]
            [ok.index :as index]))

(defn render [{global-meta :meta posts :entries post :entry}]
  (layout/blog global-meta
               post
               [:main.single-post
                [:div#content
                 (index/render-post post)]]))
