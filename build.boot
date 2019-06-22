(set-env!
  :source-paths #{"src"}
  :resource-paths #{"content"}
  :dependencies '[[perun "0.4.3-SNAPSHOT" :scope "test"]
                  [hiccup "1.0.5" :exclusions [org.clojure/clojure]]
                  [pandeiro/boot-http "0.8.3" :exclusions [org.clojure/clojure]]])

(require '[io.perun :as perun]
         '[pandeiro.boot-http :refer [serve]])