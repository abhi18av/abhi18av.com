(set-env!
  :source-paths #{"src"}
  :resource-paths #{"content"}
  :dependencies '[[perun "0.4.3-SNAPSHOT" :scope "test"]
                  [hiccup "1.0.5" :exclusions [org.clojure/clojure]]])

(require '[io.perun :as perun])
