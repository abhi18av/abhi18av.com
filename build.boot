(set-env!
  :source-paths #{"src"}
  :resource-paths #{"content"}
  :dependencies '[[perun "0.4.3-SNAPSHOT" :scope "test"]
                  [hiccup "1.0.5" :exclusions [org.clojure/clojure]]
                  [pandeiro/boot-http "0.8.3" :exclusions [org.clojure/clojure]]])

(require '[io.perun :as perun]
         '[pandeiro.boot-http :refer [serve]]

         #_'[io.perun.example.index :as index-view]
         #_'[io.perun.example.post :as post-view])



(deftask build
  "Build test blog. This task is just for testing different plugins together."
  []
  (comp
        #_(perun/global-metadata)
        #_(perun/markdown)
        (perun/asciidoctor)
        (perun/draft)
        (perun/print-meta)
        (perun/slug)
        (perun/ttr)
        (perun/word-count)
        (perun/build-date)
        #_(perun/gravatar :source-key :author-email :target-key :author-gravatar)
        #_(perun/render :renderer 'io.perun.example.post/render)
        #_(perun/collection :renderer 'io.perun.example.index/render :page "index.html")
        #_(perun/tags :renderer 'io.perun.example.tags/render)
        #_(perun/paginate :renderer 'io.perun.example.paginate/render)
        #_(perun/assortment :renderer 'io.perun.example.assortment/render
                          :grouper (fn [entries]
                                     (->> entries
                                          (mapcat (fn [entry]
                                                    (if-let [kws (:keywords entry)]
                                                      (map #(-> [% entry]) (str/split kws #"\s*,\s*"))
                                                      [])))
                                          (reduce (fn [result [kw entry]]
                                                    (let [path (str kw ".html")]
                                                      (-> result
                                                          (update-in [path :entries] conj entry)
                                                          (assoc-in [path :entry :keyword] kw))))
                                                  {}))))
        #_(perun/static :renderer 'io.perun.example.about/render :page "about.html")
        #_(perun/inject-scripts :scripts #{"start.js"})
        #_(perun/sitemap)
        #_(perun/rss :description "abhi18av blog")
        #_(perun/atom-feed :filterer :original)
        #_(perun/print-meta)
        (target)
        (notify)))

(deftask dev
  []
  (comp (watch)
        (build)
        (serve :resource-root "public")))
