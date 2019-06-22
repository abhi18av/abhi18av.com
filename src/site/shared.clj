(ns ok.shared
  (:use [markdown.core]))

(defn md2html [text]
  ;; `md-to-html-string` would not pick up the newlines to create
  ;; paragraphs on it's own.
  (map md-to-html-string
       (clojure.string/split text #"\n")))

(defn back-arrow []
  [:svg#back-arrow {:x "0px", :y "0px", :viewbox "0 0 30 30"}
   [:path {:d "M26.5,11.5H12.7l4.1-4.1c1.3-1.3,1.3-3.4,0-4.7c-1.3-1.3-3.4-1.3-4.7,0l-12,12c-0.2,0.2-0.2,0.5,0,0.7l12,12\n\tc0.6,0.6,1.5,1,2.4,1s1.7-0.3,2.4-1c1.3-1.3,1.3-3.4,0-4.7l-4.1-4.1h13.8c1.9,0,3.5-1.6,3.5-3.5S28.4,11.5,26.5,11.5z"}]])
