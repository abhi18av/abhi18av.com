(set-env!
  :source-paths #{"src"}
  :resource-paths #{"resources"}
  :dependencies '[[perun "0.4.3-SNAPSHOT" :scope "test"]
                  [hiccup "1.0.5" :exclusions [org.clojure/clojure]]
                  [pandeiro/boot-http "0.8.3" :exclusions [org.clojure/clojure]]])

(require '[io.perun :as perun]
         '[pandeiro.boot-http :refer [serve]])




(deftask build
  "Build test blog. This task is just for testing different plugins together."
  []
  (comp
        (perun/global-metadata)
        (perun/markdown)
        (perun/print-meta)
        (perun/slug)
        (perun/ttr)
        (perun/word-count)
        (perun/build-date)
        #_(perun/static :renderer 'site.about/render :page "about.html")
        #_(perun/sitemap)
        (perun/rss :description "abhi18av blog")
        (perun/atom-feed :filterer :original)
        (perun/print-meta)
        (target)
        (notify)))

(deftask dev
  []
  (comp (watch)
        (build)
        (serve :resource-root "public")))
