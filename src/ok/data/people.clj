(ns ok.data.people
  (:require [ok.shared :as shared]
            [ok.layout :as layout]))

(defn- person [person]
  [:div.team-member
   {:itemscope true
    :itemtype "https://schema.org/person"}
   [:div.team-picture
    [:img {:src (:image person)
           :alt (str (:name person) " Profile Picture")
           :itemprop "image"}]]
   [:div
    [:h3 (:name person)]
    [:p.title (:title person)]
    [:p.team-description
     {:itemprop "description"}
     (shared/md2html (:description person))]
    [:div
     [:a {:href (str "mailto:" (:email person))}
      [:span {:itemprop "email"} (:email person)]]]]])

(defn page [arg]
  (let [db (get-in arg [:meta :fsdb :manifest])
        everybody (-> db :people vals)
        public (sort-by :position (filter :position everybody))
        num-more (- (count everybody) (count public))]

    (layout/main arg
                 [:main.team
                  [:div#content.row
                   (map person (sort-by :position public))]])))
