(ns ok.data.locations
  (:require [ok.layout :as layout]
            [ok.shared :as shared]))

(defn- image
  [img]
  (let [img-url (str "/img/locations/" img)]
    [:li.image
     [:a {:href img-url}
      [:img {:src img-url}]]]))

(defn- location
  [loc]
  [:li.location
   [:h2 (:name loc)]
   [:div (:address loc)]
   [:div (shared/md2html (:description loc))]
   [:ul.images
    (map image (:images loc))]])

(defn- sort-locs
  [locs]
  (->> locs
       (map #(assoc (last %) :key (first %)))
       (sort-by #(or (:priority %) 9999))))

(defn page
  [arg]
  (let [db (get-in arg [:meta :fsdb :manifest])
        locations (sort-locs (-> db :locations))]
    (layout/main arg
                 [:main {:id "locations"}
                  [:h1 "Offices"]
                  [:ul.locations-list
                   (map location locations)]])))
