(set-env!
  :source-paths #{"src"}
  :resource-paths #{"resources"}
  :dependencies '[[perun "0.4.3-SNAPSHOT" :scope "test"]
                  [org.martinklepsch/boot-garden "1.3.2-1"]
                  #_[cider/cider-nrepl "0.22.0-beta3"]
                  [hiccup "1.0.5" :exclusions [org.clojure/clojure]]
                  [pandeiro/boot-http "0.8.3" :exclusions [org.clojure/clojure]]])

(require '[clojure.string :as str]
         '[io.perun :as perun]
         '[org.martinklepsch.boot-garden :refer [garden]]
         '[site.index :as index-view]
         '[site.post :as post-view]
         '[pandeiro.boot-http :refer [serve]])



(task-options! garden {:styles-var   'site.stylesheet/screen
                       :output-to    "public/garden.css"
                       :pretty-print false})


(deftask build
  []
  (comp
        (perun/global-metadata)
        (perun/markdown)
        (perun/draft)
        (perun/print-meta)
        (perun/slug)
        (perun/ttr)
        (perun/word-count)
        (perun/build-date)
        (perun/gravatar :source-key :author-email :target-key :author-gravatar)
        (perun/collection :renderer 'site.index/render :page "index.html")
        (perun/collection :renderer 'site.blog/render :page "blog.html")
        (perun/collection :renderer 'site.projects/render :page "projects.html")
        (perun/collection :renderer 'site.post/render :page "post.html")
        (perun/collection :renderer 'site.pursuit-of-simplicity/render :page "pursuit-of-simplicity.html")
        (perun/tags :renderer 'site.tags/render)
        (perun/paginate :renderer 'site.paginate/render)
        (perun/assortment :renderer 'site.assortment/render
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
        #_(perun/static :renderer 'site.about/render :page "about.html")
        #_(perun/inject-scripts :scripts #{"start.js"})
        #_(garden)
        (perun/sitemap)
        (perun/rss :description "Abhinav Sharma (abhi18av)")
        perun/atom-feed :filterer :original)
        (perun/print-meta)
        (target)
        (notify)))

(deftask dev
  []
  (comp (watch)
        (build)
        (serve :resource-root "public")))
