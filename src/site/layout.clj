(ns ok.layout
  (:require [hiccup.page :refer [html5]]
            [ok.helper :refer [stylesheet-link-tag image-tag] :as helper]
            [ok.john :as john]
            [ok.seo :as seo]
            [clojure.data.json :as json]))

(defn- rss-meta-tag
  [global-meta]
  [:link {:rel "alternate" :type "application/rss+xml"
            :title (:site-title global-meta)
            :href "/rss.xml"}])

(defn- atom-meta-tag
  [global-meta]
  [:link {:rel "alternate" :type "application/atom+xml"
            :title (:site-title global-meta)
            :href "/atom.xml"}])

(def ^:private footer
  [:footer
   [:div
    {:itemscope true
     :itemprop "publisher"
     :itemtype "https://schema.org/Organization"}
    [:div.name {:itemprop "name"}
     "200ok GmbH"]
    [:div {:itemprop "address"
           :itemscope true
           :itemtype "https://schema.org/PostalAddress"}
     [:a {:href "https://goo.gl/maps/GNAoiNF7mbL2"
          :title "View on Google Maps"}
      [:div {:itemprop "streetAddress"} "Badenerstrasse 313"]
      [:div
       [:span {:itemprop "postalCode"} "8003"]
       " "
       [:span {:itemprop "addressLocality"} "Zürich"]]]]
    [:div {:itemprop "telephone"} "+41 76 405 05 67"]
    [:div {:itemprop "email"}
     [:a {:href "mailto:info@200ok.ch"} "info@200ok.ch"]]
    [:img {:itemprop "logo"
           :src (helper/fully-qualifying-url "/img/200ok.svg")}]]])

(defn- scripts [global-meta]
  [:div.scripts {:style {:display "none"}}
   [:script {:src "/js/vendor/bowser.min.js"}]
   [:script {:src "/js/ie_safeguard.js" :async true}]
   [:script {:src "/js/tour.js" :async true}]
   [:script {:src "/js/highlight.pack.js"}]
   [:script "hljs.initHighlightingOnLoad();"]
   [:script (str "tour=" (json/write-str (get-in global-meta [:meta :fsdb :manifest :tour])))]
   (when (= (:target global-meta) "prod")
     [:script {:src "/js/ga.js"}])])

(def ^:private common-head
  [[:meta {:charset "utf-8"}]
   [:meta {:http-equiv "X-UA-Compatible"
           :content "IE=edge,chrome=1"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1.0, user-scalable=no"}]
   [:link {:rel "apple-touch-icon", :sizes "180x180", :href "/apple-touch-icon.png"}]
   [:link {:rel "icon", :type "image/png", :href "/favicon-32x32.png", :sizes "32x32"}]
   [:link {:rel "icon", :type "image/png", :href "/favicon-16x16.png", :sizes "16x16"}]
   [:link {:rel "manifest", :href "/manifest.json"}]
   [:link {:rel "mask-icon", :href "/safari-pinned-tab.svg", :color "#5bbad5"}]
   [:meta {:name "theme-color", :content "#ffffff"}]
   (stylesheet-link-tag "/css/normalize.css")
   (stylesheet-link-tag "/css/app.css")])

(defn- head [& content]
  (into [:head] (concat common-head content)))

(defn- body [global-meta attrs content]
  [:body (merge {:itemscope true
                 :itemtype "http://schema.org/WebPage"} attrs)
   [:div.top-bar
    [:div.top-bar-left
     [:top-bar-title {:itemprop "image"}
      [:a#logo {:href "/"}
       (image-tag "/img/200ok.svg")]]]
    [:div.top-bar-right
     [:ul.menu
      [:li [:a {:href "/blog.html"} "Blog"]]
      [:li [:a {:href "/projects.html"} "Projects"]]
      [:li [:a {:href "/team.html"} "Team"]]
      [:li [:a {:href "/atom.xml"
                :id "atom-feed"}
            [:span "&nbsp;"] "Feed"]]]]]
   content
   footer
   (scripts global-meta)])

;; public

(defn bare
  "The minimum layout."
  [global-meta content]
  (html5
   {:lang "en"}
   (head
    [:title (:site-title global-meta)]
    (rss-meta-tag global-meta)
    (atom-meta-tag global-meta))
   content
   footer
   (scripts global-meta)))

(defn main
  "The 'main' layout, which is used for every page."
  [global-meta content & {:keys [is-error?]}]
  (html5
   {:lang "en"}
   (john/mccarthy) ;; the blog doesn't have john
   (head
    [:title (:site-title global-meta)]
    (rss-meta-tag global-meta)
    (atom-meta-tag global-meta)
    (if is-error?
      [:meta {:name "robots" :content "noindex"}]))
   (body global-meta {} content)))

(defn blog
  "The layout which is used for the blog, because... it's a blog."
  [global-meta post-meta content]
  (html5
   {:lang "en"}
   (head
    [:title (seo/title global-meta post-meta)]
    (rss-meta-tag global-meta)
    (atom-meta-tag global-meta)
    (seo/meta-description-tag post-meta)
    (for [og-meta (seo/opengraph-meta global-meta post-meta)]
      og-meta)
    (for [tw-meta (seo/twitter-meta global-meta post-meta)]
      tw-meta)
    [:link {:rel "canonical"
            :href (helper/fully-qualifying-url (:permalink post-meta))}]
    ;; extra styles for syntax highlighting
    (stylesheet-link-tag "/css/styles/solarized-light.css"))
   (body global-meta
         {:id "blog"
          :itemtype "http://schema.org/Blog"}
         content)))
