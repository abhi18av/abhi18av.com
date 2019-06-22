(ns ok.data.index
  (:require [ok.layout :as layout]))

(defn page [arg]
  (let [db (get-in arg [:meta :fsdb :manifest])
        num-people      (-> db :people count)
        num-techs       (-> db :technologies count)
        num-clients     (-> db :clients count)
        num-locations   (-> db :locations count)
        num-services    (-> db :services count)
        num-projects    (-> db :projects count)
        num-opensourced (->> db :projects vals (filter :opensourced) count)]
    (layout/main
     arg
     [:main.teaser-wrapper
      [:div.teaser
       [:span
        [:span "We're "]
        ;; TODO: 'team.html' is the old code that's sourced from
        ;; markdown files. 'people.html' is the new page that's
        ;; sourced through fsdb. For now, we're pragmatic and use the
        ;; team page, because we can deploy it faster. At some point,
        ;; 'team.html' will be deprecated in favour of 'people.html'.
        ;; [:a {:href "people.html"}
        [:a {:href "team.html"}
         num-people " software engineers*,"]]
       [:span
        [:span " working from "]
        [:a {:href "locations.html"}
         num-locations " locations in Switzerland,"]]
       [:span
        [:span " highly skilled in "]
        [:a {:href "technologies.html"}
         num-techs " technologies,"]]
       [:span
        [:span " enthusiastically working for "]
        [:a {:href "clients.html"}
         num-clients " clients,"]]
       [:span
        [:span " providing "]
        [:a {:href "services.html"}
         num-services " kinds of services,"]]
       [:span
        [:span " on "]
        [:a {:href "projects.html"}
         num-projects " awesome projects,"]]
       [:span
        [:span " of which to this date "]
        [:a {:href "opensource.html"}
         num-opensourced " have been open-sourced."]]
       [:div.supporting
        "* including supporting roles & network"]]
      [:a.stopper {:target "_blank"
                 :href "https://www.meetup.com/zh-clj-Zurich-Clojure-User-Group/"}
       [:img {:src "clojure_sun.svg"}]]])))
